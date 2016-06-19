package es.ucm.fdi.tp.practica6.net;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.control.commands.Command;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica6.net.ControlGUI.QuitButtonListener;
import es.ucm.fdi.tp.practica6.net.response.ChangeTurnResponse;
import es.ucm.fdi.tp.practica6.net.response.ErrorResponse;
import es.ucm.fdi.tp.practica6.net.response.GameOverResponse;
import es.ucm.fdi.tp.practica6.net.response.GameStartResponse;
import es.ucm.fdi.tp.practica6.net.response.MoveEndResponse;
import es.ucm.fdi.tp.practica6.net.response.MoveStartResponse;
import es.ucm.fdi.tp.practica6.net.response.Response;

/**
 * Establece un servidor con un juego al que se conectarán los clientes
 */
public class GameServer extends Controller implements GameObserver {

	private int port;
	private int numPlayersNeeded;
	private int connectedClients;
	private Game game;
	private GameFactory factory;
	private List<Piece> pieces;
	private List<Connection> clients;
	private ControlGUI view;
	
	volatile private ServerSocket server;
	volatile private boolean stopped;
	volatile private boolean gameOver;
	
	/**
	 * Establece un nuevo GameServer
	 * @param g Juego
	 * @param pieces Lista de piezas del juego
	 * @param p Puerto que usará el servidor
 	 * @param f Factoria del juego
	 */
	public GameServer(Game g, List<Piece> pieces, int p, GameFactory f) {
		super(g, pieces);
		game = g;
		factory = f;
		this.pieces = pieces;
		g.addObserver(this);
		port = p;
		numPlayersNeeded = pieces.size();
		clients = new ArrayList<Connection>();
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() { 
					view = new ControlGUI(new QuitButtonListener() {
						public void quitButtonPushed() {
							stopped = true;
							stop();
							try {
								server.close();
								view.close();
							} catch (IOException e) {
							}
						}
					});
				}	
			});
		} catch (InterruptedException | InvocationTargetException e) {
				
		}
		
	}
	
	/**
	 * Muestra un mensaje por la ventana del servidor
	 * @param msg mensaje a mostrar.
	 */
	private void log(String msg) {
		view.log(msg);
	}
	
	/**
	 * Sobrescribe el método makeMove() capturando excepciones 
	 */
	public synchronized void makeMove(Player p) {
		try {
			super.makeMove(p) ;		
		} catch(GameError e) {}
	}
	
	/**
	 * Sobrescribe el método stop() capturando excepciones 
	 */
	public synchronized void stop() {
		try {
			super.stop() ;			
		} catch(GameError e) {}
	}
	
	/**
	 * Sobrescribe el método restart() capturando excepciones 
	 */
	public synchronized void restart() {
		try {
			super.restart() ;		
		} catch(GameError e) {}
	}
	
	/**
	 * Inicia un nuevo servidor y trata de conectar con los clientes
	 */
	public void start() {
		try {
			server = new ServerSocket(port);
			stopped = false;
			gameOver = false;
		} catch (IOException e) {}
		
			while (!stopped) {
				try {
					if (numPlayersNeeded != connectedClients) {
						log("Waiting for " + (numPlayersNeeded - connectedClients) + " more players...");
					}
					Socket s = server.accept();
					log("Trying to connect client...");
					handleRequest(s);
				} catch (IOException e) {	
					if (!stopped) {
						log("Error while waiting for a connection: " + e.getMessage());
					}
				}
			}
			
	}
	
	/**
	 * Trata de establecer conexion con un cliente y mandar la factoria y pieza correspondiente.
	 * Si se realiza con exito se crea un listener para recibir los comandos del cliente y si no
	 * se cierra la conexión
	 * @param s
	 */
	public void handleRequest(Socket s) {
		try {
			Connection c = new Connection(s);
			
			Object clientRequest = c.getObject();
			
			if ( !(clientRequest instanceof String) && !( ((String) clientRequest).equals("Connect"))) {
				c.sendObject(new GameError("Invalidad Request: Operation must be Connect"));
				c.close();
				
			} else {
				if (numPlayersNeeded == connectedClients){
					c.sendObject(new GameError("Server is already full"));
					log("Server is already full. Cannot connect");
					c.close();
					return;
				}
				connectedClients++;
				clients.add(c);
				log("Connected Succesfully");
				c.sendObject("Connection completed");
				c.sendObject(factory);
				c.sendObject(pieces.get(connectedClients - 1));
				
				if (numPlayersNeeded == connectedClients){
					if (game.getState().equals(State.Starting)) {
						log("The game has started...");
						game.start(pieces);
					} else {
						//Ya se ha jugado una partida
						log("The game has been restarted...");
						game.restart();						
					}
					
				}
				// Si estamos buscando de nuevo jugadores (ya acabó una partida) debemos poner gameOver
				// a false para poder conectar a estos nuevos jugadores.
				gameOver = false;
				startClientListener(c);
			}		
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	/**
	 * Recibe y ejecuta las órdenes recibidas por los clientes
	 * @param c Connection del cliente
	 */
	void startClientListener(Connection c) {
			new Thread() { 
				public void run() {
					while (!stopped && !gameOver) {
						try {
							Command cmd;
							cmd = (Command) c.getObject();
							
							cmd.execute(GameServer.this);
						} catch (ClassNotFoundException | IOException e) {
							if (!stopped && !gameOver) {
								game.stop();
							}
						}
					}
				}
			}.start();
	}
	
	/**
	 * Envía un Response a todos los clientes
	 * @param p Response a enviar
	 */
	void sendResponseToClients(Response p) {
		try {
			for (Connection c: clients) {
				c.sendObject(p);
			}
		} catch (IOException e) {}
		
	}
	
	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces,
			Piece turn) {
		sendResponseToClients(new GameStartResponse(board, gameDesc, pieces, turn));
		
	}
	
	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		sendResponseToClients(new GameOverResponse(board, state, winner));
		gameOver = true;
		restartGame();
	}
		
	@Override
	public void onMoveStart(Board board, Piece turn) {
		sendResponseToClients(new MoveStartResponse(board, turn));
	}
	
	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		sendResponseToClients(new MoveEndResponse(board, turn, success));
	}
	
	@Override
	public void onChangeTurn(Board board, Piece turn) {
		sendResponseToClients(new ChangeTurnResponse(board, turn));
	}
	
	@Override
	public void onError(String msg) {
		sendResponseToClients(new ErrorResponse(msg));
		 // log(msg); No creo que esto se necesario mostrarlo en el servidor
		
	}
	
	/**
	 * Reinicia el juego. Desconecta a todos los clientes y se prepara para recibir nuevos
	 * @param co
	 */
	private void restartGame () {
		try {
			for (Connection c: clients) {
				c.close();
			}	
			log("All clients disconnected");
			connectedClients = 0;
			log("Waiting for " + (numPlayersNeeded) + " more players...");
			clients.clear();
		} catch (IOException e) {}
	}
}

package es.ucm.fdi.tp.practica6.net;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.control.commands.Command;
import es.ucm.fdi.tp.basecode.bgame.control.commands.PlayCommand;
import es.ucm.fdi.tp.basecode.bgame.control.commands.QuitCommand;
import es.ucm.fdi.tp.basecode.bgame.control.commands.RestartCommand;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica6.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica6.net.response.Response;

/**
 * Crea un cliente que tratará de concetarse con un servidor
 */
public class GameClient extends Controller implements Observable<GameObserver> {
	
	private String host;
	private int port;
	private List<GameObserver> observers;
	private Piece localPiece;
	private GameFactoryExt gameFactory;
	private Connection connectionToServer;
	private boolean gameOver;
	
	/**
	 * Crea un nuevo cliente que trata de conecarse al servidor.
	 * @param host Direccion del dispositivo que aloja el servidor
	 * @param port Puerto que utiliza el servidor
	 * @throws Exception
	 */
	public GameClient(String host, int port) throws Exception {
		super(null, null);
		observers = new ArrayList <GameObserver>();
		this.host = host;
		this.port = port;
		connect();
	}
	
	/**
	 * Comienza el juego. Recibe ordenes del servidor y las ejecuta hasta que
	 * el juego acabe.
	 */
	public void start() {
		gameOver = false;
		while (!gameOver) {
			try {
			Response r = (Response) connectionToServer.getObject();
				for (GameObserver o : observers) {
					r.run(o);
				}
			} catch (ClassNotFoundException | IOException e) {
				
			}
			
		}
	}
	
	/**
	 * @return Devuelve la factoría recibida
	 */
	public GameFactoryExt getGameFactory() {
		return gameFactory;
	}
	
	/**
	 * @return devuelve la pieza recibida.
	 */
	public Piece getPlayerPiece() {
		return localPiece;
	}
	
	/**
	 * Trata de crear una nueva conexión con el servidor y recibir los
	 * objetos necesarios para el juego.
	 * @throws Exception
	 */
	private void connect() throws Exception {
		observers.add(new GameObserver() {

			@Override
			public void onGameStart(Board board, String gameDesc,
					List<Piece> pieces, Piece turn) {}

			@Override
			public void onGameOver(Board board, State state, Piece winner) {
				gameOver = true;
				try {
					connectionToServer.close();
				} catch (IOException e) {}
			}

			@Override
			public void onMoveStart(Board board, Piece turn) {}

			@Override
			public void onMoveEnd(Board board, Piece turn, boolean success) {}

			@Override
			public void onChangeTurn(Board board, Piece turn) {}

			@Override
			public void onError(String msg) {}		
		});
		connectionToServer = new Connection(new Socket(host, port));
		connectionToServer.sendObject("Connect");
	
		Object serverResponse = connectionToServer.getObject();
		if (serverResponse instanceof Exception) { 
			// Si no es una excepcion, el server ha mandado el string "Connection Completed"
			throw (Exception) serverResponse;
		}
		try {
			// Recibimos factoría y pieza del juego
			gameFactory = (GameFactoryExt) connectionToServer.getObject();
			localPiece = (Piece) connectionToServer.getObject();
		} catch (Exception e) {
			throw new GameError("Unknown server response: " + e.getMessage());
		}
	}

	@Override
	public void addObserver(GameObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(GameObserver o) {
		observers.remove(o);		
	}
	
	/**
	 * envía el movimiento que el cliente quiere hacer al servidor para que 
	 * este trate de ejecutarlo
	 */
	public void makeMove(Player p) {
		sendCommandToServer(new PlayCommand(p));
	}
	
	/**
	 * Manda al servidor que el cliente ha salido del juego
	 */
	public void stop() {
		sendCommandToServer (new QuitCommand());
	}
	
	/**
	 * El cliente envia al sevidor la peticion de reiniciar el juego
	 * NOTA: este metodo no se usa puesto que nuestras ventanas de juego,
	 * si estan en opcion multiventana (como es el caso del modo Cliente)
	 * No tienen boton para reiniciar la partida
	 */
	public void restart() {
		sendCommandToServer (new RestartCommand());
	}
	
	/**
	 * Manda al servidor un comando del cliente
	 * @param c
	 */
	public void sendCommandToServer(Command c) {
		try {
			connectionToServer.sendObject(c);
		} catch (IOException e) {
		}
	}
}

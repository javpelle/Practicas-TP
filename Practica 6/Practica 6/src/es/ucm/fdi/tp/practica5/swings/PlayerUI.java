package es.ucm.fdi.tp.practica5.swings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.bgame.control.SwingPlayer;
import es.ucm.fdi.tp.practica5.swings.AutomaticMoves.ListenerAutomaticMoves;
import es.ucm.fdi.tp.practica5.swings.PlayerModes.PlayerModeChangedListener;
import es.ucm.fdi.tp.practica5.swings.QuitAndRestart.RestartAndQuitButtonListener;
import es.ucm.fdi.tp.practica5.Main.PlayerMode;

/**
 * Clase que extiende de JFrame. Es nuestra ventana principal que incorpora los
 * distintos paneles de juego. Implementa ademas GameObserver para ser
 * notificada de los distintos lances del juego.
 */
public class PlayerUI extends JFrame implements GameObserver {

	private static final long serialVersionUID = 1L;

	/**
	 * Mapa donde se asigna a cada pieza un color
	 */
	private Map<Piece, Color> colorPieces;

	/**
	 * A map that associates pieces with PlayerModes (manual, random, etc.).
	 * 
	 * <p>
	 * Map que asocia fichas con Modos de Juego (manual, random, etc.).
	 */
	private Map<Piece, PlayerMode> piecesModes;

	/**
	 * Lista de piezas
	 */
	private List<Piece> pieces;

	/**
	 * Indica la pieza a que pertenece la ventana. Si es null el juego se está
	 * desarrolando en ventana unica
	 */
	private Piece viewPiece;

	/**
	 * Tablero de juego
	 */
	private SwingBoard izda;

	/**
	 * Panel de configuracion
	 */
	private PanelConfiguration dcha;

	private Board board;
	private Piece turn;
	private Controller c;
	private Player randomPlayer;
	private Player aiPlayer;
	private SwingPlayer manual;
	private int miliSecIntelligent;
	private volatile boolean isThinking;
	private int lightRow;
	private int lightCol;

	/**
	 * 
	 * @param game
	 *            String del juego que incluye el nombre de la pieza si es
	 *            Multiview
	 * @param viewPiece
	 *            Pieza a la que corresponde la pantalla. Null si no es
	 *            Multiview
	 * @param c
	 *            Controlador
	 * @param g
	 *            Lista de GameObservers
	 * @param randomPlayer
	 *            Jugador aleatorio
	 * @param aiPlayer
	 *            Jugador inteligente (no se usa)
	 */
	public PlayerUI(String game, Piece viewPiece, Controller c,
			Observable<GameObserver> g, Player randomPlayer, Player aiPlayer,
			SwingPlayer manual) {
		super("Board Games: " + game);
		// Anadimos la ventana como observadora
		g.addObserver(this);
		this.randomPlayer = randomPlayer;
		this.aiPlayer = aiPlayer;
		this.manual = manual;
		this.viewPiece = viewPiece;
		this.c = c;
		piecesModes = new HashMap<Piece, PlayerMode>();
		// Inicializamos a -1 la celda que tendra un highLight, esta
		// servirça al usuario para saber donde hizo clic en los juegos
		// de movimiento de origen-destino
		lightRow = -1;
		lightCol = -1;
		miliSecIntelligent = 5000;

		// Guardamos el mapa que recoge de cada pieza su modo de juego
		// Este mapa solo debe modificarse en SwingCtrl
		colorPieces = new HashMap<Piece, Color>();
		// Establecemos tamaño y Layout para la ventana
		setSize(new Dimension(1150, 700));
		setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Inicializa los componentes de la ventana cada vez que comienza una nueva
	 * partida
	 */
	private void printBoard() {
		if (izda != null) {
			// Si uno de los paneles es distinto de null quiere decir
			// que ambos ya existen (el juego ha sido reiniciado),
			// por tanto los borramos para volver a ponerlos
			remove(izda);
			remove(dcha);
		}
		izda = new SwingBoard(board, colorPieces,
				new PieceButton.SelectedLabel() {

					@Override
					public void selectedLabel(int row, int col) {
						if ((viewPiece == null || viewPiece.equals(turn))
								&& !isThinking) {
							swingDetectMoves(row, col);
						}
					}

					@Override
					public void desSelectLabel() {
						if (lightRow != -1
								&& (viewPiece == null || viewPiece.equals(turn))) {
							manual.setSecondClickFalse();
							izda.unSetHightlight(lightRow, lightCol);
						}
						dcha.enableButton();
					}
				});

		dcha = new PanelConfiguration(pieces, viewPiece, board, colorPieces,
				piecesModes, new PieceColors.ColorChangedListener() {
			@Override
			public void colorChanged(Color color, Piece p) {
				colorPieces.remove(p);
				colorPieces.put(p, color);
				updateLeftPanel();
				updatePlayerInfo();
			}
		}, new RestartAndQuitButtonListener() {

			@Override
			public void restartButtonPushed() {
				c.restart();
			}

			@Override
			public void quitButtonPushed() {
				JFrame quitConfirmation = new JFrame();
				Object[] options = { "Yes", "No" };
				int n = JOptionPane.showOptionDialog(quitConfirmation,
						"Are you sure you want to quit the game?", "",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options,
						options[1]);
				if (n == 0) {
					// If chosen option is 'Yes' we stop the game and
					// finished it.
					c.stop();
					closeWindow();
				}
			}

		}, new PlayerModeChangedListener() {

			@Override
			public void playerModeChanged(Piece p, String mode) {
				if (mode.equals("Manual")) {
					piecesModes.remove(p);
					piecesModes.put(p, PlayerMode.MANUAL);
				} else if (mode.equals("Intelligent")) {
					piecesModes.remove(p);
					piecesModes.put(p, PlayerMode.AI);
				} else {
					piecesModes.remove(p);
					piecesModes.put(p, PlayerMode.RANDOM);
				}
				updatePlayerInfo();
			}

		}, new ListenerAutomaticMoves() {

			public void randomMove() {
				makeMove(randomPlayer);
			}

			@Override
			public void intelligentMove() {
				makeMove(aiPlayer);
			}

		});
		add(izda, BorderLayout.CENTER);
		add(dcha, BorderLayout.EAST);
		setVisible(true);
	}

	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces,
			Piece turn) {
		this.board = board;
		this.pieces = pieces;
		this.turn = turn;
		if (izda == null) {
			// Si el panel izda es null, quiere decir que es la primera vez
			// que inicializamos el juego. Si esto ocurre, todos los jugadores
			// iniciales son manuales (por requisito).
			for (int i = 0; i < pieces.size(); i++) {
				colorPieces.put(pieces.get(i), new Color(i * 6666666 + 666));
			}
			for (Piece p : pieces) {
				piecesModes.put(p, PlayerMode.MANUAL);
			}
		} else {
			// Reseteamos el player manual. Esto es necesario para los juegos
			// que
			// al principio son de un clic y despues de dos clics (en este caso
			// attt)
			// para el resto de juegos este metodo es vacio.
			manual.resetPlayer();
		}
		// Establecemos unos colores predeterminados para las piezas

		printBoard();
		dcha.addTextToStatusInfo("Starting '" + gameDesc + "'" + "\n");
		if (viewPiece == null) {
			dcha.enableButton();
			dcha.addTextToStatusInfo("Turn for " + turn);
			dcha.addTextToStatusInfo("Click on a cell");
		} else if (viewPiece.equals(turn)) {
			dcha.enableButton();
			dcha.addTextToStatusInfo("Turn for you (" + turn + ")");
			dcha.addTextToStatusInfo("Click on a cell");
		} else {
			dcha.disableButton();
			dcha.addTextToStatusInfo("Turn for " + turn);
		}
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		this.board = board;
		updateLeftPanel();

		dcha.addTextToStatusInfo("Game Over!!");
		dcha.addTextToStatusInfo("Game Status: " + state);
		if (state == State.Won) {
			dcha.addTextToStatusInfo("Winner: " + winner);
		}
		updatePlayerInfo();
		// Restablecemos los botones para quitar y volver a empezar
		// el juego, ademas del que genera movimientos aleatorios
		// (este ultimo mostrara un mensaje de error al ser pulsado),
		// por si el ultimo jugador fue Random (si esto es así estarían
		// deshabilitados y no podria reiniciarse el juego en modo ventana
		// unica)
		dcha.enableButton();
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		isThinking = false;
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		this.turn = turn;
		this.board = board;
		updateLeftPanel();
		updatePlayerInfo();
		
		if (viewPiece == null) {
			dcha.enableButton();
			dcha.addTextToStatusInfo("Turn for " + turn);
			nextMove();
		} else if (viewPiece.equals(turn)) {
			dcha.enableButton();
			dcha.addTextToStatusInfo("Turn for you (" + turn + ")");
			nextMove();
		} else {
			dcha.disableButton();
			dcha.addTextToStatusInfo("Turn for " + turn);
		}
	}

	@Override
	public void onError(String msg) {
		if (viewPiece == null || viewPiece.equals(turn)) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, msg);
		}
	}

	/**
	 * Comprueba si el siguiente turno no es manual y si es asi, juega de forma
	 * automatica.
	 */
	private void nextMove() {
		if (!piecesModes.get(turn).equals(PlayerMode.MANUAL)) {
			dcha.disableButton();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					if (piecesModes.get(turn).equals(PlayerMode.RANDOM)) {
						makeMove(randomPlayer);
					} else {
						makeMove(aiPlayer);
					}

				}
			});
		} else {
			dcha.addTextToStatusInfo("Click on a cell");
		}
	}

	/**
	 * Comprueba si el movimiento recibido es de origen o destino en el caso de
	 * juegos de movimiento origen-destino o si es movimiento unico y lo ejecuta
	 * 
	 * @param row
	 *            fila seleccionada
	 * @param col
	 *            columna seleccionada
	 */
	public void swingDetectMoves(int row, int col) {
		int numPieces;
		try {
			numPieces = board.getPieceCount(turn);
		} catch (Exception e) {
			numPieces = 0;
		}
		if (manual.generateMoveFromSwing(row, col, turn, numPieces)) {
			if (manual.isTwoClicks()) {
				unSetHighLight(lightRow, lightCol);
				dcha.enableButton();
			}
			c.makeMove(manual);

		} else {
			// Marcamos la casilla de origen seleccionada
			setHighLight(row, col);
			lightRow = row;
			lightCol = col;
			dcha.addTextToStatusInfo("You have selected (" + row + "," + col
					+ ") as oring cell");
			dcha.addTextToStatusInfo("Click on a destination cell");
			dcha.disableButton();
		}
	}

	/**
	 * Marca una casilla de origen seleccionada
	 * 
	 * @param row
	 *            fila
	 * @param col
	 *            columna
	 */
	private void setHighLight(int row, int col) {
		izda.setHightlight(row, col);
	}

	/**
	 * Desmarca una casilla de origen
	 * 
	 * @param row
	 *            fila
	 * @param col
	 *            columna
	 */
	private void unSetHighLight(int row, int col) {
		izda.unSetHightlight(row, col);
		lightRow = -1;
		lightCol = -1;
	}

	/**
	 * Cierra la ventana.
	 */
	private void closeWindow() {
		WindowEvent close = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(close);
	}

	/**
	 * Se engarga de llamar al controlador para ejectuar un movimiento
	 */
	private void makeMove(Player p) {
		dcha.disableButton();
		isThinking = true;
		final Piece thinkingTurn = turn;

		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					c.makeMove(p);
				} catch (GameError e) {
					muestraTimeout();
				}
				isThinking = false;
			}
		});
		t.start();

		new Thread(new Runnable() {
			public void run() {
				try {
					t.join(miliSecIntelligent);
					if (isThinking) {
						// Todavia sigue pensando
						t.interrupt();
						piecesModes.put(thinkingTurn, PlayerMode.MANUAL);
						updatePlayerInfo();
					}
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}
	
	private void muestraTimeout() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				dcha.addTextToStatusInfo("No se ha podido generar un movimiento inteligente en "
						+ (miliSecIntelligent / 1000) + " segundos.");
				dcha.addTextToStatusInfo("Modo de juego cambiado a manual.");
				dcha.enableButton();
			}
		});
	}
	
	private void updatePlayerInfo() {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					dcha.updatePlayerInfo(board);
				}
			});
		} catch (Exception e) {

		}
	}
	
	private void updateLeftPanel() {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					izda.update(board, colorPieces);
				}
			});
		} catch (Exception e) {

		}
	}
}
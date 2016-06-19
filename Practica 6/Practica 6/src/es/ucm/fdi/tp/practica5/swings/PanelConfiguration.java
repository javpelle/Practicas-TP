package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.swings.AutomaticMoves.ListenerAutomaticMoves;
import es.ucm.fdi.tp.practica5.swings.PieceColors.ColorChangedListener;
import es.ucm.fdi.tp.practica5.swings.PlayerModes.PlayerModeChangedListener;
import es.ucm.fdi.tp.practica5.swings.QuitAndRestart.RestartAndQuitButtonListener;
import es.ucm.fdi.tp.practica5.Main.PlayerMode;

/**
 * Clase que contine todos los JPanel de la configuracion del juego
 */
public class PanelConfiguration extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Status status;
	private PlayerInfo playerInfo;
	private PieceColors pieceColors;
	private PlayerModes playerModes;
	private AutomaticMoves automaticMoves;
	private QuitAndRestart quitAndRestart;
	
	/**
	 * Contructora de la clase
	 * @param pieces Lista de piezas
	 * @param viewPiece Pieza del multiview
	 * @param board Tablero
	 * @param colorPieces Mapa que asocia a cada pieza un color
	 * @param piecesModes Mapa que asocia a cada pieza un modo de juego
	 * @param listenerColor Listener del boton de cambio de color deuna pieza
	 * @param listenerQuitAndRestart Listener de los botones quit y restart
	 * @param listenerPlayerMode Listener del boton de cambio de modo de juego
	 * @param listenerRandomMove Listener del boton de generacion de movimiento Random
	 */
	public PanelConfiguration(List<Piece> pieces, Piece viewPiece, Board board,
			Map<Piece, Color> colorPieces, Map<Piece, PlayerMode> piecesModes,
			ColorChangedListener listenerColor, RestartAndQuitButtonListener listenerQuitAndRestart,
			PlayerModeChangedListener listenerPlayerMode, ListenerAutomaticMoves listenerRandomMove) {
		status = new Status();
        playerInfo = new PlayerInfo(pieces, viewPiece, board, colorPieces, piecesModes);
        pieceColors = new PieceColors(pieces, listenerColor);
        playerModes = new PlayerModes(pieces, viewPiece,listenerPlayerMode);
        automaticMoves = new AutomaticMoves(listenerRandomMove);
        quitAndRestart = new QuitAndRestart(viewPiece != null, listenerQuitAndRestart);
        initComponents();
	}

	/**
	 * Inicializa los componentes
	 */
	private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(status);
        add(playerInfo);
        add(pieceColors);
        add(playerModes);
        add(automaticMoves);
        add(quitAndRestart);
	}
	
	/**
	 * Añade un mensaje al panel StatusInfo
	 * @param message
	 */
	public void addTextToStatusInfo (String message) {
		status.addText(message);
	}
	
	/**
	 * Deshabilita los botones Random, Quit y Restart
	 */
	public void disableButton() {
		automaticMoves.disableButton();
		quitAndRestart.disableButtons();
	}
	
	/**
	 * Habilita los botones Random, Quit y Restart
	 */
	public void enableButton() {
		automaticMoves.enableButton();
		quitAndRestart.enableButtons();
	}
	
	/**
	 * Actualiza la tabla de informacion de los jugadores
	 */
	public void updatePlayerInfo(Board board){
		playerInfo.updateTable(board);	
	}
}


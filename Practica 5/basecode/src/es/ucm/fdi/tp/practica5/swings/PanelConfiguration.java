package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PanelConfiguration extends JPanel {
	private Status status;
	private PlayerInfo playerInfo;
	private PieceColors pieceColors;
	private PlayerModes playerModes;
	private AutomaticMoves automaticMoves;
	private QuitAndRestart quitAndRestart;
	
	public PanelConfiguration(List<Piece> pieces, Piece viewPiece, Board board, Color[] colors) {
		super();
		status = new Status();
        playerInfo = new PlayerInfo(pieces, viewPiece, board);
        pieceColors = new PieceColors(pieces, colors);
        playerModes = new PlayerModes(pieces);
        automaticMoves = new AutomaticMoves();
        quitAndRestart = new QuitAndRestart(false);
        initComponents();
	}

	private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(status);
        add(playerInfo);
        add(pieceColors);
        add(playerModes);
        add(automaticMoves);
        add(quitAndRestart);
	}
}
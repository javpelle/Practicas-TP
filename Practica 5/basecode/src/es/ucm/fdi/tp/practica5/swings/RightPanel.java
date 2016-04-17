package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class RightPanel extends JPanel {
	private Status status;
	private PlayerInfo playerInfo;
	private PieceColors pieceColors;
	private PlayerModes playerModes;
	private AutomaticMoves automaticMoves;
	private QuitAndRestart quitAndRestart;
	
	public RightPanel(List<Piece> pieces, Piece viewPiece, Board board, Color[] colors) {
		super();
		status = new Status();
        playerInfo = new PlayerInfo(pieces, viewPiece, board);
        pieceColors = new PieceColors(pieces);
        playerModes = new PlayerModes();
        automaticMoves = new AutomaticMoves();
        quitAndRestart = new QuitAndRestart(false);
        
        setLayout(new GridLayout(0,1));
        add(status);
        add(playerInfo);
        add(pieceColors);
        add(playerModes);
        add(automaticMoves);
        add(quitAndRestart);
	}

}

package es.ucm.fdi.tp.practica5.swings;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class RightPanel extends JPanel {
	private Status status;
	private PlayerInfo playerInfo;
	private PieceColors pieceColors;
	private PlayerModes playerModes;
	private AutomaticMoves automaticMoves;
	private QuitAndRestart quitAndRestart;
	
	public RightPanel() {
		super();
		status = new Status();
        playerInfo = new PlayerInfo(0);
        pieceColors = new PieceColors();
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

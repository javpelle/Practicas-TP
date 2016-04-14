package es.ucm.fdi.tp.practica5.swings;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class RightPanel extends JPanel {
	private Status status;
	private PlayerInfo playerInfo;
	private PieceColors pieceColors;
	
	public RightPanel() {
		super();
		this.status = new Status();
        this.playerInfo = new PlayerInfo(0);
        this.pieceColors = new PieceColors();
        setLayout(new GridLayout(0,1));
        add(status);
        add(playerInfo);
        add(pieceColors);
	}

}

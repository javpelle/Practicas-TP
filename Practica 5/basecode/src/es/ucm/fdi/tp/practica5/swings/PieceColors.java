package es.ucm.fdi.tp.practica5.swings;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PieceColors extends JPanel {
	private JButton chooseColor;
	private JComboBox playersList;
	
	public PieceColors () {
		super();
		setBorder(new TitledBorder("Piece Colors"));
		playersList = new JComboBox();
		chooseColor = new JButton("Choose Color");
		add(playersList);
		add(chooseColor);
	}
}

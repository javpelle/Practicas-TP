package es.ucm.fdi.tp.practica5.swings;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PieceColors extends JPanel {
	private JButton chooseColor;
	
	public PieceColors () {
		super();
		chooseColor = new JButton("Choose Color");
		add(chooseColor);
	}
}

package es.ucm.fdi.tp.practica5.swings;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class AutomaticMoves extends JPanel {
	
	private JButton random;
	private JButton intelligent;
	
	public AutomaticMoves() {
		super();
		setBorder(new TitledBorder("Automatic Moves"));
		random = new JButton("Random");
		intelligent = new JButton("intelligent");
		intelligent.setEnabled(false);
		add(random);
		add(intelligent);
	}
}

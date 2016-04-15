package es.ucm.fdi.tp.practica5.swings;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PlayerModes extends JPanel {
	
	private JButton set;
	
	public PlayerModes () {
		super();
		setBorder(new TitledBorder("Player Modes"));
		set = new JButton ("set");
		add(set);
	}
}

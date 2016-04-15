package es.ucm.fdi.tp.practica5.swings;

import javax.swing.JButton;
import javax.swing.JPanel;

public class QuitAndRestart extends JPanel {
	private JButton quit;
	private JButton restart;
	
	public QuitAndRestart(boolean multiwindow) {
		super();
		quit = new JButton("Quit");
		add(quit);
		if (!multiwindow) {
			restart = new JButton("Restart");
			add(restart);
		}
	}
}

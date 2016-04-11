package es.ucm.fdi.tp.practica5.swings;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Status extends JPanel {
	
	JScrollPane p;
	public Status() {
		super();
		this.p = new JScrollPane();
		
		add(p);
	}
}

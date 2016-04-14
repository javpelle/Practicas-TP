package es.ucm.fdi.tp.practica5.swings;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Status extends JPanel {
	
	private JTextArea txt;
	private JScrollPane p;
	
	public Status() {
		super(false);
		this.txt = new JTextArea("Hola me llamo Javier Pellejero Ortega y no apruebo ni una xd ajaj",4,4);
		this.p = new JScrollPane(txt);
		this.txt.setLineWrap(true); 
		this.txt.setWrapStyleWord(true);
        add(p);		
	}
}

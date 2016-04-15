package es.ucm.fdi.tp.practica5.swings;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Status extends JPanel {
	
	private JTextArea txt;
	private JScrollPane p;
	
	public Status() {
		super(false);
		setBorder(new TitledBorder("Status Messages"));
		txt = new JTextArea("                   ");
		txt.setEditable(false);
		p = new JScrollPane(txt);
		//this.txt.setLineWrap(true); 
		//this.txt.setWrapStyleWord(true);
        add(p);		
	}
}

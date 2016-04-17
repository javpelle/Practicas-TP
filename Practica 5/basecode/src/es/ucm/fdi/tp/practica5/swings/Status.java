package es.ucm.fdi.tp.practica5.swings;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Status extends JPanel {
	
	private JTextArea txt;
	
	public Status() {
		super(false);
		setBorder(new TitledBorder("Status Messages"));
		txt = new JTextArea("                   \n" +
				"              ");
		txt.setEditable(false);
		
		//this.txt.setLineWrap(true); 
		//this.txt.setWrapStyleWord(true);
        add(new JScrollPane(txt));		
	}
}

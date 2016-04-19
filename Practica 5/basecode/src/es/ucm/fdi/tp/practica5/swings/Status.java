package es.ucm.fdi.tp.practica5.swings;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Status extends JPanel {
	
	private JTextArea txt;
	private Dimension preferred;
	
	public Status() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Status Messages"));
		txt = new JTextArea();
		txt.setEditable(false);
		
		//this.txt.setLineWrap(true); 
		//this.txt.setWrapStyleWord(true);
        add(new JScrollPane(txt));
	}
	
	public Dimension getPreferredSize() {
		preferred = super.getPreferredSize();
		preferred.setSize(500, 600);
		return preferred;
	}
	
	public void addText (String message){
		txt.append(message + '\n');
	}
}

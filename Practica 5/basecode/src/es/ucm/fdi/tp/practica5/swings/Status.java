package es.ucm.fdi.tp.practica5.swings;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * JPanel que contiene el JTextArea de informacion del juego
 */
public class Status extends JPanel {
	
	private JTextArea txt;
	private Dimension preferred;
	
	/**
	 * Constructora de la calse
	 */
	public Status() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Status Messages"));
		txt = new JTextArea();
		txt.setEditable(false);
		
        add(new JScrollPane(txt));
	}
	
	/**
	 * Establece las dimensiones preferidas del JPanel
	 */
	public Dimension getPreferredSize() {
		preferred = super.getPreferredSize();
		preferred.setSize(500, 600);
		return preferred;
	}
	
	/**
	 * Añade una linea de texto al JTextArea
	 * @param message Mensaje a añadir
	 */
	public void addText (String message){
		txt.append("* " + message + '\n');
	}
}

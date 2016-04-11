package es.ucm.fdi.tp.practica5.swings;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class PlayerInfo extends JPanel {
	private JTable info;
	private JScrollPane p;
	
	public PlayerInfo(int players) {
		super();
		info = new JTable(3,4);
		this.p = new JScrollPane(info);
        add(p);	
	}
}

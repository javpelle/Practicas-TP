package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PieceColors extends JPanel {
	private JButton chooseColor;
	private JComboBox <Piece>playersList;   //JComboBox es un generico
	
	public PieceColors (Controller c) {
		super();
		setBorder(new TitledBorder("Piece Colors"));
		playersList = new JComboBox <Piece>();
		chooseColor = new JButton("ChooseColor");
		add(playersList);
		
		
		
		add(chooseColor);
		chooseColor.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				Color c = JColorChooser.showDialog(getParent(), "Elige el color correspondiente", Color.BLACK);		
				
				}
			}); 
		
	} 	
}

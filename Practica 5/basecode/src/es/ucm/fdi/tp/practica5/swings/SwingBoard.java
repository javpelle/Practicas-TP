package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;

public class SwingBoard extends JPanel {
	private PieceButton[][] board;
	
	public SwingBoard(int dim, Controller c) {
		super();
		setLayout(new GridLayout(dim,dim));
		board = new PieceButton[dim][dim];
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				
			}
		}
	}
	
	
}

package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class SwingBoard extends JPanel {
	private PieceButton[][] board;
	
	public SwingBoard(int f, int c) {
		super();
		setLayout(new GridLayout(f,c));
		board = new PieceButton[f][c];
		
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				board[i][j] = new PieceButton(new Color ((int) (Math.random() * 200000000)), null);
				add(board[i][j]);
			}
		}
	}
	
	
}

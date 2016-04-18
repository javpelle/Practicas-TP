package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;


public class SwingBoard extends JPanel {
	private PieceButton[][] backBoard;
	
	public SwingBoard(int dim,Board board, List<Piece> pieces, Color[] colors) {
		super();
		setLayout(new GridLayout(dim,dim));
		backBoard = new PieceButton[dim][dim];
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				backBoard[i][j] = new PieceButton(i,j);
			}
		}
		update (dim, board, pieces, colors);
	}
	
	public void update(int dim,Board board, List<Piece> pieces, Color[] colors) {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (board != null) {
					Piece p = board.getPosition(i, j);
					if (p != null && !p.getId().equals("*")) {
						backBoard[i][j].setPieceButton(colors[pieces.indexOf(p)], board.getPosition(i, j));
					} else if (p != null) {
						backBoard[i][j].setPieceButton(Color.GREEN, board.getPosition(i, j));
					} else {
						backBoard[i][j].setPieceButton(Color.GRAY, null);
					}
					add(backBoard[i][j]);
				}	
			} 
		}
	}
}

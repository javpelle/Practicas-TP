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
	
	public SwingBoard(int dim,Board board, List<Piece> pieces ) {
		super();
		setLayout(new GridLayout(dim,dim));
		backBoard = new PieceButton[dim][dim];
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (board != null) {
					Piece p = board.getPosition(i, j);
					if (p != null) {
						backBoard[i][j] = new PieceButton(new Color(pieces.indexOf(p)*1000), board.getPosition(i, j));
						add(backBoard[i][j]);
						}
					else {
						backBoard[i][j] = new PieceButton(Color.GRAY, null);
						add(backBoard[i][j]);
					}
				}	
			}
		}
	}
	
	
}

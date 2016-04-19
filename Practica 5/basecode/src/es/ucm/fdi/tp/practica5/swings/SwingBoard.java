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
	private int row;
	private int col;
	private PieceButton[][] backBoard;
	private Piece pieceSelected;
	private Piece turno;
	private PieceButton highlightCell;
	
	public SwingBoard(Board board, List<Piece> pieces, Color[] colors, Piece turn) {
		super();
		row = board.getRows();
		col = board.getCols();
		setLayout(new GridLayout(row, col));
		backBoard = new PieceButton[row][col];
		pieceSelected = null;
		highlightCell = null;
		turno = turn;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				backBoard[i][j] = new PieceButton(i,j, new PieceButton.SelectedLabel() {
					
					@Override
					public void selectedLabel(int row, int col) {
						if ((pieceSelected == null || pieceSelected.equals(turno)) && turn.equals(board.getPosition(row, col))) {
							pieceSelected = board.getPosition(row, col);
							if (highlightCell != null) {
								highlightCell.setBorderColor(Color.black);
							}
							highlightCell = backBoard[row][col];
							highlightCell.setBorderColor(Color.YELLOW);
						} else if (pieceSelected != null && !turn.equals(board.getPosition(row, col))) {
							
						}
					}
				});
			}
		}
		update (board, pieces, colors);
	}
	
	public void update(Board board, List<Piece> pieces, Color[] colors) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
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

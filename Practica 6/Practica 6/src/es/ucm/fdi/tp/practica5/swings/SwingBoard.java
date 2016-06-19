package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import java.util.Map;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.swings.PieceButton.SelectedLabel;

/**
 * JPanel que contine el tablero de juego
 */
public class SwingBoard extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int row;
	private int col;
	private PieceButton[][] backBoard;	
	
	/**
	 * Constructora del JPanel
	 * @param board tablero
	 * @param colorPieces Mapa que asigna a cada pieza un color
	 * @param listenerLabel Listener de las celdas del tablero
	 */ 
	public SwingBoard(Board board, Map<Piece, Color> colorPieces, SelectedLabel listenerLabel) {
		super();
		row = board.getRows();
		col = board.getCols();
		setLayout(new GridLayout(row, col));
		backBoard = new PieceButton[row][col];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				backBoard[i][j] = new PieceButton(i,j, listenerLabel);
			}
		}
		update (board, colorPieces);
	}
	
	/**
	 * Actualiza el tablero
	 * @param board
	 * @param pieceColor
	 */
	public void update(Board board, Map<Piece, Color> pieceColor) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board != null) {
					Piece p = board.getPosition(i, j);
					if (p != null && !p.getId().equals("*")) {
						backBoard[i][j].setPieceButton(pieceColor.get(p));
					} else if (p != null) {
						backBoard[i][j].setPieceButton(Color.ORANGE);
					} else {
						backBoard[i][j].setPieceButton(Color.GRAY);
					}
					add(backBoard[i][j]);
				}	
			} 
		}
		repaint();
	}
	
	/**
	 * Marca una casilla de origen
	 * @param row
	 * @param col
	 */
	public void setHightlight (int row, int col) {
		backBoard[row][col].setBorderColor(Color.YELLOW);
	}
	
	/**
	 * Desmarca una casilla de origen
	 * @param row
	 * @param col
	 */
	public void unSetHightlight (int row, int col) {
		backBoard[row][col].setBorderColor(Color.BLACK);
	}
}

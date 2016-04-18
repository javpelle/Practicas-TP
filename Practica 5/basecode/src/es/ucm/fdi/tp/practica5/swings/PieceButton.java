package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;

import javax.swing.JButton;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PieceButton extends JButton {
	private Piece piece;
	private Color color;
	private int row;
	private int col;
	
	public PieceButton (int col, int row) {
		piece = null;
		color = null;
		this.row = row;
		this.col = col;
	}
	
	public boolean comparePieces(Piece otherPiece) {
		return piece.getId() == otherPiece.getId();
	}
	
	public void setPieceButton (Color color, Piece piece) {
		this.color = color;
		this.piece = piece;
		setBackground(this.color);
	}
}

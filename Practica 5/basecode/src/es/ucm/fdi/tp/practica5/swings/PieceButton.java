package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;

import javax.swing.JButton;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PieceButton extends JButton {
	private Piece piece;
	private Color color;
	
	public PieceButton (Color color, Piece piece) {
		super();
		this.color = color;
		this.piece = piece;
		setBackground(this.color);
	}
	
	public boolean comparePieces(Piece otherPiece) {
		return piece.getId() == otherPiece.getId();
	}
}

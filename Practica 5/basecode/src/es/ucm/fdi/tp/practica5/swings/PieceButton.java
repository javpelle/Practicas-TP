package es.ucm.fdi.tp.practica5.swings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.swings.PieceColors.ColorChangedListener;

public class PieceButton extends JLabel {
	private Piece piece;
	private Color color;
	private int row;
	private int col;
	
	public PieceButton (int row, int col, final SelectedLabel listener) {
		piece = null;
		color = null;
		this.row = row;
		this.col = col;
		setVisible(true);
		setOpaque(true);
		setBorderColor(Color.black);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				listener.selectedLabel(row, col);
				
			}
			});
	}
	
	public interface SelectedLabel {
		public void selectedLabel(int row, int col);
	}
	
	
	public void setPieceButton (Color color, Piece piece) {
		this.color = color;
		this.piece = piece;
		setBackground(this.color);
	}
	
	public void setBorderColor (Color color) {
		setBorder(BorderFactory.createLineBorder(color));
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
}

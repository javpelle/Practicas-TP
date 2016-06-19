package es.ucm.fdi.tp.practica6.swings;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;

import javax.swing.JLabel;

/**
 * Clase que compone una celda del tablero
 */
public class PieceButton extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private Color color;
	/**
	 * Contructora de la clase
	 * @param row fila
	 * @param col columna
	 * @param listener Listener de la celda
	 */
	public PieceButton (final int row, final int col, final SelectedLabel listener) {
		color = null;
		setVisible(true);
		setOpaque(true);
		setBorderColor(Color.black);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					 if(e.getButton() == MouseEvent.BUTTON1) {
						 listener.selectedLabel(row, col);
					 } else if(e.getButton() == MouseEvent.BUTTON3) {
					     listener.desSelectLabel();
					 }
				} catch (Exception f) {}
			}
		});
	}
	
	/**
	 * Interfaz que contine los metodos a llamar cuando son pulsados los botones
	 */
	public interface SelectedLabel {
		public void selectedLabel(int row, int col);
		public void desSelectLabel();
	}
	
	/**
	 * Cambia el color a una celda
	 * @param color
	 * @param piece
	 */
	public void setPieceButton (Color color) {
		this.color = color;
		setBackground(this.color);
	}
	
	/**
	 * Cambia el color del borde de una celda
	 * @param color
	 */
	public void setBorderColor (Color color) {
		setBorder(BorderFactory.createLineBorder(color));
	}
}

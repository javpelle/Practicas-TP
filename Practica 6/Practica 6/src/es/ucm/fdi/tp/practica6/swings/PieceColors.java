package es.ucm.fdi.tp.practica6.swings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * Clase que contiene los componentes necesarios para cambiar
 * el color de una pieza
 */
public class PieceColors extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton chooseColor;
	private JComboBox <Piece>playersList;   // JComboBox es un generico
	
	/**
	 * Interfaz que contine los metodos a llamar cuando son pulsados los botones
	 */
	public interface ColorChangedListener {
		public void colorChanged(Color c, Piece p);
	}
	
	/**
	 * Contructora de la clase
	 * @param pieces lista de piezas
	 * @param listener Listener del boton Choose Color
	 */
	public PieceColors (List<Piece> pieces, ColorChangedListener listener) {
		super();
		setBorder(new TitledBorder("Piece Colors"));
		playersList = new JComboBox <Piece>();
		chooseColor = new JButton("ChooseColor");
		for (int i = 0; i < pieces.size(); i++) {
			playersList.addItem(pieces.get(i));
		}
		add(playersList);
		add(chooseColor);
		listenerPieceColors(listener);	
	}
	
	private void listenerPieceColors(final ColorChangedListener listener) {
		chooseColor.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				Color c = JColorChooser.showDialog(getParent(), "Elige el color correspondiente", Color.BLACK);		
				Piece p = (Piece) playersList.getSelectedItem();
				if (c != null) {	
					listener.colorChanged(c, p);
				}
			}
		});
	}
}

package es.ucm.fdi.tp.practica5.swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * Clase que contine los componentes necesarios para
 * cambiar el modo de juego de una pieza
 */
public class PlayerModes extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton set;
	private JComboBox <Piece> infoPieces;
	private JComboBox <String> playerMode;
	
	/**
	 * Constructora de la clase.
	 * @param pieces Lista de piezas
	 * @param viewPiece Pieza de la multiventana. Null si no es multiventana
	 * @param listener Listener del boton Set
	 */
	public PlayerModes (List<Piece> pieces, Piece viewPiece, PlayerModeChangedListener listener) {
		setBorder(new TitledBorder("Player Modes"));
		set = new JButton ("set");
		infoPieces = new JComboBox<Piece>();
		if (viewPiece == null) {
			// Estamos jugando en una sola ventana
			for (int i = 0; i < pieces.size(); i++) {
				infoPieces.addItem(pieces.get(i));
			}
		} else {
			// Estamos jugando en multiventana
			// El propietario de la ventana solo puede cambiar su metodo de juego
			infoPieces.addItem(viewPiece);
		}
		
		playerMode = new JComboBox<String>();
		playerMode.addItem("Manual");
		playerMode.addItem("Random");
		playerMode.addItem("Intelligent");
		add(infoPieces);
		add(playerMode);
		add(set);
		listenerPlayerModes(listener);
	}
	
	/**
	 * Interfaz que contine los metodos a llamar cuando son pulsados los botones
	 */
	public interface PlayerModeChangedListener {
		public void playerModeChanged(Piece p, String mode);
	}
	
	private void listenerPlayerModes(final PlayerModeChangedListener listener) {
		set.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				listener.playerModeChanged((Piece)infoPieces.getSelectedItem(), (String)playerMode.getSelectedItem());
			}
		});
	}
	
	
}

package es.ucm.fdi.tp.practica5.swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Clase que contiene los botones para generar movimientos automaticos
 */
public class AutomaticMoves extends JPanel {
	
	private JButton random;
	private JButton intelligent;
	public interface ListenerRandom {
		public void randomMove();
	}
	
	/**
	 * Contructora de la clase
	 * @param listener Listener del boton Random
	 */
	public AutomaticMoves(ListenerRandom listener) {
		super();
		setBorder(new TitledBorder("Automatic Moves"));
		random = new JButton("Random");
		intelligent = new JButton("intelligent");
		// Mantenemos este boton desactivado siempre puesto que no lo utilizamos
		intelligent.setEnabled(false);
		add(random);
		add(intelligent);
		randomMoveClicked(listener);
		
		}
	
	private void randomMoveClicked (final ListenerRandom listener) {
		random.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				try {
					listener.randomMove();
				} catch (Exception f){}
			}
		});
	}
	
	/**
	 * Deshabilita el boton Random
	 */
	public void disableButton() {
		random.setEnabled(false);
	}
	
	/**
	 * Habilita el boton Random
	 */
	public void enableButton() {
		random.setEnabled(true);
	}
	
}

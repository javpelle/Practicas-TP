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
	private static final long serialVersionUID = 1L;
	
	private JButton random;
	private JButton intelligent;
	public interface ListenerAutomaticMoves {
		public void randomMove();
		public void intelligentMove();
	}
	
	/**
	 * Contructora de la clase
	 * @param listener Listener del boton Random
	 */
	public AutomaticMoves(ListenerAutomaticMoves listener) {
		setBorder(new TitledBorder("Automatic Moves"));
		random = new JButton("Random");
		intelligent = new JButton("intelligent");
		// Mantenemos este boton desactivado siempre puesto que no lo utilizamos
		add(random);
		add(intelligent);
		randomMoveClicked(listener);
		intelligentMoveClicked(listener);
		
		}
	
	private void randomMoveClicked (final ListenerAutomaticMoves listener) {
		random.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				try {
					listener.randomMove();
				} catch (Exception f){}
			}
		});
	}
	
	private void intelligentMoveClicked (final ListenerAutomaticMoves listener) {
		intelligent.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				try {
					listener.intelligentMove();
				} catch (Exception f){}
			}
		});
	}
	
	/**
	 * Deshabilita el boton Random
	 */
	public void disableButton() {
		random.setEnabled(false);
		intelligent.setEnabled(false);
	}
	
	/**
	 * Habilita el boton Random
	 */
	public void enableButton() {
		random.setEnabled(true);
		intelligent.setEnabled(true);
	}
	
}

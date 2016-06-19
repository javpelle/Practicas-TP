package es.ucm.fdi.tp.practica5.swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * JPanel que contiene los botones Quit y Restart
 */
public class QuitAndRestart extends JPanel {
	
	private JButton quit;
	private JButton restart;
	
	/**
	 * Interfaz que contine los metodos a llamar cuando son pulsados los botones
	 */
	public interface RestartAndQuitButtonListener {
		public void restartButtonPushed();
		public void quitButtonPushed();
	}
	
	/**
	 * Contructora de la clase
	 * @param multiwindow True si es multiwindow. False en otro caso.
	 * @param listener Listener de los botones Quit y Restart
	 */
	public QuitAndRestart(boolean multiwindow, RestartAndQuitButtonListener listener) {
		super();
		quit = new JButton("Quit");
		add(quit);
		if (!multiwindow) {
			restart = new JButton("Restart");
			add(restart);
		}
		if (restart != null) {
			listenerRestartButton(listener);
		}
		
		listenerQuitButton(listener);
	}
	
	private void listenerRestartButton (final RestartAndQuitButtonListener listener) {
		restart.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				listener.restartButtonPushed();
			}
		});
	}
	
	private void listenerQuitButton (final RestartAndQuitButtonListener listener) {
		quit.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				listener.quitButtonPushed();
			}
		});
	}
	
	/**
	 * Desactiva los botones de esta clase
	 */
	public void disableButtons () {
		quit.setEnabled(false);
		if (restart != null ) {
			restart.setEnabled(false);
		}
	}
	
	/**
	 * Activa los botones de esta clase
	 */
	public void enableButtons () {
		quit.setEnabled(true);
		if (restart != null ) {
			restart.setEnabled(true);
		}
	}	
	
}

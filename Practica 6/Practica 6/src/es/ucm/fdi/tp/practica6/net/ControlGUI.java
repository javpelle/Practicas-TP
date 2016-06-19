package es.ucm.fdi.tp.practica6.net;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Construye una ventana del servidor en la que se muestra la información que
 * arroja el servidor y desde la cual se permite parar el mismo.
 */
public class ControlGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextArea infoArea;
	private JButton quitButton;
	
	/**
	 * Construye una nueva ventana para el servidor.
	 * @param listener
	 */
	public ControlGUI (QuitButtonListener listener) {
		super("Game Server");
		setLayout(new BorderLayout());
		setSize(new Dimension(700, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		infoArea = new JTextArea();
		infoArea.setEditable(false);
		infoArea.setPreferredSize(new Dimension(400,400));
		add(new JScrollPane(infoArea), BorderLayout.CENTER);
		
		quitButton = new JButton("Stop Server");	
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				listener.quitButtonPushed();
			}
		});
		
		add(quitButton, BorderLayout.EAST);
		setVisible(true);
		
	}
	
	/**
	 * Muestra un nuevo mensaje en el JTextArea de la ventana
	 * @param msg mensaje a mostrar
	 */
	public void log(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				infoArea.append("* " + msg + '\n');
			}	
		});
	}
	
	/**
	 * Permite ser llamada para comunicar a GameServer que el boton quit ha sido pulsado
	 * y se debe parar y cerrar el server.
	 */
	public interface QuitButtonListener {
		public void quitButtonPushed();
	}
	
	/**
	 * Cierra la ventana del servidor y por tanto finaliza el programa.
	 */
	public void close() {
		WindowEvent close = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(close);
	}
}

package es.ucm.fdi.tp.practica6.swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

/**
 * Panel que implementa un JSpinner para seleccionar el tiempo maximo para ejecutar 
 * un movimiento inteligente
 */
public class TimeIntelligent extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JSpinner jsSec;
	private JLabel label;
	private JButton set;
	
	/**
	 * Contruye un nuevo panel
	 * @param listener
	 */
	public TimeIntelligent(TimeListener listener) {
		setBorder(new TitledBorder("Set Time to Intelligent Moves"));
		jsSec = new JSpinner(new SpinnerNumberModel(5, 1, 30, 1));
		label = new JLabel("Seconds");
		set = new JButton("Set");
		add(label);
		add(jsSec);
		add(set);
		set.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
            	listener.setPressed((int) jsSec.getValue());
            }
        });
	}
	
	/**
	 * Permite ser llamada para comunicar a nuestra ventana de juego que el tiempo ha sido cambiado
	 */
	public interface TimeListener {
		public void setPressed(int sec);
	}
}

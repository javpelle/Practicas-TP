package tp.pr3.ventanas;

import javax.swing.JLabel;
import javax.swing.JFrame;

public class HolaMundoSwing extends JFrame{
	private JLabel texto1;
	
	public HolaMundoSwing(String toString) {
		texto1=new JLabel(toString);
		texto1.setBounds(0,0,0,0);
		add(texto1);
	}
	
	public void cambiar (String toString) {
		remove(texto1);
		texto1=new JLabel(toString);
		texto1.setBounds(0,0,0,0);
		add(texto1);
	}
}

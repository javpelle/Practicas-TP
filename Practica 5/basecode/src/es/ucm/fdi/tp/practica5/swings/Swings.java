package es.ucm.fdi.tp.practica5.swings;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Swings extends JFrame{
	
  
    public Swings(String game) {
    	
        super(game);
        setSize(320, 200);
        setVisible(true);
        add(new Status());
        
      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void setLayout(FlowLayout flowLayout) {
		// TODO Auto-generated method stub
		
	}


}
package es.ucm.fdi.tp.practica5.swings;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;

public class Swing extends JFrame{
		
    public Swing(String game, int dim, Controller c) {
    	
        super("Board Games: " + game);
       
        setSize(new Dimension(1200, 800));  
        
        setLayout(new BorderLayout());
       
        SwingBoard izda = new SwingBoard(dim, c);
        RightPanel dcha = new RightPanel(c);
       
       // dcha.setLayout(new BoxLayout(status, BoxLayout.PAGE_AXIS));
        
        add(izda, BorderLayout.CENTER);
        add(dcha, BorderLayout.EAST);
        setVisible(true);
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void setLayout(FlowLayout flowLayout) {
		// TODO Auto-generated method stub
		
	}


}
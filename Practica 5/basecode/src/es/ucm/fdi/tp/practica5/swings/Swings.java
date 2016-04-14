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

public class Swings extends JFrame{
		
    public Swings(String game) {
    	
        super("Board Games: " + game);
       
        setSize(new Dimension(1200, 800));  
        
        setLayout(new GridLayout(1,0));
       
        LeftPanel izda = new LeftPanel();
        RightPanel dcha = new RightPanel();
       
       // dcha.setLayout(new BoxLayout(status, BoxLayout.PAGE_AXIS));
        
        add(izda);
        add(dcha);
        setVisible(true);
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void setLayout(FlowLayout flowLayout) {
		// TODO Auto-generated method stub
		
	}


}
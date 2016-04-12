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
	
	private Status status;
	private PlayerInfo playerInfo;
	
    public Swings(String game) {
    	
        super(game);
        this.status = new Status();
        this.playerInfo = new PlayerInfo(0);
        setSize(new Dimension(600, 400));  
        setVisible(true);
        setLayout(new BorderLayout());
       
        JPanel izda = new JPanel();
        JPanel dcha = new JPanel();
       
       // dcha.setLayout(new BoxLayout(status, BoxLayout.PAGE_AXIS));
        
        dcha.add(status);
        dcha.add(playerInfo);
        add(izda, BorderLayout.CENTER);
        add(dcha, BorderLayout.EAST);
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void setLayout(FlowLayout flowLayout) {
		// TODO Auto-generated method stub
		
	}


}
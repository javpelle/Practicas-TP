package es.ucm.fdi.tp.practica5.swings;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
        setSize(new Dimension(500, 500));  
        setVisible(true);
        setLayout(new GridLayout(0,2));
        JPanel izda = new JPanel();
        izda.setLayout(new GridLayout(2,0));
       
        izda.add(status);
        izda.add(playerInfo);
        add(new JPanel());
        add(izda);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void setLayout(FlowLayout flowLayout) {
		// TODO Auto-generated method stub
		
	}


}
package es.ucm.fdi.tp.practica5.swings;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class Swing extends JFrame implements GameObserver{
	
		private List<Piece> pieces;
		private Board board;
		private Piece turn;
		
    public Swing(String game, int dim, Controller c, Observable<GameObserver> g) {
    	
        super("Board Games: " + game);
        setSize(new Dimension(1200, 800));
        g.addObserver(this);  
        setLayout(new BorderLayout());
       
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    private void printBoard() {
        SwingBoard izda = new SwingBoard (board.getCols(),board,pieces );
        RightPanel dcha = new RightPanel();
        add(izda, BorderLayout.CENTER);
        add(dcha, BorderLayout.EAST);
        setVisible(true);       
    }
    
	private void setLayout(FlowLayout flowLayout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces,
			Piece turn) {
		this.board = board;
		this.pieces = pieces;
		this.turn = turn;
		printBoard();
		
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	



}
package es.ucm.fdi.tp.practica5.swings;
import java.awt.BorderLayout;
import java.awt.Color;
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

public class Swing extends JFrame implements GameObserver {
	
		private List<Piece> pieces;
		private Color[] colors;
		private Board board;
		private Piece turn;
		private Piece viewPiece;
		
    public Swing(String game, int dim, Piece viewPiece, Controller c, Observable<GameObserver> g) {
    	super("Board Games: " + game);   
    	this.viewPiece = viewPiece;
        setSize(new Dimension(1200, 800));
        g.addObserver(this);
        setLayout(new BorderLayout());
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    private void printBoard() {
        final SwingBoard izda = new SwingBoard (board,pieces, colors, turn);
        PanelConfiguration dcha = new PanelConfiguration(pieces, viewPiece, board, colors, new PieceColors.ColorChangedListener() {
			
			@Override
			public void colorChanged() {
				izda.update(board, pieces, colors);
			}
		});
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
		colors = new Color[pieces.size()];
		for (int i = 0; i < pieces.size(); i++) {
			colors[i]= new Color(pieces.indexOf(pieces.get(i))*20000 + 500);
		}
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
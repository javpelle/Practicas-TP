package es.ucm.fdi.tp.practica5.swings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class Swing extends JFrame implements GameObserver {
	
	private static final long serialVersionUID = 1L;
	// private Map<Piece, Color> colorPieces;

	private List<Piece> pieces;
	private Color[] colors;
	private Board board;
	private Piece turn;
	private Piece viewPiece;
	private SwingBoard izda;
	private PanelConfiguration dcha;
	private Controller c;
	
    public Swing(String game, Piece viewPiece, Controller c, Observable<GameObserver> g) {
    	super("Board Games: " + game);   
    	this.viewPiece = viewPiece;
    	this.c = c;
        setSize(new Dimension(1200, 800));
        g.addObserver(this);
        setLayout(new BorderLayout());      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    private void printBoard() {
    	izda = new SwingBoard (board,pieces, colors, turn);
        dcha = new PanelConfiguration(pieces, viewPiece, board, colors, new PieceColors.ColorChangedListener() {
			
			@Override
			public void colorChanged(Color c, Piece p) {
				colors[pieces.indexOf(p)] = c;
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
			colors[i]= new Color(pieces.indexOf(pieces.get(i))*100000 + 500);
		}
		printBoard();		
		dcha.addTextToStatusInfo("Starting '" + gameDesc + "'" + "\n" + "Turn for " + turn);
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		dcha.addTextToStatusInfo("Game Over!!");
		dcha.addTextToStatusInfo("Game Status: " + state);
		if (state == State.Won) {
			dcha.addTextToStatusInfo("Winner: " + winner);
		}
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		// imposibilitamos los botones quit, random, (intelligent)
		
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		// restablecemos los botones quit, random, (intelligent)
		
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		dcha.addTextToStatusInfo("Turn for " + turn);		
	}

	@Override
	public void onError(String msg) {
		JFrame error = new JFrame();
		JOptionPane.showMessageDialog(error, msg);
	}

	



}
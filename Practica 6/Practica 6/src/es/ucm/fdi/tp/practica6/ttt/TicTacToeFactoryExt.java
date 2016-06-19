package es.ucm.fdi.tp.practica6.ttt;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Factoria de TicTacToe, que extiende de la original, y tiene definido el metodo createSwingView, 
 * y la capacidad de generar SwingPlayers
 * 
 */



import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;
import es.ucm.fdi.tp.basecode.ttt.TicTacToeFactory;
import es.ucm.fdi.tp.practica6.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica6.connectn.ConnectNSwingPlayer;
import es.ucm.fdi.tp.practica6.swings.PlayerUI;

public class TicTacToeFactoryExt extends TicTacToeFactory implements GameFactoryExt {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructora vacia
	 */
	public TicTacToeFactoryExt() {}
	
	/**
	 * Crea una o varias ventanas swing
	 * 
	 */
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					if (viewPiece == null) {    //Generamos una sola ventana
						new PlayerUI("TicTacToe", null, c, g, random, ai, createSwingPlayer());
					} else {
						new PlayerUI("TicTacToe" + " (" + viewPiece.getId() + ")", viewPiece,
								c, g, random, ai, createSwingPlayer());
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {}		
	}
	
    /**
     * Crea un nuevo jugador ConnectN (ya que los movimientos son iguales para TTT) para swing
     */
	public ConnectNSwingPlayer createSwingPlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new ConnectNMove());
		return new ConnectNSwingPlayer(possibleMoves);
	}
}

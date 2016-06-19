package es.ucm.fdi.tp.practica5.ttt;

import java.util.ArrayList;

/**
 * Factoria de TicTacToe, que extiende de la original, y tiene definido el metodo createSwingView, 
 * y la capacidad de generar SwingPlayers
 * 
 */

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;
import es.ucm.fdi.tp.basecode.ttt.TicTacToeFactory;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.bgame.control.SwingCtrl;
import es.ucm.fdi.tp.practica5.connectn.ConnectNSwingPlayer;
import es.ucm.fdi.tp.practica5.swings.Swing;

public class TicTacToeFactoryExt extends TicTacToeFactory implements GameFactoryExt {
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
		if (viewPiece == null) {    //Generamos una sola ventana
			new Swing("TicTacToe", null, (SwingCtrl)c, g, random, ai);
		} else {
			new Swing("TicTacToe" + " (" + viewPiece.getId() + ")", viewPiece,
					(SwingCtrl) c, g, random, ai);
		}
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

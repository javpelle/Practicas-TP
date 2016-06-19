package es.ucm.fdi.tp.practica5.attt;

import java.util.ArrayList;

import es.ucm.fdi.tp.basecode.attt.AdvancedTTTFactory;
import es.ucm.fdi.tp.basecode.attt.AdvancedTTTMove;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.bgame.control.SwingCtrl;
import es.ucm.fdi.tp.practica5.swings.Swing;

/**
 * Factoria de AdavancedTTT, que extiende de la original, y tiene definido el metodo createSwingView, 
 * y la capacidad de generar SwingPlayers
 * 
 */

public class AdvancedTTTFactoryExt extends AdvancedTTTFactory implements GameFactoryExt {

	/**
	 * Construye una o varias ventanas swing
	 */
	public void createSwingView(Observable<GameObserver> g, Controller c,
			final Piece viewPiece, Player random, Player ai) {
		if (viewPiece == null) {    //Generamos una sola ventana
			new Swing("Advanced TTT", null, (SwingCtrl)c, g, random, ai);
		} else {
			new Swing("Advanced TTT" + " (" + viewPiece.getId() + ")", viewPiece,
					(SwingCtrl)c, g, random, ai);
		}
	}
	/**
	 * Constructor de jugadores ATTT
	 */
	public AdvanacedTTTSwingPlayer createSwingPlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new AdvancedTTTMove());
		return new AdvanacedTTTSwingPlayer(possibleMoves);
	}
}

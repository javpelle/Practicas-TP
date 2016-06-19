package es.ucm.fdi.tp.practica5.attt;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.attt.AdvancedTTTFactory;
import es.ucm.fdi.tp.basecode.attt.AdvancedTTTMove;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.swings.PlayerUI;

/**
 * Factoria de AdavancedTTT, que extiende de la original, y tiene definido el metodo createSwingView, 
 * y la capacidad de generar SwingPlayers
 * 
 */

public class AdvancedTTTFactoryExt extends AdvancedTTTFactory implements GameFactoryExt {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construye una o varias ventanas swing
	 */
	public void createSwingView(Observable<GameObserver> g, Controller c,
			final Piece viewPiece, Player random, Player ai) {
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					if (viewPiece == null) {    //Generamos una sola ventana
						new PlayerUI("Advanced TTT", null, c, g, random, ai, createSwingPlayer());
					} else {
						new PlayerUI("Advanced TTT" + " (" + viewPiece.getId() + ")", viewPiece,
								c, g, random, ai, createSwingPlayer());
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {}
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

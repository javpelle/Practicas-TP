package es.ucm.fdi.tp.practica5.ataxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.AIPlayer;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.DummyAIPlayer;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.AIAlgorithm;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.ataxx.AtaxxFactory;
import es.ucm.fdi.tp.practica5.ataxx.AtaxxMove;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.bgame.control.SwingPlayer;
import es.ucm.fdi.tp.practica5.swings.PlayerUI;

/**
 * Factoria de Ataxx, que extiende de la original, y tiene definido el metodo createSwingView, 
 * y la capacidad de generar SwingPlayers
 * 
 */
public class AtaxxFactoryExt extends AtaxxFactory implements GameFactoryExt {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructora vacia
	 */
	public AtaxxFactoryExt () {}
	
	/**
	 * Constructora con parametros
	 * @param dimRows  Dimension del tablero
	 * @param obstacles Numero de obtaculos que queremos en el tablero
	 */
	public AtaxxFactoryExt (int dimRows, int obstacles) {
		super(dimRows, obstacles);
	}
	
	/**
	 * Crea una o varias ventanas swing
	 */
	public void createSwingView(Observable<GameObserver> g, Controller c,
		final Piece viewPiece, Player random, Player ai) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					if (viewPiece == null) {    //Generamos una sola ventana
						new PlayerUI("Ataxx", null, c, g, random, ai, createSwingPlayer());
					} else {
						new PlayerUI("Ataxx" + " (" + viewPiece.getId() + ")", viewPiece, c, g,
								random, ai, createSwingPlayer());
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {}	
	}

	@Override
	public SwingPlayer createSwingPlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new AtaxxMove());
		return new AtaxxSwingPlayer(possibleMoves);
	}
	
	@Override
	public Player createAIPlayer(AIAlgorithm alg) {
		if ( alg != null ) {
			return new AIPlayer(alg);
		} else {
			return new DummyAIPlayer(createRandomPlayer(), 1000);
		}
	}
}

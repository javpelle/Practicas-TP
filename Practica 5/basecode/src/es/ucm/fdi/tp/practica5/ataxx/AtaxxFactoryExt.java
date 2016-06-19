package es.ucm.fdi.tp.practica5.ataxx;

import java.util.ArrayList;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.bgame.control.SwingCtrl;
import es.ucm.fdi.tp.practica5.bgame.control.SwingPlayer;
import es.ucm.fdi.tp.practica5.swings.Swing;

/**
 * Factoria de Ataxx, que extiende de la original, y tiene definido el metodo createSwingView, 
 * y la capacidad de generar SwingPlayers
 * 
 */
public class AtaxxFactoryExt extends AtaxxFactory implements GameFactoryExt {
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
		
		if (viewPiece == null) {    //Generamos una sola ventana
			new Swing("Ataxx", null, (SwingCtrl)c, g, random, ai);
		} else {
			new Swing("Ataxx" + " (" + viewPiece.getId() + ")", viewPiece, (SwingCtrl)c, g,
					random, ai);
		}
	}

	@Override
	public SwingPlayer createSwingPlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new AtaxxMove());
		return new AtaxxSwingPlayer(possibleMoves);
	}
}

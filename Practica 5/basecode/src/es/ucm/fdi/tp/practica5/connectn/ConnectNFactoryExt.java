package es.ucm.fdi.tp.practica5.connectn;

import java.util.ArrayList;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNFactory;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.bgame.control.SwingCtrl;
import es.ucm.fdi.tp.practica5.swings.Swing;


/**
 * Factoria de ConnectN, que extiende de la original, y tiene definido el metodo createSwingView, 
 * y la capacidad de generar SwingPlayers
 * 
 */ 
public class ConnectNFactoryExt extends ConnectNFactory implements GameFactoryExt {
	
	/**
	 * Constructora vacia
	 */
	public ConnectNFactoryExt () {}
	
	/**
	 * Constructora con parametros
	 * @param dim Dimension del tablero
	 */
	public ConnectNFactoryExt (int dim) {
		super(dim);
	}
	
	/**
	 * Crea una o varias ventanas swing para Connect N
	 */
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		if (viewPiece == null) {    //Generamos una sola ventana
			new Swing("ConnectN", null, (SwingCtrl) c, g, random, ai);
		} else {
			new Swing("ConnectN" + " (" + viewPiece.getId() + ")", viewPiece,
					(SwingCtrl) c, g, random, ai);
		}
	}
	
	/**
	 * Crea un nuevo jugador ConnectN para swing
	 */
	public ConnectNSwingPlayer createSwingPlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new ConnectNMove());
		return new ConnectNSwingPlayer(possibleMoves);
	}
}

package es.ucm.fdi.tp.practica5.connectn;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNFactory;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.swings.PlayerUI;


/**
 * Factoria de ConnectN, que extiende de la original, y tiene definido el metodo createSwingView, 
 * y la capacidad de generar SwingPlayers
 * 
 */ 
public class ConnectNFactoryExt extends ConnectNFactory implements GameFactoryExt {
	private static final long serialVersionUID = 1L;

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
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					if (viewPiece == null) {    //Generamos una sola ventana
						new PlayerUI("ConnectN", null, c, g, random, ai, createSwingPlayer());
					} else {
						new PlayerUI("ConnectN" + " (" + viewPiece.getId() + ")", viewPiece,
								c, g, random, ai, createSwingPlayer());
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {}
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

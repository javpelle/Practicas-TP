package es.ucm.fdi.tp.practica5.attt;

import es.ucm.fdi.tp.basecode.attt.AdvancedTTTFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.swings.Swing;
import es.ucm.fdi.tp.practica5.swings.SwingPlayer;

public class AdvancedTTTFactoryExt extends AdvancedTTTFactory implements GameFactoryExt {

	public void createSwingView(final Observable<GameObserver> g, final Controller c,
		final Piece viewPiece, Player random, Player ai) {
		if (viewPiece == null) {    //Generamos una sola ventana
			new Swing("Advanced TTT", null, c, g);
		} else {
			new Swing("Advanced TTT" + " (" + viewPiece.getId() + ")", viewPiece, c, g);
		}
	}
	
	public SwingPlayer createSwingPlayer() {
		return new SwingPlayer();
	}
}

package es.ucm.fdi.tp.practica5.ataxx;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.ataxx.AtaxxFactory;
import es.ucm.fdi.tp.practica5.swings.Swing;

import javax.swing.JFrame;

public class AtaxxFactoryExt extends AtaxxFactory {
	public AtaxxFactoryExt (int dimRows, int obstacles) {
		super(dimRows, obstacles);
	}
	
	public AtaxxFactoryExt () {
		super();
	}
	public void createSwingView(final Observable<GameObserver> g, final Controller c,
			final Piece viewPiece, Player random, Player ai) {
			if (viewPiece == null) {    //Generamos una sola ventana
				Swing ventana = new Swing("Ataxx", getDim(), null, c, g);
			} else {
				Swing ventana = new Swing("Ataxx" + "(" + viewPiece.getId() + ")", getDim(), viewPiece, c, g);
			}
		}
		
}

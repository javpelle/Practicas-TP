package es.ucm.fdi.tp.practica5.ataxx;

import java.util.ArrayList;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.control.ConsolePlayer;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.ataxx.AtaxxFactory;
import es.ucm.fdi.tp.practica4.ataxx.AtaxxMove;
import es.ucm.fdi.tp.practica5.bgame.control.GameFactoryExt;
import es.ucm.fdi.tp.practica5.swings.Swing;
import es.ucm.fdi.tp.practica5.swings.SwingPlayer;

import javax.swing.JFrame;

public class AtaxxFactoryExt extends AtaxxFactory implements GameFactoryExt {
	
	public AtaxxFactoryExt () {}
	
	public AtaxxFactoryExt (int dimRows, int obstacles) {
		super(dimRows, obstacles);
	}

	public void createSwingView(final Observable<GameObserver> g, final Controller c,
		final Piece viewPiece, Player random, Player ai) {
		if (viewPiece == null) {    //Generamos una sola ventana
			new Swing("Ataxx", null, c, g);
		} else {
			new Swing("Ataxx" + " (" + viewPiece.getId() + ")", viewPiece, c, g);
		}
	}

	@Override
	public SwingPlayer createSwingPlayer() {
		// TODO Auto-generated method stub
		return new SwingPlayer();
	}
}

package es.ucm.fdi.tp.practica5.bgame.control;

import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.practica5.swings.SwingPlayer;

public interface GameFactoryExt extends GameFactory {
	
	public SwingPlayer createSwingPlayer();

}

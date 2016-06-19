package es.ucm.fdi.tp.practica6.bgame.control;

import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;

public interface GameFactoryExt extends GameFactory {
	
	
	/**
	 * Creates a swing player for the game implementing this interface.
	 * 
	 * <p>
	 * Crea un jugador de ventana para el juego que implementa este interface.
	 * 
	 * @return A {@link Player} that represents a swing player.
	 * 
	 *         <p>
	 *         Un nuevo objeto {@link Player} para este juego.
	 */
	public SwingPlayer createSwingPlayer();

}

package es.ucm.fdi.tp.practica6.net.response;

import java.io.Serializable;

import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
/**
 * Interfaz que implementaran cada tipo de respuesta diferente que puede
 * dar el servidor a los clientes
 */
public interface Response extends Serializable {
	
	/**
	 * Avisa al observador en que punto se encuentra el juego después de
	 * que el servidor haya ejecutado las instrucciones
	 * @param o observador (cliente)
	 */
	public void run(GameObserver o);
}

package es.ucm.fdi.tp.practica6.net.response;

import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;

/**
 * Respuesta del servidor al cliente para avisarle 
 * de que ha habido un error  */
public class ErrorResponse implements Response {
	private static final long serialVersionUID = 1L;
	
	String msg;
	
	/**
	 * Constructora de la respuesta
	 * @param msg Mensaje de error
	 */
	public ErrorResponse(String msg) {
		this.msg = msg;
	}

	@Override
	public void run(GameObserver o) {
		o.onError(msg);
	}

}

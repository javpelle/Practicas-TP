package es.ucm.fdi.tp.practica6.net.response;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;


/**
 * Respuesta del servidor al cliente para avisarle 
 * de que ha habido un cambio de turno  */
public class ChangeTurnResponse implements Response {
	private static final long serialVersionUID = 1L;
	
	private Board board;
	private Piece turn;
	
	/**
	 * Constructora de la respuesta
	 */
	public ChangeTurnResponse(Board board, Piece turn) {
		this.board = board;
		this.turn = turn;
	}
	
	@Override
	public void run(GameObserver o) {
		o.onChangeTurn(board, turn);
	}

}

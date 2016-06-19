package es.ucm.fdi.tp.practica6.net.response;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * Respuesta del servidor al cliente para avisarle 
 * de que se ha acabado un movimiento  */ 

public class MoveEndResponse implements Response {
	private static final long serialVersionUID = 1L;
	
	private Board board;
	private Piece turn;
	private boolean success;
	
	/**
	 * Constructora de la respuesta
	 */
	public MoveEndResponse(Board board, Piece turn, boolean success) {
		this.board = board;
		this.turn = turn;
		this.success = success;
	}
	
	@Override
	public void run(GameObserver o) {
		o.onMoveEnd(board, turn, success);
	}

}

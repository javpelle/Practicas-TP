package es.ucm.fdi.tp.practica6.net.response;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * Respuesta del servidor al cliente para avisarle 
 * de que ha empezado un movimiento */

public class MoveStartResponse implements Response {
	private static final long serialVersionUID = 1L;

	private Board board;
	private Piece turn;
	
	
	/**
	 * Constructora de la respuesta
	 * @param board Tablero
	 * @param turn Pieza a la que le corresponde el turno
	 */
	public MoveStartResponse (Board board, Piece turn) {
		this.board = board;
		this.turn = turn;
	}
	
	@Override
	public void run(GameObserver o) {
		o.onMoveStart(board, turn);
	}

}

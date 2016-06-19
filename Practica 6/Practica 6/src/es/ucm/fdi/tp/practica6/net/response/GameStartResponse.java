package es.ucm.fdi.tp.practica6.net.response;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * Respuesta del servidor al cliente para avisarle 
 * de que el juego ha empezado */ 
public class GameStartResponse implements Response {
	private static final long serialVersionUID = 1L;
	
	private Board board;
	private String gameDesc;
	private List<Piece> pieces;
	private Piece turn;
	/**
	 * Constructora de la respuesta
	 * @param board Tablero
	 * @param gameDesc Nombre del juego
	 * @param pieces Lista con las piezas de la partida
	 * @param turn Pieza a la que le corresponde el turno
	 */
	public GameStartResponse(Board board, String gameDesc, List<Piece> pieces, Piece turn) {
		this.board = board;
		this.gameDesc = gameDesc;
		this.pieces = pieces;
		this.turn = turn;
	}
	
	@Override
	public void run(GameObserver o) {
		o.onGameStart(board, gameDesc, pieces, turn);
	}

}

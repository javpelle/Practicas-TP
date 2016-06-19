package es.ucm.fdi.tp.practica6.net.response;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;


/**
 * Respuesta del servidor al cliente para avisarle 
 * de que el juego se ha acabado  */
public class GameOverResponse implements Response {
	private static final long serialVersionUID = 1L;
	
	private Board board;
	private State state;
	private Piece winner;
	
	/**
	 * Constructora de la respues
	 * @param board Tablero
	 * @param state Estado del juego
	 * @param winner Ganador de la partida (si hay)
	 */
	public GameOverResponse(Board board, State state, Piece winner) {
		this.board = board;
		this.state = state;
		this.winner = winner;
	}

	@Override
	public void run(GameObserver o) {
		o.onGameOver(board, state, winner);
	}

}

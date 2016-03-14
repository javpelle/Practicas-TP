package es.ucm.fdi.tp.practica4.Ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * A random player for Ataxx.
 * 
 * <p>
 * Un jugador aleatorio para  .
 *
 */
public class AtaxxRandomPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		if (board.isFull()) {
			throw new GameError("The board is full, cannot make a random move!!");
		}
		// quizas y solo quizas deberiamos llamar a la funcion que genera todos los movimientos 
		// posibles y elegir uno de esa lista pero para ello necesitamos un objeto AtaxxMove que
		// no se de donde sacarlo 
		
		List<GameMove> moves = rules.validMoves(board, pieces, p);
		
		// Generamos un aleatorio entre 0 y el tamaño de la lista que recoge todos los movimientos
		// posibles menos uno y nos quedamos con dicho movimiento.
		int randomMove = Utils.randomInt(moves.size());
		return moves.get(randomMove);
	}

	/**
	 * Creates the actual move to be returned by the player. Separating this
	 * method from {@link #requestMove(Piece, Board, List, GameRules)} allows us
	 * to reuse it for other similar games by overriding this method.
	 * 
	 * <p>
	 * Crea el movimiento concreto que sera devuelto por el jugador. Se separa
	 * este metodo de {@link #requestMove(Piece, Board, List, GameRules)} para
	 * permitir la reutilizacion de esta clase en otros juegos similares,
	 * sobrescribiendo este metodo.
	 * 
	 * @param row
	 *            row number.
	 * @param col
	 *            column number..
	 * @param p
	 *            Piece to place at ({@code row},{@code col}).
	 * @return
	 */
	protected GameMove createMove(int origRow, int origCol, int row, int col, Piece p) {
		return new AtaxxMove(origRow, origCol, row, col, p);
	}

}

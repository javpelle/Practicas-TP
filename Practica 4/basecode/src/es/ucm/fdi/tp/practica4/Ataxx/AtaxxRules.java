package es.ucm.fdi.tp.practica4.Ataxx;


import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;

/**
 * Rules for Ataxx game.
 * <ul>
 * <li>The game is played on an NxN board (with N>=5 and N is an odd number).</li>
 * <li>The number of players is between 2 and 4.</li>
 * <li>The player turn in the given order, each placing a piece on an empty
 * cell. The winner is the one who has more pieces of its own when there are
 * not more availables moves.</li>
 * </ul>
 * 
 * <p>
 * Reglas del juego Ataxx.
 * <ul>
 * <li>El juego se juega en un tablero NxN (con N>=5 y N es impar).</li>
 * <li>El numero de jugadores esta entre 2 y 4.</li>
 * <li>Los jugadores juegan en el orden proporcionado, cada uno colocando una
 * ficha en una casilla vacia. El ganador es el que tine mas fichas en su poder
 *  cuando no hay mas movimientos disponibles
 * </li>
 * </ul>
 *
 */
public class AtaxxRules implements GameRules {

	// This object is returned by gameOver to indicate that the game is not
	// over. Just to avoid creating it multiple times, etc.
	//
	protected final Pair<State, Piece> gameInPlayResult = new Pair<State, Piece>(State.InPlay, null);

	private int dim;

	public AtaxxRules(int dim) {
		if (dim < 5 || dim % 2 == 0) {
			throw new GameError("Dimension must be at least 5 and odd number: " + dim);
		} else {
			this.dim = dim;
		}
	}

	@Override
	public String gameDesc() {
		return "Ataxx " + dim + "x" + dim;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		return new FiniteRectBoard(dim, dim);
	}

	@Override
	public Piece initialPlayer(Board board, List<Piece> playersPieces) {
		return playersPieces.get(0);
	}

	@Override
	public int minPlayers() {
		return 2;
	}

	@Override
	public int maxPlayers() {
		return 4;
	}

	@Override
	public Pair<State, Piece> updateState(Board board, List<Piece> playersPieces, Piece lastPlayer) {		
		Piece actualPlayer = nextPlayer(board, playersPieces, lastPlayer);
		
		for (int i = 0; i < playersPieces.size(); i++) {
			if (existsMove(board, actualPlayer)) {
				return new Pair<State, Piece>(State.InPlay, actualPlayer);
			}
			actualPlayer = nextPlayer(board, playersPieces, actualPlayer);
		}
		
		int[] contadores = new int[playersPieces.size()];
		for (int i: contadores) {
			contadores[i] = board.getPieceCount(playersPieces.get(i));
		}
		
		for (int i: contadores) {
			
		}
		
		
	
	}
	

	@Override
	public Piece nextPlayer(Board board, List<Piece> pieces, Piece lastPlayer) {
		int i = pieces.indexOf(lastPlayer);
		return pieces.get((i + 1) % pieces.size());
	}

	@Override
	public double evaluate(Board board, List<Piece> playersPieces, Piece turn) {
		return 0;
	}

	@Override
	public List<GameMove> validMoves(Board board, List<Piece> playersPieces, Piece turn) {
		List<GameMove> moves = generateMoves(board, turn, false);
		return moves;
	}
	
	/**
	 * Busca un posible movimiento para el jugador. Si lo hay devuelve true. False en caso contrario
	 * @param board
	 * @param playersPieces
	 * @param turn
	 * @return
	 */
	private boolean existsMove(Board board, Piece turn) {
		return  generateMoves(board, turn, true).size() != 0;
	}
	
	/**
	 * Busca uno o todos los movimientos posibles de un jugador
	 * @param board
	 * @param playersPieces
	 * @param turn
	 * @param onlyOne
	 * @return
	 */
	private List<GameMove> generateMoves(Board board, Piece turn, boolean onlyOne) {
		List<GameMove> moves = new ArrayList<GameMove>();		
		for (int x = 0; x <board.getRows(); x++) {
			for (int y = 0; y < board.getCols(); y++) {
				if (board.getPosition(x, y).equals(turn)) {
					vecinas(x, y, board, turn, onlyOne, moves);
					if (moves.size() != 0 && onlyOne) {
						// Si ya se ha introducido algún movimiento posible y solo
						// necesitamos buscar uno, lo devolvemos
						return moves;
					}
				}
			}
		}
		return moves;
	}
		
	/**
	 * Busca una o todas las posiciones vecinas a las que puede mover una ficha en una posicion dada
	 * @param x
	 * @param y
	 * @param board
	 * @param turn
	 * @param onlyOne
	 * @param moves
	 */
	private void vecinas(int x, int y, Board board, Piece turn, boolean onlyOne, List<GameMove> moves) {
		int deltas[][] = {
				{-2,-2}, {-2,-1}, {-2,0}, {-2,1}, {-2,2},
				{-1,-2}, {-1,-1}, {-1,0}, {-1,1}, {-1,2},
				{0,-2}, {0,-1}, {0,1}, {0,2},
				{1,-2}, {1,-1}, {1,0}, {1,1}, {1,2},
				{2,-2}, {2,-1}, {2,0}, {2,1}, {2,2}
		};
		for (int[] c: deltas)  {
			if(esValida( c[0] + x, c[1] + y, board)) {
				moves.add(new AtaxxMove(x, y, c[0] + x, c[1] + y, turn));
				if(onlyOne) {
					// Solo buscamos una posicion valida (asegura mover al usuario)
					break;
				}
			}
		}
	}
	
	/**
	 * Comprueba si una posicion dada esta dentro del tablero y libre
	 * @param i
	 * @param j
	 * @param board
	 * @return
	 */
	private boolean esValida(int i, int j, Board board) {
		if (i >= 0 && j >= 0 && i < board.getRows() && j < board.getCols()) {
			if (board.getPosition(i,j) == null) {
				return true;
			}
		}
		return false;
	}

}
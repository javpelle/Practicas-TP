package es.ucm.fdi.tp.practica5.ataxx;


import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
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
	private int obstacles;

	public AtaxxRules(int dim, int obstacles) {
		if (dim < 5 || dim % 2 == 0) {
			throw new GameError("Dimension must be at least 5 and odd number: " + dim);
		} else {
			this.dim = dim;
		}
		if (obstacles > (dim * dim) / 2 || obstacles % 4 != 0) {
			throw new GameError("The number of obstacles must be minor or equal than " + (dim * dim) / 2 + " and multiple of 4: " + obstacles);
		} else {
			this.obstacles = obstacles / 4;
		}
	}

	@Override
	public String gameDesc() {
		return "Ataxx " + dim + "x" + dim;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {		
		FiniteRectBoard tablero = new FiniteRectBoard(dim, dim);
		// Player 1
		tablero.setPosition(0 , 0, pieces.get(0));
		tablero.setPosition(dim - 1 , dim - 1, pieces.get(0));
		tablero.setPieceCount(pieces.get(0), 2);
		// Player 2
		tablero.setPosition(0 , dim - 1, pieces.get(1));
		tablero.setPosition(dim - 1 , 0, pieces.get(1));
		tablero.setPieceCount(pieces.get(1), 2);
		// Player 3
		if (3 <= pieces.size()) {
			tablero.setPosition(dim / 2 , 0, pieces.get(2));
			tablero.setPosition(dim / 2 , dim - 1, pieces.get(2));
			tablero.setPieceCount(pieces.get(2), 2);
		}
		// Player 4
		if (4 == pieces.size()) {
			tablero.setPosition(0, dim / 2, pieces.get(3));
			tablero.setPosition(dim - 1, dim / 2, pieces.get(3));
			tablero.setPieceCount(pieces.get(3), 2);
		}
		rellenarObstaculos(tablero);
		return tablero;
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
		Piece posibleWinner = soloUnoConFichas(board, playersPieces);
		if (posibleWinner != null) {
			return new Pair<State, Piece>(State.Won, posibleWinner);
		}
		Piece actualPlayer = nextPlayer(board, playersPieces, lastPlayer);
		if (actualPlayer != null) {
			return new Pair<State, Piece>(State.InPlay, actualPlayer);
		}		
		
		int[] contadores = new int[playersPieces.size()];
		for (int i = 0; i < playersPieces.size(); i++) {
			contadores[i] = board.getPieceCount(playersPieces.get(i));
		}
		
		boolean empate = false;
		int max = 0;
		for (int i = 1; i < playersPieces.size(); i++) {
			if (contadores[i] > contadores[max]) {
				//mejor puntuacion
				max = i;
				empate = false;
			} else if (contadores[i] == contadores[max]) {
				// empate en el primer lugar
				empate = true;
			}
		}
		if (empate) {
			return new Pair<State, Piece>(State.Draw, null);
		} else {
			// no ha habido empate
			return new Pair<State, Piece>(State.Won, playersPieces.get(max));
		}
	}
	

	@Override
	public Piece nextPlayer(Board board, List<Piece> pieces, Piece lastPlayer) {
		int i = pieces.indexOf(lastPlayer);
		Piece actualPlayer = pieces.get((i + 1) % pieces.size());
		
		for(int j = 0; j < pieces.size(); j++) {
			if (existsMove(board, actualPlayer)) {
				return actualPlayer;
			} 
			i = pieces.indexOf(actualPlayer );
			actualPlayer = pieces.get((i + 1) % pieces.size());
		}
		return null;
	}

	@Override
	public double evaluate(Board board, List<Piece> playersPieces, Piece turn,Piece p) {
		return 0;
	}

	@Override
	public List<GameMove> validMoves(Board board, List<Piece> playersPieces, Piece turn) {
		List<GameMove> moves = generateMoves(board, turn, false);
		return moves;
	}
	
	/**
	 * Busca un posible movimiento para el jugador. Si lo hay devuelve true. False en caso contrario
	 * @param board tablero sobre el que se ejecuta el juego
	 * @param playersPieces lista de jugadores
	 * @param turn Pieza del jugador que tiene el turno.
	 * @return la lista de movimientos posibles
	 */
	private boolean existsMove(Board board, Piece turn) {
		// Si la pieza no tiene fichas nos ahorramos recorrer el tablero en busca de movimientos
		// posibles para esa pieza.
		return (board.getPieceCount(turn) != 0 && !generateMoves(board, turn, true).isEmpty());
	}
	
	/**
	 * Busca uno o todos los movimientos posibles de un jugador
	 * @param board tablero sobre el que jugamos
	 * @param playersPieces lista de jugadores
	 * @param turn Pieza del jugador que tiene el turno
	 * @param onlyOne booleano que indica si basta con generar un movimiento valido o todos
	 * @return la lista de movimientos posibles
	 */
	private List<GameMove> generateMoves(Board board, Piece turn, boolean onlyOne) {
		List<GameMove> moves = new ArrayList<GameMove>();		
		for (int x = 0; x <board.getRows(); x++) {
			for (int y = 0; y < board.getCols(); y++) {
				if (board.getPosition(x, y) != null && board.getPosition(x, y).equals(turn)) {
					vecinas(x, y, board, turn, onlyOne, moves);
					if (!moves.isEmpty() && onlyOne) {
						// Si ya se ha introducido alg�n movimiento posible y solo
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
	 * @param x fila de la casilla 
	 * @param y columna de la casilla
	 * @param board tablero sobre el que se ejecuta el juego
	 * @param turn pieza del jugador que tiene el turno
	 * @param onlyOne booleano que indica si basta con generar una posicion valida o todas.
	 * @param moves lista de movimientos posibles.
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
	 * @param i coordenada x
	 * @param j coordenada y
	 * @param board tablero sobre el que estamos
	 * @return true si la casilla es valida. False en otro caso
	 */
	private boolean esValida(int i, int j, Board board) {
		if (i >= 0 && j >= 0 && i < board.getRows() && j < board.getCols()) {
			if (board.getPosition(i,j) == null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Compruba si solo un jugador tiene fichas
	 * @param board Tablero sobre el que jugamos
	 * @param pieces Lista de jugadores
	 * @return Piece ganador si solo ese tiene piezas. Null en otro caso.
	 */
	private Piece soloUnoConFichas(Board board,List<Piece> pieces) {
		Piece winner = null;
		boolean ninguno = true;
		for (Piece c : pieces) {
			if (ninguno && board.getPieceCount(c) != 0) {
				ninguno = false;
				winner = c;
			} else if (!ninguno && board.getPieceCount(c) != 0) {
				return null;			
			}
		}
		return winner;
	}
	
	/**
	 * Rellena el tablero con los obstaculos dados
	 * @param board tablero sobre el que estamos jugando
	 */
	private void rellenarObstaculos(Board board) {
		Piece obstacle = new Piece("*");
		int fila, columna;
		for(int k = 0; k < obstacles; k++) {
			do {
				fila = Utils.randomInt((board.getRows()/ 2) + 1);		
				columna = Utils.randomInt(board.getCols()/ 2);
			} while (board.getPosition(fila, columna) != null);
			board.setPosition(fila, columna, obstacle);
			board.setPosition(columna, dim - fila - 1, obstacle);
			board.setPosition(dim - fila - 1, dim - columna - 1, obstacle);
			board.setPosition(dim - columna - 1, fila, obstacle);
		}
	}
}
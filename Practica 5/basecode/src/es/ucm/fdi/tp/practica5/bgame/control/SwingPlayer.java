package es.ucm.fdi.tp.practica5.bgame.control;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public abstract class SwingPlayer extends Player {
	
	/**
	 * List of moves that can be played by the player (typically include one
	 * type of move for simple games, e.g., Tic-Tac-Toe).
	 * 
	 * <p>
	 * Lista de tipos de movimientos que puede jugar el jugador (solo un tipo de
	 * movimientos para juegos simples).
	 */
	private List<GameMove> availableMoves;
	
	/**
	 * Guardamos el movimiento para que el controlador lo ejecute
	 */
	protected GameMove moveToDo;
	
	/**
	 * Constructora 
	 * @param availableMoves Movimientos del player
	 */ 
	public SwingPlayer(List<GameMove> availableMoves) {
		this.availableMoves = new ArrayList<GameMove>(availableMoves);
	}

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces,
			GameRules rules) {
		if (moveToDo == null) {
			throw new GameError("It has not notificated any move");
		}
		
		GameMove m = moveToDo;
		moveToDo = null;
		return m;				
	}
	/**
	 * Establece un nuevo movimiento en nuestro player
	 * @param m Nuevo movimiento
	 */
	public void setMoveToDo(GameMove m) {
		moveToDo = m;
	}
	
	/**
	 * Establece un nuevo movimiento
	 * @param row Fila seleccionada
	 * @param col Columna seleccionada
	 * @param p Pieza del movimiento
	 * @param pieceCount Numero de piezas del turno
	 * @return True si el movimiento ha sido completado, false si hace falta seleccionar otra casilla
	 */
	public abstract boolean generateMoveFromSwing(int row, int col, Piece p, int pieceCount);
	
	
	/**
	 * Indica si el juego necesita dos cliks para realizar movimientos
	 */
	public abstract boolean isTwoClicks();
	
	/**
	 * Pone el atributo secondClick que tendran todos los players de los juegos a false
	 */
	public abstract void setSecondClickFalse();
}

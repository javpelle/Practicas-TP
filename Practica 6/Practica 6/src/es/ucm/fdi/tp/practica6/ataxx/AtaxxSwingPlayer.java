package es.ucm.fdi.tp.practica6.ataxx;

import java.util.ArrayList;

import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica6.ataxx.AtaxxMove;
import es.ucm.fdi.tp.practica6.bgame.control.SwingPlayer;

/**
 * Clase que representa un jugador ataxx para el modo de ventanas
 * 
 *
 */
public class AtaxxSwingPlayer extends SwingPlayer {
	private static final long serialVersionUID = 1L;

	/**
	 * Fila de la celda de origen de nuestro movimiento
	 */
	private int origRow;
	
	/**
	 * Columna de la celda de origen de nuestro movimiento
	 */
	private int origCol;
	
	/**
	 * Booleano que inidica si se ha seleccionado ya una celda de origen
	 */
	private boolean secondClick;
	
	
	/**
	 * Constructora 
	 * @param Lista de movimientos del jugador ("Las llamadas que realizamos pasan un movimiento vacio")
	 */
	public AtaxxSwingPlayer(ArrayList<GameMove> possibleMoves){
		super(possibleMoves);
		secondClick = false;
	};

	@Override
	public boolean generateMoveFromSwing(int row, int col, Piece p, int pieceCount) {
		if (secondClick) {
			// Ya se ha producido un clic en una celda de origen
			AtaxxMove m = new AtaxxMove(origRow, origCol, row, col, p);
			setMoveToDo(m);
			// Como ya hemos generado un movimiento el proximo clic será
			// el primero y por tanto no debe haber una pieza guardada
			setSecondClickFalse();
			return true;
		} else {
			secondClick = true;
			origRow = row;
			origCol = col;
			return false;
		}
	}

	@Override
	public boolean isTwoClicks() {
		return true;
	}

	@Override
	public void setSecondClickFalse() {
		secondClick = false;
	}

	@Override
	public void resetPlayer() {
		// El atributo isTwoClicks nunca se modifica en este player		
	}
}

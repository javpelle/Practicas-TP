package es.ucm.fdi.tp.practica5.attt;

import java.util.List;

import es.ucm.fdi.tp.basecode.attt.AdvancedTTTMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;
import es.ucm.fdi.tp.practica5.ataxx.AtaxxMove;
import es.ucm.fdi.tp.practica5.bgame.control.SwingPlayer;

public class AdvanacedTTTSwingPlayer extends SwingPlayer {
	
	/**
	 * Fila de la celda de origen de nuestro movimiento
	 */
	private int origRow;
	
	/**
	 * Columna de la celda de origen de nuestro movimiento
	 */
	private int origCol;
	
	/**
	 * Booleano que inidica si se hay que hacer un click para realizar un movimineto, o dos (uno de origen, y 
	 * otro de destino)
	 */
	private boolean isTwoClicks;
	
	/**
	 * Booleano que inidica si se ha seleccionado ya una celda de origen
	 */
	private boolean secondClick;

	/**
	 * Constructora de la clase
	 * @param availableMoves Lista de movimientos del jugador ("Las llamadas que realizamos pasan un movimiento vacio")
	 */
	public AdvanacedTTTSwingPlayer(List<GameMove> availableMoves) {
		super(availableMoves);
		isTwoClicks = false;
		secondClick = false;
	}

	

	@Override
	public boolean generateMoveFromSwing(int row, int col, Piece p, int pieceCount) {
		if (pieceCount == 0) {
			isTwoClicks = true;
			if (secondClick) {
				// Ya se ha producido un clic en una celda de origen
				AdvancedTTTMove m = new AdvancedTTTMove(origRow, origCol, row, col, p);
				setMoveToDo(m);
				// Como ya hemos generado un movimiento el proximo clic será
				// el primero y por tanto no debe haber una pieza guardada
				setFalse();
				return true;
			} else {
				secondClick = true;
				origRow = row;
				origCol = col;
				return false;
			}
			
		} else {
			AdvancedTTTMove m = new AdvancedTTTMove(-1,-1,row,col,p);
			setMoveToDo(m);
			return true;
		}
	}
	
	/**
	 * El atributo secondClick toma el valor false
	 */
	public void setFalse() {
		secondClick = false;
	}

	@Override
	public boolean isTwoClicks() {
		return isTwoClicks;
	}



	@Override
	public void setSecondClickFalse() {
		secondClick = false;
		
	}

}

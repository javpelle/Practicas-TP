package es.ucm.fdi.tp.practica5.connectn;

import java.util.ArrayList;

import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;
import es.ucm.fdi.tp.practica5.ataxx.AtaxxMove;
import es.ucm.fdi.tp.practica5.bgame.control.SwingPlayer;

public class ConnectNSwingPlayer extends SwingPlayer {
	public ConnectNSwingPlayer(ArrayList<GameMove> possibleMoves){
		super(possibleMoves);
	};

	@Override
	public boolean generateMoveFromSwing(int row, int col, Piece p, int pieceCount) {
		ConnectNMove m = new ConnectNMove(row,col,p);
		setMoveToDo(m);
		return true;
	}

	@Override
	public boolean isTwoClicks() {
		return false;
	}

	@Override
	public void setSecondClickFalse() {
		// TODO Auto-generated method stub
		
	}
}

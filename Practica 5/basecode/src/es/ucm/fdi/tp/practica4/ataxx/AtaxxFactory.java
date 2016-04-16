package es.ucm.fdi.tp.practica4.ataxx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.control.ConsolePlayer;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.DummyAIPlayer;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.AIAlgorithm;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.views.GenericConsoleView;

/**
 * A factory for creating Ataxx games. See {@link AtaxxRules} for the
 * description of the game.
 * 
 * 
 * <p>
 * Factoria para la creacion de juegos Ataxx. Vease {@link AtaxxRules}
 * para la descripcion del juego.
 */
public class AtaxxFactory implements GameFactory {
	
	private static final long serialVersionUID = 1L;

	private int dim;
	private int obstacles;

	public AtaxxFactory() {
		this(7, 0);
	}

	public AtaxxFactory(int dim, int obstacles) {
		if (dim < 5 || dim%2 == 0) {
			throw new GameError("Dimension must be at least 5 and an odd number: " + dim);
		} else {
			this.dim = dim;
		} if (obstacles > (dim * dim) / 2 || obstacles % 4 != 0) {
			throw new GameError("The number of obstacles must be minor or equal than " + (dim * dim) / 2 + " and multiple of 4: " + obstacles);
		} else {
			this.obstacles = obstacles;
		}
	}

	@Override
	public GameRules gameRules() {
		return new AtaxxRules(dim, obstacles);
	}

	@Override
	public Player createConsolePlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new AtaxxMove());
		return new ConsolePlayer(new Scanner(System.in), possibleMoves);
	}

	@Override
	public Player createRandomPlayer() {
		return new AtaxxRandomPlayer();
	}

	@Override
	public Player createAIPlayer(AIAlgorithm alg) {
		return new DummyAIPlayer(createRandomPlayer(), 1000);
	}

	/**
	 * By default, we have two players, X and O.
	 * <p>
	 * Por defecto, hay dos jugadores, X y O.
	 */
	@Override
	public List<Piece> createDefaultPieces() {
		List<Piece> pieces = new ArrayList<Piece>();
		pieces.add(new Piece("X"));
		pieces.add(new Piece("O"));
		return pieces;
	}

	@Override
	public void createConsoleView(Observable<GameObserver> g, Controller c) {
		new GenericConsoleView(g, c);
	}

	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		throw new UnsupportedOperationException("There is no swing view");
	}
	
	public int getDim(){
		return dim;
	}

}
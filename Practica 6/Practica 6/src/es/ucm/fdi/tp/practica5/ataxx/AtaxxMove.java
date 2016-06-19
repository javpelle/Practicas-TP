package es.ucm.fdi.tp.practica5.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * A Class representing a move for Ataxx.
 * 
 * <p>
 * Clase para representar un movimiento del juego Ataxx.
 * 
 */
public class AtaxxMove extends GameMove {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The row where to place the piece return by {@link GameMove#getPiece()}.
	 * <p>
	 * Fila en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int row;

	/**
	 * The column where to place the piece return by {@link GameMove#getPiece()}
	 * .
	 * <p>
	 * Columna en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int col;

	/**
	 * The row where to place the origin piece
	 */
	protected int rowOrigin;
	
	/**
	 * The column where to place the origin piece
	 */
	protected int colOrigin;
	
	
	/**
	 * This constructor should be used ONLY to get an instance of
	 * {@link AtaxxMove} to generate game moves from strings by calling
	 * {@link #fromString(String)}
	 * 
	 * <p>
	 * Solo se debe usar este constructor para obtener objetos de
	 * {@link AtaxxMove} para generar movimientos a partir de strings usando
	 * el metodo {@link #fromString(String)}
	 * 
	 */
	public AtaxxMove() {
	}

	/**
	 * Constructs a move for placing a piece of the type referenced by {@code p}
	 * at position ({@code row},{@code col}).
	 * 
	 * <p>
	 * Construye un movimiento para colocar una ficha del tipo referenciado por
	 * {@code p} en la posicion ({@code row},{@code col}).
	 * 
	 * @param row
	 *            Number of row.
	 *            <p>
	 *            Numero de fila.
	 * @param col
	 *            Number of column.
	 *            <p>
	 *            Numero de columna.
	 * @param p
	 *            A piece to be place at ({@code row},{@code col}).
	 *            <p>
	 *            Ficha a colocar en ({@code row},{@code col}).
	 */
	public AtaxxMove(int x, int y, int row, int col, Piece p) {
		super(p);
		this.row = row;
		this.col = col;
		this.rowOrigin = x;
		this.colOrigin = y;
	}

	@Override
	public void execute(Board board, List<Piece> pieces) {
		
		if (board.getPosition(row, col) == null && distanciaLegal() && board.getPosition(rowOrigin, colOrigin).equals(getPiece())) {
			board.setPosition(row, col, getPiece());
			if (!contigua()) {
				// Si la casilla a la que se mueve la pieza no es contigua, entonces eliminamos la original.
				board.setPosition(rowOrigin, colOrigin, null);
			} else {
				board.setPieceCount(getPiece(), board.getPieceCount(getPiece()) + 1);
			}
			transformarVecinas(board);
		} else {
			throw new GameError("position (" + row + "," + col + ") is already occupied or the move is not valid!");
		}
	}
	
	/**
	 * Devuelve true si la ficha se ha movido a una casilla contigua (se duplica).
	 * False si se ha desplazado a distancia 2 (se elimina la original)
	 * @return
	 */
	private boolean contigua() {
		return (row - rowOrigin >= -1 && row - rowOrigin <= 1 && col - colOrigin >= -1 && col - colOrigin <= 1);	
	}
	
	/**
	 * Devuelve true si la ficha se ha movido en un rango de casillas menor o igual que 2.
	 * False en caso contrario.
	 * @return
	 */
	private boolean distanciaLegal() {
		return (row - rowOrigin >= -2 && row - rowOrigin <= 2 && col - colOrigin >= -2 && col - colOrigin <= 2);
	}

	/**
	 * This move can be constructed from a string of the form "row SPACE col"
	 * where row and col are integers representing a position.
	 * 
	 * <p>
	 * Se puede construir un movimiento desde un string de la forma
	 * "row SPACE col" donde row y col son enteros que representan una casilla.
	 */
	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 4) {
			return null;
		}

		try {
			int row, col, origRow, origCol;
			origRow = Integer.parseInt(words[0]);
			origCol = Integer.parseInt(words[1]);
			row = Integer.parseInt(words[2]);
			col = Integer.parseInt(words[3]);
			return createMove(origRow, origCol, row, col, p);
		} catch (NumberFormatException e) {
			return null;
		}

	}

	/**
	 * Creates a move that is called from {@link #fromString(Piece, String)}.
	 * Separating it from that method allows us to use this class for other
	 * similar games by overriding this method.
	 * 
	 * <p>
	 * Crea un nuevo movimiento con la misma ficha utilizada en el movimiento
	 * actual. Llamado desde {@link #fromString(Piece, String)}; se separa este
	 * metodo del anterior para permitir utilizar esta clase para otros juegos
	 * similares sobrescribiendo este metodo.
	 * 
	 * @param row
	 *            Row of the move being created.
	 *            <p>
	 *            Fila del nuevo movimiento.
	 * 
	 * @param col
	 *            Column of the move being created.
	 *            <p>
	 *            Columna del nuevo movimiento.
	 */
	protected GameMove createMove(int origRow, int origCol, int row, int col, Piece p) {
		return new AtaxxMove(origRow, origCol, row, col, p);
	}

	@Override
	public String help() {
		return "Row and column for origin and for destination, separated by spaces (four numbers).";
	}

	@Override
	public String toString() {
		// En los ejemplos de pdf los movimientos no son "escritos" por pantalla
		if (getPiece() == null) {
			return help();
		} else {
			return "Piece '" + getPiece() + "' from ("+ rowOrigin + "," + colOrigin +") to (" + row + "," + col + ")";
		}
	}
	
	/**
	 * Vemos que posiciones vecinas a la posicion de destino pertenecen a 
	 * jugadores rivales y las convertimos en piezas del jugador actual.
	 * @param board tablero sobre el que trabajamos
	 */
	private void transformarVecinas(Board board) {
		Piece obstacle = new Piece ("*");
		int contador = 0;
		int deltas[][] = {
			{-1,-1}, {-1,0}, {-1,1},
			{0,-1},	{0,1},
			{1,-1}, {1,0}, {1,1},
		};
		for (int[] c: deltas)  {
			if(esValida(row + c[0], col + c[1], board) && board.getPosition(row + c[0], col + c[1]) != null && !board.getPosition(row + c[0], col + c[1]).equals(obstacle)) {
				// Restamos uno al contador de la ficha transformada
				board.setPieceCount(board.getPosition(row + c[0], col + c[1]),
				board.getPieceCount(board.getPosition(row + c[0], col + c[1])) - 1);
				// Sumamos uno al contador de las fichas nuevas
				contador++;
				board.setPosition(row + c[0], col + c[1], getPiece());
			}
		}
		board.setPieceCount(getPiece(),	board.getPieceCount(getPiece()) + contador);
	}
	
	/**
	 * Comprueba que dadas unas coordenadas no se salgan del tablero
	 * @param i coordenada x
	 * @param j coordenada y
	 * @param board tablero sobre el que jugamos
	 * @return true si es una posicion dentro del tablero. False en otro caso.
	 */
	private boolean esValida(int i, int j, Board board) {
		return (i >= 0 && j >= 0 && i < board.getRows() && j < board.getCols());	
	}
	
}

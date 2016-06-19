package es.ucm.fdi.tp.practica5.bgame.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.Main.PlayerMode;

public class SwingCtrl extends Controller {
	
	/**
	 * A map that associates pieces with players (manual, random, etc.).
	 * 
	 * <p>
	 * Map que asocia fichas con jugadores (manual, random, etc.).
	 */
	protected Map<Piece, Player> players;
	
	/**
	 * A map that associates pieces with PlayerModes (manual, random, etc.).
	 * 
	 * <p>
	 * Map que asocia fichas con Modos de Juego (manual, random, etc.).
	 */
	private Map<Piece, PlayerMode> piecesModes;
	
	/**
	 * Construcotara del cotrolador
	 * @param game Juego que queremos ejecutar
	 * @param pieces Lista de piezas de los jugadores
	 * @param players Lista de jugadores
	 * @param pieceModes Mapa de asociacion de piezas y modos de jugador
	 */
	public SwingCtrl(Game game, List<Piece> pieces, List<Player> players, Map <Piece, PlayerMode> pieceModes) {
		super(game, pieces);
		this.piecesModes = pieceModes;
		this.players = new HashMap<Piece, Player>();
		for (int i = 0; i < pieces.size(); i++) {
			this.players.put(pieces.get(i), players.get(i));
		}
	}
	
	/**
	 * Realiza un movimiento
	 */
	public void makeMove(Player p) {
		game.makeMove(p);
	};
	
	/**
	 * Devuelve el jugador correspondiente a una pieza
	 * @param piece Pieza de la que ser quiere obtener el jugador
	 * @return jugador correspondiente a la pieza
	 */
	
	public Player getPlayerFromPiece(Piece piece) {
		Player p = players.get(piece);
		return p;
	}
	
	/**
	 * Actualiza los mapas de la clase
	 * @param piece Pieza a actualizar
	 * @param player Nuevo jugador asociado a la pieza
	 * @param mode Nuevo modo asociado a la pieza
	 */
	public void setPlayerFromPiece (Piece piece, Player player, PlayerMode mode) {
		players.remove(piece);
		players.put(piece, player);
		piecesModes.remove(piece);
		piecesModes.put(piece, mode);
	}
	
	/**
	 * Permite obtener la descripcion del modo de jugador de una pieza
	 * @param p Pieza de la que se quiere obtener la informacion
	 * @return String con la descripcion del modo
	 */
	public String toStringPlayerMode (Piece p) {
		return piecesModes.get(p).getDesc();
	}
	
	/**
	 * Permite obtener el modo de jugador de una pieza
	 * @param p Pieza de la que se quiere obtener la informacion
	 * @return Modo de la pieza
	 */
	public PlayerMode getPlayerModeFromPiece (Piece p) {
		return piecesModes.get(p);
	}
	
	/**
	 * 
	 * @return Mapa de piezas y Player Modes
	 */
	public Map<Piece, PlayerMode> getPlayerModeMap() {
		return piecesModes;
	}
}

package tp.pr3.logica;

/**
 * Esta clase consta de dos atributos privados enteros correspondientes con
 * dos coordenadas (x, y).
 * Se encarga de devolver un array de posiciones vecinas de una dada que estén
 * dentro de las dimensiones de la superficie.
 */
public class Posicion {
	private int f, c;
	
	/**
	 * Constructora de la clase Posicion que inicializa f y c con atributos
	 * pasados por parámetro.
	 */
	public Posicion(int f, int c) {
		this.f = f; this.c = c;
	}
	
	/**
	 * Constructora de la clase Posicion que inicializa f y c a partir de 
	 * la suma de una posición dada y un vector (f, c).
	 */
	public Posicion(Posicion p, int f, int c) {
		this.f = p.f + f; this.c = p.c + c;
	}
	
	/**
	 * Devuelve null si la posición a estudiar se sale de la superficie.
	 * En caso contrario devuelve dicha posición.
	 */
	public static Posicion crea(Posicion base, int df, int dc, Posicion esquina) {
		Posicion nueva = new Posicion(base, df, dc);
		if (nueva.f > esquina.f || nueva.c > esquina.c || nueva.f < 0 || nueva.c < 0) {
			return null;
		} else {
			return nueva;
		}
	}
	
	/**
	 * Comprueba si cada una de las posiciones vecinas están dentro o 
	 * fuera de la superfice y devuelve un array de Posicion con las
	 * que estén dentro.
	 */
	public Posicion[] vecinas(Posicion p, Posicion esquina) {
		Posicion posibles[] = {
				crea(p, -1, -1, esquina),
				crea(p, -1, 0, esquina),
				crea(p, -1, 1, esquina),
				crea(p, 0, -1, esquina),
				crea(p, 0, 1, esquina),
				crea(p, 1, -1, esquina),
				crea(p, 1, 0, esquina),
				crea(p, 1, 1, esquina)
		};
		int nv = 0; // Numero de validas
		for (Posicion c : posibles) {
			if (c != null) nv ++; 
		}	
			Posicion buenas[] = new Posicion[nv];
			nv = 0;
			for (Posicion c : posibles) {
				if (c != null) buenas[nv ++] = c; 
			}		
			return buenas;
	}
	
	/**
	 * Devuelve la coordenada f.
	 */
	public int getF(){
		return this.f;
	}
	
	/**
	 * Devuelve la coordenada c.
	 */
	public int getC(){
		return this.c;
	}
}

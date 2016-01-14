package tp.pr3.logica;

/**
 * Clase abstracta que representa una celula del mundo. 
 */
public interface Celula {
	
	abstract public Posicion ejecutaMovimiento(int f, int c, Superficie superficie);
	abstract public boolean esComestible();
}
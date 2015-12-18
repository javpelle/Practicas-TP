package tp.pr3.logica;

/**
 * Clase abstracta que representa una celula del mundo. 
 */
abstract class Celula {
	protected boolean esComestible;
	
	abstract public Posicion ejecutaMovimiento(int f, int c, Superficie superficie);
	abstract public boolean esComestible();
}
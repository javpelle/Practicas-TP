package tp.pr1.logica;

/**
 * Clase que representa una célula del mundo. Consta de atributos privados que
 * contabilizan el número de turnos sin mover y el número de pasos dados de la
 * célula.
 */
abstract class Celula {
	protected boolean esComestible;
	
	abstract public Posicion ejecutaMovimiento(int f, int c, Superficie superficie);
	abstract public boolean esComestible();
}
package tp.pr1.logica;

/**
 * Clase que representa una célula del mundo. Consta de atributos privados que
 * contabilizan el número de turnos sin mover y el número de pasos dados de la
 * célula.
 */
abstract class Celula {
	private boolean movido;
	protected boolean esComestible;
	/**
	 * Contructora de la clase Celula
	 */
	public Celula(){	
		movido = false;
	}
	
	abstract public Posicion ejecutaMovimiento(int f, int c, Superficie superficie);
	abstract public boolean esComestible();

	/**
	 * Restauramos el atributo privado movido a false (célula no movida)
	 */
	public void setRestaurar (){ 
		this.movido = false;
	}
	
	/**
	 * Cambiamos el atributo privado movido a true (la célula ya ha sido movida.
	 */
	public void setMovido() {
		this.movido = true;
	}
	
	/**
	 * @return Devuelve true si la célula ya ha sido movida. False en otro caso.
	 */
	public boolean getMovido(){
		return this.movido;
	}
}
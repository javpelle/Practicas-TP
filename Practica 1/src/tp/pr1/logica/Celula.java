package tp.pr1.logica;

/**
 * Clase que representa una célula del mundo. Consta de atributos privados que
 * contabilizan el número de turnos sin mover y el número de pasos dados de la
 * célula.
 */
public class Celula {
	static final int MAX_PASOS_SIN_MOVER = 2;
	static final int PASOS_REPRODUCCION = 5;
	private int sinMovimientos, pasosDados;
	private boolean movido;
	
	/**
	 * Contructora de la clase Celula
	 */
	public Celula(){	
		sinMovimientos = 0;
		pasosDados = 0;
		movido = false;
	}

	/**
	 * Restauramos el atributo privado movido a false (célula no movida)
	 */
	public void setRestaurar (){ 
		this.movido = false;
	}
	
	/**
	 * Aumentamos en uno el número de pasos dados.
	 */
	public void setPasosDados() {
		this.pasosDados++;
	}
	
	/**
	 * Inicializamos a cero el número de pasos dados.
	 */
	public void setRestaurarPasosDados() {
		this.pasosDados = 0;
	}
	
	/**
	 * Aumentamos en uno el número de turnos en los que la célula no se ha movido.
	 */
	public void setSinMovimientos() {
		this.sinMovimientos++;
	}
	
	/**
	 * Cambiamos el atributo privado movido a true (la célula ya ha sido movida.
	 */
	public void setMovido() {
		this.movido = true;
	}
	
	/**
	 * @return Devuelve el número de pasos que faltan para la reproducción.
	 */
	public int getSinMover() {	
		return MAX_PASOS_SIN_MOVER - this.sinMovimientos;
	}
	
	/**
	 * @return Devuelve el número de pasos que faltan para la muerte de la célula por falta de movimiento.
	 */
	public int getReproduccion() {	
		return PASOS_REPRODUCCION - this.pasosDados;
	}
	
	/**
	 * @return Devuelve true si la célula ya ha sido movida. False en otro caso.
	 */
	public boolean getMovido(){
		return this.movido;
	}

	/**
	 * @return Devuelve true si la célula debe reproducirse. False en caso contrario.
	 */
	public boolean reproduccion() {	
		return PASOS_REPRODUCCION == this.pasosDados;
	}
	
	/**
	 * @return Devuelve true si la célula debe morir por falta de movimiento. False en otro caso.
	 */
	public boolean muerte() {	
		return MAX_PASOS_SIN_MOVER == this.sinMovimientos;
	}
}
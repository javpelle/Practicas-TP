package tp.pr3.logica;

import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.NumerosNegativos;

public class MundoComplejo extends Mundo {
	private int sim;
	private int com;
	private boolean esSimple;
	
	/**
	 * Constructora del mundo complejo
	 * @param f Filas
	 * @param c Columnas
	 * @param sim Numero de celulas simples
	 * @param com Numero de celulas complejas
	 * @throws ErrorDeInicializacion
	 * @throws NumerosNegativos
	 */
	
	public MundoComplejo (int f, int c, int sim, int com) throws ErrorDeInicializacion, NumerosNegativos {
		super(f, c);
		this.sim = sim;
		this.com = com;
		esSimple = false;
		if (f * c < (this.sim + this.com)) {
			throw new ErrorDeInicializacion();
		} else {
			inicializaMundo();
		}
	}
	
	/**
	 * LLena el mundo con el numero indicado de celulas simples y complejas
	 */
	
	public void inicializaMundo(){
		superficie.vaciar();
		for(int i = 0; i < sim; i++) {
			superficie.nuevaCelulaSimple();
		}
		for(int i = 0; i < com; i++) {
			superficie.nuevaCelulaCompleja();
		}
	}
	
	/**
	 * Devuelve False, ya que el mundo no es complejo
	 */
	
	public boolean esSimple(){
		return esSimple;
	}
}

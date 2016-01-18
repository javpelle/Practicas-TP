package tp.pr3.logica;

import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.NumerosNegativos;

public class MundoSimple extends Mundo {
	private int sim;
	private boolean esSimple;
	
	/**
	 * Constructora del mundo simple
	 * @param f Filas
	 * @param c Columnas
	 * @param sim Numero de celulas
	 * @throws ErrorDeInicializacion
	 * @throws NumerosNegativos
	 */
	
	public MundoSimple (int f, int c, int sim) throws ErrorDeInicializacion, NumerosNegativos {
		super(f, c);
		esSimple = true;
		this.sim = sim;
		if (f*c < this.sim){
			throw new ErrorDeInicializacion();
		} else {
			inicializaMundo();
		}
	}
	
	/** 
	 * LLena el mundo con el numero indicado de celulas (simples)
	 */
	
	public void inicializaMundo(){
		superficie.vaciar();
		for(int i = 0; i < sim; i++) {
			superficie.nuevaCelulaSimple();
		}
	}
	
	/**
	 * Devuelve true, ya que se trata de un mundo simple
	 */

	public boolean esSimple(){
		return esSimple;
	}
}

package tp.pr3.logica;

import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.NumerosNegativos;

public class MundoSimple extends Mundo {
	private int sim;
	private boolean esSimple;
	
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
	
	public void inicializaMundo(){
		superficie.vaciar();
		for(int i = 0; i < sim; i++) {
			superficie.nuevaCelulaSimple();
		}
	}

	public boolean esSimple(){
		return esSimple;
	}
}

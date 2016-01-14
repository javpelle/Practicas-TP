package tp.pr3.logica;

import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.NumerosNegativos;

public class MundoComplejo extends Mundo {
	private int sim;
	private int com;
	private boolean esSimple;
	
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
	
	public void inicializaMundo(){
		superficie.vaciar();
		for(int i = 0; i < sim; i++) {
			superficie.nuevaCelulaSimple();
		}
		for(int i = 0; i < com; i++) {
			superficie.nuevaCelulaCompleja();
		}
	}
	
	public boolean esSimple(){
		return esSimple;
	}
}

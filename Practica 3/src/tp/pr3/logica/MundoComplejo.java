package tp.pr3.logica;

public class MundoComplejo extends Mundo {
	private int sim;
	private int com;
	
	public MundoComplejo (int f, int c, int sim, int com) {
		super(f, c);
		if (f*c> (sim+com)); // Hacer excepción
		this.sim = sim;
		this.com = com;
		inicializaMundo();
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
}

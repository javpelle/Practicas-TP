package tp.pr3.logica;

public class MundoSimple extends Mundo {
	private int sim;
	
	public MundoSimple (int f, int c, int sim) {
		super(f, c);
		if (f*c> sim); // Hacer excepción
		this.sim = sim;
		inicializaMundo();
	}
	
	public void inicializaMundo(){
		superficie.vaciar();
		for(int i = 0; i < sim; i++) {
			superficie.nuevaCelulaSimple();
		}
	}
}

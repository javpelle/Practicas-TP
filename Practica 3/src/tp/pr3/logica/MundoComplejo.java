package tp.pr3.logica;

public class MundoComplejo extends Mundo {
	
	public void inicializaMundo(){
		superficie.vaciar();
		for(int i = 0; i < CELULAS_INICIO; i++) {
			superficie.nuevaCelula();
		}
	}
}

package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoVaciar extends Comando {
	public void ejecuta(Mundo mundo) {
		mundo.vaciar();
	}
	public Comando parsea(String[ ] cadenaComando) {
		if(cadenaComando[0].equals("vaciar")) {
			return new ComandoVaciar();
		} else {
			return null;
		}
	}
	public String textoAyuda() {
		return "VACIAR: crea un mundo vacío.\n";
	}
}

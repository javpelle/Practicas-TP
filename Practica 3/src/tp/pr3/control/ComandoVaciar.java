package tp.pr3.control;

import tp.pr3.logica.Mundo;

class ComandoVaciar extends Comando {
	
	/**
	 * Ejecuta la instruccion del mundo vaciar
	 */
	public void ejecuta(Mundo mundo) {
		mundo.vaciar();
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, y lo devuelve en tal caso.Si no, devuelve null.
	 */
	public Comando parsea(String[ ] cadenaComando) {
		if(cadenaComando[0].equals("vaciar")) {
			return this;
		} else {
			return null;
		}
	}
	
	/**
	 * @return Devuelve un string con la ayuda
	 */
	public String textoAyuda() {
		return "VACIAR: crea un mundo vacío.\n";
	}
}

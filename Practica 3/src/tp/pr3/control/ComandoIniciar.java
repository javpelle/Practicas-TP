package tp.pr3.control;

import tp.pr3.logica.Mundo;

class ComandoIniciar extends Comando {
	
	/**
	 * Ejecuta la intruccion del mundo, iniciar mundo
	 */
	public void ejecuta(Mundo mundo) {
		 mundo.iniciarMundo();
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, y lo devuelve en tal caso.Si no, devuelve null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if(cadenaComando[0].equals("iniciar")) {
			return this;
		} else {
			return null;
		}
	}
	
	/**
	 * @return Devuelve un string con la ayuda
	 */
	public String textoAyuda() {
		return "INICIAR: inicia una nueva simulación.\n";
	}

}

package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoVaciar extends Comando {
	
	/**
	 * Ejecuta la instruccion del mundo vaciar
	 */
	public void ejecuta(Mundo mundo) {
		mundo.vaciar();
	}
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

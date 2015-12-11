package tp.pr1.control;
import tp.pr1.logica.Mundo;

class ComandoPaso extends Comando {
	
	/**
	 * Ejecuta la instruccion del mundo encargada de avanzar un paso en la evolucion
	 */
	public void ejecuta(Mundo mundo) {
		mundo.evoluciona();
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, y lo devuelve en tal caso.Si no, devuelve null.
	 */
	public Comando parsea(String[ ] cadenaComando) {
		if(cadenaComando[0].equals("paso")) {
			return this;
		} else {
			return null;
		}
	}
	
	/**
	 * @return Devuelve un string con la ayuda
	 */
	public String textoAyuda() {
		return "PASO: Realiza un paso en la simulación.\n";
	}
}

package tp.pr1.control;
import tp.pr1.logica.Mundo;

class ComandoSalir extends Comando {
	
	/**
	 * Ejecuta la instruccion del mundo encargarda de finalizar la simulacion
	 */
	public void ejecuta(Mundo mundo) {
		mundo.setEsSimulacionTerminada();
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, y lo devuelve en tal caso.Si no, devuelve null.
	 */
	public Comando parsea(String[ ] cadenaComando) {
		if(cadenaComando[0].equals("salir") || cadenaComando[0].equals("exit")) {
			return this;
		} else {
			return null;
		}
	}
	
	/**
	 * @return Devuelve un string con la ayuda
	 */
	public String textoAyuda() {
		return "SALIR: Cierra la aplicación.\n";
	}
}

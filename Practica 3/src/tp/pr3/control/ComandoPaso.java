package tp.pr3.control;

class ComandoPaso extends Comando {
	
	/**
	 * Ejecuta la instruccion del controlador encargada de avanzar un paso en la evolucion
	 */
	public void ejecuta(Controlador controlador) {
		controlador.daUnPaso();
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

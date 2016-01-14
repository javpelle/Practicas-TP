package tp.pr3.control;


class ComandoSalir implements Comando {
	
	/**
	 * Ejecuta la instruccion del controlador encargarda de finalizar la simulacion
	 */
	public void ejecuta(Controlador controlador) {
		controlador.terminarSimulacion();
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

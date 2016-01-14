package tp.pr3.control;


class ComandoVaciar implements Comando {
	
	/**
	 * Ejecuta la instruccion del mundo vaciar
	 */
	public void ejecuta(Controlador controlador) {
		controlador.vaciarMundo();
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

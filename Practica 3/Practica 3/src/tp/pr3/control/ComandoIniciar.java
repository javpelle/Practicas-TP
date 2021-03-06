package tp.pr3.control;



class ComandoIniciar implements Comando {
	
	/**
	 * Ejecuta la intruccion del mundo, iniciar mundo
	 */
	public void ejecuta(Controlador controlador) {
		controlador.iniciarMundo();
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

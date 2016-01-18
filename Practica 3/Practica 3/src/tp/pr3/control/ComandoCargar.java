package tp.pr3.control;

class ComandoCargar implements Comando {
private String fichero;
	/**
	 * Ejecuta el metodo cargar de controlador
	 */
	public void ejecuta(Controlador controlador) {
		controlador.cargar(fichero);
	}
	/**
	 * omprueba si el array de string se corresponde con el comando, y lo devuelve en tal caso.Si no, devuelve null.
	 */
	public Comando parsea(String[ ] cadenaComando) {
		if (cadenaComando[0].equals("cargar") && cadenaComando.length == 2) {
			fichero = cadenaComando[1];
			return this;
		} else {
			return null;
		}
	}
	
	
	/**
	 * @return Devuelve un string con la ayuda del comando
	 */
	public String textoAyuda() {
		return "CARGAR <fichero>: Carga una partida del fichero descrito.\n" ;
	}	
}

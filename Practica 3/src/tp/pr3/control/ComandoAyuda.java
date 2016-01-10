package tp.pr3.control;


class ComandoAyuda extends Comando {
	

	/**
	 * Muestra la ayuda
	 */
	public void ejecuta(Controlador controlador) {
		System.out.print(ParserComandos.AyudaComandos());
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, y lo devuelve en tal caso.Si no, devuelve null.
	 */
	public Comando parsea(String[ ] cadenaComando) {
		if(cadenaComando[0].equals("ayuda") || cadenaComando[0].equals("help")) {
			return this;
		} else {
			return null;
		}
	}
	
	/**
	 * @return Devuelve un string con la ayuda
	 */
	public String textoAyuda() {
		return "AYUDA / HELP: Muestra esta ayuda.\n";
	}
}

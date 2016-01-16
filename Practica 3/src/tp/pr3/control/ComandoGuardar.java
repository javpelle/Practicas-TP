package tp.pr3.control;

class ComandoGuardar implements Comando {
	private String fichero;
	
	public void ejecuta(Controlador controlador) {
		controlador.guardar(fichero);
	}
	
	public Comando parsea(String[ ] cadenaComando) {
		if (cadenaComando[0].equals("guardar") && cadenaComando.length == 2) {
			fichero = cadenaComando[1];
			return this;
		} else {
			return null;
		}
	}
	
	public String textoAyuda() {
		return "GUARDAR <fichero>: Guarda una partida en el fichero descrito.\n" ;
	}
}

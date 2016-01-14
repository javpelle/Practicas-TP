package tp.pr3.control;

class ComandoCargar implements Comando {
private String fichero;
	
	public void ejecuta(Controlador controlador) {
		controlador.cargar(fichero);
	}
	
	public Comando parsea(String[ ] cadenaComando) {
		if (cadenaComando[0] == "cargar" && cadenaComando.length == 2) {
			fichero = cadenaComando[1];
			return this;
		} else {
			return null;
		}
	}
	
	public String textoAyuda() {
		return "CARGAR <fichero>: Carga una partida del fichero descrito.\n" ;
	}	
}

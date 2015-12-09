package tp.pr1.control;

public class ParserComandos {
	private static Comando[] comandos;
	
	/**
	 * Recorremos el array de comando monstrando la ayuda de cada comando
	 * @return Salida con la ayuda de cada comando
	 */
	static public String AyudaComandos() {
		String ayuda = "";
		for (Comando c: comandos) {
			ayuda += c.textoAyuda();
		}
		return ayuda;
	}
		
	static public Comando parseaComando(String[ ] cadenas) {
		Comando devolver = null;
		if (cadenas.equals("paso")) {
			devolver = new ComandoPaso();
		}
		return devolver;
			
		}
}


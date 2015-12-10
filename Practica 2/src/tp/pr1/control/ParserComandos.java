package tp.pr1.control;

public class ParserComandos {
	private static Comando[] comandos = {
		new ComandoAyuda(),
		new ComandoCrearCelulaCompleja(0, 0),
		new ComandoCrearCelulaSimple(0, 0),
		new ComandoEliminarCelula(0, 0),
		new ComandoIniciar(),
		new ComandoPaso(),
		new ComandoSalir(),
		new ComandoVaciar(),
	};
	
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
	
	/**
	 * Recorremos el array de comandos y buscamos si algún comando se corresponde
	 * con el string introducido.
	 * @param cadenas caracteres introducidos por consola
	 * @return Devolvemos el comando correspondiente. Null en caso contrario.
	 */
	static public Comando parseaComando(String[ ] cadenas) {
		Comando devolver = null;
		for (Comando c: comandos) {
			devolver = c.parsea(cadenas);
			if (devolver != null) {
				return devolver;
			}
		}
		return devolver;
	}
}


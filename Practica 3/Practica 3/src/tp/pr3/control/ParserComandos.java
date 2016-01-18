package tp.pr3.control;

import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.FormatoNumericoIncorrecto;
import tp.pr3.exceptions.NumerosNegativos;

/**
 * Clase encargada de corresponer a los Strings introducidos por consola 
 * con sus comandos correspondientes.
 * Ademas muestra la ayuda.
 */
public class ParserComandos {
	private static Comando[] comandos = {
		new ComandoAyuda(),
		new ComandoCrearCelula(),
		new ComandoEliminarCelula(),
		new ComandoIniciar(),
		new ComandoPaso(),
		new ComandoSalir(),
		new ComandoVaciar(),
		new ComandoJugar(),
		new ComandoGuardar(),
		new ComandoCargar()		
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
	 * @throws NumerosNegativos 
	 * @throws ErrorDeInicializacion 
	 */
	static public Comando parseaComando(String[ ] cadenas) throws FormatoNumericoIncorrecto, ErrorDeInicializacion, NumerosNegativos {
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


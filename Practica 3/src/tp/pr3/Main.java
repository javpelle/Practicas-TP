package tp.pr3;

import java.util.Scanner;
import tp.pr3.control.Controlador;
import tp.pr3.logica.Mundo;

/**
 * Ess la clase que contiene el m�todo main de la pr�ctica. Crea Mundo y Controlador invocando a sus
 * constructoras, e invoca al m�todo realizaSimulacion() de la clase Controlador.
 */
public class Main {
	/**
	 * Crea Mundo y Controlador e invoca a realizaSimulacion()
	 * @param args
	 */
	public static void main(String[] args) {
		Mundo mundo = new Mundo();
		Scanner in = new Scanner(System.in);
		Controlador ejecucion = new Controlador(mundo,in);
		ejecucion.realizaSimulacion();
		System.out.print("Adios!");
	}
}

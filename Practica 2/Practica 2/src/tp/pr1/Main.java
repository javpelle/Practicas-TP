package tp.pr1;

import java.util.Scanner;
import tp.pr1.control.Controlador;
import tp.pr1.logica.Mundo;

/**
 * Es la clase que contiene el método main de la práctica. Crea Mundo y Controlador invocando a sus
 * constructoras, e invoca al método realizaSimulacion() de la clase Controlador.
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

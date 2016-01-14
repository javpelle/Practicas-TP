package tp.pr3;

import java.util.Scanner;

import tp.pr3.control.Controlador;
import tp.pr3.exceptions.ComandoError;
import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.NumerosNegativos;
import tp.pr3.logica.Mundo;
import tp.pr3.logica.MundoComplejo;

/**
 * Es la clase que contiene el m�todo main de la pr�ctica. Crea Mundo y Controlador invocando a sus
 * constructoras, e invoca al m�todo realizaSimulacion() de la clase Controlador.
 */
public class Main {
	/**
	 * Crea Mundo y Controlador e invoca a realizaSimulacion()
	 * @param args
	 * @throws ComandoError 
	 * @throws ErrorDeInicializacion 
	 */
	public static void main(String[] args) {
		try {
			Mundo mundo = new MundoComplejo(2,2,2,0);
			Scanner in = new Scanner(System.in);
			Controlador ejecucion = new Controlador(mundo,in);
			ejecucion.realizaSimulacion();
		} catch (ErrorDeInicializacion e) {
			System.out.println(e);
		} catch (NumerosNegativos f) {
			System.out.println(f);
		}
		System.out.print("Adios!");
	}
}

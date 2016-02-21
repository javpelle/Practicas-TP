package tp.pr3;

import java.util.Scanner;

import tp.pr3.control.Controlador;
import tp.pr3.exceptions.ComandoError;
import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.NumerosNegativos;
import tp.pr3.logica.Mundo;
import tp.pr3.logica.MundoComplejo;
import tp.pr3.ventanas.HolaMundoSwing;

/**
 * Es la clase que contiene el método main de la práctica. Crea Mundo y Controlador invocando a sus
 * constructoras, e invoca al método realizaSimulacion() de la clase Controlador.
 */
public class Main {
	/**
	 * Crea Mundo y Controlador e invoca a realizaSimulacion()
	 * @param args
	 * @throws NumerosNegativos 
	 * @throws ComandoError 
	 * @throws ErrorDeInicializacion 
	 */
	public static void main(String[] args) throws ErrorDeInicializacion, NumerosNegativos {
		
		Mundo mundo = new MundoComplejo(2,2,2,0);
		Scanner in = new Scanner(System.in);
		Controlador ejecucion = new Controlador(mundo,in);
		ejecucion.realizaSimulacion();
		in.close();
		System.out.print("Adios!");
		
	}
}

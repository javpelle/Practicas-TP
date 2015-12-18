package tp.pr3.control;

import tp.pr3.logica.Mundo;

import java.util.Scanner;

/**
 * Esta clase interpreta los posibles comandos cuyo m�todo principal es 
 * public void realizaSimulacion().
 * Por cada lectura de comando correcta se invoca el m�todo correspondiente.
 */
public class Controlador {
	private Mundo mundo;
	private Scanner in;
	
	/**
	 * Constructora de la clase Controlador, sus atributos private Mundo, private Scanner
	 * son inicializados a los atributos pasados como par�metro.
	 * @param mundo par�metro de la clase Mundo.
	 * @param in par�metro de la clase Scanner.
	 */
	public Controlador(Mundo mundo, Scanner in){
		this.mundo = mundo;
		this.in = in;
	}
	
	/**
	 * Consta de un bucle que lee comandos e invoca distintos m�todos en funci�n de dichos comandos.
	 */
	public void realizaSimulacion() {
		while (mundo.esSimulacionTerminada()) {
			mundo.pintarMundo();
			System.out.print("Comando > ");
			String line = in.nextLine();
			line = line.toLowerCase();
			String[] words = line.split(" ");
			Comando comando = ParserComandos.parseaComando(words);
			if (comando != null){
				comando.ejecuta(this.mundo);
			} else {
				System.out.print("El comando introducido no es v�lido. \n");
			}
		}	
	}
}

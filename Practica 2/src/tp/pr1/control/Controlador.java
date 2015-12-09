package tp.pr1.control;

import tp.pr1.logica.Mundo;

import java.util.Scanner;

/**
 * Esta clase interpreta los posibles comandos cuyo método principal es 
 * public void realizaSimulacion().
 * Por cada lectura de comando correcta se invoca el método correspondiente.
 */
public class Controlador {
	private Mundo mundo;
	private Scanner in;
	
	/**
	 * Constructora de la clase Controlador, sus atributos private Mundo, private Scanner
	 * son inicializados a los atributos pasados como parámetro.
	 * @param mundo parámetro de la clase Mundo.
	 * @param in parámetro de la clase Scanner.
	 */
	public Controlador(Mundo mundo, Scanner in){
		this.mundo = mundo;
		this.in = in;
	}
	
	/**
	 * Consta de un bucle que lee comandos e invoca distintos métodos en función de dichos comandos.
	 */
	public void realizaSimulacion() {		
		Comando comando = ParserComandos.parseaComando(words);
		if (comando!=null) comando.ejecuta(this.mundo);
	}
}

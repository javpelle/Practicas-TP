package tp.pr3.control;

import tp.pr3.exceptions.ComandoError;
import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.FormatoNumericoIncorrecto;
import tp.pr3.exceptions.IndicesFueraDeRango;
import tp.pr3.exceptions.NumerosNegativos;
import tp.pr3.exceptions.PalabraIncorrecta;
import tp.pr3.exceptions.PosicionVacia;
import tp.pr3.logica.Mundo;
import tp.pr3.logica.MundoComplejo;
import tp.pr3.logica.MundoSimple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Esta clase interpreta los posibles comandos cuyo método principal es 
 * public void realizaSimulacion().
 * Por cada lectura de comando correcta se invoca el método correspondiente.
 */
public class Controlador {
	boolean simulacionTerminada;
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
		this.simulacionTerminada = false;
	}
	
	/**
	 * Consta de un bucle que lee comandos e invoca distintos métodos en función de dichos comandos.
	 * @throws ComandoError 
	 */
	public void realizaSimulacion() {
		while (!simulacionTerminada) {
			mundo.pintarMundo();
			System.out.print("Comando > ");
			String line = in.nextLine();
			line = line.toLowerCase();
			String[] words = line.split(" ");
			try {
				Comando comando = ParserComandos.parseaComando(words);
				if (comando != null) {
				comando.ejecuta(this);
				} else {
					throw new ComandoError();
				}
			} catch(ComandoError e) {
				System.out.println(e);
			} catch (FormatoNumericoIncorrecto e) {
				System.out.println(e);
			} catch (ErrorDeInicializacion e){
				System.out.println(e);
			} catch (NumerosNegativos f) {
				System.out.println(f);
			}
		}	
	}
	
	/**
	 * Ejecta un paso del mundo
	 */
	
	public void daUnPaso(){
		mundo.evoluciona();
	}
	
	/**
	 * Se cierra el programa
	 */
	
	public void terminarSimulacion(){
		simulacionTerminada = true;
	}
	
	/**
	 * Elimina la celula en la fila y columna correspondiente
	 * @param f fila
	 * @param c columna
	 */
	
	public void eliminarCelula(int f,int c) {
		try {
			mundo.eliminarCelula(f, c);
		} catch (IndicesFueraDeRango e) {
			System.out.print(e);
		} catch (PosicionVacia e) {
			System.out.print(e);
		}
	}
	
	/**
	 * Vacia el mundo del controlador
	 */
	
	public void vaciarMundo(){
		mundo.vaciar();
	}
	
	/**
	 * Inicializa el mundo con unos valors por defecto definidos en el método main
	 */
	
	public void iniciarMundo(){
		mundo.inicializaMundo();
	}
	
	/**
	 * Establece como mundo del controlador el recibido por parametro
	 * @param mundo Mundo especificado por el usuario 
	 */
	
	public void juega(Mundo mundo){
		this.mundo = mundo;
	}
	
	/**
	 * Se crea en el mundo una nueva celula en la fila y columna correspondiente
	 * @param f Fila
	 * @param c Columna
	 * @param esSimple Tipo de célula
	 */
	public void nuevaCelula(int f, int c, boolean esSimple) {
		if (esSimple) {
			mundo.nuevaCelulaSimple(f, c);
		} else {
			mundo.nuevaCelulaCompleja(f, c);
		}
	}
	
	/**
	 * Se carga el mundo del archivo en el atributo mundo del controlador
	 * @param archivo Archivo de texto donde se encuentra la información del mundo a cargar
	 */


	public void cargar(String archivo)  {
		File ficheroEntrada = new File(archivo);
		Scanner entrada = null;
		try {
			if (!ficheroEntrada.exists()) {
				throw new FileNotFoundException();
			} else {
				entrada = new Scanner(ficheroEntrada);
				String complejidad;
				complejidad = entrada.nextLine();
				int f, c;
				try {
					f = Integer.parseInt(entrada.nextLine());
					c = Integer.parseInt(entrada.nextLine());
				} catch (NumberFormatException e) {
					throw new PalabraIncorrecta ();
				}				
				Mundo mundoAux = null;
				if (complejidad.equals("simple")) {
					mundoAux = new MundoSimple(f,c,0);
				} else if (complejidad.equals("complejo")) {
					mundoAux = new MundoComplejo(f,c,0,0);
				} else {
					throw new PalabraIncorrecta();
				}
				mundoAux.cargar(entrada);
				this.mundo = mundoAux;
			}
		} catch (FileNotFoundException e) {
			System.out.print(e + " Archivo no encontrado...\n");
		} catch (IOException | NoSuchElementException e) {
			System.out.print(e + ". Error en la lectura del archivo...\n");
		} catch (PalabraIncorrecta e) {
			System.out.print(e);
		} catch (ErrorDeInicializacion | NumerosNegativos e) {
			System.out.print(e);
		} catch (IndicesFueraDeRango e) {
			System.out.print(e);
		} finally {
			if (ficheroEntrada.exists()) {
				entrada.close();
			}
		}
	}
	
	/**
	 * Se guarda en un archivo el mundo actual de controlador
	 * @param archivo Arhivo de texto donde se desea almacenar la informacion del mundo
	 */
	
	public void guardar(String archivo) {
		try {
			File ficheroSalida = new File (archivo);
			FileWriter salida = new FileWriter (ficheroSalida);
			mundo.guardar(salida);
			salida.close();
		} catch (IOException e) {
			System.out.print(e);
		}
		
	}
	
	/**
	 * Indíca qué tipo de mundo tiene controlador
	 * @return True si el mundo es Simple. False en caso contrario.
	 */
	
	public boolean getEsMundoSimple() {
		return mundo.esSimple();
	}
	
	/**
	 * Comprueba si la posición (f,c) es válida
	 * @param f Fila 
	 * @param c Columna
	 * @return True si la posición es válida.False si no
	 */
	
	public boolean getDentro(int f, int c) {
		return mundo.dentro(f, c);
	}
	
	/**
	 * Comprueba si hay una celula en la posición indicada
	 * @param f Fila
	 * @param c Columna 
	 * @return True si no hay celula.False en caso contrario.
	 */
	
	public boolean getCelulaNula(int f, int c) {
		return mundo.celulaNula(f, c);
	}
}

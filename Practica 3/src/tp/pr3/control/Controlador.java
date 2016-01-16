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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
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
	
	public void daUnPaso(){
		mundo.evoluciona();
	}
	
	public void terminarSimulacion(){
		simulacionTerminada = true;
	}
	
	public void eliminarCelula(int f,int c) {
		try {
			mundo.eliminarCelula(f, c);
		} catch (IndicesFueraDeRango e) {
			System.out.print(e);
		} catch (PosicionVacia e) {
			System.out.print(e);
		}
	}
	
	public void vaciarMundo(){
		mundo.vaciar();
	}
	
	public void iniciarMundo(){
		mundo.inicializaMundo();
	}
	
	public void juega(Mundo mundo){
		this.mundo = mundo;
	}
	
	public void nuevaCelula(int f, int c, boolean esSimple) {
		if (esSimple) {
			mundo.nuevaCelulaSimple(f, c);
		} else {
			mundo.nuevaCelulaCompleja(f, c);
		}
	}


	public void cargar(String archivo) {
		try {
			File ficheroEntrada = new File(archivo);
			if (!ficheroEntrada.exists()) {
				throw new FileNotFoundException();
			} else {
				Scanner entrada = new Scanner(ficheroEntrada);
				String complejidad;
				complejidad = entrada.nextLine();
				int f = entrada.nextInt();
				int c = entrada.nextInt();
				Mundo mundoAux = null;
				if (complejidad.equals("simple")) {
					mundoAux = new MundoSimple(f,c,0);
				} else if (complejidad.equals("complejo")) {
					mundoAux = new MundoComplejo(f,c,0,0);
				} else {
					throw new PalabraIncorrecta();
				}
				mundo.cargar(entrada);
				this.mundo = mundoAux;
			}
		} catch (FileNotFoundException e) {
			System.out.print(e);
		} catch (IOException e) {
			System.out.print(e);
		} catch (PalabraIncorrecta e) {
			System.out.print(e);
		} catch (ErrorDeInicializacion e) {
			System.out.print(e);
		} catch (NumerosNegativos e) {
			System.out.print(e);
		} finally {
			entrada.close();
		}
	}
	
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
	
	public boolean getEsMundoSimple() {
		return mundo.esSimple();
	}
	
	public boolean getDentro(int f, int c) {
		return mundo.dentro(f, c);
	}
	
	public boolean getCelulaNula(int f, int c) {
		return mundo.celulaNula(f, c);
	}
}

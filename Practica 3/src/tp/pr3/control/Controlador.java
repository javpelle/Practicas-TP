package tp.pr3.control;

import tp.pr3.exceptions.ComandoError;
import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.FormatoNumericoIncorrecto;
import tp.pr3.exceptions.IndicesFueraDeRango;
import tp.pr3.exceptions.NumeroNoValido;
import tp.pr3.exceptions.NumerosNegativos;
import tp.pr3.exceptions.PosicionNoVacia;
import tp.pr3.exceptions.PosicionVacia;
import tp.pr3.logica.Mundo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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


	public void cargar(String archivo){
		try {
			File file = new File(archivo);
			if (!file.exists()) {
				throw new FileNotFoundException();
			} else {
				Scanner sc = new Scanner(file);
				String complejidad = sc.nextLine();
			}
			
		} catch (FileNotFoundException e){
			System.out.print(e);
		}  catch (IOException e){
			System.out.print(e);
		}
	}
	
	public void guardar(String archivo) {
		
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

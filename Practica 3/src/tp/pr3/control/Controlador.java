package tp.pr3.control;

import tp.pr3.exceptions.ComandoError;
import tp.pr3.exceptions.IndicesFueraDeRango;
import tp.pr3.exceptions.NumeroNoValido;
import tp.pr3.exceptions.PosicionVacia;
import tp.pr3.logica.Mundo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Esta clase interpreta los posibles comandos cuyo m�todo principal es 
 * public void realizaSimulacion().
 * Por cada lectura de comando correcta se invoca el m�todo correspondiente.
 */
public class Controlador {
	boolean simulacionTerminada;
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
		this.simulacionTerminada = false;
	}
	
	/**
	 * Consta de un bucle que lee comandos e invoca distintos m�todos en funci�n de dichos comandos.
	 * @throws ComandoError 
	 */
	public void realizaSimulacion() {
		while (!simulacionTerminada) {
			mundo.pintarMundo();
			System.out.print("Comando > ");
			String line = in.nextLine();
			line = line.toLowerCase();
			String[] words = line.split(" ");
			Comando comando = ParserComandos.parseaComando(words);
			try {
				if (comando != null) {
				comando.ejecuta(this);
				} else {
					throw new ComandoError();
				}
			} catch(ComandoError e) {
				System.out.println(e);
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
	
	public void nuevaCelula(int f, int c) {
		// vemos si el mundo es simple o compuesto
		if(this.mundo.esSimple()) {
			this.mundo.nuevaCelulaSimple(f, c);			
		} else {
			String entero = simpleOComplejo();
			if (entero == "2") {
				this.mundo.nuevaCelulaCompleja(f, c);
			} else if (entero == "1") {
				this.mundo.nuevaCelulaSimple(f, c);
			}
		}
	}
	


	private String simpleOComplejo() {
		String celula;
		System.out.print("De que tipo: Compleja (1) o Simple (2): ");
		try {
			celula = in.nextLine();
			if (celula != "1" && celula != "2") {
				throw  new NumeroNoValido();
			}
		} catch (NumeroNoValido e){
			System.out.println(e);
			return "0";
		}
		return celula;		
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
	
	public void guardar(String archivo){
		
	}

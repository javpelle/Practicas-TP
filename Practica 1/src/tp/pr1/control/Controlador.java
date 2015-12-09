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
		String s = "";
		int f = 0, c = 0;
		mundo.pintarMundo();
		
		while (!s.equals("salir")) {
			System.out.print("Introduzca un comando: ");
			s = in.next();
			s = s.toLowerCase();
			if (s.equals("crearcelula") || s.equals("eliminarcelula")) {
				f = in.nextInt() - 1;
				c = in.nextInt() - 1; 
			}
			// avanza a la siguiente línea de lectura de consola para evitar problemas con buffer
			in.nextLine(); 
			switch(s) {
			case "paso":
				mundo.evoluciona();
				mundo.pintarMundo();
				break;
			case "iniciar":
				mundo.iniciarMundo();
				mundo.pintarMundo();
				break;
			case "crearcelula":
				if(!mundo.nuevaCelula(f, c)) {
					// Si la celula no pudo ser insertada,mostramos error.
					System.out.print("Error. Introdujo coordenadas erróneas o la casilla ya está ocupada por una célula.\n");
				} else {
					System.out.print("Celula creada en (" + (f + 1) + "," + (c + 1) + ")\n");
				}
				mundo.pintarMundo();
				break;
			case "eliminarcelula":
				if (!mundo.eliminarCelula(f, c)) {
					// Si no se pudo eliminar la célula, mostramos error.
					System.out.print("Error. Introdujo coordenadas erróneas o la casilla está libre.\n");
				} else {
					System.out.print("Celula eliminada en (" + (f + 1) + "," + (c + 1) + ")\n");
				}
				mundo.pintarMundo();
				break;
			case "ayuda":
			case "help":
				System.out.print("PASO: Realiza un paso en la simulación.\n");
				System.out.print("AYUDA: Muestra esta ayuda.\n");
				System.out.print("SALIR: Cierra la aplicación.\n");
				System.out.print("INICIAR: Inicia una nueva simulación.\n");
				System.out.print("VACIAR: crea un mundo vacío.\n");
				System.out.print("CREARCELULA F C: crea una nueva celula en la posición (f,c) si es posible.\n");
				System.out.print("ELIMINARCELULA F C: elimina una celula de la posición (f,c) si es posible.\n");
				break;
			case "vaciar":
				mundo.vaciar();	
				mundo.pintarMundo();
				break;
			case "salir":
				System.out.print("Hasta la próxima!\n");
				break;
				
			default:
				System.out.print("No se ha introducido un comando válido.\n");
				
			}				
		}
	}
}

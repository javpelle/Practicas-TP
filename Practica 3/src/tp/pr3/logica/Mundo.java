package tp.pr3.logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tp.pr3.exceptions.IndicesFueraDeRango;
import tp.pr3.exceptions.NumerosNegativos;
import tp.pr3.exceptions.PosicionVacia;

/**
 * Consta de un atributo private superficie de dimensiones establecidas por constantes y
 * es inicializada con un número de células también establecida por una constante.
 * Se encarga de avanzar un paso en la evolución del mundo de células: Está al cuidado de
 * que las células, se muevan, reproduzcan o mueran si procede.
 * También se encarga entre otras cosas de crear o eliminar una célula en una posición dada.
 */
public abstract class Mundo {
	protected Superficie superficie;
	protected int filas;
	protected int columnas;
	private boolean[][] booleanMatrix;
	
	/**
	 * Constructora por defecto de la clase Mundo, inicializa la superficie a null 
	 * y el número de columnas y filas a 0 
	 */
	public Mundo() {
		this.filas = 0;
		this.columnas = 0;
		this.superficie = null;
		this.booleanMatrix = null;
	}
	
	/**
	 * Constructora con parametros (filas y columnas)  de la clase Mundo 
	 * @throws NumerosNegativos 
	 */
	public Mundo(int f, int c) throws NumerosNegativos {
		if(f <= 0 || c <= 0) {
			throw new NumerosNegativos();
		} else {
			this.filas = f;
			this.columnas = c;
			this.superficie = new Superficie(filas, columnas);
			booleanMatrix = new boolean [this.filas][this.columnas];
			for (int i = 0; i < this.filas; i++) {
				for (int j = 0; i < this.columnas; i++) {
					booleanMatrix[i][j] = false;
				}
			}
		}
	}
	
	abstract public void inicializaMundo();
	abstract public boolean esSimple();
		
	/**
	 * Avanza un paso en la evolución del Mundo, trata de mover cada célula a una posible
	 * posición vecina vacía, asegurándose de mover cada célula una única vez por
	 * ejecución del método. Además se encarga de la reproduciión o muerte de una célula si
	 *  procediera.
	 */	
	public void evoluciona() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (!superficie.celulaNula(i, j) && !booleanMatrix[i][j]) {					
					Posicion p = superficie.ejecutaMovimiento(i, j);
					if (p != null) {
						booleanMatrix[p.getF()][p.getC()] = true;
					}
				}
			}
		}
		// Una vez acabada la ejecución restauramos los booleanos de las céulas que nos 
		// indican si han sido movidas o no.
		restaurarMovimiento();
	}
	
	/**
	 * Cambia cada booleano de la matriz de booleanos a false;
	 */
	void restaurarMovimiento() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				booleanMatrix[i][j] = false;
			}
		}
	}
	
	/**
	 * Vacía el mundo, y lo vuelve a iniciar con un número de células
	 * dado por la constante inicial.
	 */

	
	/**
	 * @return true si dentro
	 */
	public boolean dentro(int f, int c) {
		return (f >= 0 && f < filas) && (c >= 0 && c < columnas);
	}
	
	/**
	 * Inserta una nueva célula simple en la posición (f, c) del array 
	 * @param f coordenada i del mundo de células.
	 * @param c coordenada j del mundo de células.
	 */
	public void nuevaCelulaSimple (int f, int c) {
			superficie.insertarCelulaSimple(f ,c);
			System.out.print("Celula simple creada en la posicion (" + (f + 1) + "," + (c + 1) + ")\n");
	}
	
	/**
	 * Inserta una nueva célula compleja en la posición (f, c) del array 
	 * @param f coordenada i del mundo de células.
	 * @param c coordenada j del mundo de células. 
	 */
	public void nuevaCelulaCompleja (int f, int c) {
			superficie.insertarCelulaCompleja(f ,c);
			System.out.print("Celula compleja creada en la posicion (" + (f + 1) + "," + (c + 1) + ")\n");
	}
	
	/**
	 * Elimina una  célula en la posición (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, esté ocupada.
	 * @param f coordenada i del mundo de células.
	 * @param c coordenada j del mundo de células.
	 * @return Devolvemos true si logramos borrar la célula. False en otro caso.
	 * @throws IndicesFueraDeRango 
	 * @throws PosicionVacia 
	 */
	public void eliminarCelula (int f, int c) throws IndicesFueraDeRango, PosicionVacia {
		if (!dentro(f, c)) {
			throw new IndicesFueraDeRango();
		} else if (superficie.celulaNula(f ,c)) {
			throw new PosicionVacia();
		} else {
			superficie.eliminarCelula(f ,c);
			System.out.print("Celula eliminada en la posicion (" + (f + 1) + "," + (c + 1) + ")\n");
		}
	}
	
	/**
	 * Vacía el mundo.
	 */
	public void vaciar() {
		superficie.vaciar();
	}
	
	/**
	 * Muestra la superfice por consola.
	 */
	public void pintarMundo(){
		superficie.pintarSuperficie();
	}
	
	public void cargar(BufferedReader entrada) throws IOException {
		
		
	}
	
	public void guardar(FileWriter salida) throws IOException {
		if (esSimple()) {
			salida.write("simple\r\n");			
		} else {
			salida.write("complejo\r\n");
		}
		salida.write(this.filas + "\r\n");
		salida.write(this.columnas + "\r\n");
		superficie.guardar(salida);
	}
	
	public boolean celulaNula(int f, int c) {
		return superficie.celulaNula(f,c);
	}
}
	

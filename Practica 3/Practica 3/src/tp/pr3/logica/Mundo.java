package tp.pr3.logica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.exceptions.IndicesFueraDeRango;
import tp.pr3.exceptions.NumerosNegativos;
import tp.pr3.exceptions.PalabraIncorrecta;
import tp.pr3.exceptions.PosicionVacia;

/**
 * Consta de un atributo private superficie de dimensiones establecidas por constantes y
 * es inicializada con un n�mero de c�lulas tambi�n establecida por una constante.
 * Se encarga de avanzar un paso en la evoluci�n del mundo de c�lulas: Est� al cuidado de
 * que las c�lulas, se muevan, reproduzcan o mueran si procede.
 * Tambi�n se encarga entre otras cosas de crear o eliminar una c�lula en una posici�n dada.
 */
public abstract class Mundo {
	protected Superficie superficie;
	protected int filas;
	protected int columnas;
	private boolean[][] booleanMatrix;
	
	/**
	 * Constructora por defecto de la clase Mundo, inicializa la superficie a null 
	 * y el n�mero de columnas y filas a 0 
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
				for (int j = 0; j < this.columnas; j++) {
					booleanMatrix[i][j] = false;
				}
			}
		}
	}
	
	abstract public void inicializaMundo();
	abstract public boolean esSimple();
		
	/**
	 * Avanza un paso en la evoluci�n del Mundo, trata de mover cada c�lula a una posible
	 * posici�n vecina vac�a, asegur�ndose de mover cada c�lula una �nica vez por
	 * ejecuci�n del m�todo. Adem�s se encarga de la reproducii�n o muerte de una c�lula si
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
		// Una vez acabada la ejecuci�n restauramos los booleanos de las c�ulas que nos 
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
	 * Vac�a el mundo, y lo vuelve a iniciar con un n�mero de c�lulas
	 * dado por la constante inicial.
	 */

	
	/**
	 * @return true si dentro
	 */
	public boolean dentro(int f, int c) {
		return (f >= 0 && f < filas) && (c >= 0 && c < columnas);
	}
	
	/**
	 * Inserta una nueva c�lula simple en la posici�n (f, c) del array 
	 * @param f coordenada i del mundo de c�lulas.
	 * @param c coordenada j del mundo de c�lulas.
	 */
	public void nuevaCelulaSimple (int f, int c) {
			superficie.insertarCelulaSimple(f ,c);
			System.out.print("Celula simple creada en la posicion (" + (f + 1) + "," + (c + 1) + ")\n");
	}
	
	/**
	 * Inserta una nueva c�lula compleja en la posici�n (f, c) del array 
	 * @param f coordenada i del mundo de c�lulas.
	 * @param c coordenada j del mundo de c�lulas. 
	 */
	public void nuevaCelulaCompleja (int f, int c) {
			superficie.insertarCelulaCompleja(f ,c);
			System.out.print("Celula compleja creada en la posicion (" + (f + 1) + "," + (c + 1) + ")\n");
	}
	
	/**
	 * Elimina una  c�lula en la posici�n (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, est� ocupada.
	 * @param f coordenada i del mundo de c�lulas.
	 * @param c coordenada j del mundo de c�lulas.
	 * @return Devolvemos true si logramos borrar la c�lula. False en otro caso.
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
	 * Vac�a el mundo.
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
	
	/**
	 * LLama al m�todo cargar de la superficie
	 * @param entrada Scanner de lectura
	 * @throws IOException
	 * @throws PalabraIncorrecta
	 * @throws IndicesFueraDeRango
	 */
	
	public void cargar(Scanner entrada) throws IOException, PalabraIncorrecta, IndicesFueraDeRango {
		superficie.cargar(entrada);
		
	}
	
	/**
	 * Escribe en el arhivo la informaci�n principal del mundo (filas, columnas, tipo de Mundo)
	 * @param salida 
	 * @throws IOException
	 */
	
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
	
	/**
	 * Indica si hay o no una c�lula en la posici�n indicada
	 * @param f Fila
	 * @param c Columna
	 * @return True si la posici�n est� vacia. False en caso contrario
	 */
	
	public boolean celulaNula(int f, int c) {
		return superficie.celulaNula(f,c);
	}
}
	

package tp.pr3.logica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.exceptions.IndicesFueraDeRango;
import tp.pr3.exceptions.PalabraIncorrecta;

/**
 * Esta superficie cuenta con los atributos privados filas, columnas (enteros),
 * y una matriz de células.
 * Consta de distintos atributos desde los que se puede modificar dicha matriz.
 */
public class Superficie {
	private Celula [][] superficie;
	private int filas;
	private int columnas;

	/**
	 * Constructora encargada de inicializar Superficie con todos sus elementos a null.
	 * @param nf Dimensión i de la matriz.
	 * @param nc Dimensión j de la matriz.
	 */
	public Superficie(int nf, int nc){
		this.filas = nf;
		this.columnas = nc;
		this.superficie = new Celula[nf][nc];
		for (int i = 0; i < nf; i++) {
			for (int j = 0; j < nc; j++) {
				this.superficie[i][j] = null; 
			}
		}
	}
	
	/**
	 * Realiza el movimiento correspondiente de la celula de la posicion (F, C)	
	 * @param f Fila en la que se encuentra la célula
	 * @param c Columna en la que se encuentra la célula
	 * @return Posicion a la que se ha movido; null si no se mueve
	 */
	public Posicion ejecutaMovimiento(int f, int c){
		Posicion p = superficie[f][c].ejecutaMovimiento(f, c, this);
		return p;
	}
	
	/**
	 * Cada célula de la matriz pasa a null
	 */
	public void vaciar() {
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) superficie[i][j] = null; 
		}
	}
	
	/**
	 * Inserta una célula simple en una posición aleatoria.
	 */
	public void nuevaCelulaSimple() {  
		int aleatorioFila, aleatorioColumna;
		do {
			aleatorioFila = (int) (Math.random() * this.filas);
			aleatorioColumna = (int) (Math.random() * this.columnas);
		} while (this.superficie[aleatorioFila][aleatorioColumna] != null);
		this.superficie[aleatorioFila][aleatorioColumna] = new CelulaSimple();		
	}
	
	/**
	 * Inserta una célula compleja en una posición aleatoria.
	 */
	public void nuevaCelulaCompleja() {
		int aleatorioFila, aleatorioColumna;
		do {
			aleatorioFila = (int) (Math.random() * this.filas);
			aleatorioColumna = (int) (Math.random() * this.columnas);
		} while (this.superficie[aleatorioFila][aleatorioColumna] != null);
		this.superficie[aleatorioFila][aleatorioColumna] = new CelulaCompleja();
	}
	
	/**
	 * Crea una nueva célula simple en la posición indicada.
	 * @param f coordenada i
	 * @param c coordenada j
	 */
	public void insertarCelulaSimple(int f, int c) {
		this.superficie[f][c] = new CelulaSimple();	
	}
	
	/**
	 * Crea una nueva célula compleja en la posición indicada.
	 * @param f coordenada i
	 * @param c coordenada j
	 */
	public void insertarCelulaCompleja(int f, int c) {
		this.superficie[f][c] = new CelulaCompleja();	
	}
	
	/**
	 * Pone a null la posición indicada.
	 * @param f coordenada i.
	 * @param c coordenada j.
	 */
	public void eliminarCelula(int f, int c) {
		this.superficie[f][c] = null;
	}
	
	/**
	 * Copia una célula dada a una posición dada. Después elimina la original.
	 * @param f coordenada i de la célula dada.
	 * @param c coordenada j de la célula dada.
	 * @param nuevaF coordenada i de la posición a donde mover.
	 * @param nuevaC coordenada j de la posición a donde mover.
	 */
	public void moverCelula(int f, int c, int nuevaF, int nuevaC) {
		this.superficie[nuevaF][nuevaC] = this.superficie[f][c];
		eliminarCelula(f, c);
	}
	
	/**
	 * Muestra la superficie por consola.
	 */
	public void pintarSuperficie() {
		System.out.print("\n");
		System.out.print("    ");
		for (int i = 1; i <= this.columnas; i++) System.out.print(i + "      ");
		System.out.print("\n");
		for (int i = 0; i < this.filas; i++) {
			System.out.print(i + 1);
			for (int j = 0; j < this.columnas; j++) {
				if (this.superficie[i][j] != null) {
					if(this.superficie[i][j].esComestible()){
						System.out.print("   X   ");
					} else {
						System.out.print("   *   ");
					}
			
				}
				else {
					System.out.print( "   -   ");
				}
			}
			System.out.print("\n"); 
		}
		System.out.print("\n");
	}
	
	/**
	 * @return Devolvemos el número de filas de la superficie
	 */
	public int getFilas() {
		return filas;
	}
	
	/**
	 * @return Devolvemos el número de columnas de las superficie
	 */
	public int getColumnas() {
		return columnas;
	}
	
	/**
	 * Devolvemos si hay o no una célula en una celda
	 * @param f coordenada f de la celda a estudiar
	 * @param c coordenada c de la celda a estudiar
	 * @return Devuelve true si la celda está vacia, false si hay celula
	 */
	public boolean celulaNula(int f, int c) {
		return this.superficie[f][c] == null;
	}
	
	/**
	 * Devuelve si la celula es o no comestible
	 * @param f coordenada f de la celula a estudiar
	 * @param c coordenada c de la celula a estudiar
	 * @return Devuelve true si es simple, false si es compleja.
	 */
	public boolean esComestible(int f, int c) {
		return superficie[f][c].esComestible();
	}
	
	public void cargar(Scanner entrada) throws PalabraIncorrecta, IndicesFueraDeRango {
		while (entrada.hasNextLine()) {
			String lineaEntrada = entrada.nextLine();
			String[] lineaParseada = lineaEntrada.split(" ");
			if (lineaParseada[2].equals("simple") && lineaParseada.length == 5) {
				try {
					int f = Integer.parseInt(lineaParseada[0]);
					int c = Integer.parseInt(lineaParseada[1]);
					int pasosDados = Integer.parseInt(lineaParseada[3]);
					int sinMovimientos = Integer.parseInt(lineaParseada[4]);	
					if (dentro(f,c)) {
						superficie[f][c]= new CelulaSimple(pasosDados, sinMovimientos);
					} else {
						throw new IndicesFueraDeRango();
					}
				} catch (NumberFormatException e) {
					throw new PalabraIncorrecta();
				}
			} else if (lineaParseada[2].equals("compleja") && lineaParseada.length == 4) {
				try {
					int f = Integer.parseInt(lineaParseada[0]);
					int c = Integer.parseInt(lineaParseada[1]);
					int celulasComidas = Integer.parseInt(lineaParseada[3]);
						
					if (dentro(f,c)) {
						superficie[f][c]= new CelulaCompleja(celulasComidas);
					} else {
						throw new IndicesFueraDeRango();
					}
				} catch (NumberFormatException e) {
					throw new PalabraIncorrecta();
				}
			} else {
				throw new PalabraIncorrecta ();
			}
		}
	}
	
	public void guardar(FileWriter salida) throws IOException {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (!celulaNula(i,j)) {
					salida.write(i + " " + j + " ");
					superficie[i][j].guardar(salida);
				}
			}
		}
	}
	
	public boolean dentro(int f, int c) {
		if (f >= 0 && f < this.filas && c >= 0 && c < this.columnas) {
			return true;
		} else {
			return false;
		}
	}
}


package tp.pr1.logica;

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
	 * Cada célula de la matriz pasa a null
	 */
	public void vaciar() {
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) superficie[i][j] = null; 
		}
	}
	
	/**
	 * Inserta una célula en una posición aleatoria.
	 */
	public void nuevaCelula() {  
		int aleatorioFila, aleatorioColumna;
		do {
			aleatorioFila = (int) (Math.random() * this.filas);
			aleatorioColumna = (int) (Math.random() * this.columnas);
		} while (this.superficie[aleatorioFila][aleatorioColumna] != null);
		this.superficie[aleatorioFila][aleatorioColumna] = new Celula();
	}
	
	/**
	 * Cada célula de la superficie reestablece su booleano que indica si ya ha sido
	 * movida.
	 */
	public void restaurarMovimiento() {  
		// Al final del turno, restaura el booleano de cada célula
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++){
				if (superficie[i][j] != null) {
					superficie[i][j].setRestaurar ();
				} 
			}
		}		
	}
	
	/**
	 * Devuelve true si la casilla (f, c) está libre.
	 * @return Devuelve true si (f, c) está libre, false en caso contrario.
	 */
	public boolean comprobarCasilla(int f, int c){
		return this.superficie[f][c] == null;
	}
	
	/**
	 * Crea una nueva célula en la posición indicada.
	 * @param f coordenada i
	 * @param c coordenada j
	 */
	public void insertarCelula(int f, int c) {
		this.superficie[f][c] = new Celula();	
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
				if (this.superficie[i][j] != null){
					int sinMover = this.superficie[i][j].getSinMover();
					int reproduccion = this.superficie[i][j].getReproduccion();
					System.out.print("  " + sinMover + "-" + reproduccion + "  ");
				}
				else {
					System.out.print( "   -   ");
				}
			}
			System.out.print("\n"); 
		}
		System.out.print("\n");
	}
	
	public void setSinMovimientos(int f, int c) {
		this.superficie[f][c].setSinMovimientos();
	}
	
	public boolean celulaNula(int f, int c) {
		return this.superficie[f][c] == null;
	}
	
	public boolean getMovido(int f, int c) {
		return this.superficie[f][c].getMovido();
	}
	
	public boolean reproduccion(int f, int c) {
		return this.superficie[f][c].reproduccion();
	}
	
	public boolean muerte(int f, int c) {
		return this.superficie[f][c].muerte();
	}
	
	public void setRestaurarPasosDados(int f, int c) {
		this.superficie[f][c].setRestaurarPasosDados();
	}
	
	public void setPasosDados(int f, int c) {
		this.superficie[f][c].setPasosDados();
	}
	
	public void setMovido(int f, int c) {
		this.superficie[f][c].setMovido();
	}
	
	
	
}


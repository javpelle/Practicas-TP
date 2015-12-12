package tp.pr1.logica;

/**
 * Consta de un atributo private superficie de dimensiones establecidas por constantes y
 * es inicializada con un número de células también establecida por una constante.
 * Se encarga de avanzar un paso en la evolución del mundo de células: Está al cuidado de
 * que las células, se muevan, reproduzcan o mueran si procede.
 * También se encarga entre otras cosas de crear o eliminar una célula en una posición dada.
 */
public class Mundo {
	private static final int NF = 4;
	private static final int NC = 4;
	private static final int CELULAS_INICIO = 10;
	private Superficie superficie;
	
	/**
	 * Constructora de la clase Mundo, inicializa la superficie con las dimensiones dadas
	 * y crea una determinada cantidad de células en posiciones aleatorias,
	 */
	public Mundo () {
		this.superficie = new Superficie(NF, NC);
		for (int i = 0; i < CELULAS_INICIO; i++) {
			superficie.nuevaCelula();	
		}		
	}
	
	/**
	 * Avanza un paso en la evolución del Mundo, trata de mover cada célula a una posible
	 * posición vecina vacía, asegurándose de mover cada célula una única vez por
	 * ejecución del método. Además se encarga de la reproduciión o muerte de una célula si
	 *  procediera.
	 */
	public void evoluciona() {
		Posicion esquina = new Posicion(NF - 1, NC - 1);
		for (int i = 0; i < NF; i++) {
			for (int j = 0; j < NC; j++) {
				if (!superficie.celulaNula(i, j) && !superficie.getMovido(i, j)) {					
					Posicion origen = new Posicion(i, j);
					Posicion destino = validas(origen, esquina);
					if (destino == null) {
						superficie.setSinMovimientos(i, j);
						// aumenta sinMovimientos + 1
						if (superficie.reproduccion(i, j) || superficie.muerte(i, j)) { 
							// Si la célula no puede moverse y tiene que 
							// reproducirse, o le toca morir, la eliminamos
							superficie.eliminarCelula(i, j);
							System.out.print("Muere la celula de la casilla (" + (i + 1) + "," + (j + 1) +") \n");
						} else {
							superficie.setPasosDados(i, j); 
						}
						//Si no ha muerto y no ha movido, aumentamos los pasos sin mover.
					} else {
						superficie.setMovido(i, j);
						superficie.moverCelula(i, j, destino.getF(), destino.getC());
						System.out.print("Movimiento de  (" + (i + 1) + "," + (j + 1) +") a (" +  (destino.getF() + 1) + "," + (destino.getC() + 1) +") \n");		
						if (superficie.reproduccion(destino.getF(), destino.getC())) {  
							superficie.insertarCelula(i,j);
							superficie.setRestaurarPasosDados(destino.getF(), destino.getC());
							System.out.print("Nueva celula en   (" + (i + 1) + "," + (j + 1) +") cuyo padre ha sido (" +  (destino.getF() + 1) + "," + (destino.getC() + 1) +") \n");
						}
						// Aumentamos +1 los pasos dados por la célula.
						else superficie.setPasosDados(destino.getF(), destino.getC());
					}
					// Marcamos a la célula como movida
					
				}			
			}
		}
		// Una vez acabada la ejecución restauramos los booleanos de las céulas que nos 
		// indican si han sido movidas o no.
		superficie.restaurarMovimiento();
	}
	
	
	/**
	 * Vacía el mundo, y lo vuelve a iniciar con un número de células
	 * dado por la constante inicial.
	 */
	public void iniciarMundo() {
		superficie.vaciar();
		for(int i = 0; i < CELULAS_INICIO; i++) {
			superficie.nuevaCelula();
		}
	}
	
	/**
	 * @return true si dentro
	 */
	private static boolean dentro(int f, int c) {
		return (f >= 0 && f < NF) && (c >= 0 && c < NC);
	}
	
	/**
	 * Inserta una nueva célula en la posición (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, esté libre.
	 * @param f coordenada i del mundo de células.
	 * @param c coordenada j del mundo de células.
	 * @return Devolvemos true si logramos insertar la célula. False en otro caso.
	 */
	public boolean nuevaCelula (int f, int c){
		boolean insertada = false;
		if (dentro(f, c) && superficie.comprobarCasilla(f ,c)) {
			superficie.insertarCelula(f ,c);
			insertada = true;
		}
		return insertada;
	}
	
	/**
	 * Elimina una  célula en la posición (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, esté ocupada.
	 * @param f coordenada i del mundo de células.
	 * @param c coordenada j del mundo de células.
	 * @return Devolvemos true si logramos borrar la célula. False en otro caso.
	 */
	public boolean eliminarCelula (int f, int c) {
		boolean eliminida = false;
		if (dentro(f, c) && !superficie.comprobarCasilla(f ,c)) {
			superficie.eliminarCelula(f ,c);
			eliminida = true;
		}
		return eliminida;
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
	
	/**
	 * Genera una posición a la que puede moverse la célula. Si no hay posición posible
	 * devuelve null.
	 * @param p Posición de la célula a estudiar.
	 * @param esquina Posición de la última celula del array.
	 * @return Devuelve una posicón válida aleatoria o null si no la hay.
	 */
	private Posicion validas(Posicion p, Posicion esquina) { 
		Posicion buenas[] = p.vecinas(p, esquina);
			int nv = 0;
			// Comprobamos el número de posiciones vecinas libres para luego
			// crear un array de posiciones de dicho tamaño.
			for (Posicion c : buenas) {
				if (superficie.celulaNula(c.getF(),c.getC())) nv ++; 
			}
			
			if (nv == 0) {
				// En caso de no haber posicioes válidas devolvemos null
				return null;
			} else {
				// En caso de que haya al menos una posición válida volvemos a 
				// recorrer el array para guardar dichas posiciones y devolvemos
				// una aleatoriamente.
				Posicion definitivas[] = new Posicion[nv];
				nv = 0;
				for (Posicion c : buenas) {
					int fila = c.getF();
					int col = c.getC();
					if (superficie.celulaNula(fila,col)) {
						definitivas[nv ++] = c; 
					}
				}
				int aleatorio = (int) (Math.random() * nv);		
				return definitivas[aleatorio];
			}
	}
}

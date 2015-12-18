package tp.pr3.logica;

/**
 * Representa una celula simple del mundo
 */
class CelulaSimple extends Celula {
	static final int MAX_PASOS_SIN_MOVER = 2;
	static final int PASOS_REPRODUCCION = 5;
	private int sinMovimientos, pasosDados;
	/**
	 * Contructora de la clase CelulaSimple
	 */
	public CelulaSimple() {
		esComestible = true;
		sinMovimientos = 0;
		pasosDados = 0;
	}
	
	/**
	 * Devolvemos la posicion a la que va a mover la célula simple
	 * @param f Fila en la que se encuentra la célula
	 * @param c Columna en la que se encuentra la célula
	 * @param superficie Tablero del mundo
	 * @return Devolvemos la posicion a la que movemos, o null en caso de que no se pueda mover
	 */
	public Posicion ejecutaMovimiento(int f, int c, Superficie superficie){
		Posicion p = new Posicion(f,c);
		Posicion esquina = new Posicion(superficie.getFilas(), superficie.getColumnas());
		Posicion destino = valida(p, esquina, superficie);
		if (destino == null) {
			setSinMovimientos();
			// aumenta sinMovimientos + 1
			if (reproduccion() || muerte()) { 
				// Si la célula no puede moverse y tiene que 
				// reproducirse, o le toca morir, la eliminamos
				superficie.eliminarCelula(f, c);
				System.out.print("Muere la celula de la casilla (" + (f + 1) + "," + (c + 1) +") \n");
			} else {
				setPasosDados(); 
			}
			//Si no ha muerto y no ha movido, aumentamos los pasos sin mover.
		} else {
			superficie.moverCelula(f, c, destino.getF(), destino.getC());
			System.out.print("Movimiento de  (" + (f + 1) + "," + (c + 1) +") a (" +  (destino.getF() + 1) + "," + (destino.getC() + 1) +") \n");		
			if (reproduccion()) {  
				superficie.insertarCelulaSimple(c,f);
				setRestaurarPasosDados();
				System.out.print("Nueva celula en   (" + (c + 1) + "," + (f + 1) +") cuyo padre ha sido (" +  (destino.getF() + 1) + "," + (destino.getC() + 1) +") \n");
			} else {
				// Aumentamos +1 los pasos dados por la célula.
				setPasosDados();
			}
		}
		
		return destino;
		
	}
	
	/**
	 * Generamos una posicion aleatoria a la que se mueva la célula simple si es que puede hacerlo
	 * @param p posicion de la célula actual.
	 * @param esquina posición de la última celda del array de células
	 * @param superficie Matriz de células
	 * @return Devolvemos una posicion aleatoria posible o null si no hay ninguna
	 */
	private Posicion valida(Posicion p, Posicion esquina, Superficie superficie) {
		Posicion buenas[] = p.vecinas(p, esquina);
		int nv = 0;
		// Comprobamos el número de posiciones vecinas libres para luego
		// crear un array de posiciones de dicho tamaño.
		for (Posicion i : buenas) {
			if (superficie.celulaNula(i.getF(),i.getC())) nv ++; 
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
			for (Posicion i : buenas) {
				int fila = i.getF();
				int col = i.getC();
				if (superficie.celulaNula(fila,col)) {
					definitivas[nv ++] = i; 
				}
			}
			int aleatorio = (int) (Math.random() * nv);		
			return definitivas[aleatorio];
		}
	}
	
	/**
	 * Devolvemos si la célula es comestible
	 * @return devolvemos esComestible
	 */
	public boolean esComestible() {
		return esComestible;
	}
	
	/**
	 * Aumentamos en uno el número de pasos dados.
	 */
	public void setPasosDados() {
		this.pasosDados++;
	}
	
	/**
	 * Inicializamos a cero el número de pasos dados.
	 */
	public void setRestaurarPasosDados() {
		this.pasosDados = 0;
	}
	
	/**
	 * Aumentamos en uno el número de turnos en los que la célula no se ha movido.
	 */
	public void setSinMovimientos() {
		this.sinMovimientos++;
	}
	
	/**
	 * @return Devuelve el número de pasos que faltan para la reproducción.
	 */
	public int getSinMover() {	
		return MAX_PASOS_SIN_MOVER - this.sinMovimientos;
	}
	
	/**
	 * @return Devuelve el número de pasos que faltan para la muerte de la célula por falta de movimiento.
	 */
	public int getReproduccion() {	
		return PASOS_REPRODUCCION - this.pasosDados;
	}
	
	/**
	 * @return Devuelve true si la célula debe reproducirse. False en caso contrario.
	 */
	public boolean reproduccion() {	
		return PASOS_REPRODUCCION == this.pasosDados;
	}
	
	/**
	 * @return Devuelve true si la célula debe morir por falta de movimiento. False en otro caso.
	 */
	public boolean muerte() {	
		return MAX_PASOS_SIN_MOVER == this.sinMovimientos;
	}
}



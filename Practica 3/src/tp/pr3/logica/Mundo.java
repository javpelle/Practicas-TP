package tp.pr3.logica;

/**
 * Consta de un atributo private superficie de dimensiones establecidas por constantes y
 * es inicializada con un n�mero de c�lulas tambi�n establecida por una constante.
 * Se encarga de avanzar un paso en la evoluci�n del mundo de c�lulas: Est� al cuidado de
 * que las c�lulas, se muevan, reproduzcan o mueran si procede.
 * Tambi�n se encarga entre otras cosas de crear o eliminar una c�lula en una posici�n dada.
 */
public class Mundo {
	private static final int NF = 4;
	private static final int NC = 4;
	private static final int CELULAS_INICIO = 4;
	private Superficie superficie;
	private boolean simulacionTerminada;
	private boolean[][] booleanMatrix;
	
	/**
	 * Constructora de la clase Mundo, inicializa la superficie con las dimensiones dadas
	 * y crea una determinada cantidad de c�lulas en posiciones aleatorias,
	 */
	public Mundo () {
		simulacionTerminada  = true;
		this.superficie = new Superficie(NF, NC);
		for (int i = 0; i < CELULAS_INICIO; i++) {
			superficie.nuevaCelula();	
		}
		booleanMatrix = new boolean [NF][NC];
		for (int i = 0; i < NF; i++) {
			for (int j = 0; i < NC; i++) {
				booleanMatrix[i][j] = false;
			}
		}
	}
	
	/**
	 * Avanza un paso en la evoluci�n del Mundo, trata de mover cada c�lula a una posible
	 * posici�n vecina vac�a, asegur�ndose de mover cada c�lula una �nica vez por
	 * ejecuci�n del m�todo. Adem�s se encarga de la reproducii�n o muerte de una c�lula si
	 *  procediera.
	 */
	public void evoluciona() {
		for (int i = 0; i < NF; i++) {
			for (int j = 0; j < NC; j++) {
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
		for (int i = 0; i < NF; i++) {
			for (int j = 0; j < NC; j++) {
				booleanMatrix[i][j] = false;
			}
		}
	}
	
	/**
	 * Vac�a el mundo, y lo vuelve a iniciar con un n�mero de c�lulas
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
	 * Inserta una nueva c�lula simple en la posici�n (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, est� libre.
	 * @param f coordenada i del mundo de c�lulas.
	 * @param c coordenada j del mundo de c�lulas.
	 * @return Devolvemos true si logramos insertar la c�lula. False en otro caso.
	 */
	public boolean nuevaCelulaSimple (int f, int c){
		boolean insertada = false;
		if (dentro(f, c) && superficie.celulaNula(f ,c)) {
			superficie.insertarCelulaSimple(f ,c);
			System.out.print("Celula simple creada en la posicion (" + (f + 1) + "," + (c + 1) + ")\n");
			insertada = true;
		}
		return insertada;
	}
	
	/**
	 * Inserta una nueva c�lula compleja en la posici�n (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, est� libre.
	 * @param f coordenada i del mundo de c�lulas.
	 * @param c coordenada j del mundo de c�lulas.
	 * @return Devolvemos true si logramos insertar la c�lula. False en otro caso.
	 */
	public boolean nuevaCelulaCompleja (int f, int c){
		boolean insertada = false;
		if (dentro(f, c) && superficie.celulaNula(f ,c)) {
			superficie.insertarCelulaCompleja(f ,c);
			System.out.print("Celula compleja creada en la posicion (" + (f + 1) + "," + (c + 1) + ")\n");
			insertada = true;
		}
		return insertada;
	}
	
	/**
	 * Elimina una  c�lula en la posici�n (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, est� ocupada.
	 * @param f coordenada i del mundo de c�lulas.
	 * @param c coordenada j del mundo de c�lulas.
	 * @return Devolvemos true si logramos borrar la c�lula. False en otro caso.
	 */
	public boolean eliminarCelula (int f, int c) {
		boolean eliminida = false;
		if (dentro(f, c) && !superficie.celulaNula(f ,c)) {
			superficie.eliminarCelula(f ,c);
			System.out.print("Celula eliminada en la posicion (" + (f + 1) + "," + (c + 1) + ")\n");
			eliminida = true;
		}
		return eliminida;
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
	 * Devuelve si la simulacion ha sido o no terminada
	 * @return Devuelve si la simulacion ha sido o no terminada
	 */
	public boolean esSimulacionTerminada(){
		return simulacionTerminada;
	}
	
	/**
	 * Cambia simulacionTerminada a false
	 */
	public void setEsSimulacionTerminada(){
		simulacionTerminada = false;
	}

}

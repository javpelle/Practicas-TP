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
	private static final int CELULAS_INICIO = 4;
	private Superficie superficie;
	private boolean simulacionTerminada;
	
	/**
	 * Constructora de la clase Mundo, inicializa la superficie con las dimensiones dadas
	 * y crea una determinada cantidad de células en posiciones aleatorias,
	 */
	public Mundo () {
		simulacionTerminada  = true;
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
		for (int i = 0; i < NF; i++) {
			for (int j = 0; j < NC; j++) {
				if (!superficie.celulaNula(i, j) && !superficie.getMovido(i, j)) {					
					Posicion p = superficie.ejecutaMovimiento(i, j);
					if (p != null) {
						superficie.setMovido(p.getF(), p.getC());
					}
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
	 * Inserta una nueva célula simple en la posición (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, esté libre.
	 * @param f coordenada i del mundo de células.
	 * @param c coordenada j del mundo de células.
	 * @return Devolvemos true si logramos insertar la célula. False en otro caso.
	 */
	public boolean nuevaCelulaSimple (int f, int c){
		boolean insertada = false;
		if (dentro(f, c) && superficie.celulaNula(f ,c)) {
			superficie.insertarCelulaSimple(f ,c);
			insertada = true;
		}
		return insertada;
	}
	
	/**
	 * Inserta una nueva célula compleja en la posición (f, c) del array siempre y cuando
	 * esa celda exista y en ese caso, esté libre.
	 * @param f coordenada i del mundo de células.
	 * @param c coordenada j del mundo de células.
	 * @return Devolvemos true si logramos insertar la célula. False en otro caso.
	 */
	public boolean nuevaCelulaCompleja (int f, int c){
		boolean insertada = false;
		if (dentro(f, c) && superficie.celulaNula(f ,c)) {
			superficie.insertarCelulaCompleja(f ,c);
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
		if (dentro(f, c) && !superficie.celulaNula(f ,c)) {
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
	
	public boolean esSimulacionTerminada(){
		simulacionTerminada = false;
		return simulacionTerminada;
	}
	
	public void 
}
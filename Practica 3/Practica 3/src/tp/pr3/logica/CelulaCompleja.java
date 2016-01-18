package tp.pr3.logica;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Representa una celula compleja del mundo.
 */
class CelulaCompleja implements Celula {
	static final int MAX_CELULAS_COMIDAS = 2;
	private int celulasComidas;
	private boolean esComestible;
	
	/**
	 * Constructora de la clase CelulaSimple
	 */
	public CelulaCompleja() {
		esComestible = false;
		celulasComidas = 0;
	}
	
	/**
	 * Creamos una nueva celula compleja y cargamos sus atributos de un fichero.
	 * @param celulasComidas numero de celulas comidas guardadas en el fichero
	 */
	public CelulaCompleja(int celulasComidas) {
		esComestible = false;
		this.celulasComidas = celulasComidas;
	}
	
	/**
	 * Devolvemos si la célula es comestible
	 * @return devolvemos esComestible
	 */
	public boolean esComestible() {
		return esComestible;
	}

	/**
	 * Devolvemos la posicion a la que va a mover la célula compleja	
	 */
	public Posicion ejecutaMovimiento(int f, int c, Superficie superficie) {
		Posicion destino = generarPosicion(f, c, superficie);
		if(destino != null) {
			System.out.print("Celula compleja en (" + (f + 1) + "," + (c + 1) +") se mueve a (" +  (destino.getF() + 1) + "," + (destino.getC() + 1)+")");
			if (superficie.celulaNula(destino.getF(), destino.getC())){
				superficie.moverCelula(f, c, destino.getF(), destino.getC());
				System.out.print(" --NO COME--\n");
			} else {
				superficie.moverCelula(f, c, destino.getF(), destino.getC());
				setComidas();
				System.out.print(" --COME--\n");
				if (explosion()) {
					superficie.eliminarCelula(destino.getF(), destino.getC());
					System.out.print("Explota la celula compleja en (" +  (destino.getF() + 1) + "," + (destino.getC() + 1) + ")\n");
					destino = null;
				}
			} 
				
		} else { 
			System.out.print("Celula compleja en (" + (f + 1) + "," + (c + 1) +") no se mueve\n"); 
		}
		return destino;
	}
	
	/**
	 * Generamos una posición aleatoria a la que mover nuestra célula compleja
	 * @param f coordenada x de la célula actual
	 * @param c coordenada y de la célula actual
	 * @param superficie superficie sobre la que estamos trabajando
	 * @return Devolvemos la posicion originada o null si dicha posicion está
	 * 	ocupada por otra célula compleja
	 */
	private Posicion generarPosicion(int f, int c, Superficie superficie) {
		// Generamos un aleatorio en [0, NC * NF - 2]
		int aleatorio = (int) (Math.random() * (superficie.getFilas() * superficie.getColumnas() - 1));
		// Planteamos una aplicacion biyectiva de R^2 en R a = g(f,c) = f * NC + c
		int a = f * superficie.getColumnas() + c;
		// Si el aleatorio es mayor o igual a la celda actual sumamos uno
		if (aleatorio >= a) {
			aleatorio++;
		}
		// Calculamos g^-1(z) = (z/NC, z%NC)
		Posicion devuelta = new Posicion(aleatorio /superficie.getColumnas(), aleatorio %superficie.getColumnas());
		if (!superficie.celulaNula(devuelta.getF(), devuelta.getC()) && !superficie.esComestible(devuelta.getF(), devuelta.getC())) {
			return null;
		} else {
			return devuelta;
		}
	} 
	
	/**
	 * Devolvemos el número de celulas simples que ha comido una compleja.
	 * @return Devolvemos celulasComidas.
	 */
	public int getComidas() {
		return celulasComidas;
	}
	
	/**
	 * Aumentamos en uno las celulas comidas
	 */
	public void setComidas() {
		celulasComidas++;
	}
	
	/**
	 * Devolvemos true si la célula compleja debe morir.
	 * @return true si celulasComidas = MAX_Celulas_Comidas. False en otro caso.
	 */
	public boolean explosion() {
		return celulasComidas == MAX_CELULAS_COMIDAS;
	}
	
	/**
	 * Escribe en el fichero de salida el numero de celulasComidads por la célula compleja
	 */
	
	public void guardar(FileWriter salida) throws IOException {
		salida.write("compleja " + celulasComidas + "\r\n");
	}
}

	

package tp.pr3.logica;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase abstracta que representa una celula del mundo. 
 */
public interface Celula {
	
	abstract public Posicion ejecutaMovimiento(int f, int c, Superficie superficie);
	abstract public boolean esComestible();
	abstract public void guardar(FileWriter salida) throws IOException;
}
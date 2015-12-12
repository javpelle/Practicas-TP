package tp.pr1.control;
import tp.pr1.logica.Mundo;

/**
 * Clase abstracta pura cuyas subclases son los distintos tipos de comandos
 */
public abstract class Comando {
	public abstract void ejecuta(Mundo mundo);
	public abstract Comando parsea(String[ ] cadenaComando);
	public abstract String textoAyuda();
}

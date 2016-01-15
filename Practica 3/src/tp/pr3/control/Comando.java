package tp.pr3.control;

import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.FormatoNumericoIncorrecto;
import tp.pr3.exceptions.NumerosNegativos;


public interface Comando {
	
	/**
	 * Dado un comando parseado correctamente, lo lleva a cabo
	 * @param controlador sobre el que ejecutarlo
	 */
	public abstract void ejecuta(Controlador controlador) throws ErrorDeInicializacion, NumerosNegativos;
	/**
	 * Comprueba si es posible, a priori, ejecutar el comando dado (pero no lo ejecuta todavia)
	 * @return si consigue interpretarlo, devuelve un comando configurado listo para ejecutar
	 */
	public abstract Comando parsea(String[ ] cadenaComando) throws FormatoNumericoIncorrecto;
	public abstract String textoAyuda();
}

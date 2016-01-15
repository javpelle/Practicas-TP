package tp.pr3.control;

import tp.pr3.exceptions.ErrorDeInicializacion;
import tp.pr3.exceptions.FormatoNumericoIncorrecto;
import tp.pr3.exceptions.NumerosNegativos;
import tp.pr3.logica.Mundo;
import tp.pr3.logica.MundoComplejo;
import tp.pr3.logica.MundoSimple;

class ComandoJugar implements Comando {
	private boolean esMundoSimple;
	private Mundo mundo;
	private int filas;
	private int columnas;
	private int sim;
	private int com;
	
	
	/**
	 * crea una celula simple en (f,c)
	 * @throws NumerosNegativos 
	 * @throws ErrorDeInicializacion 
	 */
	public void ejecuta(Controlador controlador) throws ErrorDeInicializacion, NumerosNegativos {
		if (esMundoSimple) {
			this.mundo = new MundoSimple(filas, columnas, sim);
		} else {
			this.mundo = new MundoComplejo(filas, columnas, sim, com);
		}
		 controlador.juega(this.mundo); 
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, lo ejecuta con (f - 1,c - 1) y lo devuelve en tal caso.Si no, devuelve null.
	 * @throws FormatoNumericoIncorrecto 
	 * @throws NumerosNegativos 
	 * @throws ErrorDeInicializacion 
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("simple") && cadenaComando.length == 5) {
			try {
				esMundoSimple = true;
				this.filas = Integer.parseInt(cadenaComando[2]);
				this.columnas = Integer.parseInt(cadenaComando[3]);
				this.sim = Integer.parseInt(cadenaComando[4]);
			} catch (NumberFormatException e) {
				throw new FormatoNumericoIncorrecto();
			}
			return this;
		} else if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("complejo") && cadenaComando.length == 6) {
			try {
				esMundoSimple = false;
				this.filas = Integer.parseInt(cadenaComando[2]);
				this.columnas = Integer.parseInt(cadenaComando[3]);
				this.sim = Integer.parseInt(cadenaComando[4]);
				this.com = Integer.parseInt(cadenaComando[5]);
			} catch (IllegalArgumentException e) {
				throw new FormatoNumericoIncorrecto();
			}
			return this;
		} else {
			return null;
		}
	}
	
	/**
	 * @return Devuelve un string con la ayuda
	 */
	public String textoAyuda() {
		return "CREARCELULA F C: crea una nueva celula en la posicion (f,c) si es posible.\n" ;
	}


}
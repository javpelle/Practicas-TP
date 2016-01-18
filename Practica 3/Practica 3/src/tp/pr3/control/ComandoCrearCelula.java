package tp.pr3.control;


import tp.pr3.exceptions.FormatoNumericoIncorrecto;


class ComandoCrearCelula implements Comando {
	private int f;
	private int c;
	
	/**
	 * crea una celula simple en (f,c)
	 * @throws FormatoNumericoIncorrecto 
	 */
	public void ejecuta(Controlador controlador) {
		controlador.nuevaCelula(f, c);
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, lo ejecuta con (f - 1,c - 1) y lo devuelve en tal caso.Si no, devuelve null.
	 * @throws FormatoNumericoIncorrecto 
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		if (cadenaComando[0].equals("crearcelula") && cadenaComando.length == 3) {
			try {
				this.f = Integer.parseInt(cadenaComando[1]) - 1;
				this.c = Integer.parseInt(cadenaComando[2]) - 1;
			} catch (NumberFormatException e) {
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
	
	
	/**
	 * Permite seleccionar el tipo de celula que se quiere introducir en función del mundo
	 * @return Devuelve 1(Simple)o 2(Compleja) 
	 */
}

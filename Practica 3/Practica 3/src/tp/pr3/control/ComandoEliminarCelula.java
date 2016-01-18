package tp.pr3.control;

import tp.pr3.exceptions.FormatoNumericoIncorrecto;


class ComandoEliminarCelula implements Comando {
	private int f;
	private int c;
	
	/**
	 * Elimina una celula en (f,c)
	 */
	public void ejecuta(Controlador controlador) {
		controlador.eliminarCelula(f, c);
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, lo ejecuta con (f - 1,c - 1) y lo devuelve en tal caso.Si no, devuelve null.
	 * @throws FormatoNumericoIncorrecto 
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		if (cadenaComando[0].equals("eliminarcelula") && cadenaComando.length >= 3) {
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
		return "ELIMINARCELULA F C: elimina la celula de la posición (f,c) si es posible.\n";
	}

}

package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoCrearCelulaSimple extends Comando {
	private int f;
	private int c;
	
	/**
	 * crea una celula simple en (f,c)
	 */
	public void ejecuta(Mundo mundo) {
		 if (!mundo.nuevaCelulaSimple(f, c)) {
			 System.out.print("La celula no se puede insertar en la posicion seleccionada. \n");
		 }
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, lo ejecuta con (f - 1,c - 1) y lo devuelve en tal caso.Si no, devuelve null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0].equals("crearcelulasimple") && cadenaComando.length >= 3) {
			try {
				this.f = Integer.parseInt(cadenaComando[1]) - 1;
				this.c = Integer.parseInt(cadenaComando[2]) - 1;
			} catch (IllegalArgumentException e) {
				System.out.println("Exception thrown: " + e);
				return null;
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
		return "CREARCELULASIMPLE F C: crea una nueva celula simple en la posición (f,c) si es posible.\n" ;
	}


}

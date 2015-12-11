package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoEliminarCelula extends Comando {
	private int f;
	private int c;
	
	/**
	 * Elimina una celula en (f,c)
	 */
	public void ejecuta(Mundo mundo) {
		 if (!mundo.eliminarCelula(f, c)) {
			 System.out.print("La celula no se puede eliminar de la posicion seleccionada. \n");
		 }
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, lo ejecuta con (f - 1,c - 1) y lo devuelve en tal caso.Si no, devuelve null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0].equals("eliminarcelula") && cadenaComando.length >= 3) {
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
		return "ELIMINARCELULA F C: elimina la celula de la posición (f,c) si es posible.\n";
	}

}

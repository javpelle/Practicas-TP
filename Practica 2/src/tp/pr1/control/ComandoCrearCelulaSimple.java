package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoCrearCelulaSimple extends Comando {
	private int f;
	private int c;
	
	public ComandoCrearCelulaSimple(int fila, int columna) {
		f = fila;
		c = columna;
	}
	
	public void ejecuta(Mundo mundo) {
		 if (!mundo.nuevaCelulaSimple(f, c)) {
			 System.out.print("La celula no se puede insertar en la posicion seleccionada. \n");
		 }
	}
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0].equals("crearcelulasimple") && cadenaComando.length >= 3) {
			this.f = Integer.parseInt(cadenaComando[1]) - 1;
			this.c = Integer.parseInt(cadenaComando[2]) - 1;
			return this;
		} else {
			return null;
		}
	}
	public String textoAyuda() {
		return "CREARCELULASIMPLE F C: crea una nueva celula simple en la posición (f,c) si es posible.\n" ;
	}


}

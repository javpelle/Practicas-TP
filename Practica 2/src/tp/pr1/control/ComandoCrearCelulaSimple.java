package tp.pr1.control;

import tp.pr1.logica.Mundo;

public class ComandoCrearCelulaSimple {
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
		return null;
	}
	public String textoAyuda() {
		return "CREARCELULASIMPLE F C: crea una nueva celula simple en la posición (f,c) si es posible.\n" ;
	}


}

package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoEliminarCelula extends Comando {
	private int f;
	private int c;
	
	public ComandoEliminarCelula(int fila, int columna) {
		f = fila;
		c = columna;
	}
	
	public void ejecuta(Mundo mundo) {
		 if (!mundo.eliminarCelula(f, c)) {
			 System.out.print("La celula no se puede eliminar de la posicion seleccionada. \n");
		 }
	}
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0].equals("eliminarcelula")) {
			int f = Integer.parseInt(cadenaComando[1]) - 1;
			int c = Integer.parseInt(cadenaComando[2]) - 1;
			return new ComandoEliminarCelula(f, c);
		} else {
			return null;
		}
	}
	public String textoAyuda() {
		return "ELIMINARCELULA F C: elimina la celula de la posición (f,c) si es posible.\n";
	}

}

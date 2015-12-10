package tp.pr1.control;
import tp.pr1.logica.Mundo;

class ComandoCrearCelulaCompleja  extends Comando {
		private int f;
		private int c;
		
		public ComandoCrearCelulaCompleja(int fila, int columna) {
			f = fila;
			c = columna;
		}
		
		public void ejecuta(Mundo mundo) {
			 if (!mundo.nuevaCelulaCompleja(f, c)) {
				 System.out.print("La celula no se puede insertar en la posicion seleccionada. \n");
			 }
		}
		
		public Comando parsea(String[] cadenaComando) {
			if (cadenaComando[0].equals("crearcelulacompleja") && cadenaComando.length >= 3) {
				this.f = Integer.parseInt(cadenaComando[1]) - 1;
				this.c = Integer.parseInt(cadenaComando[2]) - 1;
				return this;
			} else {
				return null;
			}
		}
		public String textoAyuda() {
			return "CREARCELULACOMPLEJA F C: crea una nueva celula compleja en la posición (f,c) si es posible.\n";
		}

}

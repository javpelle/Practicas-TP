package tp.pr1.control;
import tp.pr1.logica.Mundo;

public class ComandoCrearCelulaCompleja  extends Comando {
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
			return null;
		}
		public String textoAyuda() {
			return "CREARCELULACOMPLEJA F C: crea una nueva celula compleja en la posición (f,c) si es posible.\n";
		}

}

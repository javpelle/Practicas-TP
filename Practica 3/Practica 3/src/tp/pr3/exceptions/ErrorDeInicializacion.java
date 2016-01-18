package tp.pr3.exceptions;

public class ErrorDeInicializacion extends Exception {
	public ErrorDeInicializacion() {
		super ("La superficie no tiene capacidad para el numero de celulas introducidas...\n");
	}
}

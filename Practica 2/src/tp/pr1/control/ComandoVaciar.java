package tp.pr1.control;

import tp.pr1.logica.Mundo;

public class ComandoVaciar {
	public void ejecuta(Mundo mundo) {
		mundo.vaciar();
	}
	public Comando parsea(String[ ] cadenaComando) {
		return null;
	}
	public String textoAyuda() {
		return "VACIAR: crea un mundo vacío.\n";
	}
}

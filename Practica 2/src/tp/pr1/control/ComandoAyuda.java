package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoAyuda extends Comando {
	public void ejecuta(Mundo mundo) {
		mundo.esSimulacionTerminada();
	}
	public Comando parsea(String[ ] cadenaComando) {
		return null;
	}
	public String textoAyuda() {
		return "AYUDA: Muestra esta ayuda.\n";
	}
}

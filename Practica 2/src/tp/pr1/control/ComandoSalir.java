package tp.pr1.control;
import tp.pr1.logica.Mundo;

class ComandoSalir extends Comando {
	public void ejecuta(Mundo mundo) {
		mundo.esSimulacionTerminada();
	}
	public Comando parsea(String[ ] cadenaComando) {
		return null;
	}
	public String textoAyuda() {
		return "SALIR: Cierra la aplicación.\n";
	}
}

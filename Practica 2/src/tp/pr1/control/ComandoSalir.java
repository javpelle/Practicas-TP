package tp.pr1.control;
import tp.pr1.logica.Mundo;

class ComandoSalir extends Comando {
	public void ejecuta(Mundo mundo) {
		mundo.setEsSimulacionTerminada();
	}
	
	public Comando parsea(String[ ] cadenaComando) {
		if(cadenaComando[0].equals("salir") || cadenaComando[0].equals("exit")) {
			return this;
		} else {
			return null;
		}
	}
	
	public String textoAyuda() {
		return "SALIR: Cierra la aplicación.\n";
	}
}

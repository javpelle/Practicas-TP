package tp.pr1.control;
import tp.pr1.logica.Mundo;

class ComandoPaso extends Comando {
	
	public void ejecuta(Mundo mundo) {
		mundo.evoluciona();
	}
	
	public Comando parsea(String[ ] cadenaComando) {
		if(cadenaComando[0].equals("paso")) {
			return this;
		} else {
			return null;
		}
	}
	
	public String textoAyuda() {
		return "PASO: Realiza un paso en la simulación.\n";
	}
}

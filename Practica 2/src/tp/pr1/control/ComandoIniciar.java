package tp.pr1.control;

import tp.pr1.logica.Mundo;

public class ComandoIniciar {
	
	public void ejecuta(Mundo mundo) {
		 mundo.iniciarMundo();
	}
	public Comando parsea(String[] cadenaComando) {
		return null;
	}
	public String textoAyuda() {
		return "INICIAR: inicia una nueva simulación.\n";
	}

}

package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoIniciar extends Comando {
	
	public void ejecuta(Mundo mundo) {
		 mundo.iniciarMundo();
	}
	
	public Comando parsea(String[] cadenaComando) {
		if(cadenaComando[0].equals("iniciar")) {
			return this;
		} else {
			return null;
		}
	}
	
	public String textoAyuda() {
		return "INICIAR: inicia una nueva simulación.\n";
	}

}

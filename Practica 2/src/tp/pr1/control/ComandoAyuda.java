package tp.pr1.control;

import tp.pr1.logica.Mundo;

class ComandoAyuda extends Comando {
	public void ejecuta(Mundo mundo) {
		System.out.print(ParserComandos.AyudaComandos());
	}
	public Comando parsea(String[ ] cadenaComando) {
		if(cadenaComando[0].equals("ayuda") || cadenaComando[0].equals("help")) {
			return this;
		} else {
			return null;
		}
	}
	public String textoAyuda() {
		return "AYUDA / HELP: Muestra esta ayuda.\n";
	}
}

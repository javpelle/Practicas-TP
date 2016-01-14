package tp.pr3.control;


public interface Comando {
	public abstract void ejecuta(Controlador controlador);
	public abstract Comando parsea(String[ ] cadenaComando);
	public abstract String textoAyuda();
}

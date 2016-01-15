package tp.pr3.control;

import java.util.NoSuchElementException;
import java.util.Scanner;

import tp.pr3.exceptions.FormatoNumericoIncorrecto;
import tp.pr3.exceptions.NumeroNoValido;


class ComandoCrearCelula implements Comando {
	private int f;
	private int c;
	
	/**
	 * crea una celula simple en (f,c)
	 * @throws FormatoNumericoIncorrecto 
	 */
	public void ejecuta(Controlador controlador) {
		
		controlador.nuevaCelula(f,c);
	}
	
	/**
	 * Comprueba si el array de string se corresponde con el comando, lo ejecuta con (f - 1,c - 1) y lo devuelve en tal caso.Si no, devuelve null.
	 * @throws FormatoNumericoIncorrecto 
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		if (cadenaComando[0].equals("crearcelula") && cadenaComando.length == 3) {
			try {
				this.f = Integer.parseInt(cadenaComando[1]) - 1;
				this.c = Integer.parseInt(cadenaComando[2]) - 1;
			} catch (NumberFormatException e) {
				throw new FormatoNumericoIncorrecto();
			}
			return this;
		} else {
			return null;
		}
	}
	
	/**
	 * @return Devuelve un string con la ayuda
	 */
	public String textoAyuda() {
		return "CREARCELULA F C: crea una nueva celula en la posicion (f,c) si es posible.\n" ;
	}
	
	private int simpleOComplejo() { //FALTA sc.close()
		int celula;
		System.out.print("De que tipo: Compleja (1) o Simple (2): ");
		try {
			Scanner sc = new Scanner(System.in);
			celula = sc.nextInt();
			if (celula != 1 && celula != 2) {
				throw  new NumeroNoValido();
			}
		} catch (NoSuchElementException | NumeroNoValido e){
			System.out.println(e);
			return 0;
		}
		return celula;		
	}


}

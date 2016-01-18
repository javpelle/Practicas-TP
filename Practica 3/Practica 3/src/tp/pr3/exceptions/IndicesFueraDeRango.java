package tp.pr3.exceptions;

public class IndicesFueraDeRango extends Exception {
	public IndicesFueraDeRango() {
		super ("Se ha intentado acceder a una posicion del vector no existente...\n");
	}
}

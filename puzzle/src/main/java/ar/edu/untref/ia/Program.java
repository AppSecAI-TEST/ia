package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;

public class Program {

	public static final void main(String arg[]) {

		List<Integer> instanciaJuego = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			instanciaJuego.add(Integer.parseInt(arg[i]));
		}
		Nodo nodoInicial = new Nodo(instanciaJuego);

		Puzzle puzzle = new Puzzle();
		Integer cantidadDeMovimientos = puzzle.resolver(nodoInicial);

		puzzle.imprimirRecorrido();
		System.out.println("Cantidad de movimientos minimos: " + cantidadDeMovimientos);
	}
}

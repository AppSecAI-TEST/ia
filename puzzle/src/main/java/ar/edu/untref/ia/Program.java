package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;

public class Program {

	public static final void main(String arg[]) {

		List<Integer> instanciaJuego = new ArrayList<>();

		instanciaJuego.add(Integer.parseInt(arg[0]));
		instanciaJuego.add(Integer.parseInt(arg[1]));
		instanciaJuego.add(Integer.parseInt(arg[2]));
		instanciaJuego.add(Integer.parseInt(arg[3]));
		instanciaJuego.add(Integer.parseInt(arg[4]));
		instanciaJuego.add(Integer.parseInt(arg[5]));
		instanciaJuego.add(Integer.parseInt(arg[6]));
		instanciaJuego.add(Integer.parseInt(arg[7]));
		instanciaJuego.add(Integer.parseInt(arg[8]));

		Puzzle puzzle = new Puzzle();
		Nodo nodoInicial = new Nodo(instanciaJuego);
		puzzle.resolver(nodoInicial);

//		puzzle.imprimirRecorrido();

	}
}

package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Puzzle {

	static final Nodo SOLUCION = new Nodo(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

	private List<Nodo> adyacentes;

	private List<Nodo> recorrido = new ArrayList<Nodo>();

	public List<Nodo> resolver(Nodo nodoInicial) {

		Queue<Nodo> colaDeVisitas = new LinkedList<Nodo>();
		boolean resuelto = estaResuelto(nodoInicial);

		if (!resuelto) {

			colaDeVisitas.add(nodoInicial);
			nodoInicial.setVisitado(true);
			recorrido.add(nodoInicial);

			int j = 0;
			
			while (!colaDeVisitas.isEmpty() && !resuelto && j < 100) {

				Nodo desacolado = colaDeVisitas.remove();
				agregarAdyacentes(desacolado);

				for (Nodo nodoActual : adyacentes) {
					if (!nodoActual.fueVisitado()) {
						nodoActual.setVisitado(true);
						colaDeVisitas.add(nodoActual);
						if (!fueRecorrido(nodoActual)) {
							recorrido.add(nodoActual);
							resuelto = estaResuelto(nodoActual);
						}
					}
				}
				j++;
			}
		}

		return recorrido;
	}

	private boolean estaResuelto(Nodo estadoJuego) {
		return SOLUCION.equals(estadoJuego);
	}

	private void agregarAdyacentes(Nodo estadoJuego) {

		adyacentes = new ArrayList<Nodo>();
		int posicionLibre = estadoJuego.obtenerPosicionLibre();
		List<Integer> movimientosPosibles = estadoJuego.obtenerMovimientosPosibles(posicionLibre);
		Iterator<Integer> movimientosPosiblesIt = movimientosPosibles.iterator();
		while (movimientosPosiblesIt.hasNext()) {

			int indiceAReemplazar = movimientosPosiblesIt.next();

			List<Integer> copiaEstadoJuego = new ArrayList<>();
			for (int i = 0; i < estadoJuego.getEstadoJuego().size(); i++) {
				copiaEstadoJuego.add(estadoJuego.getEstadoJuego().get(i));
			}

			Nodo nodo = new Nodo(copiaEstadoJuego);
			nodo.getEstadoJuego().set(posicionLibre, nodo.getEstadoJuego().get(indiceAReemplazar));
			nodo.getEstadoJuego().set(indiceAReemplazar, 0);
			if (!fueRecorrido(nodo)) {
				adyacentes.add(nodo);
			}
		}
	}

	private boolean fueRecorrido(Nodo nodo) {

		boolean fueRecorrido = false;

		for (int i = 0; i < recorrido.size(); i++) {
			if (recorrido.get(i).equals(nodo)) {
				fueRecorrido = true;
			}
		}

		return fueRecorrido;
	}

	public void imprimirRecorrido() {

		for (int i = 0; i < 100; i++) {

			System.out.println(recorrido.get(i).getEstadoJuego().get(0) + " " + recorrido.get(i).getEstadoJuego().get(1) + " " + recorrido.get(i).getEstadoJuego().get(2));
			System.out.println(recorrido.get(i).getEstadoJuego().get(3) + " " + recorrido.get(i).getEstadoJuego().get(4) + " " + recorrido.get(i).getEstadoJuego().get(5));
			System.out.println(recorrido.get(i).getEstadoJuego().get(6) + " " + recorrido.get(i).getEstadoJuego().get(7) + " " + recorrido.get(i).getEstadoJuego().get(8));
			System.out.println("\n");
		}
	}
}
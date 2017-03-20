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

	public Integer resolver(Nodo nodoInicial) {

		Queue<Nodo> colaDeVisitas = new LinkedList<Nodo>();
		boolean resuelto = estaResuelto(nodoInicial);
		recorrido.add(nodoInicial);
		Nodo desacolado = null;

		if (!resuelto) {

			colaDeVisitas.add(nodoInicial);
			nodoInicial.setVisitado(true);

			while (!colaDeVisitas.isEmpty() && !resuelto) {

				desacolado = colaDeVisitas.remove();
				agregarAdyacentes(desacolado);
				Iterator<Nodo> adyacentesIt = adyacentes.iterator();

				while (adyacentesIt.hasNext() && !resuelto) {
					Nodo nodoActual = adyacentesIt.next();
					if (!nodoActual.fueVisitado()) {
						nodoActual.setVisitado(true);
						colaDeVisitas.add(nodoActual);
						resuelto = estaResuelto(nodoActual);
						if (!recorrido.contains(nodoActual)) {
							recorrido.add(nodoActual);
						}
					}
				}
			}
		}

		return recorrido.size();
	}

	private boolean estaResuelto(Nodo estadoJuego) {
		return SOLUCION.equals(estadoJuego);
	}

	private void agregarAdyacentes(Nodo estadoJuego) {

		adyacentes = new ArrayList<Nodo>();
		//Obtiene la posicion del 0 en la instancia del juego
		int posicionLibre = estadoJuego.obtenerPosicionLibre();
		
		//Obtiene los movimientos posibles que se pueden realizar
		List<Integer> movimientosPosibles = estadoJuego.obtenerMovimientosPosibles(posicionLibre);
		
		Iterator<Integer> movimientosPosiblesIt = movimientosPosibles.iterator();
		while (movimientosPosiblesIt.hasNext()) {

			int indiceAReemplazar = movimientosPosiblesIt.next();

			//Genera una copia del estado del juego para poder reemplazar segun los movimientos posibles
			List<Integer> copiaEstadoJuego = new ArrayList<>();
			for (int i = 0; i < estadoJuego.getEstadoJuego().size(); i++) {
				copiaEstadoJuego.add(estadoJuego.getEstadoJuego().get(i));
			}

			Nodo nodo = new Nodo(copiaEstadoJuego);
			nodo.getEstadoJuego().set(posicionLibre, nodo.getEstadoJuego().get(indiceAReemplazar));
			nodo.getEstadoJuego().set(indiceAReemplazar, 0);
			if (!recorrido.contains(nodo)) {
				adyacentes.add(nodo);
			}
		}
	}

	public void imprimirRecorrido() {

		for (int i = 0; i < recorrido.size(); i++) {

			System.out.println(recorrido.get(i).getEstadoJuego().get(0) + " " + recorrido.get(i).getEstadoJuego().get(1)
					+ " " + recorrido.get(i).getEstadoJuego().get(2));
			System.out.println(recorrido.get(i).getEstadoJuego().get(3) + " " + recorrido.get(i).getEstadoJuego().get(4)
					+ " " + recorrido.get(i).getEstadoJuego().get(5));
			System.out.println(recorrido.get(i).getEstadoJuego().get(6) + " " + recorrido.get(i).getEstadoJuego().get(7)
					+ " " + recorrido.get(i).getEstadoJuego().get(8));
			System.out.println("\n");
		}
	}
}
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

	public Nodo resolver(Nodo nodoInicial) {

		Queue<Nodo> colaDeVisitas = new LinkedList<Nodo>();
		boolean resuelto = estaResuelto(nodoInicial);
		recorrido.add(nodoInicial);
		Nodo desacolado = null;
		Nodo nodo = null;

		if (!resuelto) {

			colaDeVisitas.add(nodoInicial);
			nodoInicial.setVisitado(true);
			nodoInicial.setPadre(null);

			while (!colaDeVisitas.isEmpty() && !resuelto) {

				desacolado = colaDeVisitas.remove();
				agregarAdyacentes(desacolado);
				Iterator<Nodo> adyacentesIt = adyacentes.iterator();

				while (adyacentesIt.hasNext() && !resuelto) {
					nodo = adyacentesIt.next();
					if (!nodo.fueVisitado()) {
						nodo.setVisitado(true);
						colaDeVisitas.add(nodo);
						resuelto = estaResuelto(nodo);
						if (!recorrido.contains(nodo)) {
							recorrido.add(nodo);
						}
					}
				}
			}
		}

		return nodo;
	}

	private boolean estaResuelto(Nodo estadoJuego) {
		return SOLUCION.equals(estadoJuego);
	}

	private void agregarAdyacentes(Nodo nodoPadre) {

		adyacentes = new ArrayList<Nodo>();
		// Obtiene la posicion del 0 en la instancia del juego
		int posicionLibre = nodoPadre.obtenerPosicionLibre();

		// Obtiene los movimientos posibles que se pueden realizar
		List<Integer> movimientosPosibles = nodoPadre.obtenerMovimientosPosibles(posicionLibre);

		Iterator<Integer> movimientosPosiblesIt = movimientosPosibles.iterator();
		while (movimientosPosiblesIt.hasNext()) {

			int indiceAReemplazar = movimientosPosiblesIt.next();

			// Genera una copia del estado del juego para poder reemplazar segun
			// los movimientos posibles
			List<Integer> copiaEstadoJuego = new ArrayList<>();
			for (int i = 0; i < nodoPadre.getEstadoJuego().size(); i++) {
				copiaEstadoJuego.add(nodoPadre.getEstadoJuego().get(i));
			}

			Nodo nodo = new Nodo(copiaEstadoJuego);
			nodo.getEstadoJuego().set(posicionLibre, nodo.getEstadoJuego().get(indiceAReemplazar));
			nodo.getEstadoJuego().set(indiceAReemplazar, 0);
			nodo.setPadre(nodoPadre);
			if (!recorrido.contains(nodo)) {
				adyacentes.add(nodo);
			}
		}
	}

	public Integer imprimirRecorrido(Nodo nodoInicial) {

		List<Nodo> listaAImprimir = new LinkedList<Nodo>();
		Nodo nodoSolucion = resolver(nodoInicial);
		Nodo nodoRecorrido = nodoSolucion;

		while (nodoRecorrido.getPadre() != null) {

			listaAImprimir.add(nodoRecorrido);
			nodoRecorrido = nodoRecorrido.getPadre();
		}

		for (int i = listaAImprimir.size() - 1; i >= 0; i--) {

			System.out.println(
					listaAImprimir.get(i).getEstadoJuego().get(0) + " " + listaAImprimir.get(i).getEstadoJuego().get(1)
							+ " " + listaAImprimir.get(i).getEstadoJuego().get(2));
			System.out.println(
					listaAImprimir.get(i).getEstadoJuego().get(3) + " " + listaAImprimir.get(i).getEstadoJuego().get(4)
							+ " " + listaAImprimir.get(i).getEstadoJuego().get(5));
			System.out.println(
					listaAImprimir.get(i).getEstadoJuego().get(6) + " " + listaAImprimir.get(i).getEstadoJuego().get(7)
							+ " " + listaAImprimir.get(i).getEstadoJuego().get(8));
			System.out.println("\n");
		}

		return listaAImprimir.size();
	}
}
package ar.edu.untref.ia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nodo {

	private Nodo padre;
	private List<Integer> estadoJuego;
	private Map<Integer, List<Integer>> movimientosPosibles = new HashMap<>();

	public Nodo(List<Integer> estadoJuego) {
		this.estadoJuego = estadoJuego;
		this.cargarMapaMovimientosPosibles();
	}

	private void cargarMapaMovimientosPosibles() {

		movimientosPosibles.put(0, Arrays.asList(1, 3));
		movimientosPosibles.put(1, Arrays.asList(0, 2, 4));
		movimientosPosibles.put(2, Arrays.asList(1, 5));
		movimientosPosibles.put(3, Arrays.asList(0, 4, 6));
		movimientosPosibles.put(4, Arrays.asList(1, 3, 5, 7));
		movimientosPosibles.put(5, Arrays.asList(2, 4, 8));
		movimientosPosibles.put(6, Arrays.asList(3, 7));
		movimientosPosibles.put(7, Arrays.asList(4, 6, 8));
		movimientosPosibles.put(8, Arrays.asList(5, 7));
	}

	public List<Integer> getEstadoJuego() {
		return estadoJuego;
	}

	public int obtenerPosicionLibre() {

		boolean encontrado = false;
		int i = 0;
		int posicionLibre = 0;

		while (i < 9 && !encontrado) {
			if (estadoJuego.get(i) == 0) {
				posicionLibre = i;
				encontrado = true;
			}
			i++;
		}

		return posicionLibre;
	}

	public List<Integer> obtenerMovimientosPosibles(int posicionLibre) {
		return movimientosPosibles.get(posicionLibre);
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	@Override
	public boolean equals(Object obj) {

		boolean iguales = true;
		int i = 0;
		
		Nodo nodo = (Nodo) obj;
		
		while (i < this.estadoJuego.size() && iguales) {
			if (estadoJuego.get(i) != nodo.estadoJuego.get(i)) {
				iguales = false;
			}
			i++;
		}
		
		return iguales;
	}
}
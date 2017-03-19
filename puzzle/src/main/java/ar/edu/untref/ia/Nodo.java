package ar.edu.untref.ia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nodo {

	private List<Integer> estadoJuego;
	private Boolean visitado;
	private Map<Integer, List<Integer>> movimientosPosibles = new HashMap<>();

	public Nodo(List<Integer> estadoJuego) {
		this.estadoJuego = estadoJuego;
		this.visitado = false;
		cargarMapaMovimientosPosibles();
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

	public Boolean fueVisitado() {
		return visitado;
	}

	public void setVisitado(Boolean visitado) {
		this.visitado = visitado;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (estadoJuego == null) {
			if (other.estadoJuego != null)
				return false;
		} else if (!estadoJuego.equals(other.estadoJuego))
			return false;
		return true;
	}
}
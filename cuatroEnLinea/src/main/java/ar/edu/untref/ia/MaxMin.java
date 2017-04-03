package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MaxMin {

	private final Integer PROFUNDIDAD = 6;
	int movimientosExplorados = 0;
	private String contenido = "O";

	public Casilla mejorJugadaMax(Tablero tablero) {

		List<Casilla> posicionesLibres = new ArrayList<>();
		Casilla mejorJugada = null;
		Casilla posicionLibreAOcupar;
		int maximaPonderacion = 0;
		int valorMinimo;

		posicionesLibres = tablero.obtenerPosicionesLibres(tablero);
		Iterator<Casilla> posicionesLibresIt = posicionesLibres.iterator();

		while (posicionesLibresIt.hasNext()) {

			posicionLibreAOcupar = posicionesLibresIt.next();

			ponderarPosicionesLibres(posicionesLibres);

			valorMinimo = valorMinimo(posicionesLibres, tablero, posicionLibreAOcupar);

			if (valorMinimo > maximaPonderacion) {
				maximaPonderacion = valorMinimo;
				mejorJugada = posicionLibreAOcupar;
			}
		}

		return mejorJugada;
	}

	private void ponderarPosicionesLibres(List<Casilla> posicionesLibres) {

		for (int indice = 0; indice < posicionesLibres.size(); indice++) {

			if (indice % 2 == 0) {
				posicionesLibres.get(indice).setPonderacion(10);
			} else {
				posicionesLibres.get(indice).setPonderacion(5);
			}

		}
	}

	private int valorMinimo(List<Casilla> posicionesLibres, Tablero tablero, Casilla casilla) {

		int mejorJugadaMinimo = 11;
		Tablero tableroCopiado;
		Casilla posibleJugada;
		List<Casilla> posicionesLibresDeLaJugada;
		boolean iterar = true;

		movimientosExplorados++;
		if (movimientosExplorados >= PROFUNDIDAD) {
			mejorJugadaMinimo = casilla.getPonderacion();
			iterar = false;
		}

		if (contenido.equals("O")) {
			contenido = "X";
		} else {
			contenido = "O";
		}

		Iterator<Casilla> posicionesLibresIt = posicionesLibres.iterator();
		while (posicionesLibresIt.hasNext() && iterar) {
			posibleJugada = posicionesLibresIt.next();
			posibleJugada.setContenido(contenido);
			tableroCopiado = copiarTablero(tablero);
			tableroCopiado.setPosicion(posibleJugada);
			posicionesLibresDeLaJugada = tableroCopiado.obtenerPosicionesLibres(tableroCopiado);
			ponderarPosicionesLibres(posicionesLibresDeLaJugada);
			mejorJugadaMinimo = minimo(mejorJugadaMinimo,
					valorMaximo(posicionesLibresDeLaJugada, tableroCopiado, posibleJugada));
		}

		return mejorJugadaMinimo;
	}

	private int valorMaximo(List<Casilla> posicionesLibres, Tablero tablero, Casilla casilla) {

		int mejorJugadaMaximo = 0;
		Tablero tableroCopiado;
		Casilla posibleJugada;
		List<Casilla> posicionesLibresDeLaJugada;
		boolean iterar = true;

		movimientosExplorados++;
		if (movimientosExplorados >= PROFUNDIDAD) {
			mejorJugadaMaximo = casilla.getPonderacion();
			iterar = false;
		}

		if (contenido.equals("O")) {
			contenido = "X";
		} else {
			contenido = "O";
		}

		Iterator<Casilla> posicionesLibresIt = posicionesLibres.iterator();
		while (posicionesLibresIt.hasNext() && iterar) {
			posibleJugada = posicionesLibresIt.next();
			posibleJugada.setContenido(contenido);
			tableroCopiado = copiarTablero(tablero);
			tableroCopiado.setPosicion(posibleJugada);
			posicionesLibresDeLaJugada = tableroCopiado.obtenerPosicionesLibres(tableroCopiado);
			ponderarPosicionesLibres(posicionesLibresDeLaJugada);

			mejorJugadaMaximo = maximo(mejorJugadaMaximo,
					valorMinimo(posicionesLibresDeLaJugada, tableroCopiado, posibleJugada));
		}

		return mejorJugadaMaximo;
	}

	private int minimo(int actual, int nuevo) {

		int minimo;

		if (actual <= nuevo) {
			minimo = actual;
		} else {
			minimo = nuevo;
		}

		return minimo;
	}

	private int maximo(int actual, int nuevo) {

		int maximo;

		if (actual >= nuevo) {
			maximo = actual;
		} else {
			maximo = nuevo;
		}

		return maximo;
	}

	private Tablero copiarTablero(Tablero tablero) {

		Tablero tableroCopiado = new Tablero();

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				tableroCopiado.setPosicion(i, j, tablero.getPosicion(i, j).getContenido());
			}
		}
		return tableroCopiado;
	}
}
package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MaxMin {

	private final Integer PROFUNDIDAD = 3;
	int movimientosExplorados = 0;
	private final String FICHA_JUGADOR_UNO = "O";

	public Casilla mejorJugadaMax(Tablero tablero) {

		Tablero tableroProbable;
		List<Casilla> posicionesLibres = new ArrayList<>();
		Casilla mejorJugada = null;
		Casilla posicionLibreAOcupar;
		int maximaPonderacion = 0;
		int valorMinimo;

		posicionesLibres = tablero.obtenerPosicionesLibres(tablero);
		Iterator<Casilla> posicionesLibresIt = posicionesLibres.iterator();

		while (posicionesLibresIt.hasNext()) {

			posicionLibreAOcupar = posicionesLibresIt.next();
			tableroProbable = copiarTablero(tablero);
			posicionLibreAOcupar.setContenido(FICHA_JUGADOR_UNO);
			posicionesLibres = ponderarPosicionesLibres(tableroProbable, posicionLibreAOcupar);

			valorMinimo = valorMinimo(posicionesLibres, tableroProbable);

			if (valorMinimo > maximaPonderacion) {
				maximaPonderacion = valorMinimo;
				mejorJugada = posicionLibreAOcupar;
			}
		}

		return mejorJugada;
	}

	private List<Casilla> ponderarPosicionesLibres(Tablero tablero, Casilla posicionLibreAOcupar) {

		List<Casilla> posicionesLibresDeLaJugada = new ArrayList<>();

		tablero.setPosicion(posicionLibreAOcupar);
		posicionesLibresDeLaJugada = tablero.obtenerPosicionesLibres(tablero);

		for (int indice = 0; indice < posicionesLibresDeLaJugada.size(); indice++) {

			posicionesLibresDeLaJugada.get(indice).setPonderacion(10);
		}

		return posicionesLibresDeLaJugada;
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

	private int valorMinimo(List<Casilla> posicionesLibres, Tablero tablero) {

		int mejorJugadaMinimo = 11;
		Casilla posibleJugada;

		Iterator<Casilla> posicionesLibresIt = posicionesLibres.iterator();

		while (posicionesLibresIt.hasNext() && movimientosExplorados <= PROFUNDIDAD) {

			posibleJugada = posicionesLibresIt.next();
			movimientosExplorados++;
			mejorJugadaMinimo = minimo(mejorJugadaMinimo,
					valorMaximo(ponderarPosicionesLibres(tablero, posibleJugada), tablero));
		}

		return mejorJugadaMinimo;
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

	private int valorMaximo(List<Casilla> posicionesLibres, Tablero tablero) {

		int mejorJugadaMaximo = 0;
		Casilla posibleJugada;

		Iterator<Casilla> posicionesLibresIt = posicionesLibres.iterator();
		while (posicionesLibresIt.hasNext() && movimientosExplorados <= PROFUNDIDAD) {

			posibleJugada = posicionesLibresIt.next();
			movimientosExplorados++;
			mejorJugadaMaximo = maximo(mejorJugadaMaximo,
					valorMinimo(ponderarPosicionesLibres(tablero, posibleJugada), tablero));
		}

		return mejorJugadaMaximo;
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
}
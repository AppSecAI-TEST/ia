package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MaxMin {

	private final String FICHA_JUGADOR_UNO = "O";
	private final String FICHA_JUGADOR_DOS = "X";
	private final String ESPACIO_LIBRE = "_";

	private final Integer PROFUNDIDAD = 6;

	int movimientosExplorados = 0;
	private String contenido = FICHA_JUGADOR_UNO;

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

			ponderarPosicionesLibres(posicionesLibres, tablero);

			valorMinimo = valorMinimo(posicionesLibres, tablero, posicionLibreAOcupar);

			if (valorMinimo > maximaPonderacion) {
				maximaPonderacion = valorMinimo;
				mejorJugada = posicionLibreAOcupar;
			}
		}

		return mejorJugada;
	}

	private void ponderarPosicionesLibres(List<Casilla> posicionesLibres, Tablero tablero) {

		int cantidadDeO;
		int cantidadDeX;
		int cantidadDe_;
		Casilla casillaLindante;

		for (int indice = 0; indice < posicionesLibres.size(); indice++) {

			cantidadDeO = 0;
			cantidadDeX = 0;
			cantidadDe_ = 0;

			int fila = posicionesLibres.get(indice).getFila();
			int columna = posicionesLibres.get(indice).getColumna();

			if (fila - 1 > 0 && columna - 1 > 0) {

				casillaLindante = tablero.getPosicion(fila - 1, columna - 1);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (fila - 1 > 0) {

				casillaLindante = tablero.getPosicion(fila - 1, columna);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (fila - 1 > 0 && columna + 1 < 7) {
				casillaLindante = tablero.getPosicion(fila - 1, columna + 1);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (columna - 1 > 0) {
				casillaLindante = tablero.getPosicion(fila, columna - 1);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (columna + 1 < 7) {
				casillaLindante = tablero.getPosicion(fila, columna + 1);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (fila + 1 < 6 && columna - 1 > 0) {
				casillaLindante = tablero.getPosicion(fila + 1, columna - 1);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (fila + 1 < 6) {
				casillaLindante = tablero.getPosicion(fila + 1, columna);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (fila + 1 < 6 && columna + 1 < 7) {
				casillaLindante = tablero.getPosicion(fila + 1, columna + 1);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			posicionesLibres.get(indice).setPonderacion(cantidadDeO + cantidadDe_ - cantidadDeX);
			// System.out.println("(" + posicionesLibres.get(indice).getFila() +
			// ", " +
			// posicionesLibres.get(indice).getColumna() + "): " +
			// (cantidadDeO + cantidadDe_ - cantidadDeX) + " ");
		}

	}

	private int esFicha(Casilla casillaLindante, String contenidoAComparar) {

		int valor = 0;

		if (casillaLindante.getContenido() == contenidoAComparar) {
			valor = 1;
		}

		return valor;
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
			ponderarPosicionesLibres(posicionesLibresDeLaJugada, tableroCopiado);
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
			ponderarPosicionesLibres(posicionesLibresDeLaJugada, tableroCopiado);

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
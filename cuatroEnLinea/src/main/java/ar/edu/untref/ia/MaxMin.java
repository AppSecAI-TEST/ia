package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MaxMin {

	private final String FICHA_JUGADOR_UNO = "O";
	private final String FICHA_JUGADOR_DOS = "X";
	private final String ESPACIO_LIBRE = "_";

	private final Integer PROFUNDIDAD = 50;

	int movimientosExplorados = 0;
	private String contenido = FICHA_JUGADOR_UNO;

	private boolean jugarDefensivo = false;
	private boolean jugarOfensivo = false;

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

	public Casilla mejorJugadaMin(Tablero tablero) {

		List<Casilla> posicionesLibres = new ArrayList<>();
		Casilla mejorJugada = null;
		Casilla posicionLibreAOcupar;
		int minimaPonderacion = 100;
		int valorMaximo;

		jugarDefensivo = false;
		jugarOfensivo = false;

		posicionesLibres = tablero.obtenerPosicionesLibres(tablero);
		Iterator<Casilla> posicionesLibresIt = posicionesLibres.iterator();

		while (posicionesLibresIt.hasNext()) {

			posicionLibreAOcupar = posicionesLibresIt.next();

			ponderarPosicionesLibres(posicionesLibres, tablero);

			valorMaximo = valorMaximo(posicionesLibres, tablero, posicionLibreAOcupar);

			if (valorMaximo < minimaPonderacion) {
				minimaPonderacion = valorMaximo;
				mejorJugada = posicionLibreAOcupar;
			}
		}

		return mejorJugada;
	}

	private void ponderarPosicionesLibres(List<Casilla> posicionesLibres, Tablero tablero) {

		int cantidadDeO = 0;
		int cantidadDeX = 0;
		int cantidadDe_ = 0;
		Casilla casillaLindante;
		int indice = 0;

		while (indice < posicionesLibres.size() && !jugarDefensivo && !jugarOfensivo) {

			cantidadDeO = 0;
			cantidadDeX = 0;
			cantidadDe_ = 0;

			int fila = posicionesLibres.get(indice).getFila();
			int columna = posicionesLibres.get(indice).getColumna();

			if (fila - 1 >= 0 && columna - 1 >= 0) {

				casillaLindante = tablero.getPosicion(fila - 1, columna - 1);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (fila - 1 >= 0) {

				casillaLindante = tablero.getPosicion(fila - 1, columna);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (fila - 1 >= 0 && columna + 1 < 7) {
				casillaLindante = tablero.getPosicion(fila - 1, columna + 1);
				cantidadDeO = cantidadDeO + esFicha(casillaLindante, FICHA_JUGADOR_UNO);
				cantidadDeX = cantidadDeX + esFicha(casillaLindante, FICHA_JUGADOR_DOS);
				cantidadDe_ = cantidadDe_ + esFicha(casillaLindante, ESPACIO_LIBRE);
			}

			if (columna - 1 >= 0) {
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

			if (fila + 1 < 6 && columna - 1 >= 0) {
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

			jugarOfensivo = jugarOfensivo(tablero, fila, columna);
			jugarDefensivo = jugarDefensivo(tablero, fila, columna);

			if (jugarOfensivo) {
				posicionesLibres.get(indice).setPonderacion(-5);
			} else if (jugarDefensivo) {
				posicionesLibres.get(indice).setPonderacion(-3);
			} else {
				if (cantidadDeO <= cantidadDeX) {
					posicionesLibres.get(indice).setPonderacion(10);
				}
				if (cantidadDeO < cantidadDeX) {
					posicionesLibres.get(indice).setPonderacion(1);
				}
				if (cantidadDeO == cantidadDeX) {
					posicionesLibres.get(indice).setPonderacion(5);
				}
			}
			indice++;
		}
	}

	private boolean jugarDefensivo(Tablero tablero, int fila, int columna) {

		boolean jugarDefensivo = false;
		int cantidadFichasJugadorUnoHorizontal = 0;
		int cantidadFichasJugadorUnoVertical = 0;
		int cantidadFichasJugadorUnoDiagonal1 = 0;
		int cantidadFichasJugadorUnoDiagonal2 = 0;

		// Horizontal Izquierda
		for (int i = 1; i <= 3; i++) {
			if (columna - i >= 0) {
				if (tablero.getPosicion(fila, columna - i).getContenido() == FICHA_JUGADOR_UNO) {
					cantidadFichasJugadorUnoHorizontal++;
				}
			}
		}

		// Horizontal Derecha
		for (int i = 1; i <= 3; i++) {
			if (columna + i < 7) {
				if (tablero.getPosicion(fila, columna + i).getContenido() == FICHA_JUGADOR_UNO) {
					cantidadFichasJugadorUnoHorizontal++;
				}
			}
		}

		// Vertical
		for (int i = 1; i <= 3; i++) {
			if (fila + i < 6) {
				if (tablero.getPosicion(fila + i, columna).getContenido() == FICHA_JUGADOR_UNO) {
					cantidadFichasJugadorUnoVertical++;
				}
			}
		}

		// Diagonal 1 Abajo
		for (int i = 1; i <= 3; i++) {
			if (fila + i < 6 && columna - i >= 0) {
				if (tablero.getPosicion(fila + i, columna - i).getContenido() == FICHA_JUGADOR_UNO) {
					cantidadFichasJugadorUnoDiagonal1++;
				}
			}
		}

		// Diagonal 1 Arriba
		for (int i = 1; i <= 3; i++) {
			if (fila - i >= 0 && columna + i < 7) {
				if (tablero.getPosicion(fila - i, columna + i).getContenido() == FICHA_JUGADOR_UNO) {
					cantidadFichasJugadorUnoDiagonal1++;
				}
			}
		}

		// Diagonal 2 Abajo
		for (int i = 1; i <= 3; i++) {
			if (fila + i < 6 && columna + i < 7) {
				if (tablero.getPosicion(fila + i, columna + i).getContenido() == FICHA_JUGADOR_UNO) {
					cantidadFichasJugadorUnoDiagonal2++;
				}
			}
		}

		// Diagonal 2 Arriba
		for (int i = 1; i <= 3; i++) {
			if (fila - i >= 0 && columna - i >= 0) {
				if (tablero.getPosicion(fila - i, columna - i).getContenido() == FICHA_JUGADOR_UNO) {
					cantidadFichasJugadorUnoDiagonal2++;
				}
			}
		}

		if (cantidadFichasJugadorUnoHorizontal == 3 || cantidadFichasJugadorUnoVertical == 3
				|| cantidadFichasJugadorUnoDiagonal1 == 3 || cantidadFichasJugadorUnoDiagonal2 == 3) {
			jugarDefensivo = true;
		}

		return jugarDefensivo;
	}

	private boolean jugarOfensivo(Tablero tablero, int fila, int columna) {

		boolean jugarOfensivo = false;
		int cantidadFichasJugadorUnoHorizontal = 0;
		int cantidadFichasJugadorUnoVertical = 0;
		int cantidadFichasJugadorUnoDiagonal1 = 0;
		int cantidadFichasJugadorUnoDiagonal2 = 0;

		// Horizontal Izquierda
		for (int i = 1; i <= 3; i++) {
			if (columna - i >= 0) {
				if (tablero.getPosicion(fila, columna - i).getContenido() == FICHA_JUGADOR_DOS) {
					cantidadFichasJugadorUnoHorizontal++;
				}
			}
		}

		// Horizontal Derecha
		for (int i = 1; i <= 3; i++) {
			if (columna + i < 7) {
				if (tablero.getPosicion(fila, columna + i).getContenido() == FICHA_JUGADOR_DOS) {
					cantidadFichasJugadorUnoHorizontal++;
				}
			}
		}

		// Vertical
		for (int i = 1; i <= 3; i++) {
			if (fila + i < 6) {
				if (tablero.getPosicion(fila + i, columna).getContenido() == FICHA_JUGADOR_DOS) {
					cantidadFichasJugadorUnoVertical++;
				}
			}
		}

		// Diagonal 1 Abajo
		for (int i = 1; i <= 3; i++) {
			if (fila + i < 6 && columna - i >= 0) {
				if (tablero.getPosicion(fila + i, columna - i).getContenido() == FICHA_JUGADOR_DOS) {
					cantidadFichasJugadorUnoDiagonal1++;
				}
			}
		}

		// Diagonal 1 Arriba
		for (int i = 1; i <= 3; i++) {
			if (fila - i >= 0 && columna + i < 7) {
				if (tablero.getPosicion(fila - i, columna + i).getContenido() == FICHA_JUGADOR_DOS) {
					cantidadFichasJugadorUnoDiagonal1++;
				}
			}
		}

		// Diagonal 2 Abajo
		for (int i = 1; i <= 3; i++) {
			if (fila + i < 6 && columna + i < 7) {
				if (tablero.getPosicion(fila + i, columna + i).getContenido() == FICHA_JUGADOR_DOS) {
					cantidadFichasJugadorUnoDiagonal2++;
				}
			}
		}

		// Diagonal 2 Arriba
		for (int i = 1; i <= 3; i++) {
			if (fila - i >= 0 && columna - i >= 0) {
				if (tablero.getPosicion(fila - i, columna - i).getContenido() == FICHA_JUGADOR_DOS) {
					cantidadFichasJugadorUnoDiagonal2++;
				}
			}
		}

		if (cantidadFichasJugadorUnoHorizontal == 3 || cantidadFichasJugadorUnoVertical == 3
				|| cantidadFichasJugadorUnoDiagonal1 == 3 || cantidadFichasJugadorUnoDiagonal2 == 3) {
			jugarOfensivo = true;
		}

		return jugarOfensivo;
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

		if (contenido.equals(FICHA_JUGADOR_UNO)) {
			contenido = FICHA_JUGADOR_DOS;
		} else {
			contenido = FICHA_JUGADOR_UNO;
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

		if (contenido.equals(FICHA_JUGADOR_UNO)) {
			contenido = FICHA_JUGADOR_DOS;
		} else {
			contenido = FICHA_JUGADOR_UNO;
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
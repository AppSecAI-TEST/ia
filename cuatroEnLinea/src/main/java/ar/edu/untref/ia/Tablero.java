package ar.edu.untref.ia;

public class Tablero {

	private final String ESPACIO_LIBRE = "_";
	private final int JUGADOR_UNO = 1;
	private final String FICHA_JUGADOR_UNO = "O";
	private final int JUGADOR_DOS = 2;
	private final String FICHA_JUGADOR_DOS = "X";

	private String[][] posiciones = new String[6][7];
	private boolean juegoTerminado = false;

	public Tablero() {

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				this.setPosicion(i, j, ESPACIO_LIBRE);
			}
		}
	}

	public void setPosicion(int i, int j, String ficha) {
		this.posiciones[i][j] = ficha;
	}

	public String getPosicion(int i, int j) {
		return this.posiciones[i][j];
	}

	public String[][] getPosiciones() {
		return this.posiciones;
	}

	public boolean jugar(int jugador, int columna) {

		int indiceColumna = columna - 1;
		boolean jugadaValida = hayEspacioEnLaColumna(indiceColumna);

		if (jugadaValida) {

			int filaLibre = obtenerFilaLibre(indiceColumna);

			if (jugador == JUGADOR_UNO) {
				this.posiciones[filaLibre][indiceColumna] = FICHA_JUGADOR_UNO;
			}

			if (jugador == JUGADOR_DOS) {
				this.posiciones[filaLibre][indiceColumna] = FICHA_JUGADOR_DOS;
			}
		}

		return jugadaValida;
	}

	private int obtenerFilaLibre(int columna) {

		boolean filaEncontrada = false;
		int filaLibre = 5;

		while (filaLibre >= 0 && !filaEncontrada) {
			if (this.getPosicion(filaLibre, columna) == ESPACIO_LIBRE) {
				filaEncontrada = true;
			}
			filaLibre--;
		}

		return filaLibre + 1;
	}

	private boolean hayEspacioEnLaColumna(int columna) {
		return (getPosicion(0, columna) == ESPACIO_LIBRE);
	}

	public String estadoTablero() {

		String estadoTablero = "";

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				estadoTablero = estadoTablero + " " + getPosicion(i, j);
			}
			estadoTablero = estadoTablero + "\n";
		}
		
		return estadoTablero;
	}

	public String estadoTableroConSubIndices() {

		String estadoTablero = "";

		for (int i = 0; i < 6; i++) {
			estadoTablero = estadoTablero + i + " ";
			for (int j = 0; j < 7; j++) {
				estadoTablero = estadoTablero + " " + getPosicion(i, j);
			}
			estadoTablero = estadoTablero + "\n";
		}

		estadoTablero = estadoTablero + "\n" + "   1 2 3 4 5 6 7";
		estadoTablero = estadoTablero + "\n" + "   0 1 2 3 4 5 6";
		return estadoTablero;
	}
	
	public boolean ganoElJuego(int filaJugada, int columnaJugada, String fichaJugada) {

		boolean gano = false;
		boolean horizontal = comprobarHorizontal(filaJugada, columnaJugada, fichaJugada);
		boolean vertical = comprobarVertical(filaJugada, columnaJugada, fichaJugada);
		boolean diagonalArribaDerecha = comprobarDiagonalArribaDerecha(filaJugada, columnaJugada, fichaJugada);
		boolean diagonalArribaIzquierda = comprobarDiagonalArribaIzquierda(filaJugada, columnaJugada, fichaJugada);
		gano = horizontal || vertical || diagonalArribaDerecha || diagonalArribaIzquierda;
		
		this.juegoTerminado = gano;
		
		return gano;
	}

	private boolean comprobarDiagonalArribaIzquierda(int filaJugada, int columnaJugada, String fichaJugada) {

		boolean gano = false;
		int cantidadFichasIguales = 0;
		int filaHaciaAbajo = filaJugada;
		int filaHaciaArriba = filaJugada;
		int columnaDerecha = columnaJugada;
		int columnaIzquierda = columnaJugada;
		String fichaEncontrada = fichaJugada;

		while (filaHaciaAbajo < 6 & columnaDerecha < 7 & !gano & (fichaEncontrada.equals(fichaJugada))) {
			fichaEncontrada = getPosicion(filaHaciaAbajo, columnaDerecha);
			if (fichaEncontrada.equals(fichaJugada)) {
				cantidadFichasIguales++;
				if (cantidadFichasIguales == 4) {
					gano = true;
				}
			}
			filaHaciaAbajo++;
			columnaDerecha++;
		}

		if (!gano) {
			cantidadFichasIguales--;
			fichaEncontrada = fichaJugada;
			while (filaHaciaArriba >= 0 & columnaIzquierda >= 0 & !gano & (fichaEncontrada.equals(fichaJugada))) {
				fichaEncontrada = getPosicion(filaHaciaArriba, columnaIzquierda);
				if (fichaEncontrada.equals(fichaJugada)) {
					cantidadFichasIguales++;
					if (cantidadFichasIguales == 4) {
						gano = true;
					}
				}
				filaHaciaArriba--;
				columnaIzquierda--;
			}
		}

		return gano;
	}

	private boolean comprobarDiagonalArribaDerecha(int filaJugada, int columnaJugada, String fichaJugada) {

		boolean gano = false;
		int cantidadFichasIguales = 0;
		int filaHaciaAbajo = filaJugada;
		int filaHaciaArriba = filaJugada;
		int columnaIzquierda = columnaJugada;
		int columnaDerecha = columnaJugada;
		String fichaEncontrada = fichaJugada;

		while (filaHaciaAbajo < 6 & columnaIzquierda >= 0 & !gano & (fichaEncontrada.equals(fichaJugada))) {
			fichaEncontrada = getPosicion(filaHaciaAbajo, columnaIzquierda);
			if (fichaEncontrada.equals(fichaJugada)) {
				cantidadFichasIguales++;
				if (cantidadFichasIguales == 4) {
					gano = true;
				}
			}
			filaHaciaAbajo++;
			columnaIzquierda--;
		}

		if (!gano) {
			cantidadFichasIguales--;
			fichaEncontrada = fichaJugada;
			while (filaHaciaArriba < 6 & columnaDerecha < 7 & !gano & (fichaEncontrada.equals(fichaJugada))) {
				fichaEncontrada = getPosicion(filaHaciaArriba, columnaDerecha);
				if (fichaEncontrada.equals(fichaJugada)) {
					cantidadFichasIguales++;
					if (cantidadFichasIguales == 4) {
						gano = true;
					}
				}
				filaHaciaArriba++;
				columnaDerecha++;
			}
		}

		return gano;
	}

	private boolean comprobarVertical(int filaJugada, int columnaJugada, String fichaJugada) {

		boolean gano = false;
		int cantidadFichasIguales = 0;
		String fichaEncontrada = fichaJugada;

		while (filaJugada < 6 & !gano & (fichaEncontrada.equals(fichaJugada))) {
			fichaEncontrada = getPosicion(filaJugada, columnaJugada);
			if (fichaEncontrada.equals(fichaJugada)) {
				cantidadFichasIguales++;
				if (cantidadFichasIguales == 4) {
					gano = true;
				}
			}
			filaJugada++;
		}

		return gano;
	}

	private boolean comprobarHorizontal(int filaJugada, int columnaJugada, String fichaJugada) {

		boolean gano = false;
		int cantidadFichasIguales = 0;
		int indiceIzquierda = columnaJugada;
		int indiceDerecha = columnaJugada;
		String fichaEncontrada = fichaJugada;

		while (indiceIzquierda >= 0 & !gano & (fichaEncontrada.equals(fichaJugada))) {
			fichaEncontrada = getPosicion(filaJugada, indiceIzquierda);
			if (fichaEncontrada.equals(fichaJugada)) {
				cantidadFichasIguales++;
				if (cantidadFichasIguales == 4) {
					gano = true;
				}
			}
			indiceIzquierda--;
		}

		if (!gano) {
			cantidadFichasIguales--;
			fichaEncontrada = fichaJugada;
			while (indiceDerecha < 7 & !gano & (fichaEncontrada.equals(fichaJugada))) {
				fichaEncontrada = getPosicion(filaJugada, indiceDerecha);
				if (fichaEncontrada.equals(fichaJugada)) {
					cantidadFichasIguales++;
					if (cantidadFichasIguales == 4) {
						gano = true;
					}
				}
				indiceDerecha++;
			}
		}

		return gano;
	}

	public boolean elJuegoTermino() {
		return juegoTerminado;
	}
}
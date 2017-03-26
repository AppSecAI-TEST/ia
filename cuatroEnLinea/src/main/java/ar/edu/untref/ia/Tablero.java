package ar.edu.untref.ia;

public class Tablero {

	private final String ESPACIO_LIBRE = "_";
	private final int JUGADOR_UNO = 1;
	private final String FICHA_JUGADOR_UNO = "O";
	private final int JUGADOR_DOS = 2;
	private final String FICHA_JUGADOR_DOS = "X";
	
	private String[][] posiciones = new String[6][7];

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

		boolean jugadaValida = hayEspacioEnLaColumna(columna);
		int indiceColumna = columna - 1;
		
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

	public boolean ganoElJuego(int filaJugada, int columnaJugada, String fichaJugada) {
		
		boolean gano = false;
		
		boolean izquierda = comprobarALaIzquierda(filaJugada, columnaJugada, fichaJugada);
		boolean derecha = comprobarALaDerecha(filaJugada, columnaJugada, fichaJugada);
		
		gano = derecha || izquierda;
		
		return gano;
	}

	private boolean comprobarALaIzquierda(int filaJugada, int columnaJugada, String fichaJugada) {
		
		boolean gano = false;
		int cantidadFichasIguales = 0;
		
		while(columnaJugada >= 0 & !gano) {
			if (getPosicion(filaJugada, columnaJugada) == fichaJugada) {
				cantidadFichasIguales++;
				if (cantidadFichasIguales == 4) {
					gano = true;
				}
			}
			columnaJugada--;
		}
		
		return gano;
	}

	private boolean comprobarALaDerecha(int filaJugada, int columnaJugada, String fichaJugada) {
		
		boolean gano = false;
		int cantidadFichasIguales = 0;
		
		while(columnaJugada < 7 & !gano) {
			if (getPosicion(filaJugada, columnaJugada) == fichaJugada) {
				cantidadFichasIguales++;
				if (cantidadFichasIguales == 4) {
					gano = true;
				}
			}
			columnaJugada++;
		}
		
		return gano;
	}

}
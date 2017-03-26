package ar.edu.untref.ia;

public class Tablero {

	private final int JUGADOR_UNO = 1;
	private final String FICHA_JUGADOR_UNO = "O";
	private final int JUGADOR_DOS = 2;
	private final String FICHA_JUGADOR_DOS = "X";
	
	private String[][] posiciones = new String[7][6];

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
		int filaLibre = 0;
		
		while (filaLibre < 6 && !filaEncontrada) {
			if (this.getPosicion(filaLibre, columna) != null) {
				filaEncontrada = true;
			}
			filaLibre++;
		}
		
		return filaLibre - 1;
	}

	private boolean hayEspacioEnLaColumna(int columna) {
		return (getPosicion(0, columna) == null);
	}
}
package ar.edu.untref.ia;

public class Tablero {

	private String [][] posiciones = new String[7][6]; 
			 
	public String getPosicion(int i, int j) {
		return this.posiciones[i][j];
	}
}

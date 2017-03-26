package ar.edu.untref.ia;

public class Tablero {

	private String [][] posiciones = new String[7][6]; 
			 

	public void setPosicion(int i, int j, String ficha) {
		this.posiciones[i][j] = ficha;
	}
	
	public String getPosicion(int i, int j) {
		return this.posiciones[i][j];
	}

	public String [][] getPosiciones() {
		return this.posiciones;
	}



}

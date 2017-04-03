package ar.edu.untref.ia;

public class Casilla {

	private final String ESPACIO_LIBRE = "_";

	private int fila;
	private int columna;
	private int ponderacion;
	private String contenido;

	public Casilla(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.ponderacion = 0;
		this.contenido = ESPACIO_LIBRE;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(int ponderacion) {
		this.ponderacion = ponderacion;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}
}
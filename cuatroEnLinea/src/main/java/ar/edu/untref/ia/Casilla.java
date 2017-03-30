package ar.edu.untref.ia;

public class Casilla {

	private final String ESPACIO_LIBRE = "_";

	private int ponderacion;
	private String contenido;

	public Casilla() {
		this.ponderacion = 0;
		this.contenido = ESPACIO_LIBRE;
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
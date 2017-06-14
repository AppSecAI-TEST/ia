package ar.edu.untref.ia;

public class Situacion {

	private int puntosJugador;
	private int puntosBanca;
	private Accion accion;

	public Situacion(int puntosJugador, int puntosBanca, Accion accion) {
		this.puntosJugador = puntosJugador;
		this.puntosBanca = puntosBanca;
		this.accion = accion;
	}

	public int getPuntosJugador() {
		return puntosJugador;
	}

	public int getPuntosBanca() {
		return puntosBanca;
	}

	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	@Override
	public boolean equals(Object otroObjeto) {
		boolean equals = false;
		if (otroObjeto == null || !(otroObjeto instanceof Situacion)) {
			equals = false;
		}
		if (otroObjeto == this) {
			equals = true;
		}
		Situacion otraSituacion = (Situacion) otroObjeto;
		if (otraSituacion.getPuntosJugador() == this.puntosJugador
				&& otraSituacion.getPuntosBanca() == this.puntosBanca
				&& otraSituacion.getAccion() == this.accion) {
			equals = true;
		}
		return equals;
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode = 31 * hashCode + this.puntosJugador;
		hashCode = 31 * hashCode + this.puntosBanca;
		if (this.accion == null) {
			hashCode = 31 * hashCode;
		} else {
			hashCode = 31 * hashCode + this.accion.hashCode();
		}
		return hashCode;
	}
}
package ar.edu.untref.ia;

public class Situacion {

	private int puntosDelJugador;
	private int puntosDeLaBanca;
	private Accion accion;

	public Situacion(int puntosDelJugador, int puntosDeLaBanca, Accion accion) {
		this.puntosDelJugador = puntosDelJugador;
		this.puntosDeLaBanca = puntosDeLaBanca;
		this.accion = accion;
	}

	public int getPuntosDelJugador() {
		return puntosDelJugador;
	}

	public int getPuntosDeLaBanca() {
		return puntosDeLaBanca;
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
		if (otraSituacion.getPuntosDelJugador() == this.puntosDelJugador
				&& otraSituacion.getPuntosDeLaBanca() == this.puntosDeLaBanca
				&& otraSituacion.getAccion() == this.accion) {
			equals = true;
		}
		return equals;
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode = 31 * hashCode + this.puntosDelJugador;
		hashCode = 31 * hashCode + this.puntosDeLaBanca;
		if (this.accion == null) {
			hashCode = 31 * hashCode;
		} else {
			hashCode = 31 * hashCode + this.accion.hashCode();
		}
		return hashCode;
	}

}
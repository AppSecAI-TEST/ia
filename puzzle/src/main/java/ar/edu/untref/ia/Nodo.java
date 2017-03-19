package ar.edu.untref.ia;

import java.util.List;

public class Nodo {

	private List<Integer> estadoJuego;
	private Boolean visitado;

	public Nodo(List<Integer> estadoJuego) {
		this.estadoJuego = estadoJuego;
		this.visitado = false;
	}

	public List<Integer> getEstadoJuego() {
		return estadoJuego;
	}

	public Boolean fueVisitado() {
		return visitado;
	}

	public void setVisitado(Boolean visitado) {
		this.visitado = visitado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (estadoJuego == null) {
			if (other.estadoJuego != null)
				return false;
		} else if (!estadoJuego.equals(other.estadoJuego))
			return false;
		return true;
	}
}
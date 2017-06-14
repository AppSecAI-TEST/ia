package ar.edu.untref.ia;

public class Juego {

	private boolean terminado;
	private Jugador jugador;
	private Jugador banca;
	private Maso maso;
	private int puntosJugador;
	private int puntosBanca;

	public Juego() {

		this.terminado = false;
		this.jugador = new Jugador();
		this.banca = new Jugador();
		this.maso = new Maso();
		this.puntosJugador = 0;
		this.puntosBanca = 0;
		comenzar();
	}

	private void comenzar() {

		this.jugador.pedir(this.maso);
		this.jugador.pedir(this.maso);
		this.banca.pedir(this.maso);
		actualizarPuntuacion();
	}

	private void actualizarPuntuacion() {
		this.puntosJugador = this.jugador.getPuntos();
		this.puntosBanca = this.banca.getPuntos();
	}

	protected boolean estaTerminado() {
		return this.terminado;
	}

	protected Jugador getJugador() {
		return this.jugador;
	}

	protected Jugador getBanca() {
		return this.banca;
	}

	protected Maso getMaso() {
		return this.maso;
	}

	protected void setMaso(Maso maso) {
		this.maso = maso;
	}

	protected int getPuntosJugador() {
		return this.puntosJugador;
	}

	protected int getPuntosBanca() {
		return this.puntosBanca;
	}

	protected int jugada(Accion accion) {

		if (accion == Accion.PEDIR) {
			this.jugador.pedir(this.maso);
			this.actualizarPuntuacion();
		} else if (accion == Accion.MANTENERSE) {
			this.terminado = true;
			while (this.puntosBanca < 17) {
				this.banca.pedir(this.maso);
				this.actualizarPuntuacion();
			}
		}
		return this.calcularRecompensaJugador();
	}

	/**
	 * recompensa 1: jugador gana recompensa 0: empate recompensa -1: banca gana
	 */
	private int calcularRecompensaJugador() {

		int recompensa = 0;
		if (this.puntosJugador > 21) {
			this.terminado = true;
			recompensa = -1;
		} else {
			if (this.puntosBanca > 21) {
				recompensa = 1;
			} else if (this.puntosJugador < this.puntosBanca) {
				recompensa = -1;
			} else if (this.puntosJugador == this.puntosBanca) {
				recompensa = 0;
			} else if (this.puntosJugador > this.puntosBanca) {
				recompensa = 1;
			}
		}
		return recompensa;
	}
}
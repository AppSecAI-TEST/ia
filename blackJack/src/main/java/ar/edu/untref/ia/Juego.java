package ar.edu.untref.ia;

public class Juego {

	private Jugador jugador;
	private Jugador banca;
	private Maso maso;
	private boolean etapaDeAprendizaje;
	private boolean estaTerminado;
	private int puntosDelJugador;
	private int puntosDeLaBanca;
	private boolean jugadorSePlanto;

	public Juego(boolean etapaDeAprendizaje) {
		this.jugador = new Jugador();
		this.banca = new Jugador();
		this.maso = new Maso();
		this.puntosDelJugador = 0;
		this.puntosDeLaBanca = 0;
		this.etapaDeAprendizaje = etapaDeAprendizaje;
		this.estaTerminado = false;
		this.jugadorSePlanto = false;
		this.comienzo();
	}

	public void comienzo() {
		this.jugador.pedirCarta(this.maso);
		this.jugador.pedirCarta(this.maso);
		this.banca.pedirCarta(this.maso);
		this.actualizarPuntuacion();
		this.imprimirEstadoDelJuego(true, false, false);
	}

	protected boolean estaTerminado() {
		return this.estaTerminado;
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

	protected int getPuntosDelJugador() {
		return this.puntosDelJugador;
	}

	protected int getPuntosDeLaBanca() {
		return this.puntosDeLaBanca;
	}

	private void imprimirEstadoDelJuego(boolean inicioDelJuego, boolean jugadorPide, boolean jugadorDobla) {
		if (!etapaDeAprendizaje) {
			if (inicioDelJuego) {
				System.out.println("\n****************************");
				System.out.println("******** BLACK JACK ********");
				System.out.println("****************************");
				System.out.println("Reparto inicial de cartas...\n");
			} else {
				if (jugadorPide) {
					System.out.println("\n------------------------------------");
					System.out.println("Siguiente jugada: JUGADOR PIDE CARTA \n");
				} else if (jugadorDobla) {
					System.out.println("\n-------------------------------");
					System.out.println("Siguiente jugada: JUGADOR DOBLA APUESTA\n");
				} else {
					if (!jugadorSePlanto) {
						jugadorSePlanto = true;
						if (!jugadorDobla) {
							System.out.println("\nATENCION: EL JUGADOR SE PLANTA");
						}
					}
					System.out.println("\n----------------------------------");
					System.out.println("Siguiente jugada: BANCA PIDE CARTA \n");
				}
			}System.out.println("CARTAS DE LA BANCA: ");
			for (String cartaDeLaBanca : banca.getMano()) {
				System.out.println(cartaDeLaBanca);
			}
			System.out.println("\nPuntaje de la banca: " + this.banca.getPuntos() + "\n");
			System.out.println("CARTAS DEL JUGADOR: ");
			for (String cartaDelJugador : jugador.getMano()) {
				System.out.println(cartaDelJugador);
			}
			System.out.println("\nPuntaje del jugador: " + this.jugador.getPuntos());
		}
	}

	private void actualizarPuntuacion() {
		this.puntosDelJugador = this.jugador.getPuntos();
		this.puntosDeLaBanca = this.banca.getPuntos();
	}

	protected int jugada(Accion accion) {
		if (accion == Accion.PEDIR) {
			this.jugador.pedirCarta(this.maso);
			imprimirEstadoDelJuego(false, true, false);
			this.actualizarPuntuacion();
		} else if (accion == Accion.DOBLAR) {
			this.jugador.pedirCarta(this.maso);
			imprimirEstadoDelJuego(false, false, true);
			this.actualizarPuntuacion();
			this.estaTerminado = true;
			if (this.puntosDelJugador < 22) {
				while (this.puntosDeLaBanca < 17) {
					this.banca.pedirCarta(this.maso);
					imprimirEstadoDelJuego(false, false, false);
					this.actualizarPuntuacion();
				}
			}
		} else if (accion == Accion.PLANTARSE) {
			this.estaTerminado = true;
			while (this.puntosDeLaBanca < 17) {
				this.banca.pedirCarta(this.maso);
				imprimirEstadoDelJuego(false, false, false);
				this.actualizarPuntuacion();
			}
		}
		return this.calcularRecompensaJugador();
	}

	/**
	 * recompensa 1: jugador gana
	 * recompensa 0: empate
	 * recompensa -1: banca gana
	 */
	private int calcularRecompensaJugador() {
		int recompensa = 0;
		if (this.puntosDelJugador > 21) {
			this.estaTerminado = true;
			recompensa = -1;
			this.imprimirSiCorresponde(!etapaDeAprendizaje, this.estaTerminado, "\nEl jugador supero los 21 puntos: GANA LA BANCA");
		} else {
			if (this.puntosDeLaBanca > 21) {
				recompensa = 1;
				this.imprimirSiCorresponde(!etapaDeAprendizaje, this.estaTerminado, "\nLa banca supero los 21 puntos: GANA EL JUGADOR");
			} else if (this.puntosDelJugador < this.puntosDeLaBanca) {
				recompensa = -1;
				this.imprimirSiCorresponde(!etapaDeAprendizaje, this.estaTerminado, "\nLa banca tiene mas puntos que el jugador: GANA LA BANCA");
			} else if (this.puntosDelJugador == this.puntosDeLaBanca) {
				recompensa = 0;
				this.imprimirSiCorresponde(!etapaDeAprendizaje, this.estaTerminado, "\nEL juego ha terminado: EMPATE");
			} else if (this.puntosDelJugador > this.puntosDeLaBanca) {
				recompensa = 1;
				this.imprimirSiCorresponde(!etapaDeAprendizaje, this.estaTerminado, "\nEl jugador tiene mas puntos que la banca: GANA EL JUGADOR");
			}
		}
		return recompensa;
	}

	private void imprimirSiCorresponde(boolean noAprendizaje, boolean terminado, String mensaje) {
		if (noAprendizaje && terminado) {
			System.out.println(mensaje);
		}
	}

}
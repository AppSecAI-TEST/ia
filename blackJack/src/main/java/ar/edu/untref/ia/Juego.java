package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;

public class Juego {

	private Jugador jugador;
	private Jugador banca;
	private Maso maso;
	private boolean etapaDeAprendizaje;
	private boolean juegoHaTerminado;
	private int puntosDelJugador;
	private int puntosDeLaBanca;
	private boolean jugadorSePlanto;
	private List<String> cartasDelJugador = new ArrayList<>();
	private List<String> cartasDeLaBanca = new ArrayList<>();

	public Juego(boolean etapaDeAprendizaje) {
		this.jugador = new Jugador();
		this.banca = new Jugador();
		this.maso = new Maso();
		this.puntosDelJugador = 0;
		this.puntosDeLaBanca = 0;
		this.etapaDeAprendizaje = etapaDeAprendizaje;
		this.juegoHaTerminado = false;
		this.jugadorSePlanto = false;
		this.comienzo();
	}

	public void comienzo() {
		cartasDelJugador.add(this.jugador.pedir(this.maso));
		cartasDelJugador.add(this.jugador.pedir(this.maso));
		cartasDeLaBanca.add(this.banca.pedir(this.maso));
		actualizarPuntuacion();
		imprimirEstadoDelJuego(true, false, false);
	}

	private void imprimirEstadoDelJuego(boolean inicioDelJuego, boolean jugadorPide, boolean jugadorDobla) {
		if (!etapaDeAprendizaje) {
			if (inicioDelJuego) {
				System.out.println("\n");
				System.out.println("****************************");
				System.out.println("******** BLACK JACK ********");
				System.out.println("****************************");
				System.out.println("Reparto inicial de cartas...");
				System.out.println("\n");
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
			}
			System.out.println("CARTAS DE LA BANCA: ");
			for (String cartaDeLaBanca : cartasDeLaBanca) {
				System.out.println(cartaDeLaBanca);
			}
			System.out.println("\nPuntaje de la banca: " + this.banca.getPuntos() + "\n");
			System.out.println("CARTAS DEL JUGADOR: ");
			for (String cartaDelJugador : cartasDelJugador) {
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
			cartasDelJugador.add(this.jugador.pedir(this.maso));
			imprimirEstadoDelJuego(false, true, false);
			this.actualizarPuntuacion();
		} else if (accion == Accion.DOBLAR) {
			cartasDelJugador.add(this.jugador.pedir(this.maso));
			imprimirEstadoDelJuego(false, false, true);
			this.actualizarPuntuacion();
			this.juegoHaTerminado = true;
			if (this.puntosDelJugador < 22) {
				while (this.puntosDeLaBanca < 17) {
					cartasDeLaBanca.add(this.banca.pedir(this.maso));
					imprimirEstadoDelJuego(false, false, false);
					this.actualizarPuntuacion();
				}
			}
		} else if (accion == Accion.PLANTARSE) {
			this.juegoHaTerminado = true;
			while (this.puntosDeLaBanca < 17) {
				cartasDeLaBanca.add(this.banca.pedir(this.maso));
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
			this.juegoHaTerminado = true;
			recompensa = -1;
			this.imprimirSiCorresponde(!etapaDeAprendizaje, this.juegoHaTerminado, "\nEl jugador supero los 21 puntos: GANA LA BANCA");
		} else {
			if (this.puntosDeLaBanca > 21) {
				recompensa = 1;
				this.imprimirSiCorresponde(!etapaDeAprendizaje, this.juegoHaTerminado, "\nLa banca supero los 21 puntos: GANA EL JUGADOR");
			} else if (this.puntosDelJugador < this.puntosDeLaBanca) {
				recompensa = -1;
				this.imprimirSiCorresponde(!etapaDeAprendizaje, this.juegoHaTerminado, "\nLa banca tiene mas puntos que el jugador: GANA LA BANCA");
			} else if (this.puntosDelJugador == this.puntosDeLaBanca) {
				recompensa = 0;
				this.imprimirSiCorresponde(!etapaDeAprendizaje, this.juegoHaTerminado, "\nEL juego ha terminado: EMPATE");
			} else if (this.puntosDelJugador > this.puntosDeLaBanca) {
				recompensa = 1;
				this.imprimirSiCorresponde(!etapaDeAprendizaje, this.juegoHaTerminado, "\nEl jugador tiene mas puntos que la banca: GANA EL JUGADOR");
			}
		}
		return recompensa;
	}

	private void imprimirSiCorresponde(boolean noAprendizaje, boolean terminado, String mensaje) {
		if (noAprendizaje && terminado) {
			System.out.println(mensaje);
		}

	}

	protected boolean estaTerminado() {
		return this.juegoHaTerminado;
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
}
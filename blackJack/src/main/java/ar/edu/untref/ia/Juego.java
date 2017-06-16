package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;

public class Juego {

	private boolean aprendizaje;
	private boolean terminado;
	private Jugador jugador;
	private Jugador banca;
	private Maso maso;
	private int puntosJugador;
	private int puntosBanca;

	List<String> cartasJugador = new ArrayList<>();
	List<String> cartasBanca = new ArrayList<>();

	public Juego(boolean aprendizaje) {
		this.aprendizaje = aprendizaje;
		this.terminado = false;
		this.jugador = new Jugador();
		this.banca = new Jugador();
		this.maso = new Maso();
		this.puntosJugador = 0;
		this.puntosBanca = 0;
		comenzar();
	}

	public void comenzar() {

		cartasJugador.add(this.jugador.pedir(this.maso));
		cartasJugador.add(this.jugador.pedir(this.maso));
		cartasBanca.add(this.banca.pedir(this.maso));
		actualizarPuntuacion();
		imprimirEstadoJuego(true, false);
	}

	private void imprimirEstadoJuego(boolean inicioJuego, boolean pideJugador) {

		if (!aprendizaje) {

			if (inicioJuego) {
				System.out.println("\n");
				System.out.println("******************");
				System.out.println("*** BLACK JACK ***");
				System.out.println("******************");
				System.out.println("Se reparten las cartas ...");
				System.out.println("\n");
			} else {
				System.out.println("Siguiente jugada ...");
			}

			if (pideJugador) {
				System.out.println("JUGADOR PIDE CARTA \n");
			} else if (!inicioJuego) {
				System.out.println("BANCA PIDE CARTA \n");
			}

			System.out.println("CARTAS BANCA: ");
			for (String cartaBanca : cartasBanca) {
				System.out.println(cartaBanca);
			}
			System.out.println("\n Puntos Banca:" + this.banca.getPuntos());

			System.out.println("\n");

			System.out.println("CARTAS JUGADOR: ");
			for (String cartaJugador : cartasJugador) {
				System.out.println(cartaJugador);
			}
			System.out.println("\n Puntos Jugador:" + this.jugador.getPuntos());

			System.out.println("\n");
		}
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
			cartasJugador.add(this.jugador.pedir(this.maso));
			imprimirEstadoJuego(false, true);
			this.actualizarPuntuacion();
		} else if (accion == Accion.MANTENERSE) {
			this.terminado = true;
			while (this.puntosBanca < 17) {
				cartasBanca.add(this.banca.pedir(this.maso));
				imprimirEstadoJuego(false, false);
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
			this.imprimirSiCorresponde(!aprendizaje, "El jugador se paso de 21, GANA LA BANCA");
		} else {
			if (this.puntosBanca > 21) {
				recompensa = 1;
				this.imprimirSiCorresponde(!aprendizaje, "La banca se paso de 21, GANA EL JUGADOR");
			} else if (this.puntosJugador < this.puntosBanca) {
				recompensa = -1;
				this.imprimirSiCorresponde(!aprendizaje, "La banca tiene mas puntos que el jugador, GANA LA BANCA");
			} else if (this.puntosJugador == this.puntosBanca) {
				recompensa = 0;
				this.imprimirSiCorresponde(!aprendizaje, "Juego terminado, EMPATE");
			} else if (this.puntosJugador > this.puntosBanca) {
				recompensa = 1;
				if (this.terminado) {
					this.imprimirSiCorresponde(!aprendizaje,
							"El jugador tiene mas puntos que la banca, GANA EL JUGADOR");
				}
			}
		}
		return recompensa;
	}

	private void imprimirSiCorresponde(boolean imprimir, String mensaje) {
		if (imprimir) {
			System.out.println(mensaje);
		}

	}
}
package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;

public class Juego {

	private boolean terminado;
	private Jugador jugador;
	private Jugador banca;
	private Maso maso;
	private int puntosJugador;
	private int puntosBanca;
	
	List<String> cartasJugador = new ArrayList<>();
	List<String> cartasBanca = new ArrayList<>();

	public Juego() {

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
		imprimirEstadoJuego(true);		
	}

	private void imprimirEstadoJuego(boolean inicioJuego) {
				
		if (inicioJuego) {
			System.out.println("\n");
			System.out.println("******************");
			System.out.println("*** BLACK JACK ***");
			System.out.println("******************");
			System.out.println("\n");
			System.out.println("Situación inicial ...");
			System.out.println("\n");
		} else {
			System.out.println("Nueva situación ...");
			System.out.println("\n");			
		}
		
		System.out.println("Cartas Banca: ");
		for(String cartaBanca : cartasBanca) {
			System.out.println(cartaBanca);
		}
		
		System.out.println("\n");
		
		System.out.println("Cartas Jugador: ");
		for(String cartaJugador : cartasJugador) {
			System.out.println(cartaJugador);
		}
		
		System.out.println("\n");
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
			imprimirEstadoJuego(false);
			this.actualizarPuntuacion();
		} else if (accion == Accion.MANTENERSE) {
			this.terminado = true;
			while (this.puntosBanca < 17) {
				cartasBanca.add(this.banca.pedir(this.maso));
				imprimirEstadoJuego(false);
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
			System.out.println("El jugador se paso de 21, GANA LA BANCA");
		} else {
			if (this.puntosBanca > 21) {
				recompensa = 1;
				System.out.println("La banca se paso de 21, GANA EL JUGADOR");
			} else if (this.puntosJugador < this.puntosBanca) {
				recompensa = -1;
				System.out.println("La banca hizo mas puntos que el jugador, GANA LA BANCA");
			} else if (this.puntosJugador == this.puntosBanca) {
				recompensa = 0;
				System.out.println("Juego terminado, EMPATE");
			} else if (this.puntosJugador > this.puntosBanca) {
				recompensa = 1;
				System.out.println("El jugador hizo mas puntos que la banca, GANA EL JUGADOR");
			}
		}
		return recompensa;
	}
}
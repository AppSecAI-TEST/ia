package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class BlackJack {

	public Accion politicaRandom() {

		Accion accionRandom = Accion.PEDIR;

		if (Math.random() >= 0.5) {
			accionRandom = Accion.MANTENERSE;
		}
		return accionRandom;
	}

	public Accion politicaGolosaEpsilon(Double epsilon, Map<Situacion, Double> valueFunction, int puntosJugador,
			int puntosBanca) {

		// Exploracion
		if (Math.random() < epsilon) {
			return politicaRandom();
		} else {
			// Explotacion
			return mejorPolitica(valueFunction, puntosJugador, puntosBanca);
		}
	}

	private Accion mejorPolitica(Map<Situacion, Double> valueFunction, int puntosJugador, int puntosBanca) {

		Accion mejorAccion;
		Situacion situacionPedir = new Situacion(puntosJugador, puntosBanca, Accion.PEDIR);
		Situacion situacionMantenerse = new Situacion(puntosJugador, puntosBanca, Accion.MANTENERSE);
		Double valorPedir = valueFunction.getOrDefault(situacionPedir, 0.0);
		Double valorMantenerse = valueFunction.getOrDefault(situacionMantenerse, 0.0);
		if (valorPedir > valorMantenerse) {
			mejorAccion = Accion.PEDIR;
		} else if (valorMantenerse > valorPedir) {
			mejorAccion = Accion.MANTENERSE;
		} else {
			mejorAccion = politicaRandom();
		}
		return mejorAccion;
	}

	public void iteracion(int iteraciones, boolean politicaGolosaEpsilon, boolean mejorPolitica) {

		List<Integer> recordGanados = new ArrayList<>();
		Double epsilon;
		int nZero = 100;
		int ganados = 0;
		// (jugador, banca, accion)
		Map<Situacion, Double> valueFunction = new HashMap<>();
		// (jugador, banca)
		Map<Situacion, Integer> contadorEstado = new HashMap<>();
		// (jugador, banca, accion)
		Map<Situacion, Integer> contadorEstadoAccion = new HashMap<>();

		for (int i = 0; i < iteraciones; i++) {

			Juego juego = new Juego();
			int puntosActualesJugador = juego.getPuntosJugador();
			int puntosActualesBanca = juego.getPuntosBanca();
			Accion accionActual = null;
			Integer recompensaActual = null;
			Situacion situacionActual = new Situacion(puntosActualesJugador, puntosActualesBanca, accionActual);

			List<Situacion> observadorClaves = new ArrayList<>();
			while (!juego.estaTerminado()) {

				int numeroCartasRestantes = juego.getMaso().getCartasRestantes().size();
				if (numeroCartasRestantes < 52 * 0.6) {
					juego.setMaso(new Maso());
				}

				if (accionActual == null && recompensaActual == null) {
					accionActual = this.politicaRandom();
				} else if (accionActual != Accion.MANTENERSE && recompensaActual != -1) {
					epsilon = (double) (nZero / (nZero + contadorEstado.getOrDefault(situacionActual, 0)));

					if (politicaGolosaEpsilon) {
						accionActual = politicaGolosaEpsilon(epsilon, valueFunction, puntosActualesJugador,
								puntosActualesBanca);
					}
					if (mejorPolitica) {
						accionActual = mejorPolitica(valueFunction, puntosActualesJugador, puntosActualesBanca);
					}
				} else {
					accionActual = this.politicaRandom();
				}
				situacionActual.setAccion(accionActual);
				if (!observadorClaves.contains(situacionActual) && puntosActualesJugador <= 21) {
					observadorClaves.add(situacionActual);
				}

				recompensaActual = juego.jugada(accionActual);
			}

			this.QLearning(recompensaActual, observadorClaves, contadorEstado, contadorEstadoAccion, valueFunction);

			if (i > iteraciones * 0.8) {
				if (recompensaActual == 1) {
					ganados += 1;
				}
			}
			recordGanados.add(recompensaActual);
		}
		System.out.println("Ganados: " + (ganados / (iteraciones * 0.2)) * 100);

		System.out.println("Value function: ");
		for (Situacion key : valueFunction.keySet()) {
			System.out.println(Integer.toString(key.getPuntosJugador()) + " " + Integer.toString(key.getPuntosBanca())
					+ " " + key.getAccion() + " = " + valueFunction.get(key));
		}
		System.out.println("Contador estado: ");
		for (Situacion key : contadorEstado.keySet()) {
			System.out.println(Integer.toString(key.getPuntosJugador()) + " " + Integer.toString(key.getPuntosBanca())
					+ " = " + contadorEstado.get(key));
		}
		System.out.println("Contador estado accion: ");
		for (Situacion key : contadorEstadoAccion.keySet()) {
			System.out.println(Integer.toString(key.getPuntosJugador()) + " " + Integer.toString(key.getPuntosBanca())
					+ " " + key.getAccion() + " = " + contadorEstadoAccion.get(key));
		}

	}

	private void QLearning(Integer recompensa, List<Situacion> observadorClaves, Map<Situacion, Integer> contadorEstado,
			Map<Situacion, Integer> contadorEstadoAccion, Map<Situacion, Double> valueFunction) {

		Double nuevoD = 0.0;

		if (recompensa != null) {
			ListIterator<Situacion> situacionIterator = observadorClaves.listIterator();

			while (situacionIterator.hasNext()) {
				Situacion situacionActual = situacionIterator.next();
				contadorEstado.put(situacionActual, contadorEstado.getOrDefault(situacionActual, 0) + 1);
				contadorEstadoAccion.put(situacionActual, contadorEstadoAccion.getOrDefault(situacionActual, 0) + 1);
				double alpha = 1.0 / contadorEstadoAccion.get(situacionActual);
				double anterior = valueFunction.getOrDefault(situacionActual, 0.0);
				if (situacionIterator.hasNext()) {
					Situacion situacionSiguiente = situacionIterator.next();
					Situacion situacionPedir = new Situacion(situacionSiguiente.getPuntosJugador() + 1,
							situacionSiguiente.getPuntosBanca(), situacionSiguiente.getAccion());
					Situacion situacionMantenerse = new Situacion(situacionSiguiente.getPuntosJugador(),
							situacionSiguiente.getPuntosBanca(), situacionSiguiente.getAccion());
					Double valorMaximo = maximo(valueFunction.get(situacionPedir),
							valueFunction.get(situacionMantenerse));
					nuevoD = valorMaximo * 0.8;
				}
				situacionActual = situacionIterator.previous();
				valueFunction.put(situacionActual, (1 - alpha) * anterior + alpha * (recompensa + nuevoD));
				situacionIterator.next();
			}
		}
	}

	private Double maximo(Double double1, Double double2) {
		if (double1 >= double2) {
			return double1;
		} else {
			return double2;
		}
	}

}
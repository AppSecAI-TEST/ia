package ar.edu.untref.ia;

import java.util.*;

public class BlackJack {

	public Accion politicaRandom() {
		Accion accionRandom = Accion.PEDIR;
		if (Math.random() > 0.666) {
			accionRandom = Accion.DOBLAR;
		} else if (Math.random() > 0.333) {
			accionRandom = Accion.PLANTARSE;
		}
		return accionRandom;
	}

	public Accion politicaEpsilonGreedy(Double epsilon, Map<Situacion, Double> tablaDeDecisiones, int puntosJugador,
										int puntosBanca) {
		// Exploracion
		if (Math.random() < epsilon) {
			return politicaRandom();
		} else {
			// Explotacion
			return mejorPolitica(tablaDeDecisiones, puntosJugador, puntosBanca);
		}
	}

	private Accion mejorPolitica(Map<Situacion, Double> tablaDeDecisiones, int puntosJugador, int puntosBanca) {
		Situacion situacionPedir = new Situacion(puntosJugador, puntosBanca, Accion.PEDIR);
		Situacion situacionDoblar = new Situacion(puntosJugador, puntosBanca, Accion.DOBLAR);
		Situacion situacionMantenerse = new Situacion(puntosJugador, puntosBanca, Accion.PLANTARSE);
		Map<Double, Accion> valorDeLasAcciones = new HashMap<>();
		valorDeLasAcciones.put(tablaDeDecisiones.getOrDefault(situacionPedir, 0.0), Accion.PEDIR);
		valorDeLasAcciones.put(tablaDeDecisiones.getOrDefault(situacionDoblar, 0.0), Accion.DOBLAR);
		valorDeLasAcciones.put(tablaDeDecisiones.getOrDefault(situacionMantenerse, 0.0), Accion.PLANTARSE);
		return valorDeLasAcciones.get(Collections.max(valorDeLasAcciones.keySet()));
	}

	public void jugar(int partidas) {
		/* Aprende a jugar con 500000 juegos */
		Map<Situacion, Double> tablaDeDecisiones = this.iteracion(500000, true);
		/* Se simulan partidas */
		for (int i = 0; i <= partidas; i++) {
			Juego juego = new Juego(false);
			while (!juego.estaTerminado()) {
				int numeroDeCartasRestantes = juego.getMaso().getCartasRestantes().size();
				if (numeroDeCartasRestantes < 52 * 0.6) {
					juego.setMaso(new Maso());
				}
				Double puntajeDeAccionPedir = tablaDeDecisiones
						.get(new Situacion(juego.getPuntosDelJugador(), juego.getPuntosDeLaBanca(), Accion.PEDIR));
				Double puntajeDeAccionDoblar = tablaDeDecisiones
						.get(new Situacion(juego.getPuntosDelJugador(), juego.getPuntosDeLaBanca(), Accion.DOBLAR));
				Double puntajeDeAccionMantenerse = tablaDeDecisiones
						.get(new Situacion(juego.getPuntosDelJugador(), juego.getPuntosDeLaBanca(), Accion.PLANTARSE));
				Map<Double, Accion> puntajeDeLasAcciones = new HashMap<>();
				puntajeDeLasAcciones.put(puntajeDeAccionPedir, Accion.PEDIR);
				puntajeDeLasAcciones.put(puntajeDeAccionDoblar, Accion.DOBLAR);
				puntajeDeLasAcciones.put(puntajeDeAccionMantenerse, Accion.PLANTARSE);
				if (puntajeDeAccionPedir == null || puntajeDeAccionDoblar == null || puntajeDeAccionMantenerse == null) {
					juego.jugada(Accion.PLANTARSE);
				} else {
					juego.jugada(puntajeDeLasAcciones.get(Collections.max(puntajeDeLasAcciones.keySet())));
				}
			}
		}
	}

	private void QLearning(Integer recompensa, List<Situacion> situacionesYaOcurridas,
						   Map<Situacion, Integer> contadorEstado, Map<Situacion, Integer> contadorEstadoAccion,
						   Map<Situacion, Double> tablaDeDecisiones) {
		Double valorMaximoFuturo = 0.0;
		if (recompensa != null) {
			ListIterator<Situacion> situacionesYaOcurridasIterator = situacionesYaOcurridas.listIterator();
			while (situacionesYaOcurridasIterator.hasNext()) {
				Situacion situacionActual = situacionesYaOcurridasIterator.next();
				contadorEstado.put(situacionActual, contadorEstado.getOrDefault(situacionActual, 0) + 1);
				contadorEstadoAccion.put(situacionActual, contadorEstadoAccion.getOrDefault(situacionActual, 0) + 1);
				double factorDeAprendizaje = 1.0 / contadorEstadoAccion.get(situacionActual);
				double QValorPrevio = tablaDeDecisiones.getOrDefault(situacionActual, 0.0);
				if (situacionesYaOcurridasIterator.hasNext()) {
					Situacion situacionSiguiente = situacionesYaOcurridasIterator.next();
					Situacion situacionDoblar = new Situacion(situacionSiguiente.getPuntosJugador() + 1,
							situacionSiguiente.getPuntosBanca(), situacionSiguiente.getAccion());
					Situacion situacionPedir = new Situacion(situacionSiguiente.getPuntosJugador() + 1,
							situacionSiguiente.getPuntosBanca(), situacionSiguiente.getAccion());
					Situacion situacionMantenerse = new Situacion(situacionSiguiente.getPuntosJugador(),
							situacionSiguiente.getPuntosBanca(), situacionSiguiente.getAccion());
					valorMaximoFuturo = Math.max(Math.max(tablaDeDecisiones.getOrDefault(situacionDoblar, 0.0), tablaDeDecisiones.getOrDefault(situacionPedir, 0.0)),
							tablaDeDecisiones.getOrDefault(situacionMantenerse, 0.0)) * 0.8;
				}
				situacionActual = situacionesYaOcurridasIterator.previous();
				tablaDeDecisiones.put(situacionActual, (1 - factorDeAprendizaje) * QValorPrevio + factorDeAprendizaje * (recompensa + valorMaximoFuturo));
				situacionesYaOcurridasIterator.next();
			}
		}
	}

	public Map<Situacion, Double> iteracion(int numeroDeIteraciones, boolean etapaDeAprendizaje) {
		int nZero = 100;
		Map<Situacion, Double> tablaDeDecisiones = new HashMap<>();
		Map<Situacion, Integer> contadorEstado = new HashMap<>();
		Map<Situacion, Integer> contadorEstadoAccion = new HashMap<>();
		List<Situacion> situacionesYaOcurridas = new ArrayList<>();
		for (int i = 0; i < numeroDeIteraciones; i++) {
			Juego juego = new Juego(etapaDeAprendizaje);
			int puntosActualesJugador = juego.getPuntosDelJugador();
			int puntosActualesBanca = juego.getPuntosDeLaBanca();
			Accion accionActual = null;
			Integer recompensaActual = null;
			Situacion situacionActual = new Situacion(puntosActualesJugador, puntosActualesBanca, accionActual);
			while (!juego.estaTerminado()) {
				if (juego.getMaso().getCartasRestantes().size() < 52 * 0.6) {
					juego.setMaso(new Maso());
				}
				if (accionActual == null && recompensaActual == null) {
					accionActual = this.politicaRandom();
				} else if (accionActual != Accion.PLANTARSE && recompensaActual != -1) {
					Double epsilon = (double) (nZero / (nZero + contadorEstado.getOrDefault(situacionActual, 0)));
					if (etapaDeAprendizaje) {
						accionActual = politicaEpsilonGreedy(epsilon, tablaDeDecisiones, puntosActualesJugador,
								puntosActualesBanca);
					}
					if (!etapaDeAprendizaje) {
						accionActual = mejorPolitica(tablaDeDecisiones, puntosActualesJugador, puntosActualesBanca);
					}
				} else {
					accionActual = this.politicaRandom();
				}
				situacionActual.setAccion(accionActual);
				if (!situacionesYaOcurridas.contains(situacionActual) && puntosActualesJugador < 22) {
					situacionesYaOcurridas.add(situacionActual);
				}
				recompensaActual = juego.jugada(accionActual);
				this.QLearning(recompensaActual, situacionesYaOcurridas, contadorEstado, contadorEstadoAccion,
						tablaDeDecisiones);
			}
		}
		return tablaDeDecisiones;
	}

}
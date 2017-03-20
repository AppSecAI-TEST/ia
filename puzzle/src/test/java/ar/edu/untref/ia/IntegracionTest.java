package ar.edu.untref.ia;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class IntegracionTest {

	private Integer cantidadDePosicionesEvaluadas;

	@Test
	public void deberiaEvaluarSolo1Posicion() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

		cantidadDePosicionesEvaluadas = puzzle.resolver(instanciaDeJuego);

		// comprobación
		assertTrue(cantidadDePosicionesEvaluadas == 1);
	}

	@Test
	public void deberiaEvaluar2Posiciones() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 0, 2, 3, 4, 5, 6, 7, 8));

		cantidadDePosicionesEvaluadas = puzzle.resolver(instanciaDeJuego);

		// comprobación
		assertTrue(cantidadDePosicionesEvaluadas == 2);
	}

	@Test
	public void deberiaEvaluar4Posiciones() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 2, 0, 3, 4, 5, 6, 7, 8));

		cantidadDePosicionesEvaluadas = puzzle.resolver(instanciaDeJuego);

		// comprobación
		assertTrue(cantidadDePosicionesEvaluadas == 4);
	}

	@Test
	public void deberiaEvaluar10Posiciones() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 2, 5, 3, 4, 0, 6, 7, 8));

		cantidadDePosicionesEvaluadas = puzzle.resolver(instanciaDeJuego);

		// comprobación
		assertTrue(cantidadDePosicionesEvaluadas == 10);
	}

//	@Test
//	public void deberia() {
//
//		// inicialización
//		Puzzle puzzle = new Puzzle();
//		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 2, 6, 3, 4, 5, 0, 7, 8));
//
//		cantidadDePosicionesEvaluadas = puzzle.resolver(instanciaDeJuego);
//	
//		// comprobación
//		assertTrue(cantidadDePosicionesEvaluadas == 10);
//	}
}
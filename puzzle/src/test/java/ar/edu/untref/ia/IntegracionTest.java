package ar.edu.untref.ia;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class IntegracionTest {

	private Integer cantidadMovimientos;

	@Test
	public void deberiaDevolverQueNoRealizoNingunMovimiento() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

		cantidadMovimientos = puzzle.resolver(instanciaDeJuego);

		// comprobación
		assertTrue(cantidadMovimientos == 0);
	}

	@Test
	public void deberiaDevolverQueRealizoUnMovimiento() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 0, 2, 3, 4, 5, 6, 7, 8));

		cantidadMovimientos = puzzle.resolver(instanciaDeJuego);

		// comprobación
		assertTrue(cantidadMovimientos == 1);
	}

	@Test
	public void deberiaDevolverQueRealizoDosMovimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 2, 0, 3, 4, 5, 6, 7, 8));

		cantidadMovimientos = puzzle.resolver(instanciaDeJuego);

		// comprobación
		assertTrue(cantidadMovimientos == 2);
	}
}
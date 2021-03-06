package ar.edu.untref.ia;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class IntegracionTest {

	@Test
	public void deberiaResolverElJuegoEn0Movimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 0);
	}

	@Test
	public void deberiaResolverElJuegoEn2Movimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 3, 0, 5, 6, 7, 8));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 2);
	}

	@Test
	public void deberiaResolverElJuegoEn3Movimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 3, 7, 5, 6, 0, 8));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 3);
	}

	@Test
	public void deberiaResolverElJuegoEn4Movimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 3, 7, 5, 0, 6, 8));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 4);
	}
	
	@Test
	public void deberiaResolverElJuegoEn4MovimientosBis() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 3, 7, 5, 6, 8, 0));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 4);
	}
	
	@Test
	public void deberiaResolverElJuegoEn5Movimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 0, 7, 5, 3, 6, 8));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 5);
	}
	
	@Test
	public void deberiaResolverElJuegoEn6Movimientos() {
		
		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 7, 0, 5, 3, 6, 8));
		
		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);
		
		Assert.assertTrue(cantidadDeMovimientos == 6);
	}

	@Test
	public void deberiaResolverElJuegoEn7Movimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 0, 3, 8, 6, 5, 7));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 7);
	}

	@Test
	public void deberiaResolverElJuegoEn8Movimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 6, 3, 8, 0, 5, 7));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 8);
	}
}
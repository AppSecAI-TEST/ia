package ar.edu.untref.ia;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class IntegracionTest {

	@Test
	public void deberiaResolverElJuegoEn8Movimientos() {

		// inicialización
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 4, 2, 6, 3, 8, 0, 5, 7));

		int cantidadDeMovimientos = puzzle.imprimirRecorrido(instanciaDeJuego);

		Assert.assertTrue(cantidadDeMovimientos == 8);
	}

}
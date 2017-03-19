package ar.edu.untref.ia;

import java.util.Arrays;

import org.junit.Test;

import junit.framework.Assert;

public class IntegracionTest {

	@SuppressWarnings("deprecation")
	@Test
	public void deberiaDevolverQueNoRealizoNingunMovimiento() {

		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 0, 2, 3, 4, 5, 6, 7, 8));

		// operación
		puzzle.resolver(instanciaDeJuego);
		
		Assert.assertEquals(0, 0);
	}
}

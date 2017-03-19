package ar.edu.untref.ia;

import java.util.Arrays;

import org.junit.Test;

public class IntegracionTest {
	
	@Test
	public void deberiaDevolverQueNoRealizoNingunMovimiento() {
		
		Puzzle puzzle = new Puzzle();
		Nodo instanciaDeJuego = new Nodo(Arrays.asList(1, 0, 5, 6, 4, 7, 2, 3, 8));
		
		//operación
		puzzle.resolver(instanciaDeJuego);
	}	
}
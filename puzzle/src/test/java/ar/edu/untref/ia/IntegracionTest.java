package ar.edu.untref.ia;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class IntegracionTest {
	
	@Test
	public void deberiaDevolverQueNoRealizoNingunMovimiento() {
		
		Puzzle puzzle = new Puzzle();
		List<Integer> instancia = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
		
		//operación
		Integer cantidadMovimientos = puzzle.resolver(instancia);
		
		Assert.assertTrue(0 == cantidadMovimientos);
	}	
}
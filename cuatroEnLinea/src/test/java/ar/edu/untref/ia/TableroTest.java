package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

public class TableroTest {

	@Test
	public void seCreaUnTableroVacio() {

		int i = 0;
		int j = 0;
		boolean posicionLibre = true;

		Tablero tablero = new Tablero();

		while (i < 7 && posicionLibre) {
			while (j < 6 && posicionLibre) {
				if (tablero.getPosicion(i, j) != null) {
					posicionLibre = false;
				}
				j++;
			}
			i++;
		}

		Assert.assertTrue(posicionLibre);
	}
}
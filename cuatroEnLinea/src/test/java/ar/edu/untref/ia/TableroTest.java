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

	@Test
	public void elTableroInformaQueUnaPosicionCualquieraEstaLibre() {

		boolean posicionLibre = true;
		Tablero tablero = new Tablero();

		String posicion = tablero.getPosicion(1, 3);
		if (posicion != null) {
			posicionLibre = false;
		}

		Assert.assertTrue(posicionLibre);
	}

	@Test
	public void elTableroInformaQueUnaPosicionEstaOcupada() {

		boolean posicionLibre = true;
		Tablero tablero = new Tablero();
		tablero.setPosicion(1, 3, "X");

		String posicion = tablero.getPosicion(1, 3);
		if (posicion != null) {
			posicionLibre = false;
		}

		Assert.assertTrue(!posicionLibre);
		Assert.assertTrue(tablero.getPosicion(1, 3) == "X");
	}
}
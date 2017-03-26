package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

public class TableroTest {

	private final int JUGADOR_UNO = 1;
	private final String FICHA_JUGADOR_UNO = "O";
	private final int JUGADOR_DOS = 2;
	private final String FICHA_JUGADOR_DOS = "X";

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
		tablero.setPosicion(1, 3, FICHA_JUGADOR_DOS);

		String posicion = tablero.getPosicion(1, 3);
		if (posicion != null) {
			posicionLibre = false;
		}

		Assert.assertTrue(!posicionLibre);
		Assert.assertTrue(tablero.getPosicion(1, 3) == FICHA_JUGADOR_DOS);
	}

	@Test
	public void elJugador1HaceSuPrimerJugadaEnLaColumna5() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 5);

		Assert.assertTrue(tablero.getPosicion(5, 4) == FICHA_JUGADOR_UNO);
	}
}
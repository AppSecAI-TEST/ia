package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

public class TableroTest {

	private final String ESPACIO_LIBRE = "_";
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

		while (i < 6 && posicionLibre) {
			while (j < 7 && posicionLibre) {
				if (tablero.getPosicion(i, j) != ESPACIO_LIBRE) {
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
		if (posicion != ESPACIO_LIBRE) {
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
		if (posicion != ESPACIO_LIBRE) {
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

	@Test
	public void seJuegaLaSecuenciaYElTableroQuedaSegunLoEsperado() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 4);
		tablero.jugar(JUGADOR_DOS, 4);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 3);

		Assert.assertTrue(tablero.getPosicion(5, 3) == FICHA_JUGADOR_UNO);
		Assert.assertTrue(tablero.getPosicion(4, 3) == FICHA_JUGADOR_DOS);
		Assert.assertTrue(tablero.getPosicion(5, 2) == FICHA_JUGADOR_UNO);
		Assert.assertTrue(tablero.getPosicion(5, 1) == FICHA_JUGADOR_DOS);
		Assert.assertTrue(tablero.getPosicion(4, 2) == FICHA_JUGADOR_UNO);
		Assert.assertTrue(tablero.getPosicion(3, 2) == FICHA_JUGADOR_DOS);
	}

	@Test
	public void elJugadorUnoGanaEnHorizontal() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 4);
		tablero.jugar(JUGADOR_DOS, 4);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 3);
		tablero.jugar(JUGADOR_UNO, 5);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 6);

		Assert.assertTrue(tablero.ganoElJuego(5, 6 - 1, FICHA_JUGADOR_UNO));
	}

	@Test
	public void elJugadorUnoGanaEnHorizontalConUnaFichaEntreMedio() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 6);
		tablero.jugar(JUGADOR_DOS, 1);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 3);
		tablero.jugar(JUGADOR_UNO, 5);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 4);

		Assert.assertTrue(tablero.ganoElJuego(5, 4 - 1, FICHA_JUGADOR_UNO));
	}

	@Test
	public void elJugadorDosGanaEnHorizontal() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 3);
		tablero.jugar(JUGADOR_UNO, 1);
		tablero.jugar(JUGADOR_DOS, 5);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 5);
		tablero.jugar(JUGADOR_UNO, 2);
		tablero.jugar(JUGADOR_DOS, 6);
		tablero.jugar(JUGADOR_UNO, 2);
		tablero.jugar(JUGADOR_DOS, 7);
		tablero.jugar(JUGADOR_UNO, 1);
		tablero.jugar(JUGADOR_DOS, 4);

		Assert.assertTrue(tablero.ganoElJuego(5, 4 - 1, FICHA_JUGADOR_DOS));
	}

	@Test
	public void elJugadorDosGanaEnHorizontalConUnaFichaEntreMedio() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 3);
		tablero.jugar(JUGADOR_UNO, 1);
		tablero.jugar(JUGADOR_DOS, 5);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 5);
		tablero.jugar(JUGADOR_UNO, 2);
		tablero.jugar(JUGADOR_DOS, 4);
		tablero.jugar(JUGADOR_UNO, 2);
		tablero.jugar(JUGADOR_DOS, 7);
		tablero.jugar(JUGADOR_UNO, 1);
		tablero.jugar(JUGADOR_DOS, 6);

		Assert.assertTrue(tablero.ganoElJuego(5, 6 - 1, FICHA_JUGADOR_DOS));
	}

	@Test
	public void elJugadorUnoGanaEnVertical() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 4);
		tablero.jugar(JUGADOR_DOS, 4);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 3);
		tablero.jugar(JUGADOR_UNO, 5);
		tablero.jugar(JUGADOR_DOS, 1);
		tablero.jugar(JUGADOR_UNO, 5);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 5);
		tablero.jugar(JUGADOR_DOS, 4);
		tablero.jugar(JUGADOR_UNO, 5);

		Assert.assertTrue(tablero.ganoElJuego(2, 5 - 1, FICHA_JUGADOR_UNO));
	}

	@Test
	public void elJugadorUnoGanaEnDiagonalHaciaArribaDerecha() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 3);
		tablero.jugar(JUGADOR_UNO, 1);
		tablero.jugar(JUGADOR_DOS, 5);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 5);
		tablero.jugar(JUGADOR_UNO, 2);
		tablero.jugar(JUGADOR_DOS, 4);
		tablero.jugar(JUGADOR_UNO, 2);
		tablero.jugar(JUGADOR_DOS, 7);
		tablero.jugar(JUGADOR_UNO, 4);
		tablero.jugar(JUGADOR_DOS, 1);
		tablero.jugar(JUGADOR_UNO, 5);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 6);
		tablero.jugar(JUGADOR_DOS, 6);
		tablero.jugar(JUGADOR_UNO, 4);
		tablero.jugar(JUGADOR_DOS, 6);
		tablero.jugar(JUGADOR_UNO, 6);

		Assert.assertTrue(tablero.ganoElJuego(2, 6 - 1, FICHA_JUGADOR_UNO));
	}

	@Test
	public void elJugadorDosGanaEnDiagonalHaciaArribaIzquierda() {

		Tablero tablero = new Tablero();

		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 3);
		tablero.jugar(JUGADOR_UNO, 1);
		tablero.jugar(JUGADOR_DOS, 5);
		tablero.jugar(JUGADOR_UNO, 3);
		tablero.jugar(JUGADOR_DOS, 5);
		tablero.jugar(JUGADOR_UNO, 2);
		tablero.jugar(JUGADOR_DOS, 4);
		tablero.jugar(JUGADOR_UNO, 2);
		tablero.jugar(JUGADOR_DOS, 7);
		tablero.jugar(JUGADOR_UNO, 5);
		tablero.jugar(JUGADOR_DOS, 2);
		tablero.jugar(JUGADOR_UNO, 6);
		tablero.jugar(JUGADOR_DOS, 6);
		tablero.jugar(JUGADOR_UNO, 7);
		tablero.jugar(JUGADOR_DOS, 4);

		Assert.assertTrue(tablero.ganoElJuego(4, 4 - 1, FICHA_JUGADOR_DOS));
	}
}
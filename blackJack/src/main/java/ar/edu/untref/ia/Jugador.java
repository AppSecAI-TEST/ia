package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

	private int puntos;
	private List<String> mano;

	public Jugador() {
		this.puntos = 0;
		this.mano = new ArrayList<>();
	}

	protected int getPuntos() {
		return this.puntos;
	}

	protected List<String> getMano() {
		return this.mano;
	}

	protected void pedir(Maso maso) {
		String carta = maso.getCarta();
		this.mano.add(carta);
		this.calcularPuntuacion();
	}

	private void calcularPuntuacion() {

		int tempPuntos = 0;
		int numeroDeAces = 0;
		for (String cartas : this.mano) {
			String[] cartasSplit = cartas.split(" ");
			if (this.isInteger(cartasSplit[0])) {
				tempPuntos += Integer.parseInt(cartasSplit[0]);
			} else if (cartasSplit[0] == "Ace") {
				tempPuntos += 11;
				numeroDeAces += 1;
			} else {
				tempPuntos += 10;
			}
		}

		while (numeroDeAces > 0 && tempPuntos > 21) {
			numeroDeAces -= 1;
			tempPuntos -= 10;
		}
		this.puntos = tempPuntos;
	}

	private boolean isInteger(String string) {
		if (string == null || string.trim().isEmpty()) {
			return false;
		}
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
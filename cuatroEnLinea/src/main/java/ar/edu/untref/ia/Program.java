package ar.edu.untref.ia;

import java.util.Scanner;

public class Program {

	private static Scanner scanner;
	private static Tablero tablero;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		int opcionSeleccionada = 0;

		while (opcionSeleccionada != 3) {

			limpiarPantalla();
			visualizarMenu();
			opcionSeleccionada = Integer.parseInt(scanner.nextLine());

			switch (opcionSeleccionada) {
			case 1:
				limpiarPantalla();
				jugar1VS1();
				break;
			case 2:
				System.out.println("Eligió la opcion 2");
				break;
			case 3:
				break;
			default:
				System.out.println("Debe seleccionar una opción valida.");
				break;
			}
		}
	}

	private static void jugar1VS1() {

		int jugadorActual = 1;
		int columnaJugada = 0;

		tablero = new Tablero();

		while (!tablero.elJuegoTermino()) {

			System.out.println("");
			System.out.println(tablero.estadoTablero());
			System.out.println("");

			System.out.println("Seleccione la columna para su ficha - Jugador: " + jugadorActual);

			columnaJugada = Integer.parseInt(scanner.nextLine());
			boolean jugadaValida = tablero.jugar(jugadorActual, columnaJugada);

			while (!jugadaValida) {
				System.out.println("La jugada ingresada no es valida - Ingrese otra columna: ");
				jugadaValida = tablero.jugar(jugadorActual, Integer.parseInt(scanner.nextLine()));
			}

			if (jugadorActual == 1) {
				jugadorActual = 2;
			} else {
				jugadorActual = 1;
			}

			limpiarPantalla();

			if (tablero.elJuegoTermino()) {
				System.out.println("GANO EL JUEGO, FELICITACIONES!!!!");
				System.out.println("Presione una tecla para continuar...");
				scanner.nextLine();
				limpiarPantalla();
				visualizarMenu();

			}
		}
	}

	private static void limpiarPantalla() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void visualizarMenu() {

		System.out.println("*******************");
		System.out.println("CUATRO EN LINEA!!!!");
		System.out.println("*******************");
		System.out.println("");
		System.out.println("");
		System.out.println("1 - 1 VS 1");
		System.out.println("2 - 1 VS PC");
		System.out.println("");
		System.out.println("3 - SALIR");
		System.out.println("");
		System.out.println("*******************");
		System.out.println("Ingrese la opción:");
	}
}

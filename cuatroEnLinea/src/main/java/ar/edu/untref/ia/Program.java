package ar.edu.untref.ia;

import java.util.Scanner;

public class Program {

	private final static int JUGADOR_UNO = 1;
	private final static int JUGADOR_DOS = 2;
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
				limpiarPantalla();
				jugar1VSPC();
				break;
			case 3:
				break;
			default:
				System.out.println("Debe seleccionar una opción valida, presione una tecla para continuar...");
				scanner.nextLine();
				break;
			}
		}
	}

	private static void jugar1VS1() {

		int jugadorActual = JUGADOR_UNO;
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

			if (jugadorActual == JUGADOR_UNO) {
				jugadorActual = JUGADOR_DOS;
			} else {
				jugadorActual = JUGADOR_UNO;
			}

			limpiarPantalla();

			if (tablero.elJuegoTermino()) {
				System.out.println("");
				System.out.println(tablero.estadoTablero());
				System.out.println("");
				System.out.println("GANO EL JUEGO, FELICITACIONES!!!!");
				System.out.println("Presione una tecla para continuar...");
				scanner.nextLine();
				limpiarPantalla();
				visualizarMenu();

			}
		}
	}

	public static void jugar1VSPC() {

		int columnaJugada = 0;

		tablero = new Tablero();

		while (!tablero.elJuegoTermino()) {

			System.out.println("");
			System.out.println(tablero.estadoTablero());
			System.out.println("");

			System.out.println("Seleccione la columna para su ficha: ");

			columnaJugada = Integer.parseInt(scanner.nextLine());
			boolean jugadaValida = tablero.jugar(JUGADOR_UNO, columnaJugada);

			while (!jugadaValida) {
				System.out.println("La jugada ingresada no es valida - Ingrese otra columna: ");
				jugadaValida = tablero.jugar(JUGADOR_UNO, Integer.parseInt(scanner.nextLine()));
			}

			limpiarPantalla();

			if (tablero.elJuegoTermino()) {
				System.out.println("");
				System.out.println(tablero.estadoTablero());
				System.out.println("");
				System.out.println("GANO EL JUEGO, FELICITACIONES!!!!");
				System.out.println("Presione una tecla para continuar...");
				scanner.nextLine();
				limpiarPantalla();
				visualizarMenu();
			} else {

				tablero.jugarPC(tablero);

				if (tablero.elJuegoTermino()) {
					System.out.println("");
					System.out.println(tablero.estadoTablero());
					System.out.println("");
					System.out.println("LO SENTIMOS HUMANO, GANO LA IA XD");
					System.out.println("Presione una tecla para continuar...");
					scanner.nextLine();
					limpiarPantalla();
					visualizarMenu();
				}
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

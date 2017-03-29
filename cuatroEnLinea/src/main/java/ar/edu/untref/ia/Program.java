package ar.edu.untref.ia;

import java.util.Scanner;

public class Program {

	private static Scanner scanner;
	private static Tablero tablero;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		int opcionSeleccionada = 0;

		while (opcionSeleccionada != 3) {

			visualizarMenu();
			opcionSeleccionada = Integer.parseInt(scanner.nextLine());

			switch (opcionSeleccionada) {
			case 1:
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
		
		tablero = new Tablero();
		
		while(!tablero.elJuegoTermino()) {
		
			System.out.println("");
			System.out.println(tablero.estadoTablero());
			System.out.println("");
			
			System.out.println("Inserte su ficha - Jugador: " + jugadorActual);
			tablero.jugar(jugadorActual, Integer.parseInt(scanner.nextLine()));
			if (jugadorActual == 1) {
				jugadorActual = 2;
			} else {
				jugadorActual = 1;
			}
		}
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

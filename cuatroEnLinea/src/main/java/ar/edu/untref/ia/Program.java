package ar.edu.untref.ia;

import java.util.Scanner;

public class Program {

	private static Scanner scanner;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		int opcionSeleccionada = 0;

		while (opcionSeleccionada != 3) {

			visualizarMenu();
			opcionSeleccionada = Integer.parseInt(scanner.nextLine());

			switch (opcionSeleccionada) {
			case 1:
				System.out.println("Eligió la opcion 1");
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

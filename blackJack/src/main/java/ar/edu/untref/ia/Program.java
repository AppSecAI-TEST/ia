package ar.edu.untref.ia;

public class Program {
	
	public static final void main(String arg[]) {

		BlackJack blackJack = new BlackJack();
		
		blackJack.iteracion(1000000, true, false);
		System.out.println("FASE DE APLICACION.");
		blackJack.iteracion(10, false, true);
	}

}

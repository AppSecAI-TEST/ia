package ar.edu.untref.ia;

import org.junit.Before;
import org.junit.Test;

public class blackJackTest {

	BlackJack blackJack;
	
	@Before
	public void init() {

		blackJack = new BlackJack();
	}

	@Test
	public void politicaRandom() {
		blackJack.iteracion(1000000,true,false);
	}
}

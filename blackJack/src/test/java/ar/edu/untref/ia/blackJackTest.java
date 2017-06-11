package ar.edu.untref.ia;

import org.junit.Before;
import org.junit.Test;

public class blackJackTest {

	private BlackJack blackJack;
	
	@Before
	public void init() {

		blackJack = new BlackJack();
	}

	@Test
	public void randomPolicy() {
		BlackJack bj = new BlackJack();
		bj.iteration(1000000,true,false);
	}
}

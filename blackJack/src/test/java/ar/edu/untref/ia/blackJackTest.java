package ar.edu.untref.ia;

import org.junit.Assert;
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

		boolean random = blackJack.randomPolicy();

		Assert.assertNotNull(random);
	}
}
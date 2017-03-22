package ar.edu.untref.ia;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class NodoTest {


	@Test
	public void devuelveCorrectamenteLaPosicionLibre0() {
		
		Nodo nodo = new Nodo(Arrays.asList(0,1,2,3,4,5,6,7,8));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 0);
	}

	@Test
	public void devuelveCorrectamenteLaPosicionLibre1() {
		
		Nodo nodo = new Nodo(Arrays.asList(1,0,2,3,4,5,6,7,8));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 1);
	}
	
	@Test
	public void devuelveCorrectamenteLaPosicionLibre2() {
		
		Nodo nodo = new Nodo(Arrays.asList(2,1,0,3,4,5,6,7,8));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 2);
	}
	
	@Test
	public void devuelveCorrectamenteLaPosicionLibre3() {
		
		Nodo nodo = new Nodo(Arrays.asList(3,1,2,0,4,5,6,7,8));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 3);
	}
	
	@Test
	public void devuelveCorrectamenteLaPosicionLibre4() {
		
		Nodo nodo = new Nodo(Arrays.asList(4,1,2,3,0,5,6,7,8));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 4);
	}
	
	@Test
	public void devuelveCorrectamenteLaPosicionLibre5() {
		
		Nodo nodo = new Nodo(Arrays.asList(5,1,2,3,4,0,6,7,8));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 5);
	}
	
	@Test
	public void devuelveCorrectamenteLaPosicionLibre6() {
		
		Nodo nodo = new Nodo(Arrays.asList(6,1,2,3,4,5,0,7,8));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 6);
	}
	
	@Test
	public void devuelveCorrectamenteLaPosicionLibre7() {
		
		Nodo nodo = new Nodo(Arrays.asList(7,1,2,3,4,5,6,0,8));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 7);
	}
	
	@Test
	public void devuelveCorrectamenteLaPosicionLibre8() {
		
		Nodo nodo = new Nodo(Arrays.asList(8,1,2,3,4,5,6,7,0));
		
		int posicionLibre = nodo.obtenerPosicionLibre();
		
		Assert.assertTrue(posicionLibre == 8);
	}
}

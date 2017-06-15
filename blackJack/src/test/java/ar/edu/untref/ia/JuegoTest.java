package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

public class JuegoTest {
	
	@Test
    public void juegoEmpiezaConEstadoNoTerminal() throws Exception{

		Juego juego = new Juego(true);
        
		boolean estaTerminado = juego.estaTerminado();

        Assert.assertFalse(estaTerminado);
    }

    @Test
    public void juegoEmpiezaConJugador() throws Exception {
        
    	Juego juego = new Juego(true);
        
    	Jugador Jugador = juego.getJugador();

        Assert.assertNotNull(Jugador);
    }

    @Test
    public void juegoEmpiezaConBanca() throws Exception {
        
    	Juego juego = new Juego(true);
        
    	Jugador banca = juego.getBanca();

        Assert.assertNotNull(banca);
    }

    @Test
    public void juegoEmpiezaConMaso () throws Exception {
        
    	Juego juego = new Juego(true);
        
    	Maso maso = juego.getMaso();

        Assert.assertNotNull(maso);
    }

    @Test
    public void elJuegadorEmpiezaElJuegoConUnaCiertaCantidadDePuntos() throws Exception {
        
    	Juego juego = new Juego(true);
        
    	int puntosJugador = juego.getPuntosJugador();

        Assert.assertNotEquals(0, puntosJugador);
    }

    @Test
    public void laBancaEmpiezaElJuegoConUnaCiertaCantidadDePuntos() throws Exception {

    	Juego juego = new Juego(true);
        
    	int puntosBanca = juego.getPuntosBanca();

        Assert.assertNotEquals(0, puntosBanca);
    }

    @Test
    public void elJugadorPideCartaEntoncesSeCalculaLaRecompensa() throws Exception {
    	
    	Juego juego = new Juego(true);
        
    	int recompensa = juego.jugada(Accion.PEDIR);

        Assert.assertNotNull(recompensa);
    }

    @Test
    public void elJugadorSeMantieneEntoncesSeCalculaLaRecompensa() throws Exception {
        
    	Juego juego = new Juego(true);
        
        int reward = juego.jugada(Accion.MANTENERSE);

        Assert.assertNotNull(reward);
    }
}
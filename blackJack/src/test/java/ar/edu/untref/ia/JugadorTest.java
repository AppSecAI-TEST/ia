package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JugadorTest {

    @Test
    public void elJugadorEmpiezaConCeroPuntos() throws Exception {
        
    	Jugador jugador = new Jugador();
        
    	int puntosIniciales = jugador.getPuntos();

        Assert.assertEquals(0, puntosIniciales);
    }

    @Test
    public void elJugadorEmpiezaSinCartas() throws Exception {
        
    	Jugador jugador = new Jugador();
        
    	List<String> mano = jugador.getMano();

        Assert.assertTrue(mano.isEmpty());
    }

    @Test
    public void elJugadorPideCartasDelMaso() throws Exception {
        
    	Jugador jugador = new Jugador();
        Maso maso = new Maso();
        
        jugador.pedirCarta(maso);
        
        List<String> mano = jugador.getMano();
        List<String> cartasRestantes = maso.getCartasRestantes();

        Assert.assertFalse(mano.isEmpty());
        Assert.assertFalse(cartasRestantes.contains(mano.get(0)));
    }

    @Test
    public void seCalculanYRetornanLosPuntosActuales() throws Exception {
     
    	Jugador jugador = new Jugador();
        Maso mano = new Maso();
        
        jugador.pedirCarta(mano);
        jugador.pedirCarta(mano);
        jugador.pedirCarta(mano);

        int puntosActuales = jugador.getPuntos();

        Assert.assertNotEquals(0, puntosActuales);
    }
}
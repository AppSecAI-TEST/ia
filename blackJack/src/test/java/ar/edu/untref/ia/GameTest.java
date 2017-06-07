package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gonzalocozzi on 06/06/17.
 */
public class GameTest {

    @Test
    public void gameStartsWithNoTerminalState() throws Exception{
        Game game = new Game();
        boolean isTerminal = game.isTerminal();

        Assert.assertFalse(isTerminal);
    }

    @Test
    public void gameStartsWithPlayer() throws Exception {
        Game game = new Game();
        Player player = game.getPlayer();

        Assert.assertNotNull(player);
    }

}

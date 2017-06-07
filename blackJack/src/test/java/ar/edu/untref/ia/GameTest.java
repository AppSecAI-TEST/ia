package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gonzalocozzi on 06/06/17.
 */
public class GameTest {

    @Test
    public void gameStartsWithNoTerminalState(){

        Game game = new Game();
        boolean isTerminal = game.isTerminal();

        Assert.assertFalse(isTerminal);
    }
}

package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void gameStartsWithCroupier() throws Exception {
        Game game = new Game();
        Player croupier = game.getCroupier();

        Assert.assertNotNull(croupier);
    }

    @Test
    public void gameHasPlayersListAndPlayerAndCroupierAreInIt() throws Exception {
        Game game = new Game();
        List<Player> playersList = game.getPlayersList();

        Assert.assertEquals(2, playersList.size());
    }

    @Test
    public void gameStartsWithDeck () throws Exception {
        Game game = new Game();
        Deck deck = game.getDeck();

        Assert.assertNotNull(deck);
    }

}

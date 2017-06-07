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

    @Test
        public void gameStartsMakingThePlayerDrawCardFromDeck() throws Exception {
        Game game = new Game();
        int playerPoints = game.getPlayerPoints();

        Assert.assertNotEquals(0, playerPoints);
    }

    @Test
    public void gameStartsMakingTheCroupierDrawCardFromDeck() throws Exception {
        Game game = new Game();
        int croupierPoints = game.getCroupierPoints();

        Assert.assertNotEquals(0, croupierPoints);
    }

    @Test
    public void gameReturnsPlayersRewardInNextStepsWhenItsActionIsDraw() throws Exception {
        Game game = new Game();
        int reward = game.step(Action.DRAW);

        Assert.assertNotNull(reward);
    }

    @Test
    public void gameReturnsPlayersRewardInNextStepWhenItsActionIsStand() throws Exception {
        Game game = new Game();
        int reward = game.step(Action.STAND);

        Assert.assertNotNull(reward);
    }

}

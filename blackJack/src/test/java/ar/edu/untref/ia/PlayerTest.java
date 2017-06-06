package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by gonzalocozzi on 06/06/17.
 */
public class PlayerTest {

    @Test
    public void playerStartsWithZeroPoints() throws Exception{

        Player player = new Player();
        int initialPoints = player.getPoints();

        Assert.assertEquals(0, initialPoints);
    }

    @Test
    public void playerStartsWithEmptyHandOfCards() throws Exception{

        Player player = new Player();
        List<String> hand = player.getHand();

        Assert.assertTrue(hand.isEmpty());
    }

    @Test
    public void playerDrawCardFromDeck() throws Exception{

        Player player = new Player();
        Deck deck = new Deck();
        player.draw(deck);
        List<String> hand = player.getHand();
        List<String> deckRemainingCards = deck.getRemainingCards();

        Assert.assertFalse(hand.isEmpty());
        Assert.assertFalse(deckRemainingCards.contains(hand.get(0)));
    }

    @Test
    public void playerCalculatesAndReturnsItsCurrentPoints() throws Exception{

        Player player = new Player();
        Deck deck = new Deck();
        player.draw(deck);
        player.draw(deck);
        player.draw(deck);

        int currentPoints = player.getPoints();

        Assert.assertNotEquals(0, currentPoints);
    }

}

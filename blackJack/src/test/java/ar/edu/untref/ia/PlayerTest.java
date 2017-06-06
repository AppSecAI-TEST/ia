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

}

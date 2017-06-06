package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;

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

}

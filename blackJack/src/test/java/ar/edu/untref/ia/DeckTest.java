package ar.edu.untref.ia;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by gonzalocozzi on 06/06/17.
 */
public class DeckTest {

    @Test
    public void deckContainsCollectionOfStringsRepresentingCards() throws Exception{

        Deck deck = new Deck();

        Assert.assertNotNull(deck.getContents());
    }

}

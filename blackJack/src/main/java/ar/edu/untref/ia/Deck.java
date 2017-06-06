package ar.edu.untref.ia;

import java.util.ArrayList;

/**
 * Created by gonzalocozzi on 06/06/17.
 */
public class Deck {

    private ArrayList<String> cards;

    public Deck(){
        this.cards = new ArrayList<>();
    }

    public ArrayList<String> getCards(){
        return this.cards;
    }

}

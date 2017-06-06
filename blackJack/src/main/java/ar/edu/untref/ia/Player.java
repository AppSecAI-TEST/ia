package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalo on 06/06/17.
 */
public class Player {

    private int points;
    private List<String> hand;

    public Player(){
        this.points = 0;
        this.hand = new ArrayList<>();
    }

    public int getPoints(){
        return this.points;
    }

    public List<String> getHand(){
        return this.hand;
    }
}

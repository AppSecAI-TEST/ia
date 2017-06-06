package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by gonzalo on 06/06/17.
 */
public class Player {

    private int points;
    private List<String> hand;

    //A player has a hand and interacts with a deck
    public Player(){
        this.points = 0;
        this.hand = new ArrayList<>();
    }

    public int getPoints(){
        this.calculatePoints();
        return this.points;
    }

    public List<String> getHand(){
        return this.hand;
    }

    //Draw a card from deck and tell the deck to remove its top card
    public void draw(Deck deck){
        String drewCard = deck.getCard();
        this.hand.add(drewCard);
    }

    //Figures out how many points the player's hand is worth
    private void calculatePoints(){
        int tempPoints = 0;
        int numberOfAces = 0;
        for(String card : this.hand){
            String[] cardSplit = card.split(" ");
            if(this.isInteger(cardSplit[0])){
                tempPoints += Integer.parseInt(cardSplit[0]);
            } else if(cardSplit[0] == "Ace"){
                tempPoints += 11;
                numberOfAces += 1;
            } else {
                tempPoints += 10;
            }
        }
        //If the player exceeds 21 points by choosing aces as 11-point cards, then the score of those cards is reduced to 1 point.
        while(numberOfAces > 0 && tempPoints > 21){
            numberOfAces -= 1;
            tempPoints -= 10;
        }
        this.points = tempPoints;
    }

    public boolean isInteger(String string) {
        if(string == null || string.trim().isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            if(!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}

package ar.edu.untref.ia;

/**
 * Created by gonzalocozzi on 07/06/17.
 */
public class Game {

    private boolean isTerminal;
    private Player player;

    public Game() {
        this.isTerminal = false;
        this.player = new Player();
    }

    public boolean isTerminal() {
        return this.isTerminal;
    }

    public Player getPlayer() {
        return this.player;
    }
}

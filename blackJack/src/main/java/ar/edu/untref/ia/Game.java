package ar.edu.untref.ia;

/**
 * Created by gonzalocozzi on 07/06/17.
 */
public class Game {

    private boolean isTerminal;
    private Player player;
    private Player croupier;

    public Game() {
        this.isTerminal = false;
        this.player = new Player();
        this.croupier = new Player();
    }

    public boolean isTerminal() {
        return this.isTerminal;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player getCroupier() {
        return this.croupier;
    }
}

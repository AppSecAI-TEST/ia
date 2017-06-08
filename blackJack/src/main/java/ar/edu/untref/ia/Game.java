package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalocozzi on 07/06/17.
 */
public class Game {

	private boolean isTerminal;
	private Player player;
	private Player croupier;
	private List<Player> playersList;
	private Deck deck;
	private int playerPoints;
	private int croupierPoints;

	public Game() {
		this.isTerminal = false;
		this.player = new Player();
		this.croupier = new Player();
		this.deck = new Deck();
		this.playersList = new ArrayList<>();
		this.playerPoints = 0;
		this.croupierPoints = 0;
		fillPlayersList();
		firstStep();
	}

	private void fillPlayersList() {
		this.playersList.add(this.player);
		this.playersList.add(this.croupier);
	}

	private void firstStep() {
		this.player.draw(this.deck);
		this.croupier.draw(this.deck);
		updatePlayersPoints();
	}

	private void updatePlayersPoints() {
		this.playerPoints = this.player.getPoints();
		this.croupierPoints = this.croupier.getPoints();
	}

	protected boolean isTerminal() {
		return this.isTerminal;
	}

	protected Player getPlayer() {
		return this.player;
	}

	protected Player getCroupier() {
		return this.croupier;
	}

	protected List<Player> getPlayersList() {
		return this.playersList;
	}

	protected Deck getDeck() {
		return this.deck;
	}

	protected int getPlayerPoints() {
		return this.playerPoints;
	}

	protected int getCroupierPoints() {
		return this.croupierPoints;
	}

	protected int step(Action action) {
		if (action == Action.DRAW) {
			this.player.draw(this.deck);
			this.updatePlayersPoints();
		} else if (action == Action.STAND) {
			this.isTerminal = true;
			while (this.croupierPoints < 17) {
				this.croupier.draw(this.deck);
				this.updatePlayersPoints();
			}
		}
		return this.calculatePlayerReward();
	}

	/**
	 * reward 1: player wins reward 0: tie reward -1: croupier wins
	 *
	 * @return reward
	 */
	private int calculatePlayerReward() {
		int reward = 0;
		if (this.playerPoints > 21) {
			this.isTerminal = true;
			reward = -1;
		}
		if (this.croupierPoints > 21) {
			if (this.playerPoints <= 21) {
				reward = 1;
			}
		} else if (this.playerPoints < this.croupierPoints) {
			reward = -1;
		} else if (this.playerPoints == this.croupierPoints) {
			reward = 0;
		} else if (this.playerPoints > this.croupierPoints) {
			reward = 1;
		}
		return reward;
	}
}
package ar.edu.untref.ia;

public class Situation {

	private int playerPoints;
	private int dealerPoints;
	private Action action;

	public Situation(int player, int dealer, Action action) {
		this.playerPoints = player;
		this.dealerPoints = dealer;
		this.action = action;
	}

	public int getPlayerPoints() {
		return playerPoints;
	}

	public int getDealerPoints() {
		return dealerPoints;
	}

	public Action getAction() {
		return action;
	}
	
	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;	
	}
	
	public void setDealerPoints(int dealerPoints) {
		this.dealerPoints = dealerPoints;	
	}
	
}
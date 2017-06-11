package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class BlackJack {

	public Action randomPolicy() {
		Action randomAction = Action.DRAW;
		if (Math.random() >= 0.5) {
			randomAction = Action.STAND;
		}
		return randomAction;
	}

	public Action epsilonGreedyPolicy(Double epsilon, Map<Situation, Double> valueFunction, int playerPoints,
			int dealerPoints) {
		// Exploration
		if (Math.random() < epsilon) {
			return randomPolicy();
		} else {
			// Exploitation
			return bestPolicy(valueFunction, playerPoints, dealerPoints);
		}
	}

	private Action bestPolicy(Map<Situation, Double> valueFunction, int playerPoints, int dealerPoints) {
		Action bestAction;
		Situation situationDraw = new Situation(playerPoints, dealerPoints, Action.DRAW);
		Situation situationStand = new Situation(playerPoints, dealerPoints, Action.STAND);
		Double valueDraw = valueFunction.getOrDefault(situationDraw, 0.0);
		Double valueStand = valueFunction.getOrDefault(situationStand, 0.0);
		if (valueDraw > valueStand) {
			bestAction = Action.DRAW;
		} else if (valueStand > valueDraw) {
			bestAction = Action.STAND;
		} else {
			bestAction = randomPolicy();
		}
		return bestAction;
	}

	public void iteration(int iterations, boolean epsilonGreedyPolicy, boolean bestPolicy) {
		int nZero = 100;
		// (player, dealer, action) key
		Map<Situation, Double> valueFunction = new HashMap<>();
		// (player, dealer) key
		Map<Situation, Integer> counterState = new HashMap<>();
		// (player, dealer, action) key
		Map<Situation, Integer> counterStateAction = new HashMap<>();
		// number of wins
		int wins = 0;
		List<Integer> winRecord = new ArrayList<>();
		Double epsilon;
		// Repeating the game a certain number of times
		for (int i = 0; i < iterations; i++) {
			// create a new random starting state
			Game game = new Game();
			int currentPlayerPoints = game.getPlayerPoints();
			int currentDealerPoints = game.getCroupierPoints();
			Action currentAction = null;
			Integer currentReward = null;
			Situation currentSituation = new Situation(currentPlayerPoints, currentDealerPoints, currentAction);
			// play a round
			List<Situation> observedKeys = new ArrayList<>();
			while (!game.isTerminal()) {
				int numberOfRemainingCards = game.getDeck().getRemainingCards().size();
				// refill the deck
				if (numberOfRemainingCards < 52 * 0.6) {
					game.setDeck(new Deck());
				}
				// find an action defined by the policy
				if(currentAction == null && currentReward == null){
					currentAction = this.randomPolicy();
				} else if (currentAction != Action.STAND && currentReward != -1) {
					epsilon = (double) (nZero / (nZero + counterState.getOrDefault(currentSituation, 0)));
					if (epsilonGreedyPolicy) {
						currentAction = epsilonGreedyPolicy(epsilon, valueFunction, currentPlayerPoints, currentDealerPoints);
					}
					if (bestPolicy) {
						currentAction = bestPolicy(valueFunction, currentPlayerPoints, currentDealerPoints);
					}
				} else {
					currentAction = this.randomPolicy();
				}
				if (!observedKeys.contains(currentSituation) && currentPlayerPoints <= 21) {
					observedKeys.add(currentSituation);
				}
				currentReward = game.step(currentAction);
			}
			this.QLearning(currentReward, observedKeys, counterState, counterStateAction, valueFunction);
			if (i > iterations * 0.8) {
				if (currentReward == 1) {
					wins += 1;
				}
			}
			winRecord.add(currentReward);
		}
		System.out.println("Wins: " + (wins / (iterations * 0.2)) * 100);
		System.out.println("Value function: ");
		for (Situation key : valueFunction.keySet()) {
			System.out.println(Integer.toString(key.getPlayerPoints()) + " " +
					Integer.toString(key.getCroupierPoints()) + " " +
					key.getAction() + " = " + valueFunction.get(key));
		}
		System.out.println("Counter state: ");
		for (Situation key : counterState.keySet()) {
			System.out.println(Integer.toString(key.getPlayerPoints()) + " " +
					Integer.toString(key.getCroupierPoints()) + " = " + counterState.get(key));
		}
		System.out.println("Counter state action: ");
		for (Situation key : counterStateAction.keySet()) {
			System.out.println(Integer.toString(key.getPlayerPoints()) + " " +
					Integer.toString(key.getCroupierPoints()) + " " +
					key.getAction() + " = " + counterStateAction.get(key));
		}

	}

	private void QLearning(Integer reward, List<Situation> observedKeys, Map<Situation, Integer> counterState,
			Map<Situation, Integer> counterStateAction, Map<Situation, Double> valueFunction) {
		Double newD = 0.0;
		if (reward != null) {
			ListIterator<Situation> situationIterator = observedKeys.listIterator();
			while (situationIterator.hasNext()) {
				Situation currentSituation = situationIterator.next();
				counterState.put(currentSituation, counterState.getOrDefault(currentSituation, 0) + 1);
				counterStateAction.put(currentSituation, counterStateAction.getOrDefault(currentSituation, 0) + 1);
				double alpha = 1.0 / counterStateAction.get(currentSituation);
				double old = valueFunction.getOrDefault(currentSituation, 0.0);
				if (situationIterator.hasNext()) {
					Situation nextSituation = situationIterator.next();
					Situation drawSituation = new Situation(nextSituation.getPlayerPoints(),
							nextSituation.getCroupierPoints(), nextSituation.getAction());
					drawSituation.setPlayerPoints(nextSituation.getPlayerPoints() + 1);
					Situation standSituation = new Situation(nextSituation.getPlayerPoints(),
							nextSituation.getCroupierPoints(), nextSituation.getAction());
					Double maxValue = max(valueFunction.get(drawSituation), valueFunction.get(standSituation));
					newD = maxValue * 0.8;
				}
				currentSituation = situationIterator.previous();
				valueFunction.put(currentSituation, (1 - alpha) * old + alpha * (reward + newD));
				situationIterator.next();
			}
		}
	}

	private Double max(Double double1, Double double2) {
		if (double1 >= double2) {
			return double1;
		} else {
			return double2;
		}
	}

}
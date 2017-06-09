package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class BlackJack {

	public Action randomPolicy() {

		Action result = Action.DRAW;

		if (Math.random() >= 0.5) {
			result = Action.STAND;
		}
		return result;
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

		Action result;

		Situation situationHit = new Situation(playerPoints, dealerPoints, Action.DRAW);
		Situation situatioStick = new Situation(playerPoints, dealerPoints, Action.STAND);

		Double valueHit = valueFunction.get(situationHit);
		Double valueStick = valueFunction.get(situatioStick);

		if (valueHit > valueStick) {
			result = Action.DRAW;
		} else if (valueStick > valueHit) {
			result = Action.STAND;
		} else {
			result = randomPolicy();
		}

		return result;
	}

	public void iteration(int iterations, boolean epsilonGreedyPolicy, boolean bestPolicy, int nZero) {

		nZero = 100;
		// (player, dealer, action) key
		Map<Situation, Double> valueFunction = new HashMap<>();
		// (player, dealer) key
		Map<Situation, Integer> counter_state = new HashMap<>();
		// (player, dealer, action) key
		Map<Situation, Integer> counter_state_action = new HashMap<>();
		// number of wins
		int wins = 0;
		List<Integer> winrecord = new ArrayList<>();

		Double epsilon = 0.0;
		// Repeating the game a certain number of times
		for (int i = 0; i <= iterations; i++) {

			// create a new random starting state
			Game game = new Game();
			int playerPoints = game.getPlayerPoints();
			int dealerPoints = game.getCroupierPoints();
			Action action = null;
			Integer reward = null;
			Situation situation = new Situation(playerPoints, dealerPoints, action);

			// play a round
			List<Situation> observed_keys = new ArrayList<>();

			while (!game.isTerminal()) {

				int numberOfCards = game.getDeck().getRemainingCards().size();

				if (numberOfCards < 52 * 0.6) {
					game.setDeck(new Deck());
					game.getDeck().shuffle();
				}
				// find an action defined by the policy
				if (action != Action.STAND && reward != -1) {
					epsilon = (double) (nZero / (nZero + counter_state.get(situation)));
					if (epsilonGreedyPolicy) {
						action = epsilonGreedyPolicy(epsilon, valueFunction, playerPoints, dealerPoints);
					}
					if (bestPolicy) {
						action = bestPolicy(valueFunction, playerPoints, dealerPoints);
					}
				} else {
					action = Action.STAND;
				}

				if (!observed_keys.contains(situation) && playerPoints <= 21) {
					observed_keys.add(situation);
				}

				reward = game.step(action);

			}
			this.QLearning(reward, observed_keys, counter_state, counter_state_action, valueFunction);

			if (i > iterations * 0.8) {
				if (reward == 1) {
					wins += 1;
				}

			}

			winrecord.add(reward);

		}

		System.out.println("Wins: " + (wins / (iterations * 0.2)) * 100);

	}

	private void QLearning(Integer reward, List<Situation> observed_keys, Map<Situation, Integer> counter_state,
			Map<Situation, Integer> counter_state_action, Map<Situation, Double> valueFunction) {

		Double newD = 0.0;

		if (reward != null) {

			ListIterator<Situation> situationIterator = observed_keys.listIterator();
			while (situationIterator.hasNext()) {
				Situation situation = situationIterator.next();
				counter_state.put(situation, counter_state.get(situation) + 1);
				counter_state_action.put(situation, counter_state_action.get(situation) + 1);

				double alpha = 1.0 / counter_state_action.get(situation);
				double old = valueFunction.get(situation);

				if (situationIterator.hasNext()) {

					Situation situationNext = situationIterator.next();

					Situation situationHit = new Situation(situationNext.getPlayerPoints(),
							situationNext.getDealerPoints(), situationNext.getAction());

					situationHit.setPlayerPoints(situationNext.getPlayerPoints() + 1);

					Situation situationStick = new Situation(situationNext.getPlayerPoints(),
							situationNext.getDealerPoints(), situationNext.getAction());

					Double maxValue = max(valueFunction.get(situationHit), valueFunction.get(situationStick));
					newD = maxValue * 0.8;

				}
				situation = situationIterator.previous();
				valueFunction.put(situation, (1 - alpha) * old + alpha * (reward + newD));
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
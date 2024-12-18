package state;

public class StateManager {

	private static int stateMoney = 0;

	public static void addToStateMoney(int moneyToAdd) {
		if (moneyToAdd <= 0) {
			throw new RuntimeException("" + moneyToAdd);
		}
		stateMoney += moneyToAdd;
		stateMoney = Math.max(0, moneyToAdd);
	}

	public static int getStateMoney() {
		return stateMoney;
	}
}
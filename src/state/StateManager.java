package state;

import entities.Human;

public class StateManager {

	private static final int welfarePerMonth = 400;
	private static int stateMoney = 100000;

	public static void addToStateMoney(int moneyToAdd) {
		/*if (moneyToAdd <= 0) {
			throw new RuntimeException("" + moneyToAdd);
		}*/
		stateMoney += moneyToAdd;
		// stateMoney = Math.max(0, stateMoney);
	}

	public static void askForWelfare(Human human) {
		System.out.println("Welfare");
		human.giveMoney(welfarePerMonth);
		addToStateMoney(-welfarePerMonth);
		human.payHealthInsurance();
	}

	public static int getStateMoney() {
		return stateMoney;
	}
}
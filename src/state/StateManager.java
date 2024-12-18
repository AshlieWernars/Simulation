package state;

import entities.Human;

public class StateManager {

	private static final int welfarePerMonth = 1200; // Based on real (NL) figures
	private static final int pensionPerMonth = 1350; // Based on real (NL) figures
	private static int stateMoney = 100000;

	public static void addToStateMoney(int moneyToAdd) {
		stateMoney += moneyToAdd;
	}

	public static void askForWelfare(Human human) {
		human.giveMoney(welfarePerMonth);
		addToStateMoney(-welfarePerMonth);
	}

	public static void resetMoney() {
		stateMoney = 100000;
	}

	public static int getStateMoney() {
		return stateMoney;
	}

	public static void askForPension(Human human) {
		human.giveMoney(pensionPerMonth);
		addToStateMoney(-pensionPerMonth);
	}
}
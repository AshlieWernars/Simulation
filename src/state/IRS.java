package state;

import entities.Human;

public class IRS {
	
	private static final double taxRate = 37.0 / 100;

	public static void taxHuman(Human human) {
		double taxes = human.getMoney() * taxRate;
		int taxesToPay = (int) Math.round(taxes);
		human.giveMoney(-taxesToPay);
	}
}
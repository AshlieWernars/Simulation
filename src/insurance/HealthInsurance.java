package insurance;

import java.util.Random;

import entities.Human;

public class HealthInsurance {

	private static final int baseInsurancePay = 150; // Per month
	private static final Random random = new Random();

	/**
	 * Calculates the health insurance cost for a Human based on their health value.
	 * 
	 * @param health the health value of the individual, ranging from 0 to 10.
	 * @return the total health insurance cost based on the given health value.
	 */
	private static int calculateHealthInsuranceCost(int health) {
		// Calculate the extra cost based on the health value
		int extraCost = 50 - (5 * health);

		// Ensure that the extra cost doesn't go below 0
		extraCost = Math.max(extraCost, 0);

		// Total cost is the base pay plus the extra cost
		return baseInsurancePay + extraCost;
	}

	/**
	 * Calculates an insurance multiplier based on the person's age.
	 * 
	 * @param age the age of the individual.
	 * @return the multiplier for insurance cost based on age.
	 */
	private static double calculateInsuranceMultiplier(int age) {
		if (age < 18) {
			return 0; // No insurance for minors
		}

		if (age > 65) {
			return 1.25; // Higher multiplier for seniors
		}

		// Random multiplier between 0.8 and 1.2 for adults
		return random.nextDouble() * (1.2 - 0.8) + 0.8;
	}

	/**
	 * Calculates the total health insurance cost for a Human, considering both
	 * their health value and age.
	 * 
	 * @param human the individual for whom the insurance cost is calculated.
	 * @return the total insurance cost for the person.
	 */
	public static int calculateHealthInsuranceCost(Human human) {
		int cost = calculateHealthInsuranceCost(human.getHealth());
		double multiplier = calculateInsuranceMultiplier(human.getAge());

		return (int) Math.round(cost * multiplier);
	}
}
package insurance;

public class HealthInsurance {

	private static final int baseInsurancePay = 5; // Per sim step

	/**
	 * Calculates the health insurance cost for a Human based on their health value.
	 * 
	 * @param health the health value of the individual, ranging from 0 to 10.
	 * @return the total health insurance cost based on the given health value.
	 */
	public static int calculateHealthInsuranceCost(int health) {
		// Calculate the extra cost based on the health value
		int extraCost = 50 - (5 * health);

		// Ensure that the extra cost doesn't go below 0
		extraCost = Math.max(extraCost, 0);

		// Total cost is the base pay plus the extra cost
		return baseInsurancePay + extraCost;
	}
}
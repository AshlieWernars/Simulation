package state;

public class IRS {

	// Tax rate as a constant
	private static final double DEFAULT_TAX_RATE = 37.0 / 100;

	/**
	 * Calculates the salary after taxes are deducted.
	 * 
	 * @param salary The gross salary before taxes.
	 * @return The net salary after taxes are deducted.
	 */
	public static int taxSalary(int salary) {
		double taxes = salary * DEFAULT_TAX_RATE;
		int taxesToPay = (int) Math.round(taxes);
		return salary - taxesToPay;
	}
}

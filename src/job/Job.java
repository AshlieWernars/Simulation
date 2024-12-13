package job;

import java.util.Random;

public enum Job {

	FARMER(15), //
	HUNTER(15), //
	BUILDER(18), //
	TEACHER(20), //
	HEALER(25), //
	GUARD(20), //
	MERCHANT(15), //
	ARTIST(20), //
	SCIENTIST(35), //
	LEADER(50);//

	private final int salaryPerHour;

	Job(int salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

	public int getSalary() {
		return salaryPerHour;
	}

	public double doJob() {
		Random r = new Random();
		double hoursWorked = 0;

		// Set the base hours worked per day for each job
		double baseHours = 8.0; // Assume 8 hours as the default for a standard workday

		// Apply job-specific work hours
		switch (this) {
		case ARTIST:
			baseHours = 6.0; // Artists might work fewer hours
			break;
		case BUILDER:
			baseHours = 8.0; // Builders typically work full days
			break;
		case FARMER:
			baseHours = 8.5; // Farmers may work slightly longer days
			break;
		case GUARD:
			baseHours = 8.0; // Guards usually work standard shifts
			break;
		case HEALER:
			baseHours = 8.0; // Healers work around 8 hours
			break;
		case HUNTER:
			baseHours = 7.5; // Hunters may work shorter days
			break;
		case LEADER:
			baseHours = 8.5; // Leaders often work long hours
			break;
		case MERCHANT:
			baseHours = 7.5; // Merchants may have variable hours
			break;
		case SCIENTIST:
			baseHours = 8.0; // Scientists typically work standard hours
			break;
		case TEACHER:
			baseHours = 7.0; // Teachers may work fewer hours, including prep
			break;
		default:
			break;
		}

		// Add some randomness to the hours worked, within ±1 hour range
		hoursWorked = baseHours + (r.nextDouble() * 2.0 - 1.0); // Random between -1 and +1 hour

		return hoursWorked;
	}

	// Return a random job for initialization
	public static Job randomJob() {
		Random rand = new Random();
		return values()[rand.nextInt(values().length)];
	}
}
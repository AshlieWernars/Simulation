package job;

import java.util.Random;

import entities.Human;

public enum Job {

	FARMER(2500), //
	HUNTER(2500), //
	BUILDER(2800), //
	TEACHER(2800), //
	HEALER(4500), //
	GUARD(2200), //
	MERCHANT(3000), //
	ARTIST(2500), //
	SCIENTIST(3500), //
	LEADER(7500);//

	private final int salary;

	Job(int salary) {
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	/**
	 * Modify traits based on job and calculate salary
	 *
	 * @param job
	 * @param human
	 * @return modified salary
	 */
	public double doJob(Job job, Human human) {
		Random r = new Random();
		// Random multiplier between 0.75 and 1.25, with an average near 1
		double multiplier = 0.75 + (r.nextDouble() * 0.5);

		// Apply trait modifications based on job
		switch (job) {
		case ARTIST:
			human.setCreativity(human.getCreativity() + 1);
			human.setAttractiveness(human.getAttractiveness() - 1);
			break;
		case BUILDER:
			human.setPhysicalStrength(human.getPhysicalStrength() + 1);
			human.setConscientiousness(human.getConscientiousness() + 1);
			break;
		case FARMER:
			human.setPhysicalStrength(human.getPhysicalStrength() + 1);
			human.setHealth(human.getHealth() + 1);
			break;
		case GUARD:
			human.setPhysicalStrength(human.getPhysicalStrength() + 1);
			human.setSocialDominance(human.getSocialDominance() + 1);
			break;
		case HEALER:
			human.setEmpathy(human.getEmpathy() + 1);
			human.setMentalHealth(human.getMentalHealth() + 1);
			break;
		case HUNTER:
			human.setPhysicalStrength(human.getPhysicalStrength() + 1);
			human.setCreativity(human.getCreativity() - 1); // WTF???
			break;
		case LEADER:
			human.setSocialDominance(human.getSocialDominance() + 1);
			human.setConscientiousness(human.getConscientiousness() + 1);
			break;
		case MERCHANT:
			// Chance of becoming greedy
			human.setSocialSkill(human.getSocialSkill() + 1);
			human.setMotivation(human.getMotivation() + 1);
			break;
		case SCIENTIST:
			human.setIntelligence(human.getIntelligence() + 1);
			human.setOpenness(human.getOpenness() + 1);
			break;
		case TEACHER:
			// Mental health should go down
			human.setSocialSkill(human.getSocialSkill() + 1);
			human.setEmpathy(human.getEmpathy() + 1);
			break;
		default:
			break;
		}

		// Calculate the salary with the multiplier
		double baseSalary = job.getSalary(); // Assuming this method exists to get the base salary
		return baseSalary * multiplier;
	}

	// Return a random job for initialization
	public static Job randomJob() {
		Random rand = new Random();
		return values()[rand.nextInt(values().length)];
	}
}
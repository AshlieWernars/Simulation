package interaction;

import entities.Human;
import job.Job;

public class CompatibilityChecker {

	public static boolean areCompatible(Human parent1, Human parent2) {
		// Compatibility based on various traits
		double compatibilityScore = 0;

		// Existing trait compatibility
		compatibilityScore += Math.abs(parent1.getSocialSkill() - parent2.getSocialSkill()) * 0.2;
		compatibilityScore += Math.abs(parent1.getPhysicalStrength() - parent2.getPhysicalStrength()) * 0.1;
		compatibilityScore += Math.abs(parent1.getHealthRiskTolerance() - parent2.getHealthRiskTolerance()) * 0.1;
		compatibilityScore += Math.abs(parent1.getEmpathy() - parent2.getEmpathy()) * 0.3;
		compatibilityScore += Math.abs(parent1.getParentalInstinct() - parent2.getParentalInstinct()) * 0.4;
		compatibilityScore += Math.abs(parent1.getMotivation() - parent2.getMotivation()) * 0.2;
		compatibilityScore += Math.abs(parent1.getConscientiousness() - parent2.getConscientiousness()) * 0.3;
		compatibilityScore += Math.abs(parent1.getNeuroticism() - parent2.getNeuroticism()) * 0.5;
		compatibilityScore += Math.abs(parent1.getIntelligence() - parent2.getIntelligence()) * 0.2;
		compatibilityScore += Math.abs(parent1.getOpenness() - parent2.getOpenness()) * 0.2;

		// Job compatibility (e.g., matching social vs solitary, complementary jobs)
		if (parent1.getJob() != null && parent2.getJob() != null) {
			compatibilityScore += getJobCompatibility(parent1.getJob(), parent2.getJob());
		}

		// Threshold based on combined trait and job weights
		return compatibilityScore <= 15; // Adjustable threshold based on combined trait and job differences
	}

	private static double getJobCompatibility(Job job1, Job job2) {
		double compatibilityScore = 0;

		// High paying jobs (maybe take into account what type of person, like if they
		// are both greedy assholes then they are perfect for eachother)
		if ((job1 == Job.LEADER || job1 == Job.SCIENTIST) && (job2 == Job.LEADER || job2 == Job.SCIENTIST)) {
			compatibilityScore -= 1.0; // Highly compatible if both are high paying jobs
		}

		// Physical jobs are more compatible with each other (e.g., Farmer, Hunter)
		if ((job1 == Job.FARMER || job1 == Job.HUNTER) && (job2 == Job.FARMER || job2 == Job.HUNTER)) {
			compatibilityScore -= 0.8;
		}

		// Intellectual/Creative jobs are more compatible with each other
		if ((job1 == Job.ARTIST || job1 == Job.SCIENTIST) && (job2 == Job.ARTIST || job2 == Job.SCIENTIST)) {
			compatibilityScore -= 0.8;
		}

		// Conflicting jobs (e.g., Hunter and Healer)
		if ((job1 == Job.SCIENTIST && job2 == Job.FARMER) || (job1 == Job.FARMER && job2 == Job.SCIENTIST)) {
			compatibilityScore += 0.8;
		}

		if ((job1 == Job.LEADER && job2 == Job.GUARD) || (job1 == Job.GUARD && job2 == Job.LEADER)) {
			compatibilityScore += 1.0;
		}

		if ((job1 == Job.HEALER && job2 == Job.HUNTER) || (job1 == Job.HUNTER && job2 == Job.HEALER)) {
			compatibilityScore += 1.0;
		}

		return compatibilityScore;
	}
}
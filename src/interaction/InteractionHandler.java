package interaction;

import java.util.Random;

import behavior.Behavior;
import entities.Human;

public class InteractionHandler {

	public static void interact(Human human1, Human human2) {
		if (human1.getAge() < 18 && human2.getAge() < 18) {
			// interact child
		} else if (human1.getAge() < 18 && human2.getAge() > 18) {
			interactAdultAndChild(human1, human2);
		} else if (human2.getAge() < 18 && human1.getAge() > 18) {
			interactAdultAndChild(human2, human1);
		} else if (human1.getAge() > 18 && human2.getAge() > 18) {
			interactAdultAndAdult(human1, human2);
		}
	}

	private static void interactAdultAndAdult(Human human1, Human human2) {
		// Example of how interactions might affect mental health and other traits
		if (human1.getBehavior().equals(Behavior.AGGRESSIVE) && human2.getBehavior().equals(Behavior.COOPERATIVE) || human1.getBehavior().equals(Behavior.COOPERATIVE) && human2.getBehavior().equals(Behavior.AGGRESSIVE)) {
			human1.setMentalHealth(human1.getMentalHealth() - 1); // Decrease mental health for aggressive behavior
			human2.setMentalHealth(human2.getMentalHealth() - 1); // Decrease the other human's mental health

			// Aggressive person may have reduced social skills after confrontation
			human1.setSocialSkill(Math.max(0, human1.getSocialSkill() - 1));
			human2.setSocialSkill(Math.max(0, human2.getSocialSkill() - 1)); // Social skill drops for both
		} else if (human1.getBehavior().equals(Behavior.AGGRESSIVE) && human2.getBehavior().equals(Behavior.AGGRESSIVE)) {
			human1.setMentalHealth(human1.getMentalHealth() - 2); // Worse mental health impact when both are aggressive
			human2.setMentalHealth(human2.getMentalHealth() - 2); // Both lose mental health

			// Both humans' social skills may drop after an aggressive interaction
			human1.setSocialSkill(Math.max(0, human1.getSocialSkill() - 2));
			human2.setSocialSkill(Math.max(0, human2.getSocialSkill() - 2)); // Social skill drops for both
		} else if (human1.getBehavior().equals(Behavior.COOPERATIVE) && human2.getBehavior().equals(Behavior.COOPERATIVE)) {
			human1.setMentalHealth(human1.getMentalHealth() + 2); // Increase mental health for cooperative behavior
			human2.setMentalHealth(human2.getMentalHealth() + 2); // Increase the other human's mental health

			// Positive impact on social skill
			human1.setSocialSkill(Math.min(10, human1.getSocialSkill() + 1));
			human2.setSocialSkill(Math.min(10, human2.getSocialSkill() + 1)); // Social skill improves for both
		} else if (human1.getBehavior().equals(Behavior.COOPERATIVE) && human2.getBehavior().equals(Behavior.FRIENDLY)) {
			human1.setMentalHealth(human1.getMentalHealth() + 1); // Friendly behavior boosts mental health
			human2.setMentalHealth(human2.getMentalHealth() + 1);

			// Cooperative and friendly people may increase empathy
			human1.setEmpathy(Math.min(10, human1.getEmpathy() + 1));
			human2.setEmpathy(Math.min(10, human2.getEmpathy() + 1)); // Empathy grows for both
		} else if (human1.getBehavior().equals(Behavior.AGGRESSIVE) && human2.getBehavior().equals(Behavior.DEFENSIVE)) {
			human1.setMentalHealth(human1.getMentalHealth() - 1); // Aggressive behavior conflicts with defensive behavior
			human2.setMentalHealth(human2.getMentalHealth() - 1);

			// Defensive person might raise their social dominance in such situations
			human2.setSocialDominance(Math.min(10, human2.getSocialDominance() + 1));
		}

		// Prevent mental health from going negative and ensure it does not exceed 10
		human1.setMentalHealth(Math.max(0, Math.min(10, human1.getMentalHealth())));
		human2.setMentalHealth(Math.max(0, Math.min(10, human2.getMentalHealth())));

		// Example: Affecting physical strength or attractiveness after certain
		// interactions
		if (human1.getBehavior().equals(Behavior.COOPERATIVE) && human2.getBehavior().equals(Behavior.COOPERATIVE)) {
			// Cooperation could lead to increased strength through teamwork (example)
			human1.setPhysicalStrength(Math.min(10, human1.getPhysicalStrength() + 1));
			human2.setPhysicalStrength(Math.min(10, human2.getPhysicalStrength() + 1));
		} else if (human1.getBehavior().equals(Behavior.AGGRESSIVE) && human2.getBehavior().equals(Behavior.AGGRESSIVE)) {
			// Aggressive behaviors could result in a physical decrease after an argument or
			// fight
			human1.setPhysicalStrength(Math.max(0, human1.getPhysicalStrength() - 1));
			human2.setPhysicalStrength(Math.max(0, human2.getPhysicalStrength() - 1));
		}

	}

	public static void interactAdultAndChild(Human child, Human adult) {
		if (adult.getParentalInstinct() < 5) { // Low parental instinct, potentially negative outcome
			child.setMentalHealth(child.getMentalHealth() - 1);
			adult.setMentalHealth(adult.getMentalHealth() - 1);
		} else if (adult.getParentalInstinct() >= 5) { // Positive parental instinct could improve child’s well-being
			child.setMentalHealth(Math.min(10, child.getMentalHealth() + 1)); // Increase mental health for the child
			adult.setMentalHealth(Math.min(10, adult.getMentalHealth() + 1)); // Positive reinforcement for the adult
		}

		// Example: Influence of adult's behavior on the child
		if (adult.getBehavior().equals(Behavior.COOPERATIVE) || adult.getBehavior().equals(Behavior.FRIENDLY)) {
			child.setSocialSkill(Math.min(10, child.getSocialSkill() + 1)); // Cooperative behavior boosts child’s social skills
		} else if (adult.getBehavior().equals(Behavior.AGGRESSIVE) || adult.getBehavior().equals(Behavior.DECEPTIVE)) {
			child.setSocialSkill(Math.max(0, child.getSocialSkill() - 1)); // Aggressive behavior might harm the child's social skill
		}

		// Ensure mental health stays within range
		child.setMentalHealth(Math.max(0, Math.min(10, child.getMentalHealth())));
		adult.setMentalHealth(Math.max(0, Math.min(10, adult.getMentalHealth())));
	}

	/**
	 * Assumes the parents are of age and compatible for reproduction. Creates a
	 * child based on the traits of the parents.
	 * 
	 * @param parent1 The first parent involved in reproduction.
	 * @param parent2 The second parent involved in reproduction.
	 * @return {@code Human} The created child, inheriting traits from both parents.
	 */
	public static Human reproduce(Human parent1, Human parent2) {
		Human child = new Human();

		child.setAge(0);
		child.setJob(null);

		// Inherit traits (mix of parent traits)
		child.setExtroversion(mixTraits(parent1.getExtroversion(), parent2.getExtroversion()));
		child.setExtroversion(applyMutation(child.getExtroversion())); // Apply mutation to extroversion

		child.setNeuroticism(mixTraits(parent1.getNeuroticism(), parent2.getNeuroticism()));
		child.setNeuroticism(applyMutation(child.getNeuroticism())); // Apply mutation to neuroticism

		child.setOpenness(mixTraits(parent1.getOpenness(), parent2.getOpenness()));
		child.setOpenness(applyMutation(child.getOpenness())); // Apply mutation to openness

		// Inherit social skills and health and apply mutation
		child.setSocialSkill(mixTraits(parent1.getSocialSkill(), parent2.getSocialSkill()));
		child.setSocialSkill(applyMutation(child.getSocialSkill())); // Apply mutation to social skill

		child.setHealth(mixTraits(parent1.getHealth(), parent2.getHealth()));
		child.setHealth(applyMutation(child.getHealth())); // Apply mutation to health

		child.setMentalHealth(mixTraits(parent1.getMentalHealth(), parent2.getMentalHealth()));
		child.setMentalHealth(applyMutation(child.getMentalHealth())); // Apply mutation to mental health

		// Inherit and mutate new traits
		child.setIntelligence(mixTraits(parent1.getIntelligence(), parent2.getIntelligence()));
		child.setIntelligence(applyMutation(child.getIntelligence())); // Apply mutation to intelligence

		child.setAttractiveness(mixTraits(parent1.getAttractiveness(), parent2.getAttractiveness()));
		child.setAttractiveness(applyMutation(child.getAttractiveness())); // Apply mutation to attractiveness

		child.setEmpathy(mixTraits(parent1.getEmpathy(), parent2.getEmpathy()));
		child.setEmpathy(applyMutation(child.getEmpathy())); // Apply mutation to empathy

		child.setCreativity(mixTraits(parent1.getCreativity(), parent2.getCreativity()));
		child.setCreativity(applyMutation(child.getCreativity())); // Apply mutation to creativity

		child.setMotivation(mixTraits(parent1.getMotivation(), parent2.getMotivation()));
		child.setMotivation(applyMutation(child.getMotivation())); // Apply mutation to motivation

		child.setHealthRiskTolerance(mixTraits(parent1.getHealthRiskTolerance(), parent2.getHealthRiskTolerance()));
		child.setHealthRiskTolerance(applyMutation(child.getHealthRiskTolerance())); // Apply mutation to health risk tolerance

		child.setConscientiousness(mixTraits(parent1.getConscientiousness(), parent2.getConscientiousness()));
		child.setConscientiousness(applyMutation(child.getConscientiousness())); // Apply mutation to conscientiousness

		child.setStressResilience(mixTraits(parent1.getStressResilience(), parent2.getStressResilience()));
		child.setStressResilience(applyMutation(child.getStressResilience())); // Apply mutation to stress resilience

		child.setParentalInstinct(mixTraits(parent1.getParentalInstinct(), parent2.getParentalInstinct()));
		child.setParentalInstinct(applyMutation(child.getParentalInstinct())); // Apply mutation to parental instinct

		child.setSocialDominance(mixTraits(parent1.getSocialDominance(), parent2.getSocialDominance()));
		child.setSocialDominance(applyMutation(child.getSocialDominance())); // Apply mutation to social dominance

		child.updateBehaviorBasedOnTraits();

		return child;
	}

	// Method to apply a small mutation to a trait (between -1 and 1)
	private static int applyMutation(int traitValue) {
		Random rand = new Random();
		// 1% chance to apply mutation (±1)
		if (rand.nextDouble() < 0.01) {
			int mutation = rand.nextInt(3) - 1; // Generate -1, 0, or 1
			traitValue += mutation;
		}
		// Ensure the trait stays within the valid range (0-10)
		return Math.max(0, Math.min(10, traitValue));
	}

	// New method to mix traits more dynamically (random bias towards one parent)
	private static int mixTraits(int trait1, int trait2) {
		Random rand = new Random();
		if (rand.nextDouble() < 0.5) {
			return trait1; // Bias towards first parent
		}
		return trait2; // Bias towards second parent
	}
}

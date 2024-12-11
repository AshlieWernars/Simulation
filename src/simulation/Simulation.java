package simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import entities.Human;
import interaction.InteractionHandler;
import job.Job;

public class Simulation extends Thread {

	private List<Human> population;
	private final int populationLimit = 1500;
	private int generation;

	private int aggressiveCount;
	private int cooperativeCount;
	private int neutralCount;
	private int curiousCount = 0;
	private int defensiveCount = 0;
	private int friendlyCount = 0;
	private int greedyCount = 0;
	private int lazyCount = 0;
	private int honestCount = 0;
	private int deceptiveCount = 0;

	private double totalHealth;
	private double totalSocialSkill;
	private double totalPhysicalStrength;
	private double totalMentalHealth;
	private double totalExtroversion;
	private double totalNeuroticism;
	private double totalOpenness;

	private double totalIntelligence;
	private double totalAttractiveness;
	private double totalEmpathy;
	private double totalCreativity;
	private double totalMotivation;
	private double totalHealthRiskTolerance;
	private double totalConscientiousness;
	private double totalStressResilience;
	private double totalParentalInstinct;
	private double totalSocialDominance;

	private boolean isRunning;
	private boolean hasStarted;
	private String stats;
	private JTextArea statsArea;
	Random random = new Random();

	public Simulation() {
		this.population = new ArrayList<>();
		this.generation = 0;
		for (int i = 0; i < 10; i++) {
			population.add(new Human());
		}

		this.isRunning = false;
		this.hasStarted = false;
	}

	public synchronized void startSimulation() {
		if (!hasStarted) {
			hasStarted = true;
			isRunning = true;
			start(); // Start the thread for the first time
		} else {
			isRunning = true; // Resume the simulation
			notify(); // Notify the thread to continue
		}
	}

	public synchronized void stopSimulation() {
		isRunning = false; // Pause the simulation
	}

	@Override
	public void run() {
		while (true) {
			if (!isRunning) {
				try {
					synchronized (this) {
						wait(); // Wait until notified to resume
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}

			simulateGeneration();

			try {
				Thread.sleep(10); // Sleep for 1 second between generations
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public void reset() {
		this.generation = 0;
		this.aggressiveCount = 0;
		this.cooperativeCount = 0;
		this.neutralCount = 0;
		this.curiousCount = 0;
		this.defensiveCount = 0;
		this.friendlyCount = 0;
		this.greedyCount = 0;
		this.lazyCount = 0;
		this.honestCount = 0;
		this.deceptiveCount = 0;

		this.totalHealth = 0;
		this.totalSocialSkill = 0;
		this.totalPhysicalStrength = 0;
		this.totalMentalHealth = 0;
		this.totalExtroversion = 0;
		this.totalNeuroticism = 0;
		this.totalOpenness = 0;
		this.totalIntelligence = 0;
		this.totalAttractiveness = 0;
		this.totalEmpathy = 0;
		this.totalCreativity = 0;
		this.totalMotivation = 0;
		this.totalHealthRiskTolerance = 0;
		this.totalConscientiousness = 0;
		this.totalStressResilience = 0;
		this.totalParentalInstinct = 0;
		this.totalSocialDominance = 0;

		this.population.clear();
		for (int i = 0; i < 10; i++) {
			population.add(new Human());
		}
	}

	public void simulateGeneration() {
		generation++;
		this.aggressiveCount = 0;
		this.cooperativeCount = 0;
		this.neutralCount = 0;
		this.curiousCount = 0;
		this.defensiveCount = 0;
		this.friendlyCount = 0;
		this.greedyCount = 0;
		this.lazyCount = 0;
		this.honestCount = 0;
		this.deceptiveCount = 0;

		this.totalHealth = 0;
		this.totalSocialSkill = 0;
		this.totalPhysicalStrength = 0;
		this.totalMentalHealth = 0;
		this.totalExtroversion = 0;
		this.totalNeuroticism = 0;
		this.totalOpenness = 0;
		this.totalIntelligence = 0;
		this.totalAttractiveness = 0;
		this.totalEmpathy = 0;
		this.totalCreativity = 0;
		this.totalMotivation = 0;
		this.totalHealthRiskTolerance = 0;
		this.totalConscientiousness = 0;
		this.totalStressResilience = 0;
		this.totalParentalInstinct = 0;
		this.totalSocialDominance = 0;

		// Humans interact with each other
		for (int i = 0; i < population.size(); i++) {
			Human human1 = population.get(i);

			if (random.nextInt(10) >= human1.getExtroversion() || random.nextInt(10) >= human1.getSocialSkill()) {
				continue; // Human interact less based on low extroversion or low social skill
			}

			// Randomly select a different human for interaction
			int randomIndex;
			do {
				randomIndex = random.nextInt(population.size());
			} while (randomIndex == i); // Ensure it's not the same human

			Human human2 = population.get(randomIndex);

			// Humans interact
			InteractionHandler.interact(human1, human2);

			human1.updateBehaviorBasedOnTraits();
			human2.updateBehaviorBasedOnTraits();
			human1.assignJob();
			human2.assignJob();

			human1.doJob();
		}

		// Reproduction (based on traits compatibility)
		reproduce();

		for (Iterator<Human> iterator = population.iterator(); iterator.hasNext();) {
			Human human = iterator.next();
			human.ageOneYear();

			// Assuming the human's age is already incremented
			double deathChance = 0;

			if (human.getAge() <= 1) {
				deathChance = 0.003; // 0.3% chance for infants
			} else if (human.getAge() <= 40) {
				deathChance = 0.001; // 0.1% chance for young adults
			} else if (human.getAge() <= 60) {
				deathChance = 0.005; // 0.5% chance for middle-aged adults
			} else if (human.getAge() <= 80) {
				deathChance = 0.02; // 2% chance for older adults
			} else if (human.getAge() <= 90) {
				deathChance = 0.10; // 10% chance for 80-90
			} else {
				deathChance = 0.30; // 30% chance for 90+
			}

			// Random chance to die based on the death chance
			if (random.nextFloat() < deathChance) {
				iterator.remove(); // Remove the human if they die
			}
		}

		for (Human human : population) {
			switch (human.getBehavior()) {
			case AGGRESSIVE:
				aggressiveCount++;
				break;
			case COOPERATIVE:
				cooperativeCount++;
				break;
			case NEUTRAL:
				neutralCount++;
				break;
			case CURIOUS:
				curiousCount++;
				break;
			case DEFENSIVE:
				defensiveCount++;
				break;
			case FRIENDLY:
				friendlyCount++;
				break;
			case GREEDY:
				greedyCount++;
				break;
			case LAZY:
				lazyCount++;
				break;
			case HONEST:
				honestCount++;
				break;
			case DECEPTIVE:
				deceptiveCount++;
				break;
			default:
				// This shouldn't happen if behavior is always one of the defined enums
				throw new IllegalArgumentException("Unknown behavior: " + human.getBehavior());
			}

			totalHealth += human.getHealth();
			totalSocialSkill += human.getSocialSkill();
			totalPhysicalStrength += human.getPhysicalStrength();
			totalMentalHealth += human.getMentalHealth();
			totalExtroversion += human.getExtroversion();
			totalNeuroticism += human.getNeuroticism();
			totalOpenness += human.getOpenness();

			// Adding new traits to accumulate totals
			totalIntelligence += human.getIntelligence();
			totalAttractiveness += human.getAttractiveness();
			totalEmpathy += human.getEmpathy();
			totalCreativity += human.getCreativity();
			totalMotivation += human.getMotivation();
			totalHealthRiskTolerance += human.getHealthRiskTolerance();
			totalConscientiousness += human.getConscientiousness();
			totalStressResilience += human.getStressResilience();
			totalParentalInstinct += human.getParentalInstinct();
			totalSocialDominance += human.getSocialDominance();
		}

		updateStats();
	}

	private void reproduce() {
		// List to store compatible humans for reproduction
		List<Human> eligibleMates = new ArrayList<>();

		// Evaluate compatibility based on traits
		for (int i = 0; i < population.size(); i++) {
			for (int j = i + 1; j < population.size(); j++) {
				Human parent1 = population.get(i);
				Human parent2 = population.get(j);

				// Check if the two humans are compatible for reproduction
				if (areCompatible(parent1, parent2)) {
					eligibleMates.add(parent1); // Add compatible parents to the list
					eligibleMates.add(parent2);
				}
			}
		}

		// Calculate the number of mates to reproduce (half of eligible mates)
		int numMatesToReproduce = eligibleMates.size() / 2;

		// Reproduce using eligible mates
		for (int i = 0; i < numMatesToReproduce; i++) {
			Human parent1 = eligibleMates.get(i * 2); // Even index
			Human parent2 = eligibleMates.get(i * 2 + 1); // Odd index

			// Create a child and add to population, but don't exceed the population limit
			if (population.size() < populationLimit) {
				Human child = InteractionHandler.reproduce(parent1, parent2);
				population.add(child); // Add the child to the population
			}
		}
	}

	private boolean areCompatible(Human parent1, Human parent2) {
		if (parent1.getAge() < 18 || parent2.getAge() < 18) {
			return false; // Basic age check
		}

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

	private double getJobCompatibility(Job job1, Job job2) {
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

	public void updateStats() {
		stats = "Generation: " + generation + "\n";

		stats += "Aggressive: " + aggressiveCount + "\n";
		stats += "Cooperative: " + cooperativeCount + "\n";
		stats += "Neutral: " + neutralCount + "\n";
		stats += "Curious: " + curiousCount + "\n";
		stats += "Defensive: " + defensiveCount + "\n";
		stats += "Friendly: " + friendlyCount + "\n";
		stats += "Greedy: " + greedyCount + "\n";
		stats += "Lazy: " + lazyCount + "\n";
		stats += "Honest: " + honestCount + "\n";
		stats += "Deceptive: " + deceptiveCount + "\n";

		stats += "Average Health: " + getAverageHealth() + "\n";
		stats += "Average Social Skill: " + getAverageSocialSkill() + "\n";
		stats += "Average Physical Strength: " + getAveragePhysicalStrength() + "\n";
		stats += "Average Mental Health: " + getAverageMentalHealth() + "\n";
		stats += "Average Extroversion: " + getAverageExtroversion() + "\n";
		stats += "Average Neuroticism: " + getAverageNeuroticism() + "\n";
		stats += "Average Openness: " + getAverageOpenness() + "\n";

		// Adding new traits to stats
		stats += "Average Intelligence: " + getAverageIntelligence() + "\n";
		stats += "Average Attractiveness: " + getAverageAttractiveness() + "\n";
		stats += "Average Empathy: " + getAverageEmpathy() + "\n";
		stats += "Average Creativity: " + getAverageCreativity() + "\n";
		stats += "Average Motivation: " + getAverageMotivation() + "\n";
		stats += "Average Health Risk Tolerance: " + getAverageHealthRiskTolerance() + "\n";
		stats += "Average Conscientiousness: " + getAverageConscientiousness() + "\n";
		stats += "Average Stress Resilience: " + getAverageStressResilience() + "\n";
		stats += "Average Parental Instinct: " + getAverageParentalInstinct() + "\n";
		stats += "Average Social Dominance: " + getAverageSocialDominance() + "\n";

		if (statsArea != null) {
			// Safely update the statsArea in the event dispatch thread
			SwingUtilities.invokeLater(() -> statsArea.setText(stats));
		}

		System.out.println(stats);
	}

	public String getStats() {
		return stats;
	}

	public int getGeneration() {
		return generation;
	}

	public int getAggressiveCount() {
		return aggressiveCount;
	}

	public int getCooperativeCount() {
		return cooperativeCount;
	}

	public int getNeutralCount() {
		return neutralCount;
	}

	public double getAverageHealth() {
		return totalHealth / population.size();
	}

	public double getAverageSocialSkill() {
		return totalSocialSkill / population.size();
	}

	public double getAveragePhysicalStrength() {
		return totalPhysicalStrength / population.size();
	}

	public double getAverageMentalHealth() {
		return totalMentalHealth / population.size();
	}

	public double getAverageExtroversion() {
		return totalExtroversion / population.size();
	}

	public double getAverageNeuroticism() {
		return totalNeuroticism / population.size();
	}

	public double getAverageOpenness() {
		return totalOpenness / population.size();
	}

	public double getAverageIntelligence() {
		return totalIntelligence / population.size();
	}

	public double getAverageAttractiveness() {
		return totalAttractiveness / population.size();
	}

	public double getAverageEmpathy() {
		return totalEmpathy / population.size();
	}

	public double getAverageCreativity() {
		return totalCreativity / population.size();
	}

	public double getAverageMotivation() {
		return totalMotivation / population.size();
	}

	public double getAverageHealthRiskTolerance() {
		return totalHealthRiskTolerance / population.size();
	}

	public double getAverageConscientiousness() {
		return totalConscientiousness / population.size();
	}

	public double getAverageStressResilience() {
		return totalStressResilience / population.size();
	}

	public double getAverageParentalInstinct() {
		return totalParentalInstinct / population.size();
	}

	public double getAverageSocialDominance() {
		return totalSocialDominance / population.size();
	}

	public void setStatsArea(JTextArea statsArea) {
		this.statsArea = statsArea;
	}
}
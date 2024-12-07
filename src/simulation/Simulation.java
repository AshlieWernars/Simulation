package simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import entities.Human;

public class Simulation extends Thread {

	private List<Human> population;
	private final int populationLimit = 200; // Set the population limit to 200
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
		for (int i = 0; i < 100; i++) {
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
				Thread.sleep(1000); // Sleep for 1 second between generations
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
		for (int i = 0; i < 100; i++) {
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

			// Randomly select a different human for interaction
			int randomIndex;
			do {
				randomIndex = random.nextInt(population.size());
			} while (randomIndex == i); // Ensure it's not the same human

			Human human2 = population.get(randomIndex);

			// Humans interact
			human1.interact(human2);

			human1.updateBehaviorBasedOnTraits();
			human2.updateBehaviorBasedOnTraits();

			for (Iterator<Human> iterator = population.iterator(); iterator.hasNext();) {
				Human human = iterator.next();
				human.ageOneYear();
				if (human.getAge() >= 100) {
					iterator.remove(); // Safely remove the human using the iterator
				}
			}

		}

		// Reproduction (based on traits compatibility)
		reproduce();

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

	// Handle reproduction based on traits compatibility
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

		// Limit reproduction to the top 50% of compatible humans
		// In this example, we simply take the first half of the eligible mates for
		// reproduction
		int numMatesToReproduce = eligibleMates.size() / 2;

		// Reproduce using the top compatible mates
		for (int i = 0; i < numMatesToReproduce; i++) {
			Human parent1 = eligibleMates.get(i * 2); // Even index
			Human parent2 = eligibleMates.get(i * 2 + 1); // Odd index

			// Create a child and add to population, but don't exceed the population limit
			if (population.size() < populationLimit) {
				Human child = Human.reproduce(parent1, parent2);
				population.add(child); // Add the child to the population
			}
		}
	}

	// Compatibility check (based on personality traits and health)
	private boolean areCompatible(Human parent1, Human parent2) {
		// Compatibility based on extroversion, social skill, and mental health
		int compatibilityScore = Math.abs(parent1.getExtroversion() - parent2.getExtroversion()) + Math.abs(parent1.getSocialSkill() - parent2.getSocialSkill()) + Math.abs(parent1.getMentalHealth() - parent2.getMentalHealth());

		// We assume that a lower score means higher compatibility
		return compatibilityScore <= 10; // Arbitrary threshold for compatibility
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

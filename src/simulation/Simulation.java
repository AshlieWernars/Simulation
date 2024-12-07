package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Human;

public class Simulation {
	private List<Human> humans; // List of humans in the simulation
	private int generations; // Number of generations for the simulation to run
	private Random rand;
	private final int populationLimit = 200; // Population limit set to 200

	public Simulation(int numHumans, int generations) {
		this.humans = new ArrayList<>();
		this.generations = generations;
		this.rand = new Random();

		// Initialize the population with random humans, but do not exceed the
		// population limit
		for (int i = 0; i < Math.min(numHumans, populationLimit); i++) {
			humans.add(new Human());
		}
	}

	// Run the simulation
	public void run() {
		for (int generation = 1; generation <= generations; generation++) {
			System.out.println("Generation " + generation);

			// Humans interact with each other
			for (int i = 0; i < humans.size(); i++) {
				Human human1 = humans.get(i);

				// Randomly select a different human for interaction
				int randomIndex;
				do {
					randomIndex = rand.nextInt(humans.size());
				} while (randomIndex == i); // Ensure it's not the same human

				Human human2 = humans.get(randomIndex);

				// Humans interact
				human1.interact(human2);

				human1.updateBehaviorBasedOnTraits();
				human2.updateBehaviorBasedOnTraits();
			}

			// Reproduction (based on traits compatibility)
			reproduce();

			// Print the current state of the population
			trackPopulation();
		}
	}

	// Track the population distribution based on behavior and traits
	private void trackPopulation() {
		int aggressive = 0, cooperative = 0, neutral = 0;
		double totalHealth = 0, totalSocialSkill = 0, totalPhysicalStrength = 0, totalMentalHealth = 0;
		double totalExtroversion = 0, totalNeuroticism = 0, totalOpenness = 0;

		// Loop through all humans to count behavior and sum trait values
		for (Human human : humans) {
			if (human.getBehavior().equals("Aggressive")) {
				aggressive++;
			} else if (human.getBehavior().equals("Cooperative")) {
				cooperative++;
			} else {
				neutral++;
			}

			totalHealth += human.getHealth();
			totalSocialSkill += human.getSocialSkill();
			totalPhysicalStrength += human.getPhysicalStrength();
			totalMentalHealth += human.getMentalHealth();
			totalExtroversion += human.getExtroversion();
			totalNeuroticism += human.getNeuroticism();
			totalOpenness += human.getOpenness();
		}

		// Calculate and print averages for all traits
		int populationSize = humans.size();
		System.out.println("Aggressive: " + aggressive + ", Cooperative: " + cooperative + ", Neutral: " + neutral);
		System.out.println("Average Health: " + (totalHealth / populationSize));
		System.out.println("Average Social Skill: " + (totalSocialSkill / populationSize));
		System.out.println("Average Physical Strength: " + (totalPhysicalStrength / populationSize));
		System.out.println("Average Mental Health: " + (totalMentalHealth / populationSize));
		System.out.println("Average Extroversion: " + (totalExtroversion / populationSize));
		System.out.println("Average Neuroticism: " + (totalNeuroticism / populationSize));
		System.out.println("Average Openness: " + (totalOpenness / populationSize));
	}

	// Handle reproduction based on traits compatibility
	private void reproduce() {
		// List to store compatible humans for reproduction
		List<Human> eligibleMates = new ArrayList<>();

		// Evaluate compatibility based on traits
		for (int i = 0; i < humans.size(); i++) {
			for (int j = i + 1; j < humans.size(); j++) {
				Human parent1 = humans.get(i);
				Human parent2 = humans.get(j);

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
			if (humans.size() < populationLimit) {
				Human child = Human.reproduce(parent1, parent2);
				humans.add(child); // Add the child to the population
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
}

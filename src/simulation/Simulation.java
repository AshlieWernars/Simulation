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
	private double totalHealth;
	private double totalSocialSkill;
	private double totalPhysicalStrength;
	private double totalMentalHealth;
	private double totalExtroversion;
	private double totalNeuroticism;
	private double totalOpenness;
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
		this.totalHealth = 0;
		this.totalSocialSkill = 0;
		this.totalPhysicalStrength = 0;
		this.totalMentalHealth = 0;
		this.totalExtroversion = 0;
		this.totalNeuroticism = 0;
		this.totalOpenness = 0;
		this.population.clear();
		for (int i = 0; i < 100; i++) {
			population.add(new Human());
		}
	}

	public void simulateGeneration() {
		generation++;
		aggressiveCount = 0;
		cooperativeCount = 0;
		neutralCount = 0;
		totalHealth = 0;
		totalSocialSkill = 0;
		totalPhysicalStrength = 0;
		totalMentalHealth = 0;
		totalExtroversion = 0;
		totalNeuroticism = 0;
		totalOpenness = 0;

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
			if (human.getBehavior().equals("Aggressive")) {
				aggressiveCount++;
			} else if (human.getBehavior().equals("Cooperative")) {
				cooperativeCount++;
			} else {
				neutralCount++;
			}
			totalHealth += human.getHealth();
			totalSocialSkill += human.getSocialSkill();
			totalPhysicalStrength += human.getPhysicalStrength();
			totalMentalHealth += human.getMentalHealth();
			totalExtroversion += human.getExtroversion();
			totalNeuroticism += human.getNeuroticism();
			totalOpenness += human.getOpenness();
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
		stats = "Generation: " + getGeneration() + "\n";
		stats += "Aggressive: " + getAggressiveCount() + "\n";
		stats += "Cooperative: " + getCooperativeCount() + "\n";
		stats += "Neutral: " + getNeutralCount() + "\n";
		stats += "Average Health: " + getAverageHealth() + "\n";
		stats += "Average Social Skill: " + getAverageSocialSkill() + "\n";
		stats += "Average Physical Strength: " + getAveragePhysicalStrength() + "\n";
		stats += "Average Mental Health: " + getAverageMentalHealth() + "\n";
		stats += "Average Extroversion: " + getAverageExtroversion() + "\n";
		stats += "Average Neuroticism: " + getAverageNeuroticism() + "\n";
		stats += "Average Openness: " + getAverageOpenness() + "\n";

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

	public void setStatsArea(JTextArea statsArea) {
		this.statsArea = statsArea;
	}
}

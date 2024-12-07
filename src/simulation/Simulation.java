package simulation;

import java.util.ArrayList;
import java.util.List;

import entities.Human;

public class Simulation {

	private List<Human> population;
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

	public Simulation() {
		this.population = new ArrayList<>();
		this.generation = 0;
		this.isRunning = false;
		// Initialize the population with humans (example: 100 humans)
		for (int i = 0; i < 100; i++) {
			population.add(new Human());
		}
	}

	public void start() {
		if (!isRunning) {
			isRunning = true;
			// You can simulate starting here (e.g., running generations, interactions)
			// Example: simulateGeneration();
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
		// Simulate a generation step (interaction, reproduction, mutation)
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

		// Example of how humans interact
		for (Human human : population) {
			// Track behaviors and traits
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
	}

	// Getter methods for statistics
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

	// Method to simulate human reproduction and behavior (this can be expanded
	// further)
	public void simulate() {
		// Simulate interactions and reproduction here
		simulateGeneration();
	}
}

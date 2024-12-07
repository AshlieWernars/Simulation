package simulation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import entities.Human;

public class Simulation extends Thread {

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
	private String stats;
	private JTextArea statsArea;

	public Simulation() {
		this.population = new ArrayList<>();
		this.generation = 0;
		for (int i = 0; i < 100; i++) {
			population.add(new Human());
		}
	}

	public void startSimulation() {
		if (!isRunning) {
			isRunning = true;
			start(); // This starts the thread and calls run()
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			simulateGeneration();
			try {
				Thread.sleep(1000); // Sleep for 1 second before simulating the next generation
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (generation == 1000) {
				isRunning = false;
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
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					statsArea.setText(stats);
				}
			});
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

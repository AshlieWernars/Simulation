package simulation;

import java.util.List;

import entities.Human;

public class StatsTracker {

	static int amountOfNewChildren;
	static int amountOfKilledHumans;

	static int amountOfChildren;

	private static int aggressiveCount;
	private static int cooperativeCount;
	private static int neutralCount;
	private static int curiousCount = 0;
	private static int defensiveCount = 0;
	private static int friendlyCount = 0;
	private static int greedyCount = 0;
	private static int lazyCount = 0;
	private static int honestCount = 0;
	private static int deceptiveCount = 0;

	private static double totalHealth;
	private static double totalSocialSkill;
	private static double totalPhysicalStrength;
	private static double totalMentalHealth;
	private static double totalExtroversion;
	private static double totalNeuroticism;
	private static double totalOpenness;

	private static double totalIntelligence;
	private static double totalAttractiveness;
	private static double totalEmpathy;
	private static double totalCreativity;
	private static double totalMotivation;
	private static double totalHealthRiskTolerance;
	private static double totalConscientiousness;
	private static double totalStressResilience;
	private static double totalParentalInstinct;
	private static double totalSocialDominance;
	private static double totalMoney;

	static int unemployedPeople;
	static int retiredPeople;

	static Human richestPerson;
	static int highestWealthReached;

	private static String stats;
	private static int populationSize;
	public static int amountOfPeopleHomeless;

	public static void reset() {
		amountOfNewChildren = 0;
		amountOfKilledHumans = 0;
		amountOfChildren = 0;
		amountOfPeopleHomeless = 0;
		aggressiveCount = 0;
		cooperativeCount = 0;
		neutralCount = 0;
		curiousCount = 0;
		defensiveCount = 0;
		friendlyCount = 0;
		greedyCount = 0;
		lazyCount = 0;
		honestCount = 0;
		deceptiveCount = 0;

		totalHealth = 0;
		totalSocialSkill = 0;
		totalPhysicalStrength = 0;
		totalMentalHealth = 0;
		totalExtroversion = 0;
		totalNeuroticism = 0;
		totalOpenness = 0;
		totalIntelligence = 0;
		totalAttractiveness = 0;
		totalEmpathy = 0;
		totalCreativity = 0;
		totalMotivation = 0;
		totalHealthRiskTolerance = 0;
		totalConscientiousness = 0;
		totalStressResilience = 0;
		totalParentalInstinct = 0;
		totalSocialDominance = 0;
		totalMoney = 0;
		unemployedPeople = 0;
		retiredPeople = 0;
		richestPerson = null;

	}

	public static void fullReset() {
		highestWealthReached = 0;
	}

	public static void track(List<Human> population, int generation) {
		populationSize = population.size();

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

			if (richestPerson == null) {
				richestPerson = human;
			} else {
				if (richestPerson.getMoney() < human.getMoney()) {
					richestPerson = human;
				}
			}

			if (highestWealthReached < human.getMoney()) {
				highestWealthReached = human.getMoney();
			}

			if (human.getHouse() == null) {
				StatsTracker.amountOfPeopleHomeless++;
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
			totalMoney += human.getMoney();
		}

		updateStats(generation);
	}

	public static void updateStats(int day) {
		int years = day / 365; // Calculate years
		int remainingDays = day % 365; // Calculate remaining days

		stats = "Year: " + years + ", Days: " + remainingDays + "\n"; // Display both years and remaining days
		stats += "Population Size: " + populationSize + "\n";
		stats += "Amount of added children: " + (amountOfNewChildren / 2) + "\n";
		stats += "Amount of killed people: " + (amountOfKilledHumans) + "\n";
		stats += "Amount of children: " + (amountOfChildren) + "\n";
		stats += "Amount of homeless: " + (amountOfPeopleHomeless) + "\n";

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
		stats += "Average Money: " + getAverageMoney() + "\n";

		stats += "Unemployed People: " + unemployedPeople + "\n";
		stats += "Retired People: " + retiredPeople + "\n";

		if (richestPerson != null) {
			stats += "Richest Person: " + richestPerson.getName() + "\n";
			stats += "Richest Person Wealth: " + richestPerson.getMoney() + "\n";
		} else {
			stats += "Richest Person: " + "your mom" + "\n";
			stats += "Richest Person Wealth: " + "-1" + "\n";
		}

		stats += "Highest Wealth: " + highestWealthReached + "\n";

		// System.out.println(stats);
	}

	public static String getStats() {
		return stats;
	}

	public static String getAverageHealth() {
		return String.format("%.2f", totalHealth / populationSize);
	}

	public static String getAverageSocialSkill() {
		return String.format("%.2f", totalSocialSkill / populationSize);
	}

	public static String getAveragePhysicalStrength() {
		return String.format("%.2f", totalPhysicalStrength / populationSize);
	}

	public static String getAverageMentalHealth() {
		return String.format("%.2f", totalMentalHealth / populationSize);
	}

	public static String getAverageExtroversion() {
		return String.format("%.2f", totalExtroversion / populationSize);
	}

	public static String getAverageNeuroticism() {
		return String.format("%.2f", totalNeuroticism / populationSize);
	}

	public static String getAverageOpenness() {
		return String.format("%.2f", totalOpenness / populationSize);
	}

	public static String getAverageIntelligence() {
		return String.format("%.2f", totalIntelligence / populationSize);
	}

	public static String getAverageAttractiveness() {
		return String.format("%.2f", totalAttractiveness / populationSize);
	}

	public static String getAverageEmpathy() {
		return String.format("%.2f", totalEmpathy / populationSize);
	}

	public static String getAverageCreativity() {
		return String.format("%.2f", totalCreativity / populationSize);
	}

	public static String getAverageMotivation() {
		return String.format("%.2f", totalMotivation / populationSize);
	}

	public static String getAverageHealthRiskTolerance() {
		return String.format("%.2f", totalHealthRiskTolerance / populationSize);
	}

	public static String getAverageConscientiousness() {
		return String.format("%.2f", totalConscientiousness / populationSize);
	}

	public static String getAverageStressResilience() {
		return String.format("%.2f", totalStressResilience / populationSize);
	}

	public static String getAverageParentalInstinct() {
		return String.format("%.2f", totalParentalInstinct / populationSize);
	}

	public static String getAverageSocialDominance() {
		return String.format("%.2f", totalSocialDominance / populationSize);
	}

	public static String getAverageMoney() {
		return String.format("%.2f", totalMoney / populationSize);
	}
}
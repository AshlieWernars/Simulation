package simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import entities.Human;
import housing.House;
import housing.HousingSystem;
import interaction.CompatibilityChecker;
import interaction.InteractionHandler;
import names.NameLoader;

public class Simulation extends Thread {

	private List<Human> population;
	private final int populationLimit = 50;
	private int day;

	private boolean isRunning;
	private boolean hasStarted;

	Random random = new Random();

	@SuppressWarnings("unused")
	public Simulation() {
		try {
			NameLoader.loadNames("names.txt");
			new HousingSystem(populationLimit);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.population = new ArrayList<>();
		this.day = 0;
		for (int i = 0; i < 50; i++) {
			population.add(new Human(false));
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

			simulateDay();

			try {
				Thread.sleep(10); // Sleep for 1 second between generations
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public void reset() {
		this.day = 0;
		StatsTracker.fullReset();

		this.population.clear();
		for (int i = 0; i < 10; i++) {
			population.add(new Human(false));
		}
	}

	public void simulateDay() {
		this.day++;
		StatsTracker.reset();

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

			int jobStatus = human1.tryToDoJob();

			switch (jobStatus) {
			case 0:
				// They did their job
				break;
			case 1:
				StatsTracker.unemployedPeople++;
				// They are unemployed
				break;
			case 2:
				// Retired
				StatsTracker.retiredPeople++;
				break;
			case 3:
				// Too young
				StatsTracker.amountOfChildren++;
				break;
			default:
				throw new RuntimeException("Unkown Job Status");
			}

			human1.payHealthInsurance();

			if (human1.getHouse() == null) { // No house
				for (House house : HousingSystem.getHouses()) {
					if (house.isFull()) {
						continue;
					}

					if (house.getPricePerMonthPerPerson() > human1.getLastSalary()) {
						continue; // House is too expensive
					}

					house.addResident(human1);
				}
			}
		}

		if (this.day % 28 == 0) {
			runMonth(); // Run your code here
		}

		HousingSystem.update();

		// Reproduction (based on traits compatibility)
		reproduce();

		for (Iterator<Human> iterator = population.iterator(); iterator.hasNext();) {
			Human human = iterator.next();
			human.ageOneYear();
			if (human.hadChildDuringSimStep()) {
				StatsTracker.amountOfNewChildren++;
				human.setHadChildDuringSimStep(false);
			}

			// Base death chance based on age
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

			// Adjust death chance based on health
			if (human.getHealth() < 5) {
				double healthFactor = (5 - human.getHealth()) / 5.0; // Scales between 0 and 1
				deathChance += deathChance * healthFactor; // Increases death chance proportionally
			}

			// Random chance to die based on the adjusted death chance
			if (random.nextFloat() < deathChance) {
				StatsTracker.amountOfKilledHumans++;
				human.die();
				iterator.remove(); // Remove the human if they die
			}
		}

		StatsTracker.track(population, day);
	}

	private void reproduce() {
		for (int i = 0; i < population.size(); i++) {
			if (population.size() >= populationLimit) {
				break;
			}

			Human parent1 = population.get(i);

			if (parent1.getAge() < 18 || parent1.getAge() > 50) {
				continue; // Basic age check
			}

			if (parent1.hadChildDuringSimStep()) {
				continue;
			}

			for (int attempts = 0; attempts < 5; attempts++) { // Limit to 5 random picks
				int randomIndex;
				do {
					randomIndex = random.nextInt(population.size());
				} while (randomIndex == i); // Ensure it's not the same human

				Human parent2 = population.get(randomIndex);

				if (parent2.getAge() < 18 || parent2.getAge() > 50) {
					continue; // Basic age check
				}

				if (parent2.hadChildDuringSimStep()) {
					continue;
				}

				// Check if the two humans are compatible for reproduction
				if (!CompatibilityChecker.areCompatible(parent1, parent2)) {
					continue;
				}

				population.add(InteractionHandler.reproduce(parent1, parent2));
				break; // Once reproduction happens, stop trying for parent1 in this step
			}
		}
	}

	// Code to run every 28 days
	private void runMonth() {
		System.out.println("28 days have passed! Running scheduled task.");
	}
}
package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import behavior.Behavior;
import genetics.Gene;

public class Human {

	private List<Gene> genes;
	private Behavior behavior; // Aggressive, Cooperative, Neutral

	private int age; // Age of the human

	private int health;
	private int socialSkill; // How well they interact with others
	private int physicalStrength; // Physical strength for reproduction
	private int mentalHealth; // Mental health
	private int extroversion;
	private int neuroticism;
	private int openness;

	public Human() {
		this.genes = new ArrayList<>(); // Initialize gene list

		this.age = new Random().nextInt(101); // Random age between 0 and 100

		this.health = Math.max(new Random().nextInt(11), 5); // Ensure health is at least 5
		this.socialSkill = Math.max(new Random().nextInt(11), 5); // Ensure social skill is at least 5
		this.physicalStrength = Math.max(new Random().nextInt(11), 5); // Ensure physical strength is at least 5
		this.mentalHealth = Math.max(new Random().nextInt(11), 5); // Ensure mental health is at least 5
		this.extroversion = Math.max(new Random().nextInt(11), 5); // Ensure extroversion is at least 5
		this.neuroticism = Math.max(new Random().nextInt(11), 5); // Ensure neuroticism is at least 5
		this.openness = Math.max(new Random().nextInt(11), 5); // Ensure openness is at least 5

		// Random behavior
		this.behavior = Behavior.randomBehavior();

		// Add random genes
		for (int i = 0; i < 10; i++) { // Assuming 10 genes per human
			// Randomly create genes (could be based on specific logic or random values)
			String geneType = (new Random().nextBoolean()) ? "GeneA" : "GeneB"; // Example of gene type
			this.genes.add(new Gene(geneType, new Random().nextInt(11))); // Add random gene to the list
		}

		this.updateBehaviorBasedOnTraits();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = Math.max(0, Math.min(age, 100)); // Ensure age stays between 0 and 100
	}

	public void ageOneYear() {
		this.age++;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = Math.max(0, Math.min(10, health)); // Clamps health between 0 and 10
	}

	public int getSocialSkill() {
		return socialSkill;
	}

	public void setSocialSkill(int socialSkill) {
		this.socialSkill = Math.max(0, Math.min(10, socialSkill)); // Clamps social skill between 0 and 10
	}

	public int getPhysicalStrength() {
		return physicalStrength;
	}

	public void setPhysicalStrength(int physicalStrength) {
		this.physicalStrength = Math.max(0, Math.min(10, physicalStrength)); // Clamps physical strength between 0 and 10
	}

	public int getMentalHealth() {
		return mentalHealth;
	}

	public void setMentalHealth(int mentalHealth) {
		this.mentalHealth = Math.max(0, Math.min(10, mentalHealth)); // Clamps mental health between 0 and 10
	}

	public int getExtroversion() {
		return extroversion;
	}

	public void setExtroversion(int extroversion) {
		this.extroversion = Math.max(0, Math.min(10, extroversion)); // Clamps extroversion between 0 and 10
	}

	public int getNeuroticism() {
		return neuroticism;
	}

	public void setNeuroticism(int neuroticism) {
		this.neuroticism = Math.max(0, Math.min(10, neuroticism)); // Clamps neuroticism between 0 and 10
	}

	public int getOpenness() {
		return openness;
	}

	public void setOpenness(int openness) {
		this.openness = Math.max(0, Math.min(10, openness)); // Clamps openness between 0 and 10
	}

	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public List<Gene> getGenes() {
		return genes;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public void updateBehaviorBasedOnTraits() {
		// Modify behavior based on traits
		if (this.extroversion > 7 && this.socialSkill > 6) {
			this.behavior = Behavior.COOPERATIVE; // Highly social and skilled humans tend to cooperate
		} else if (this.neuroticism > 6 || this.mentalHealth < 3) {
			this.behavior = Behavior.AGGRESSIVE; // High neuroticism or poor mental health might lead to aggression
		} else {
			this.behavior = Behavior.NEUTRAL; // If the traits are more balanced, the behavior is neutral
		}
	}

	// Method to interact with another human
	public void interact(Human other) {
		// Example of how interactions might affect mental health
		if (this.behavior.equals(Behavior.AGGRESSIVE) && other.getBehavior().equals(Behavior.COOPERATIVE) || this.behavior.equals(Behavior.COOPERATIVE) && other.getBehavior().equals(Behavior.AGGRESSIVE)) {
			this.mentalHealth -= 1; // Decrease mental health by 1 for aggressive behavior
		} else if (this.behavior.equals(Behavior.AGGRESSIVE) && other.getBehavior().equals(Behavior.AGGRESSIVE)) {
			this.mentalHealth -= 1; // Negative impact if you're cooperative with an aggressive person
		} else if (this.behavior.equals(Behavior.COOPERATIVE) && other.getBehavior().equals(Behavior.COOPERATIVE)) {
			this.mentalHealth += 2;
		}

		// Prevent mental health from going negative and ensure it does not exceed 10
		this.mentalHealth = Math.max(0, Math.min(10, this.mentalHealth));
	}

	public static Human reproduce(Human parent1, Human parent2) {
		Human child = new Human();

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
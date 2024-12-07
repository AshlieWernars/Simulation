package entities;

import java.util.Random;

import behavior.Behavior;

public class Human {

	private Behavior behavior; // Aggressive, Cooperative, Neutral

	private int age; // Age of the human

	private int health;
	private int socialSkill; // How well they interact with others
	private int physicalStrength; // Physical strength for reproduction
	private int mentalHealth; // Mental health
	private int extroversion;
	private int neuroticism;
	private int openness;

	// Additional traits
	private int intelligence; // Cognitive abilities
	private int attractiveness; // Physical attractiveness
	private int empathy; // Ability to understand others' emotions
	private int creativity; // Innovation and problem-solving abilities
	private int motivation; // Willingness to pursue goals
	private int healthRiskTolerance; // Willingness to take risks affecting health
	private int conscientiousness; // Discipline and responsibility
	private int stressResilience; // Ability to handle stress
	private int parentalInstinct; // Inclination to care for offspring
	private int socialDominance; // Desire to lead or control social situations

	public Human() {
	    this.age = new Random().nextInt(101); // Random age between 0 and 100

	    this.health = new Random().nextInt(11); // Random health between 0 and 10
	    this.socialSkill = new Random().nextInt(11); // Random social skill between 0 and 10
	    this.physicalStrength = new Random().nextInt(11); // Random physical strength between 0 and 10
	    this.mentalHealth = new Random().nextInt(11); // Random mental health between 0 and 10
	    this.extroversion = new Random().nextInt(11); // Random extroversion between 0 and 10
	    this.neuroticism = new Random().nextInt(11); // Random neuroticism between 0 and 10
	    this.openness = new Random().nextInt(11); // Random openness between 0 and 10

	    // Additional traits with random values between 0 and 10
	    this.intelligence = new Random().nextInt(11); // Random intelligence between 0 and 10
	    this.attractiveness = new Random().nextInt(11); // Random attractiveness between 0 and 10
	    this.empathy = new Random().nextInt(11); // Random empathy between 0 and 10
	    this.creativity = new Random().nextInt(11); // Random creativity between 0 and 10
	    this.motivation = new Random().nextInt(11); // Random motivation between 0 and 10
	    this.healthRiskTolerance = new Random().nextInt(11); // Random health risk tolerance between 0 and 10
	    this.conscientiousness = new Random().nextInt(11); // Random conscientiousness between 0 and 10
	    this.stressResilience = new Random().nextInt(11); // Random stress resilience between 0 and 10
	    this.parentalInstinct = new Random().nextInt(11); // Random parental instinct between 0 and 10
	    this.socialDominance = new Random().nextInt(11); // Random social dominance between 0 and 10

	    // Random behavior
	    this.behavior = Behavior.randomBehavior();

	    this.updateBehaviorBasedOnTraits();
	}

	public void updateBehaviorBasedOnTraits() {
	    // Modify behavior based on traits

	    // Highly social and skilled individuals tend to cooperate
	    if (this.extroversion > 7 && this.socialSkill > 6) {
	        this.behavior = Behavior.COOPERATIVE;
	    }
	    // High neuroticism, poor mental health, or high health risk tolerance might lead to aggression
	    else if (this.neuroticism > 6 || this.mentalHealth < 3 || this.healthRiskTolerance > 7) {
	        this.behavior = Behavior.AGGRESSIVE;
	    }
	    // Individuals with high empathy, motivation, and parental instinct might behave in a friendly way
	    else if (this.empathy > 6 && this.motivation > 5 && this.parentalInstinct > 6) {
	        this.behavior = Behavior.FRIENDLY;
	    }
	    // High creativity, intelligence, and openness might lead to curious behavior
	    else if (this.creativity > 7 && this.intelligence > 6 && this.openness > 6) {
	        this.behavior = Behavior.CURIOUS;
	    }
	    // Individuals with high stress resilience, conscientiousness, and social dominance might be more defensive
	    else if (this.stressResilience > 6 && this.conscientiousness > 6 && this.socialDominance > 6) {
	        this.behavior = Behavior.DEFENSIVE;
	    }
	    // High attractiveness and extroversion can result in a more dominant and self-interested personality
	    else if (this.attractiveness > 7 && this.extroversion > 6) {
	        this.behavior = Behavior.GREEDY;
	    }
	    // High levels of laziness and low motivation might result in a lazy behavior
	    else if (this.socialSkill < 4 && this.motivation < 3) {
	        this.behavior = Behavior.LAZY;
	    }
	    // Individuals with high levels of honesty and low neuroticism tend to be more honest
	    else if (this.neuroticism < 3 && this.socialSkill > 7) {
	        this.behavior = Behavior.HONEST;
	    }
	    // High levels of neuroticism combined with low openness might lead to deceptive behavior
	    else if (this.neuroticism > 6 && this.openness < 4) {
	        this.behavior = Behavior.DECEPTIVE;
	    }
	    // Default behavior is neutral if no conditions match
	    else {
	        this.behavior = Behavior.NEUTRAL;
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

	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}

	public Behavior getBehavior() {
		return behavior;
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

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = Math.max(0, Math.min(10, intelligence));
	}

	public int getAttractiveness() {
		return attractiveness;
	}

	public void setAttractiveness(int attractiveness) {
		this.attractiveness = Math.max(0, Math.min(10, attractiveness));
	}

	public int getEmpathy() {
		return empathy;
	}

	public void setEmpathy(int empathy) {
		this.empathy = Math.max(0, Math.min(10, empathy));
	}

	public int getCreativity() {
		return creativity;
	}

	public void setCreativity(int creativity) {
		this.creativity = Math.max(0, Math.min(10, creativity));
	}

	public int getMotivation() {
		return motivation;
	}

	public void setMotivation(int motivation) {
		this.motivation = Math.max(0, Math.min(10, motivation));
	}

	public int getHealthRiskTolerance() {
		return healthRiskTolerance;
	}

	public void setHealthRiskTolerance(int healthRiskTolerance) {
		this.healthRiskTolerance = Math.max(0, Math.min(10, healthRiskTolerance));
	}

	public int getConscientiousness() {
		return conscientiousness;
	}

	public void setConscientiousness(int conscientiousness) {
		this.conscientiousness = Math.max(0, Math.min(10, conscientiousness));
	}

	public int getStressResilience() {
		return stressResilience;
	}

	public void setStressResilience(int stressResilience) {
		this.stressResilience = Math.max(0, Math.min(10, stressResilience));
	}

	public int getParentalInstinct() {
		return parentalInstinct;
	}

	public void setParentalInstinct(int parentalInstinct) {
		this.parentalInstinct = Math.max(0, Math.min(10, parentalInstinct));
	}

	public int getSocialDominance() {
		return socialDominance;
	}

	public void setSocialDominance(int socialDominance) {
		this.socialDominance = Math.max(0, Math.min(10, socialDominance));
	}
}
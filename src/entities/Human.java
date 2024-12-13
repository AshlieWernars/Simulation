package entities;

import java.util.ArrayList;
import java.util.Random;

import behavior.Behavior;
import housing.House;
import insurance.HealthInsurance;
import job.Job;
import names.NameLoader;

public class Human {

	private int age = 0; // Age of the human
	private Job job = null;
	private int money = 0;
	private House house;
	private int lastSalary = 500;

	private Behavior behavior = Behavior.NEUTRAL; // Aggressive, Cooperative, Neutral

	private int health;
	private int socialSkill; // How well they interact with others
	private int physicalStrength; // Physical strength
	private int mentalHealth; // Mental health
	private int extroversion; // How outgoing the human is
	private int neuroticism; // emotional instability
	private int openness; // willingness to explore new experiences

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

	private boolean hadChildDuringSimStep = false;

	private final String name;

	private Human parent1;
	private Human parent2;
	private final ArrayList<Human> children = new ArrayList<>();
	private final Random random = new Random();
	private boolean dead = false;
	double hoursWorked;

	public Human(boolean isChild) {
		name = NameLoader.getRandomName(random.nextInt(2));

		if (isChild) {
			return;
		}

		this.age = random.nextInt(101); // Random age between 0 and 100

		this.health = random.nextInt(11); // Random health between 0 and 10
		this.socialSkill = random.nextInt(11); // Random social skill between 0 and 10
		this.physicalStrength = random.nextInt(11); // Random physical strength between 0 and 10
		this.mentalHealth = random.nextInt(11); // Random mental health between 0 and 10
		this.extroversion = random.nextInt(11); // Random extroversion between 0 and 10
		this.neuroticism = random.nextInt(11); // Random neuroticism between 0 and 10
		this.openness = random.nextInt(11); // Random openness between 0 and 10

		// Additional traits with random values between 0 and 10
		this.intelligence = random.nextInt(11); // Random intelligence between 0 and 10
		this.attractiveness = random.nextInt(11); // Random attractiveness between 0 and 10
		this.empathy = random.nextInt(11); // Random empathy between 0 and 10
		this.creativity = random.nextInt(11); // Random creativity between 0 and 10
		this.motivation = random.nextInt(11); // Random motivation between 0 and 10
		this.healthRiskTolerance = random.nextInt(11); // Random health risk tolerance between 0 and 10
		this.conscientiousness = random.nextInt(11); // Random conscientiousness between 0 and 10
		this.stressResilience = random.nextInt(11); // Random stress resilience between 0 and 10
		this.parentalInstinct = random.nextInt(11); // Random parental instinct between 0 and 10
		this.socialDominance = random.nextInt(11); // Random social dominance between 0 and 10

		this.job = Job.randomJob();

		this.updateBehaviorBasedOnTraits();
	}

	public void updateBehaviorBasedOnTraits() {
		ArrayList<Behavior> possibleBehaviors = new ArrayList<>();

		// Modify behavior based on traits

		// Highly social and skilled individuals tend to cooperate
		if (this.extroversion > 7 && this.socialSkill > 6) {
			possibleBehaviors.add(Behavior.COOPERATIVE);
		}

		// Check if at least two conditions are true for aggression
		// low conscientiousness, low stressResilience, high socialDominance, high
		// healthRiskTolerance
		if (this.conscientiousness < 5 && this.stressResilience < 5 && this.socialDominance > 7 && this.healthRiskTolerance > 7) {
			possibleBehaviors.add(Behavior.AGGRESSIVE); // Trigger aggression if at least two conditions are met
		}

		// Individuals with high empathy, motivation, and parental instinct might behave
		// in a friendly way
		if (this.empathy > 6 && this.socialSkill > 7 && this.parentalInstinct > 6) {
			possibleBehaviors.add(Behavior.FRIENDLY);
		}

		// High creativity, intelligence, and openness might lead to curious behavior
		if (this.creativity > 7 && this.intelligence > 6 && this.openness > 6) {
			possibleBehaviors.add(Behavior.CURIOUS);
		}

		// Individuals with high stress resilience, conscientiousness, and social
		// dominance might be more defensive
		if (this.stressResilience > 6 && this.conscientiousness > 6 && this.socialDominance > 6 && this.physicalStrength > 7) {
			possibleBehaviors.add(Behavior.DEFENSIVE);
		}

		// High attractiveness and extroversion can result in a more dominant and
		// self-interested personality
		if (this.attractiveness > 7 && this.extroversion > 6 && this.intelligence < 4) {
			possibleBehaviors.add(Behavior.GREEDY);
		}

		// Low social skill and low motivation might result in a lazy behavior
		if (this.motivation < 4) {
			possibleBehaviors.add(Behavior.LAZY);
		}

		// Individuals with high levels of honesty and low neuroticism tend to be more
		// honest
		if (this.neuroticism < 3 && this.socialSkill > 7) {
			possibleBehaviors.add(Behavior.HONEST);
		}

		// High levels of neuroticism combined with low openness might lead to deceptive
		// behavior
		if (this.neuroticism > 6 && this.openness < 4) {
			possibleBehaviors.add(Behavior.DECEPTIVE);
		}

		if (possibleBehaviors.contains(behavior)) {
			return;
		}

		// Assign behavior randomly from possible behavior, if there are any
		if (!possibleBehaviors.isEmpty()) {
			behavior = possibleBehaviors.get(random.nextInt(possibleBehaviors.size()));
		} else {
			behavior = Behavior.NEUTRAL;
		}
	}

	public void assignJob() {
		if (age < 18 || age > 65) { // Person under 18 or above 65 can't have a job
			job = null;
			return;
		}

		ArrayList<Job> possibleJobs = new ArrayList<>();

		// Assign job based on traits
		if (this.socialSkill > 7 && this.extroversion > 6) {
			possibleJobs.add(Job.MERCHANT); // Social and extroverted
		}

		if (this.creativity > 7 && this.intelligence > 6) {
			possibleJobs.add(Job.ARTIST); // Creative and intelligent
		}

		if (this.socialDominance > 7 && this.conscientiousness > 6) {
			possibleJobs.add(Job.LEADER); // Strong leadership qualities
		}

		if (this.physicalStrength > 7 && this.health > 6) {
			possibleJobs.add(Job.FARMER); // High physical strength and health
		}

		if (this.intelligence > 7 && this.creativity > 6 && this.openness > 6) {
			possibleJobs.add(Job.SCIENTIST); // High intellect, creativity, and openness
		}

		if (this.empathy > 7 && this.mentalHealth > 6) {
			possibleJobs.add(Job.HEALER); // Empathetic and strong mental health
		}

		if (this.socialSkill > 6 && this.motivation > 6) {
			possibleJobs.add(Job.TEACHER); // Motivated and social individuals
		}

		if (this.physicalStrength > 6 && this.socialDominance > 6) {
			possibleJobs.add(Job.GUARD); // Strong physical strength and dominance
		}

		if (this.creativity > 6 && this.intelligence > 6) {
			possibleJobs.add(Job.HUNTER); // Creative and intelligent individuals
		}

		if (this.socialSkill > 5 && this.conscientiousness > 5) {
			possibleJobs.add(Job.BUILDER); // Conscientious and social individuals
		}

		if (possibleJobs.contains(job)) {
			return;
		}

		// Assign job randomly from possible jobs, if there are any
		if (!possibleJobs.isEmpty()) {
			job = possibleJobs.get(random.nextInt(possibleJobs.size())); // Randomly select a job
		} else {
			job = null; // Unemployed
		}
	}

	/**
	 * 
	 * @return 0 if job done, 1 if no job, 2 if retired, 3 if too young
	 */
	public int tryToDoJob() {
		if (age > 65) { // Retired
			job = null;
			money += 45;
			return 2;
		}

		if (age < 18) { // Too young
			job = null;
			return 3;
		}

		if (job == null) { // Lazy bitch is unemployed
			return 1;
		}

		hoursWorked += job.doJob();

		return 0;
	}

	public void recieveSalary() {
		lastSalary = 0;

		if (job == null) { // Unemployed, will add welfare in the future
			return;
		}

		if (hoursWorked == 0) { // Lazy ass
			return;
		}

		lastSalary = (int) Math.round(hoursWorked * job.getSalary());

		hoursWorked = 0;

		money += lastSalary;
	}

	public void payHealthInsurance() {
		int healthInsuranceCost = HealthInsurance.calculateHealthInsuranceCost(this);

		if (money - healthInsuranceCost < 0) {
			// Human can't afford the insurance
			return;
		}

		money -= healthInsuranceCost;
	}

	public void payRent(int rent) {
		if (house == null) {
			// Human is not assigned to any house, no rent to pay
			this.setMentalHealth(this.getMentalHealth() - 1);
			return;
		}

		if (money - rent < 0) {
			// Not enough money to pay rent, remove from the house
			house.removeResident(this);
			return;
		}

		// Deduct rent from money if there is enough
		money -= rent;
	}

	public void die() {
		if (this.parent1 != null) {
			this.parent1.removeChild(this);
		}

		if (this.parent2 != null) {
			this.parent2.removeChild(this);
		}

		// Case where the person has no children
		if (children.isEmpty()) {
			if (parent1 != null && parent2 != null) {
				// Split money between both parents
				parent1.addMoney(money / 2);
				parent2.addMoney(money / 2);
			} else if (parent1 != null) {
				// Give all money to parent1
				parent1.addMoney(money);
			} else if (parent2 != null) {
				// Give all money to parent2
				parent2.addMoney(money);
			} else {
				// Give money to the state (implement state logic as needed)
				// state.addMoney(money);
				// throw new RuntimeException(this.getName() + " " + this.getAge() + " " +
				// this.getMoney());
			}
			return; // No need to proceed further if no children
		}

		// Case where the person has children, distribute money to them
		double amountOfChildren = this.children.size();
		int amountOfMoneyPerChild = (int) Math.round(money / amountOfChildren);

		for (Human child : children) {
			child.addMoney(amountOfMoneyPerChild);

			// Remove parent references from children
			if (child.getParent1() == this) {
				child.setParent1(null);
			}

			if (child.getParent2() == this) {
				child.setParent2(null);
			}
		}

		this.setHealth(0);
		this.dead = true;
	}

	private void addMoney(int amountOfMoney) {
		money += amountOfMoney;
		money = Math.max(0, money); // Prevent money from going below zero
	}

	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public Job getJob() {
		return job;
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

	public void setJob(Job job) {
		this.job = job;
	}

	public int getMoney() {
		return money;
	}

	public boolean hadChildDuringSimStep() {
		return hadChildDuringSimStep;
	}

	public void setHadChildDuringSimStep(boolean hadChildDuringSimStep) {
		this.hadChildDuringSimStep = hadChildDuringSimStep;
	}

	public String getName() {
		return name;
	}

	public void setParent1(Human parent1) {
		this.parent1 = parent1;
	}

	public Human getParent1() {
		return parent1;
	}

	public void setParent2(Human parent2) {
		this.parent2 = parent2;
	}

	public Human getParent2() {
		return parent2;
	}

	public ArrayList<Human> getChildren() {
		return children;
	}

	public void addChild(Human child) {
		this.children.add(child);
	}

	public void removeChild(Human child) {
		this.children.remove(child);
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public boolean isDead() {
		return dead;
	}

	public int getLastSalary() {
		return lastSalary;
	}
}
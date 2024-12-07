package behavior;

import java.util.Random;

public enum Behavior {
	
    AGGRESSIVE, 
    COOPERATIVE, 
    NEUTRAL, 
    CURIOUS, 
    DEFENSIVE, 
    FRIENDLY, 
    GREEDY, 
    LAZY, 
    HONEST, 
    DECEPTIVE;

    // Return a random behavior for initialization
    public static Behavior randomBehavior() {
        Random rand = new Random();
		return values()[rand.nextInt(values().length)];
	}
}
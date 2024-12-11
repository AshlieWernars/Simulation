package job;

import java.util.Random;

public enum Job {
	
    FARMER(50),
    HUNTER(60),
    BUILDER(70),
    TEACHER(80),
    HEALER(90),
    GUARD(65),
    MERCHANT(75),
    ARTIST(40),
    SCIENTIST(100),
    LEADER(120);

    private final int salary;

    Job(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    // Return a random job for initialization
    public static Job randomJob() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
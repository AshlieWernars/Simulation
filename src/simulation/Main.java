package simulation;

public class Main {
    public static void main(String[] args) {
        // Start simulation with 10 humans and 1000 generations
        Simulation simulation = new Simulation(10, 1000);  // 10 humans, 1000 generations
        simulation.run(); // Run the simulation

        // Optionally, print final results
        System.out.println("Simulation complete.");
    }
}

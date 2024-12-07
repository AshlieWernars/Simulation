package display;

import simulation.Simulation; // Assuming Simulation is in the 'simulation' package
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationDisplay {

	private JFrame frame;
	private JButton startButton;
	private JButton pauseButton;
	private JButton stopButton;
	private JTextArea statsArea;
	private Simulation simulation;
	private Timer simulationTimer;

	public SimulationDisplay(Simulation simulation) {
		this.simulation = simulation;
		frame = new JFrame("Simulation Control");

		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		stopButton = new JButton("Stop");

		statsArea = new JTextArea(10, 30);
		statsArea.setEditable(false);
		statsArea.setText("Simulation Stats will appear here.");

		// Layout setup
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(startButton, BorderLayout.NORTH);
		panel.add(pauseButton, BorderLayout.CENTER);
		panel.add(stopButton, BorderLayout.SOUTH);
		panel.add(new JScrollPane(statsArea), BorderLayout.EAST);

		frame.getContentPane().add(panel);
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Button Actions
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startSimulation();
			}
		});

		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pauseSimulation();
			}
		});

		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopSimulation();
			}
		});

		frame.setVisible(true);
	}

	private void startSimulation() {
		simulation.start(); // Assuming there's a start method in Simulation class
		simulationTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStats();
			}
		});
		simulationTimer.start();
	}

	private void pauseSimulation() {
		if (simulationTimer != null && simulationTimer.isRunning()) {
			simulationTimer.stop();
		}
	}

	private void stopSimulation() {
		if (simulationTimer != null) {
			simulationTimer.stop();
		}
		simulation.reset(); // Assuming there's a reset method in Simulation class
		updateStats();
	}

	private void updateStats() {
		// Fetch and display simulation data (average values, current generation, etc.)
		String stats = "Generation: " + simulation.getGeneration() + "\n";
		stats += "Aggressive: " + simulation.getAggressiveCount() + "\n";
		stats += "Cooperative: " + simulation.getCooperativeCount() + "\n";
		stats += "Neutral: " + simulation.getNeutralCount() + "\n";
		stats += "Average Health: " + simulation.getAverageHealth() + "\n";
		stats += "Average Social Skill: " + simulation.getAverageSocialSkill() + "\n";
		stats += "Average Physical Strength: " + simulation.getAveragePhysicalStrength() + "\n";
		stats += "Average Mental Health: " + simulation.getAverageMentalHealth() + "\n";
		stats += "Average Extroversion: " + simulation.getAverageExtroversion() + "\n";
		stats += "Average Neuroticism: " + simulation.getAverageNeuroticism() + "\n";
		stats += "Average Openness: " + simulation.getAverageOpenness() + "\n";

		statsArea.setText(stats);
	}

	public static void main(String[] args) {
		// Assuming you have a Simulation object to pass in
		Simulation simulation = new Simulation();
		new SimulationDisplay(simulation);
	}
}

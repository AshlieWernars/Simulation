package display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import simulation.Simulation; // Assuming Simulation is in the 'simulation' package

public class SimulationDisplay {

	private JFrame frame;
	private JButton startButton;
	private JButton pauseButton;
	private JButton stopButton;
	private JButton resetButton;
	private JTextArea statsArea;
	private Simulation simulation;
	private Timer simulationTimer;

	public SimulationDisplay(Simulation simulation) {
		this.simulation = simulation;
		frame = new JFrame("Simulation Control");

		// Create buttons
		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		stopButton = new JButton("Stop");
		resetButton = new JButton("Reset");

		// Set preferred size for buttons
		startButton.setPreferredSize(new Dimension(100, 40));
		pauseButton.setPreferredSize(new Dimension(100, 40));
		stopButton.setPreferredSize(new Dimension(100, 40));
		resetButton.setPreferredSize(new Dimension(100, 40));

		// Create panel and add buttons
		JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2x2 grid layout
		buttonPanel.add(startButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(resetButton);

		// Add panel to the frame
		frame.add(buttonPanel, BorderLayout.CENTER);

		startButton.setPreferredSize(new Dimension(100, 40));
		pauseButton.setPreferredSize(new Dimension(100, 40));
		stopButton.setPreferredSize(new Dimension(100, 40));
		resetButton.setPreferredSize(new Dimension(100, 40));

		statsArea = new JTextArea(10, 30);
		statsArea.setEditable(false);
		statsArea.setText("Simulation Stats will appear here.");

		simulation.setStatsArea(statsArea);

		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Button Actions
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start");
				startSimulation();
			}
		});

		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pause");
				pauseSimulation();
			}
		});

		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Stop");
				stopSimulation();
			}
		});

		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reset");
				resetSimulation();
			}
		});

		frame.setVisible(true);
	}

	private void startSimulation() {
		simulation.startSimulation();
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
		simulation.reset();
		simulation.updateStats();
	}

	private void resetSimulation() {
		simulation.reset(); // Reset the simulation state
		simulation.updateStats(); // Update stats after reset
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Assuming you have a Simulation object to pass in
		Simulation simulation = new Simulation();
		new SimulationDisplay(simulation);

	}
}

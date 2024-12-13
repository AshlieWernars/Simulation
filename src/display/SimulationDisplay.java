package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import housing.HousingSystem;
import simulation.Simulation;
import simulation.StatsTracker;

public class SimulationDisplay extends Canvas {

	private static final long serialVersionUID = 1L;
	private Button startButton;
	private Button stepButton;
	private Button stopButton;
	private Button resetButton;
	private Simulation simulation;

	Display display;
	Input input;

	public SimulationDisplay(Simulation simulation) {
		this.simulation = simulation;

		input = new Input();
		this.addMouseListener(input);
		this.addKeyListener(input);
		display = new Display(1920, 720, "Simulation", input, this);

		// Create buttons
		startButton = new Button(25, 50, 100, 40, "Start");
		stepButton = new Button(25, 100, 100, 40, "Step");
		stopButton = new Button(25, 150, 100, 40, "Stop");
		resetButton = new Button(25, 200, 100, 40, "Reset");

		run();
	}

	private void run() {
		long lastime = System.nanoTime();
		double ns = 1000000000 / 60;
		double delta = 0;
		int frames = 0;
		double time = System.currentTimeMillis();

		while (true) {
			long now = System.nanoTime();
			delta += (now - lastime) / ns;
			lastime = now;

			if (delta >= 1) {
				update();
				render();
				frames++;
				delta--;
				if (System.currentTimeMillis() - time >= 1000) {
					// System.out.println("FPS: " + frames);
					frames = 0;
					time = System.currentTimeMillis();

				}
			}
		}
	}

	int timer = 0;

	private void update() {
		if (startButton.isButtonPressed()) {
			simulation.startSimulation();
		}

		if (stepButton.isButtonPressed() && timer == 0) {
			timer = 60 * 1;
			simulation.simulateDay();
		}

		if (timer > 0) {
			timer--;
		}

		if (stopButton.isButtonPressed()) {
			simulation.stopSimulation();
		}

		if (resetButton.isButtonPressed()) {
			simulation.stopSimulation();
			simulation.reset(); // Reset the simulation state
			StatsTracker.updateStats(0); // Update stats after reset
			simulation.startSimulation();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			bs = this.getBufferStrategy();
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, 1920, 720);

		startButton.render(g);
		stepButton.render(g);
		stopButton.render(g);
		resetButton.render(g);

		g.setColor(Color.white);
		String simStats = StatsTracker.getStats();
		if (simStats != null) {
			int x = 130, y = 50;
			int lineHeight = 15;

			for (String line : simStats.split("\n")) {
				g.drawString(line, x, y);
				y += lineHeight; // Move down for the next line
			}
		}

		HousingSystem.render(g);

		bs.show();
		g.dispose();
	}

	public static void main(String[] args) {
		// Assuming you have a Simulation object to pass in
		Simulation simulation = new Simulation();
		new SimulationDisplay(simulation).run();
	}
}
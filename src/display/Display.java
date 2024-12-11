package display;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Display {

	public Display(int width, int height, String title, Input input, SimulationDisplay simDisplay) {
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addMouseListener(input);
		frame.addKeyListener(input);
		frame.add(simDisplay);
		frame.setVisible(true);
		frame.requestFocus();
	}
}
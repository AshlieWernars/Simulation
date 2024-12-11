package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener {

	// Var
	private static boolean mousePressed = false;
	private static int mouseX, mouseY;

	@Override
	public void keyPressed(KeyEvent e) {
		// Not used
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not used
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseX = e.getX();
			mouseY = e.getY();
			mousePressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseX = -1;
			mouseY = -1;
			mousePressed = false;
		}
		// Not used
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Not used
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	public static boolean isMousePressed() {
		return mousePressed;
	}

	public static void setMousePressed(boolean mousePressed) {
		Input.mousePressed = mousePressed;
	}

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		Input.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		Input.mouseY = mouseY;
	}
}
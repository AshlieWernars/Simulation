package display;

import java.awt.Color;
import java.awt.Graphics;

public class Button {

	int x, y, width, height;
	String text;

	public Button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
	}

	public boolean isButtonPressed() {
		if (!Input.isMousePressed()) {
			return false;
		}

		int mx = Input.getMouseX();
		int my = Input.getMouseY();

		return mx > x && mx < x + width && (my > y && my < y + height);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);

		g.setColor(Color.black);
		g.drawString(text, x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
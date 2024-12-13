package housing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class HousingSystem {

	private final static ArrayList<House> houses = new ArrayList<>();

	public HousingSystem() {
		House house = new House("Main 123", 10, 500);
		houses.add(house);
	}

	public static void update() {
		for (House house : houses) {

		}
	}

	public static void render(Graphics g) {
		int x = 400;
		int y = 200;
		for (House house : houses) {
			switch (house.getRating()) {
			case 0:
				g.setColor(Color.RED); // Rating 0 -> Red
				break;
			case 1:
				g.setColor(Color.ORANGE); // Rating 1 -> Orange
				break;
			case 2:
				g.setColor(Color.YELLOW); // Rating 2 -> Yellow
				break;
			case 3:
				g.setColor(Color.GREEN.brighter()); // Rating 3 -> Green
				break;
			case 4:
				g.setColor(Color.CYAN); // Rating 4 -> Cyan
				break;
			case 5:
				g.setColor(Color.BLUE); // Rating 5 -> Blue
				break;
			case 6:
				g.setColor(Color.MAGENTA); // Rating 6 -> Magenta
				break;
			case 7:
				g.setColor(Color.PINK); // Rating 7 -> Pink
				break;
			case 8:
				g.setColor(Color.LIGHT_GRAY); // Rating 8 -> Light Gray
				break;
			case 9:
				g.setColor(Color.DARK_GRAY); // Rating 9 -> Dark Gray
				break;
			case 10:
				g.setColor(Color.GREEN); // Rating 10 -> Green
				break;
			default:
				g.setColor(Color.WHITE); // Default to black for invalid cases
				break;
			}
		}
		
		g.fillRect(x, y, 20, 20);
		x += 50;
	}
}
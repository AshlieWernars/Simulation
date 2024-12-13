package housing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class HousingSystem {

	// Pre-Defined Var's
	private static final ArrayList<House> houses = new ArrayList<>();
	static final double[] PRICE_RANGES = { 250, 300, 350, 400, 500, 600, 750, 900, 1100, 1300, 1500 };
	private static final Color[] RATING_COLORS = { //
			Color.RED, // Rating 0
			Color.ORANGE, // Rating 1
			Color.YELLOW, // Rating 2
			Color.GREEN, // Rating 3
			Color.CYAN, // Rating 4
			Color.BLUE, // Rating 5
			Color.MAGENTA, // Rating 6
			Color.PINK, // Rating 7
			Color.LIGHT_GRAY, // Rating 8
			Color.DARK_GRAY, // Rating 9
			Color.GRAY // Rating 10
	};

	Random r = new Random();

	public HousingSystem(int populationLimit) {
		for (int i = 0; i < populationLimit; i++) {
			houses.add(new House(r.nextInt(11)));
		}
	}

	public static void update() {
		for (House house : houses) {
			house.update();
		}
	}

	public static void makeResidentsPayRent() {
		for (House house : houses) {
			house.makeResidentsPayRent();
		}
	}

	public static void render(Graphics g) {
		int x = 350;
		int y = 10;
		for (House house : houses) {
			int rating = house.getRating();

			// Check if the rating is valid (0 to 10)
			if (rating >= 0 && rating <= 10) {
				g.setColor(RATING_COLORS[rating]); // Set the color based on rating
			}

			if (house.isFull()) {
				g.setColor(Color.WHITE);
			}
			g.fillRect(x, y, 5, 5);
			x += 10;
			if (x >= 1900) {
				x = 350;
				y += 10;
			}
		}
	}

	public static ArrayList<House> getHouses() {
		return houses;
	}
}
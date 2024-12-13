package housing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.Human;

public class House {

	// Pre-Defined Var's
	private final String address;
	private final ArrayList<Human> residents = new ArrayList<>(); // List of residents (Humans) living in the house

	// Var's
	private int rating; // Rating of the house, could be from 1 to 10
	private double price = -1; // Rent price per sim step

	public House(String address, int rating) {
		this.address = address;
		this.rating = rating;
	}

	public void update() {
		setPriceBasedOnRating(rating);

		Iterator<Human> iterator = residents.iterator();
		while (iterator.hasNext()) {
			Human human = iterator.next();
			if (human.isDead()) {
				iterator.remove(); // Safe removal
				continue;
			}
		}

		double rentToPay = price / residents.size();
		int rentPerResident = (int) Math.round(rentToPay);

		for (Human human : residents) {
			human.payRent(rentPerResident);
		}
	}

	private void setPriceBasedOnRating(int rating) {
		if (rating < 0 || rating > 10) {
			throw new IllegalArgumentException("Rating is invalid: " + rating);
		}
		price = HousingSystem.PRICE_RANGES[rating];
	}

	public void addResident(Human human) {
		if (human.getHouse() != null) {
			return;
		}

		if (!residents.contains(human)) {
			residents.add(human);
		}
	}

	public void removeResident(Human human) {
		if (residents.contains(human)) {
			residents.remove(human);
			human.setHouse(null);
		}
	}

	public double getRentPrice() {
		return price;
	}

	public String getAddress() {
		return address;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = Math.max(0, Math.min(10, rating));
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative.");
		}
		this.price = price;
	}

	public List<Human> getResidents() {
		return residents;
	}
}
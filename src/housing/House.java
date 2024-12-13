package housing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.Human;

public class House {

	// Pre-Defined Var's
	private final ArrayList<Human> residents = new ArrayList<>(); // List of residents (Humans) living in the house
	private final int maxAmountOfResidents = 4;

	// Var's
	private int rating; // Rating of the house, could be from 1 to 10
	private double price = -1; // Rent price per sim step
	private double pricePerMonthPerPerson;

	public House(int rating) {
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

		for (int i = 0; i < residents.size(); i++) {
			residents.get(i).payRent(rentPerResident);
		}
	}

	private void setPriceBasedOnRating(int rating) {
		if (rating < 0 || rating > 10) {
			throw new IllegalArgumentException("Rating is invalid: " + rating);
		}
		price = HousingSystem.PRICE_RANGES[rating];
		pricePerMonthPerPerson = price / residents.size();
	}

	public boolean isFull() {
		return residents.size() >= maxAmountOfResidents;
	}

	public void addResident(Human human) {
		if (isFull()) { // Check if the house is full using the isFull method
			return;
		}

		if (human.getHouse() != null) { // Check if the human is already in a house
			return;
		}

		if (!residents.contains(human)) { // Check if the human is not already a resident
			residents.add(human);
			human.setHouse(this); // Don't forget to set the house for the human
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = Math.max(0, Math.min(10, rating));
	}

	public double getPrice() {
		return price;
	}

	public double getPricePerMonthPerPerson() {
		return pricePerMonthPerPerson;
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
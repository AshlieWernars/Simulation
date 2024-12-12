package names;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NameLoader {

	private static HashMap<Integer, List<String>> namesMap = new HashMap<>();

	public static HashMap<Integer, List<String>> loadNames(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;

		// Initialize the lists for male and female names
		namesMap.put(0, new ArrayList<>()); // Female names
		namesMap.put(1, new ArrayList<>()); // Male names

		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(",");
			String name = parts[0];
			String gender = parts[1];

			if ("f".equals(gender)) {
				namesMap.get(0).add(name); // Female names
			} else if ("m".equals(gender)) {
				namesMap.get(1).add(name); // Male names
			}
		}
		reader.close();
		return namesMap;
	}

	public static String getRandomName(int gender) {
		List<String> names = namesMap.get(gender);
		Random random = new Random();
		int index = random.nextInt(names.size());
		return names.get(index);
	}
}
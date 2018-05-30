import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class BestRelayTeam {

	public static void main(String[] args) {
		String output = "";
		TreeMap<Double, String> firstLegPeople = new TreeMap<>();
		TreeMap<Double, String> otherLegPeople = new TreeMap<>();
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();

		for (int i = 0; i < number; i++) {
			String name = scanner.next();
			double firstLeg = scanner.nextDouble();
			double otherLeg = scanner.nextDouble();
			firstLegPeople.put(firstLeg, name);
			otherLegPeople.put(otherLeg, name);
		}
		scanner.close();

		String minOutput = "";
		Iterator<Double> firstIter = firstLegPeople.keySet().iterator();
		double min = Integer.MAX_VALUE;
		while (firstIter.hasNext()) {
			String tempOutput = "";
			double value = 0;
			int count = 0;
			double first = firstIter.next();
			System.out.println(first);
			String firstName = firstLegPeople.get(first);
			tempOutput += firstName + "\n";
			value += first;
			Iterator<Double> otherIter = otherLegPeople.keySet().iterator();
			while (count < 3) {
				double other = otherIter.next();
				String otherName = otherLegPeople.get(other);
				if (!otherName.equals(firstName)) {
					value += other;
					tempOutput += otherName + "\n";
					count++;
				}

			}
			System.out.println("min" + min);
			System.out.println("current" + value);
			if (value < min) {

				min = value;
				minOutput = tempOutput;
			}
		}
		output += min + "\n" + minOutput.substring(0, minOutput.length() - 1);
		System.out.println(output);
	}

}

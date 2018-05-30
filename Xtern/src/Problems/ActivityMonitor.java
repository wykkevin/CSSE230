package Problems;

import java.util.Scanner;
import java.util.TreeSet;

public class ActivityMonitor {
	/*
	 * We get several lines of ints as input. We output the number that occur
	 * odd times. (Assume there is only one)
	 */

	public static void main(String[] args) {
		// I use a set to store all the values in it. So when it fail to add. I
		// know there is already one in it. So I remove that. The final thing
		// left is the number.
		String output = "";
		TreeSet<String> runningProcesses = new TreeSet<String>();
		Scanner scanner = new Scanner(System.in);
		String t = scanner.nextLine();
		while (scanner.hasNextLine() && !t.isEmpty()) {
			if (!runningProcesses.add(t)) {
				runningProcesses.remove(t);
			}
			t = scanner.nextLine();
			if (t.isEmpty()) {
				break;
			}
		}
		scanner.close();
		if (runningProcesses.isEmpty()) {
			System.out.println("0");
		} else {
			System.out.println(runningProcesses.first());
		}
	}

}

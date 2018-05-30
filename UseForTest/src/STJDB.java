import java.util.ArrayList;
import java.util.Scanner;

public class STJDB {

	public static void main(String[] args) {
		ArrayList<String> all = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		String n = scanner.next();
		while (n.charAt(0) != 'E') {
			all.add(n);
			n = scanner.next();
		}
		scanner.close();
		String output = "";
		for (int i = 0; i < all.size() / 2; i++) {
			String s1 = all.get(2 * i);
			String s2 = all.get(2 * i + 1);
			int num1 = 0;
			int num2 = 0;
			for (int j = 0; j < s1.length(); j++) {
				if (s1.charAt(j) == 'R') {
					if (s2.charAt(j) == 'S') {
						num1++;
					} else if (s2.charAt(j) == 'P') {
						num2++;
					}
				} else if (s1.charAt(j) == 'S') {
					if (s2.charAt(j) == 'P') {
						num1++;
					} else if (s2.charAt(j) == 'R') {
						num2++;
					}
				} else if (s1.charAt(j) == 'P') {
					if (s2.charAt(j) == 'R') {
						num1++;
					} else if (s2.charAt(j) == 'S') {
						num2++;
					}
				}
			}
			output = output + "P1: " + num1 + "\nP2: " + num2 + "\n";
		}

		System.out.println(output.substring(0, output.length() - 1));

	}
}

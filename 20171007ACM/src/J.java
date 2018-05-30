import java.util.ArrayList;
import java.util.Scanner;

public class J {

	public static void main(String[] args) {
		String output = "";
		Scanner scanner = new Scanner(System.in);
		int lines = scanner.nextInt();
		for (int i = 0; i < lines; i++) {
			String temp = scanner.next();
			String input = scanner.nextLine();
			if (temp.equals("e")) {
				output = output + encrypt(input) + "\n";
			} else if (temp.equals("d")) {
				output = output + decrypt(input) + "\n";
			}
		}
		scanner.close();
		System.out.println(output.substring(0, output.length() - 1));
	}

	public static String encrypt(String input) {
		String output = "";
		ArrayList<Integer> all = new ArrayList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				all.add(0);
			} else {
				all.add(((int) input.charAt(i)) - 96);
			}
		}
		for (int j = 1; j < all.size(); j++) {
			all.set(j, all.get(j - 1) + all.get(j));
		}
		for (int j = 1; j < all.size(); j++) {
			if (all.get(j) % 27 == 0) {
				output += " ";
			} else {
				String out = Character.toString((char) (all.get(j) % 27 + 96));
				output += out;
			}
		}
		return output;
	}

	public static String decrypt(String input) {
		String output = "";
		ArrayList<Integer> all = new ArrayList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				all.add(0);
			} else {
				all.add(((int) input.charAt(i)) - 96);
			}
		}
		for (int j = 1; j < all.size(); j++) {
			while (all.get(j) < all.get(j - 1)) {
				all.set(j, all.get(j) + 27);
			}
		}

		for (int j = all.size() - 1; j > 0; j--) {
			all.set(j, all.get(j) - all.get(j - 1));
		}
		for (int j = 1; j < all.size(); j++) {
			if (all.get(j) % 27 == 0) {
				output += " ";
			} else {
				String out = Character.toString((char) (all.get(j) % 27 + 96));
				output += out;
			}
		}
		return output;
	}
}

import java.util.HashMap;
import java.util.Scanner;

public class Simplicity {

	public static void main(String[] args) {
		int simplicity = 0;
		int minus = 0;
		HashMap<Character, Integer> letterToCount = new HashMap<Character, Integer>();
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		scanner.close();

		for (int i = 0; i < input.length(); i++) {
			char m = input.charAt(i);
			if (letterToCount.containsKey(m)) {
				letterToCount.put(m, letterToCount.get(m) + 1);
			} else {
				letterToCount.put(m, 1);
				simplicity++;
			}
		}
		int d = 1;
		while (simplicity > 2) {
			for (char ch : letterToCount.keySet()) {
				if (letterToCount.get(ch) == d) {
					simplicity--;
					minus += d;
					if (simplicity <= 2) {
						break;
					}
				}
			}
			d++;
		}
		System.out.println(minus);
	}

}

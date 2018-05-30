import java.util.ArrayList;
import java.util.Scanner;

public class Gnomes {

	public static void main(String[] args) {
		ArrayList<int[]> all = new ArrayList<int[]>();
		Scanner scanner = new Scanner(System.in);
		int lines = scanner.nextInt();
		while (all.size() < lines) {
			int[] list = new int[3];

			for (int i = 0; i < 3; i++) {
				list[i] = scanner.nextInt();
			}

			all.add(list);
		}
		scanner.close();

		String output = "Gnomes:\n";

		for (int k = 0; k < lines; k++) {
			if ((all.get(k)[0] > all.get(k)[1] && all.get(k)[1] > all.get(k)[2])
					|| (all.get(k)[0] < all.get(k)[1] && all.get(k)[1] < all.get(k)[2])) {
				output += "Ordered\n";
			} else {
				output += "Unordered\n";
			}
		}

		System.out.println(output.substring(0, output.length() - 1));
	}

}

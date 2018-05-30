import java.util.ArrayList;
import java.util.Scanner;

public class DuplicateRemoval {

	public static void main(String[] args) {
		ArrayList<int[]> all = new ArrayList<int[]>();
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		while (n != 0) {
			int[] list = new int[n + 1];

			for (int i = 0; i < n; i++) {
				list[i] = scanner.nextInt();
			}

			all.add(list);
			n = scanner.nextInt();
		}
		scanner.close();

		String output = "";
		int j = 0;
		for (int i = 0; i < all.size(); i++) {

			for (j = 0; j < all.get(i).length - 1; j++) {
				if (all.get(i)[j] != all.get(i)[j + 1]) {
					output = output + all.get(i)[j] + " ";
				}
			}
			output = output + "$\n";
		}

		System.out.println(output.substring(0, output.length() - 1));
	}

}

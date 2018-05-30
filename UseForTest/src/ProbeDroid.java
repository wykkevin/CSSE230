import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProbeDroid {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int q = scanner.nextInt();
		int[] probes = new int[q];
		for (int i = 0; i < q; i++) {
			probes[i] = scanner.nextInt();
		}
		scanner.close();

		ArrayList<ProbeDroid.Droid> droids = new ArrayList<ProbeDroid.Droid>();
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == 1 && j == 1) {
				} else {
					double k = 0;
					if (i == 1) {
						k = 999999999;
					} else if (j != 1) {
						k = ((double) (j - 1) / (i - 1));
					}
					droids.add(new Droid(new Point(i, j), k));

				}
			}
		}

		Collections.sort(droids);

		String output = "";
		for (int i = 0; i < probes.length; i++) {
			output += (droids.get(probes[i] - 1).position.y + " " + droids.get(probes[i] - 1).position.x + "\n");
		}
		System.out.println(output.substring(0, output.length() - 1));
		// for (int i = 0; i < m * n-1; i++) {
		// System.out.println((i+1)+" "+droids.get(i).position.y + " " +
		// droids.get(i).position.x);
		// }
	}

	public static class Droid implements Comparable<Droid> {
		Point position;
		double k;

		public Droid(Point position, double k) {
			this.position = position;
			this.k = k;
		}

		@Override
		public int compareTo(Droid arg0) {
			if (this.k < arg0.k) {
				return -1;
			} else if (this.k > arg0.k) {
				return 1;
			} else {
				if (this.position.x < arg0.position.x) {
					return -1;
				}
				return 1;
			}
		}

	}

}

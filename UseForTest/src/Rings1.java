
/* Solution to Rings */
import java.util.Arrays;
import java.util.Scanner;

public class Rings1 {
	public static int n, m, max;
	public static int grid[][];
	public static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		in.nextLine();
		grid = new int[n + 2][m + 2];
		for (int i = 0; i < n + 2; i++) {
			Arrays.fill(grid[i], 0);
			if (i == 0 || i == n + 1)
				continue;
			String line = in.nextLine();
			max = 0;
			for (int j = 1; j < m + 1; j++) {
				grid[i][j] = ".T".indexOf("" + line.charAt(j - 1));
				if (grid[i][j] == 1) {
					grid[i][j] = 1 + Math.min(grid[i - 1][j], grid[i][j - 1]);
				}
			}
		}
		for (int i = n; i > 0; i--) {
			for (int j = m; j > 0; j--) {
				int k = grid[i][j];
				if (k > 0) {
					k = Math.min(k, 1 + grid[i + 1][j]);
					k = Math.min(k, 1 + grid[i][j + 1]);
					max = Math.max(max, k);
					grid[i][j] = k;
				}
			}
		}
		display();
	}

	public static void display() {
		int w = 2;
		int pow = 10;
		while (max >= pow) {
			w++;
			pow *= 10;
		}
		String pad = "";
		for (int i = 0; i < w; i++)
			pad += ".";

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				String s = pad + grid[i][j];
				if (grid[i][j] == 0)
					s = pad;
				String fmt = "%" + w + "s";
				System.out.printf(fmt, s.substring(s.length() - w));
			}
			System.out.println();
		}
	}
}

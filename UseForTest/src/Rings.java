import java.util.Scanner;

public class Rings {

	public static void main(String[] args) {
		int count = 0;
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		int y = scanner.nextInt();

		String[] stringGrid = new String[y];

		for (int i = 0; i < y; i++) {
			stringGrid[i] = (scanner.next());
		}

		scanner.close();
		
		
		String[][] grid = new String[x][y];
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				grid[i][j] = stringGrid[i].substring(j, j + 1);
				if (grid[i][j].equals("T")) {
					count++;
				}
			}
		}

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if (check1(grid, grid[i][j], i, j)) {
					grid[i][j] = "1";
					count--;
				}
			}
		}

		while (count > 0) {
			int k = 2;
			for (int i = k - 1; i < y - (k - 1); i++) {
				for (int j = k - 1; j < x - (k - 1); j++) {
					if (grid[i - 1][j].equals(k - 1 + "") && grid[i + 1][j].equals(k - 1 + "")
							&& grid[i][j - 1].equals(k - 1 + "") && grid[i][j + 1].equals(k - 1 + "")) {
						grid[i][j] = k + "";
						count--;
					}
				}
			}
			k++;
		}

		String output = "";
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if (grid[i][j].equals(".")) {
					output += "..";
				} else {
					output += "." + grid[i][j];
				}
			}
			output += "\n";
		}

		System.out.println(output.substring(0, output.length() - 1));
	}

	public static boolean check1(String[][] grid, String element, int i, int j) {
		if (element.equals("T")) {
			if (i == 0 || i == grid.length - 1) {
				return true;
			}
			if (j == 0 || j == grid[0].length - 1) {
				return true;
			}
			return (!(grid[i - 1][j].equals("T") && grid[i + 1][j].equals("T") && grid[i][j - 1].equals("T")
					&& grid[i][j + 1].equals("T")));
		}
		return false;
	}
}

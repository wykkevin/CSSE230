package a;

import java.util.Scanner;

public class G {

	public static void main(String[] args) {
		String output = "";
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[][] objects = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				objects[i][j] = scanner.nextInt();
			}
		}
		scanner.close();

		int[][] newTable = new int[n][m];
		newTable[0][0] = objects[0][0];
		for (int i = 1; i < m; i++) {
			newTable[0][i] = objects[0][i] + newTable[0][i - 1];
		}
		for (int j = 1; j < n; j++) {
			newTable[j][0] = objects[j][0] + newTable[j - 1][0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				newTable[i][j] = Math.max(newTable[i - 1][j], newTable[i][j - 1]) + objects[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			output += newTable[i][m - 1] + " ";
		}
		System.out.println(output.substring(0, output.length() - 1));
	}
}

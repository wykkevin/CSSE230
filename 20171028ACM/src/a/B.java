package a;

import java.util.Scanner;

//RHIT-Don't MOve
public class B {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt();
			double v1 = scanner.nextInt();
			double v2 = scanner.nextInt();
			double w = scanner.nextInt();
			double half = (double) (n / 2);
			double k = w / 100;

			double left = (n - v1 - v2);
			long total = (long) Math.pow(2, left);
			double win = 0;
			for (int j = 0; j < left + 1; j++) {
				if (v1 + j > half) {
					win += fact(left) / (fact(left - j) * fact(j));
				}
			}
			if (win / total > k) {
				System.out.println("GET A CRATE OF CHAMPAGNE FROM THE BASEMENT!");
			} else if (win / total == 0) {
				System.out.println("RECOUNT!");
			} else {
				System.out.println("PATIENCE, EVERYONE!");
			}
		}
		scanner.close();
	}

	public static long fact(double left) {
		if (left == 1 || left == 0) {
			return 1;
		}
		return (long) (left * fact(left - 1));
	}

}

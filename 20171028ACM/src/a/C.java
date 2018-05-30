package a;

import java.util.Arrays;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		double[] list = new double[n];
		double sum = 0;
		for (int i = 0; i < n; i++) {
			scanner.next();
			list[i] = scanner.nextDouble();
		}
		Arrays.sort(list);

		for (int i = 1; i < n + 1; i++) {
			sum += (n - i + 1) * list[i - 1];

		}
		System.out.println(sum);
		System.exit(0);
	}

}

import java.util.Scanner;

public class L {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int d = scanner.nextInt();
		int s = scanner.nextInt();
		scanner.close();
		double a = 990;
		while (a + s < a * Math.cosh(d / 2 * a)) {
			a++;
		}
		double a1 = a - 0.9;
		while (a1 + s < a1 * Math.cosh(d / 2 * a1)) {
			a1 += 0.1;
		}
		double a2 = a1 - 0.09;
		while (a2 + s < a2 * Math.cosh(d / 2 * a2)) {
			a2 += 0.01;
		}
		double a3 = a - 0.009;
		while (a3 + s < a3 * Math.cosh(d / 2 * a3)) {
			a3 += 0.001;
		}
		double a4 = a - 0.0009;
		while (true) {
			if (a4 + s == a4 * Math.cosh(d / 2 * a4)) {
				System.out.println(2 * a4 * Math.sinh(d / 2 * a4));
				break;
			}
			a4 += 0.0001;
		}
	}

}

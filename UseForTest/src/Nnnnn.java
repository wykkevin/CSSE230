import java.util.Scanner;

public class Nnnnn {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int digits = scanner.nextInt();
		scanner.close();
		if (digits == 0) {
			System.out.println(0);
			return;
		}
		if (digits < 10) {
			System.out.println(digits);
			return;
		}
		for (int i = 1; i < 100000; i++) {
			if (digits % i == 0) {
				int single = digits / i;
				if (single >= Math.pow(10, (i - 1)) && single < Math.pow(100, (i - 1))) {
					System.out.println(single);
					return;
				}
			}
		}

	}

}

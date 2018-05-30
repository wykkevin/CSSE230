import java.util.Scanner;

public class JudgingMoose {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int left = scanner.nextInt();
		int right = scanner.nextInt();
		scanner.close();

		if (left == 0 && right == 0) {
			System.out.println("Not a moose");
		} else if (left == right) {
			System.out.println("Even " + left * 2);
		} else {
			System.out.println("Odd " + Math.max(left, right)*2);
		}
	}

}

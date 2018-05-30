import java.util.Scanner;

public class AnthonyAndCora {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int anthony = scanner.nextInt();
		int cora = scanner.nextInt();
		double[] possi = new double[anthony + cora];
		for (int i = 0; i < anthony + cora - 1; i++) {
			possi[i] = scanner.nextDouble();
		}
		scanner.close();

		System.out.println(cal(possi, 0, anthony, cora));

	}

	public static double cal(double[] pos, int i, int anth, int cor) {
		if (i >= pos.length || anth == 0) {
			return 0;
		}
		if (cor == 0) {
			return 1;
		}
		return (1 - pos[i]) * cal(pos, i + 1, anth - 1, cor) + pos[i] * cal(pos, i + 1, anth, cor - 1);
	}
}

import java.util.Scanner;

public class TrainPassenger {
	public static void main(String[] arg0) {
		int current = 0;
		Scanner scan = new Scanner(System.in);
		int capacity = scan.nextInt();
		int stations = scan.nextInt();
		for (int i = 0; i < stations; i++) {
			int left = scan.nextInt();
			int in = scan.nextInt();
			int stay = scan.nextInt();
			if (current - left < 0) {
				System.out.println("impossible");
				return;
			}
			current = current - left + in;
			if (current < 0 || current > capacity) {
				System.out.println("impossible");
				return;
			}
			if (stay != 0 && current != capacity) {
				System.out.println("impossible");
				return;
			}
		}
		scan.close();
		if (current == 0) {
			System.out.println("possible");
		} else {
			System.out.println("impossible");
		}
	}

}

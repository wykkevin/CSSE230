import java.util.Scanner;

public class TrickShot {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int w = scanner.nextInt();
		int l = scanner.nextInt();
		int radius = scanner.nextInt();
		int x1 = scanner.nextInt();
		int y1 = scanner.nextInt();
		int x2 = scanner.nextInt();
		int y2 = scanner.nextInt();
		int x3 = scanner.nextInt();
		int y3 = scanner.nextInt();
		int h = scanner.nextInt();
		scanner.close();

		double k3hole = (l - y3) / (w - x3);
		double k2hole = -(l - y2) / x2;
		double x1hit = x3 - Math.sqrt((2 * radius) * (2 * radius) / (1 + k3hole));
		double y1hit = y3 - k3hole * Math.sqrt((2 * radius) * (2 * radius) / (1 + k3hole));
		double k1 = (y1hit - y1) / (x1hit - x1);
		double temp = ((2 * x1 + 2 * k2hole) + Math.sqrt(Math.pow(2 * x1 + 2 * k2hole, 2)
				- 4 * (1 + k2hole * k2hole) * (x1 * x1 + y1 * y1 - (2 * radius) * (2 * radius))))
				/ (2 * (1 + k2hole) * (1 + k2hole));
		double xcuehit = x1 - temp;
		double ycuehit = y1 - k2hole * xcuehit;
		int ycue = h;
		double kcue = (-k2hole + 2 * k1 + k2hole * k1 * k1) / (2 * k2hole * k1 - k1 * k1 + 1);
		double xcue = xcuehit - kcue * (ycuehit - ycue);
		if (xcue > l || xcue < 0) {
			System.out.println("impossible");
		} else {
			double angle = Math.atan((ycuehit - ycue) / (xcuehit - xcue));
			System.out.println(xcue + " " + angle);
		}
	}

}

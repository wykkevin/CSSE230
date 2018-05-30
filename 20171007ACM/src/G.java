import java.util.Arrays;
import java.util.Scanner;

public class G {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		Point[] points = new Point[num];
		for (int i = 0; i < num; i++) {
			points[i] = new Point(scanner.nextLong(), scanner.nextLong());
		}
		scanner.close();
		int count = 0;
		Arrays.sort(points);
		for (int i = 0; i < num - 1; i++) {
			for (int j = i + 1; j < num; j++) {
//				double x = points[i].diffx(points[j]);
//				double y = points[i].diffy(points[j]);
				if (points[j].distanceTo0()-points[i].distanceTo0()>2018) {
					break;
				}
				if (points[i].distance(points[j]) == 2018) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

	public static class Point implements Comparable {
		long x;
		long y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		public double diffx(Point point) {
			return Math.abs(this.x - point.x);
		}

		public double diffy(Point point) {
			return Math.abs(this.y - point.y);
		}

		public double distance(Point point) {
			return Math.sqrt(Math.pow((point.x - this.x), 2) + Math.pow((point.y - this.y), 2));
		}

		public double distanceTo0() {
			return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		}

		@Override
		public int compareTo(Object arg0) {
			if (this.distanceTo0() > ((Point) arg0).distanceTo0()) {
				return 1;
			} else {
				return 0;
			}
		}
	}

}

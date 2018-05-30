import java.util.Scanner;

public class TellYourWorld{
	
	private static int n;
	private static int a[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		
		double k1 = a[3] - a[2];
		double k2 = (a[3] - a[1]) / 2.0;
		double k3 = a[2] - a[1];
		if (check(k1, a[2] - k1 * 2) ||
			check(k2, a[3] - k2 * 3) ||
			check(k3, a[3] - k3 * 3)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
	
	public static boolean check(double k, double b1) {
		boolean _a[] = new boolean[n + 1];
		int fp = 0; // fp marks the index of the first point not on the first line
		int cnt = 0;
		
		// mark all points on the first line
		for (int i = 1; i <= n; i++) {
			if (a[i] == k * i + b1) {
				_a[i] = true;
				cnt++;
			} else {
				if (fp == 0) {
					fp = i;
				}
			}
		}
		// before we start to find the second line, we can eliminate some cases:
		// if all the points are on the first line, we fail the test
		if (cnt == n) {
			return false;
		}
		// if all points except for 1 point are on the first line, 
		// we can definitely find another line that goes through that lonely point
		// and satisfies both requirements. therefore, we pass the check.
		if (cnt == n - 1) {
			return true;
		}
		// find the second line
		double b2 = a[fp] - k * fp;
		// check if other points are on the line
		for (int i = fp + 1; i <= n; i++) {
			if (!_a[i] && a[i] != k * i + b2) {
				return false;
			}
		}
		return true;
	}
	
}
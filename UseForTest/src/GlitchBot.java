

import java.util.Scanner;

public class GlitchBot {
	public static void main(String[] arg0) {

		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		int y = scan.nextInt();
		int instructions = scan.nextInt();
		String[] all = new String[instructions];
		Point dest = new Point(x, y);
		Point current = new Point(0, 0);
		for (int i = 0; i < instructions; i++) {
			all[i] = scan.next();
		}
		for (int i = 0; i < instructions; i++) {
			if (all[i].equals("Forward")) {
				all[i] = "Right";
				if (run(all).equal(dest)) {
					System.out.println(i+1 + " Right");
					break;
				}
				all[i] = "Left";
				if (run(all).equal(dest)) {
					System.out.println(i+1 + " Left");
					break;
				}
				all[i] = "Forward";
			} else if (all[i].equals("Left")) {
				all[i] = "Right";
				if (run(all).equal(dest)) {
					System.out.println(i+1 + " Right");
					break;
				}
				all[i] = "Forward";
				if (run(all).equal(dest)) {
					System.out.println(i+1 + " Forward");
					break;
				}
				all[i] = "Left";
			} else {
				all[i] = "Left";
				if (run(all).equal(dest)) {
					System.out.println(i+1 + " Left");
					break;
				}
				all[i] = "Forward";
				if (run(all).equal(dest)) {
					System.out.println(i+1 + " Forward");
					break;
				}
				all[i] = "Right";
			}
		}
	}

	public static Point run(String[] instrs) {
		Point init = new Point(0, 0);
		int dx = 0;
		int dy = 1;
		for (int i = 0; i < instrs.length; i++) {
			if (instrs[i].equals("Forward")) {
				init = new Point(init.x + dx, init.y + dy);
			} else if (instrs[i].equals("Left")) {
				if (dx == 0 && dy == 1) {
					dx = -1;
					dy = 0;
				} else if (dx == 0 && dy == -1) {
					dx = 1;
					dy = 0;
				} else if (dx == 1 && dy == 0) {
					dx = 0;
					dy = 1;
				} else if (dx == -1 && dy == 0) {
					dx = 0;
					dy = -1;
				}
			} else if (instrs[i].equals("Right")) {
				if (dx == 0 && dy == 1) {
					dx = 1;
					dy = 0;
				} else if (dx == 0 && dy == -1) {
					dx = -1;
					dy = 0;
				} else if (dx == 1 && dy == 0) {
					dx = 0;
					dy = -1;
				} else if (dx == -1 && dy == 0) {
					dx = 0;
					dy = 1;
				}
			}
		}
		return init;
	}

	public static class Point {
		int x;
		int y;
		int diff;
		String direction;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.direction = "North";
			this.diff = this.x + this.y;
		}

		public int difference() {
			return this.x + this.y;
		}

		public int cal(Point point) {
			return Math.abs(this.diff - point.diff);
		}

		public boolean equal(Point p) {
			return (this.x == p.x && this.y == p.y);
		}
	}

}

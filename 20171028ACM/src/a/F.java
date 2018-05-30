package a;

import java.util.Scanner;

//RHIT-Don't Move
public class F {

	public static void main(String[] args) {
		int time = 0;
		int v1 = 0;
		int v2 = 0;
		boolean changeTo01 = true;
		boolean changeTo02 = true;
		Scanner scanner = new Scanner(System.in);
		int pos1 = scanner.nextInt();
		int pos2 = scanner.nextInt();
		int temp1 = scanner.nextInt();
		int[] change1 = new int[temp1];
		for (int i = 0; i < temp1; i++) {
			change1[i] = scanner.nextInt();
		}
		int temp2 = scanner.nextInt();
		int[] change2 = new int[temp2];
		for (int i = 0; i < temp2; i++) {
			change2[i] = scanner.nextInt();
		}

		scanner.close();

		int currentIndex1 = 0;
		int currentIndex2 = 0;

		if (pos1 > pos2) {
			while (currentIndex1 < change1.length || currentIndex2 < change2.length) {
				pos1 += v1;
				pos2 += v2;
				if (currentIndex1 < change1.length) {
					if (change1[currentIndex1] == time) {
						currentIndex1++;
						if (changeTo01) {
							v1 = 1;
							changeTo01 = false;
						} else {
							v1 = 0;
							changeTo01 = true;
						}
					}
				}
				if (currentIndex2 < change2.length) {
					if (change2[currentIndex2] == time) {
						currentIndex2++;
						if (changeTo02) {
							v2 = 1;
							changeTo02 = false;
						} else {
							v2 = 0;
							changeTo02 = true;
						}
					}
				}

				if (pos2 + 5 > pos1) {
					System.out.println("bumper tap at time " + time);
					return;
				}
				time++;
			}

			int newpos1 = pos1 + v1;
			int newpos2 = pos2 + v2;
			if ((pos1 - pos2) > (newpos1 - newpos2)) {
				while (pos2 + 5 < pos1) {
					pos1 += v1;
					pos2 += v2;
					time++;
				}
				System.out.println("bumper tap at time " + time);
				return;
			} else {
				System.out.println("safe and sound");
				return;
			}
		} else {
			while (currentIndex1 < change1.length || currentIndex2 < change2.length) {
				pos1 += v1;
				pos2 += v2;
				if (currentIndex1 < change1.length) {
					if (change1[currentIndex1] == time) {
						currentIndex1++;
						if (changeTo01) {
							v1 = 1;
							changeTo01 = false;
						} else {
							v1 = 0;
							changeTo01 = true;
						}
					}
				}
				if (currentIndex2 < change2.length) {
					if (change2[currentIndex2] == time) {
						currentIndex2++;
						if (changeTo02) {
							v2 = 1;
							changeTo02 = false;
						} else {
							v2 = 0;
							changeTo02 = true;
						}
					}
				}

				if (pos1 + 5 > pos2) {
					System.out.println("bumper tap at time " + time);
					return;
				}
				time++;
			}

			int newpos1 = pos1 + v1;
			int newpos2 = pos2 + v2;
			if ((pos2 - pos1) > (newpos2 - newpos1)) {
				while (pos1 + 5 < pos2) {
					pos1 += v1;
					pos2 += v2;
					time++;
				}
				System.out.println("bumper tap at time " + time);
				return;
			} else {
				System.out.println("safe and sound");
				return;
			}
		}
	}

}

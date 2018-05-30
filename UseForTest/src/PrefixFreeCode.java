import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PrefixFreeCode {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] chang = new int[m];
		Integer[] ali = new Integer[m];
		ArrayList<String> als = new ArrayList<String>();
		HashMap<Integer, String> link = new HashMap<Integer, String>();
		Integer[] mirror = new Integer[n];
		String fin = "";

		for (int i = 0; i < n; i++) {
			als.add(sc.next());
		}
		fin = sc.next();

		als.sort(String::compareToIgnoreCase);
		for (int i = 0; i < n; i++) {
			mirror[i] = fin.indexOf(als.get(i));
			if (mirror[i] != -1) {
				link.put(mirror[i], als.get(i));
			}
		}

		link.keySet().toArray(ali);
		Arrays.sort(ali);
		int temp = 0;
		for (Integer i : ali) {
			System.out.println(link.get(i));
			chang[temp] = als.indexOf(link.get(i));
			System.out.println(chang[temp] + 1);
			temp++;
		}

	}



	public static long fac(int n) {
		long product = 1;
		for (int i = 1; i <= n; i++) {
			product *= i;
		}

		return product;
	}
}
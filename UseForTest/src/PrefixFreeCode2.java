import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class PrefixFreeCode2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		TreeSet<String> set = new TreeSet<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		HashSet<Integer> hash = new HashSet<Integer>();
		int m = scan.nextInt();
		int n = scan.nextInt();
		for (int i = 0; i < m; i++) {
			hash.add(i + 1);
			set.add(scan.next());
		}
		String test = scan.next();
		int i = 1;
		for (String s : set) {
			map.put(s, i);
			i++;
		}
		for (String s : map.keySet()) {
			test = test.replace(s, (map.get(s)).toString());
		}
		int out = calculate(test, hash, m, n);
		System.out.println(out);
	}

	public static int calculate(String string, HashSet<Integer> set, int m, int n) {
		if (string.length() == 0) {
			return 1;
		}
		int first = Character.getNumericValue(string.charAt(0));
		int count = 0;
		set.remove(first);
		for (int i : set) {
			if (i < first) {
				count++;
			} else {
				break;
			}
		}
		return (int) (count * fac(m - 1, m - n) + calculate(string.substring(1), set, m - 1, n - 1));
	}

	public static long fac(int a, int b) {
		long output = 1;
		for (int i = a; i > b; i--) {
			output *= i;
		}
		return output;
	}
}
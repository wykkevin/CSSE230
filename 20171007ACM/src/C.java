import java.util.HashMap;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		int[] coins = new int[num];
		for (int i = 0; i < num; i++) {
			coins[i] = scanner.nextInt();
		}
		scanner.close();
		int limit = coins[coins.length - 1] + coins[coins.length - 2];
		int l = coins.length;
		String output = "canonical";
		HashMap<Integer, Integer> dpmap = new HashMap<Integer, Integer>();
		dpmap.put(0, 0);
		for (int i = 0; i < l; i++) {
			dpmap.put(coins[i], 1);
		}
		for (int i = 0; i < l; i++) {
			for (int j = 1; j < l; j++) {
				dpmap.put(coins[i] + coins[j], 2);
			}
		}
		for (int i = coins[coins.length - 1] + 1; i < limit; i++) {
			int greedy = greedy(coins, l, i);
			int dp = dp(coins, l, i, dpmap);
			dpmap.put(i, dp);
			if (greedy > dp) {
				output = "non-canonical";
			}
		}
		System.out.println(output);
	}

	public static int greedy(int[] coins, int l, int sum) {
		int count = 0;
		for (int i = l - 1; i >= 0; i--) {
			while (sum >= coins[i]) {
				sum = sum - coins[i];
				count++;
				if (sum == 0) {
					break;
				}
			}
		}
		return count;
	}

	public static int dp(int coins[], int l, int V, HashMap<Integer, Integer> map) {
		if (map.containsKey(V)) {
			return map.get(V);
		}
		int i = l - 1;
		while (i >= 0) {
			if (coins[i] < V) {
				break;
			}
			i--;
		}
		int c = dp(coins, i + 1, V - coins[i], map);
		map.put(V - coins[i], c);
		if (i > 1) {
			int d = dp(coins, i + 1, V - coins[i - 1], map);
			map.put(V - coins[i - 1], d);
			return 1 + Math.min(c, d);
		}
		return 1 + c;
	}
}

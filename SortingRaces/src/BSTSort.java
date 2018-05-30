import java.util.PriorityQueue;
import java.util.TreeSet;

public class BSTSort {
	private static TreeSet<Integer> tree = new TreeSet<Integer>();
	private static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	private static int j = 0;

	public static void sort(Integer[] input) {
		for (int i = 0; i < input.length; i++) {
			Boolean addSuccess = tree.add(input[i]);
			if (!addSuccess) {
				queue.add(input[i]);
			}
		}
		int count = 0;
		for (int i = 0; i < input.length; i++) {
			input[i] = tree.first();
			if (queue.size() != 0) {
				while (queue.peek().equals(input[i])) {
					input[i] = queue.poll();
				}
			}
		}
		tree.remove(tree.first());
	}

}

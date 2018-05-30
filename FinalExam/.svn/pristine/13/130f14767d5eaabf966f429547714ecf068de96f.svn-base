package heap;

import java.util.ArrayList;
import java.util.List;

public class Heap {
	public static List<Integer> postOrderList(int[] heapArray) {
		int i = 1;
		ArrayList<Integer> output = new ArrayList<Integer>();
		recurse(output, heapArray, i);
		return output;
	}

	public static void recurse(ArrayList<Integer> output, int[] heapArray, int i) {
		if (i >= heapArray.length) {
			return;
		}
		recurse(output, heapArray, 2 * i);
		recurse(output, heapArray, 2 * i + 1);
		output.add(heapArray[i]);
	}
}
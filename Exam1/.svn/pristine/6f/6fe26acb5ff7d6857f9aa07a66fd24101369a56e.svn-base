package mode;

import java.util.HashMap;

public class IntArray {

	/**
	 * Computes the mode (the value that appears most often) of the data given.
	 * You may assume there will be no ties, so the mode is unique. You may also
	 * assume the given array is nonempty.
	 * 
	 * @return
	 */
	public static int mode(int[] arr) {
		// DONE: write and test me!
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int mostNum = 0;
		int numOfMostNum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], map.get(arr[i]) + 1);
				if (numOfMostNum < map.get(arr[i])) {
					numOfMostNum = map.get(arr[i]);
					mostNum = arr[i];
				}
			}
		}
		return mostNum;
	}
}

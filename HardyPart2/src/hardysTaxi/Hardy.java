package hardysTaxi;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all "taxicab
 * numbers that are less than n. These are represented as TaxicabNumber objects,
 * which include the sum and two different ways of writing that as a sum of two
 * cubes.
 * 
 * @author Claude Anderson.
 * 
 */
public class Hardy {

	// DONE: Declare any variables or helper methods that you need.

	/**
	 * Find the nth Hardy number (start counting with 1, not 0) and the numbers
	 * whose cubes demonstrate that it is a Hardy number.
	 * 
	 * @param n
	 * @return the nth Hardy number
	 */
	public static long nthHardyNumber(int n) {

		// DONE: If you have any fields that are arrays or collections,
		// You must clear them at the beginning of each call to this method.
		// This is so that values calculated by previous calls do not speed up
		// subsequent calls.

		// Runtime is used to find the amount of allocated memory. We use it to
		// find out when to remove the too small elements in the treeset.
		Runtime runtime = Runtime.getRuntime();
		// The TreeSet stores all the possible Hardy numbers. We can find a real
		// one when the add returns false. (Use most of the memory)
		TreeSet<Long> treeStorage = new TreeSet<>();
		// The PriorityQueue stores all the Hardy numbers. (Most in order) We
		// get the nth number by polling out from it.
		PriorityQueue<Long> taxiNumbersInOrder = new PriorityQueue<Long>();
		// The stopCounter is used to determine a stop point.
		long stopCounter = 0;
		// Counter for the larger number in a Hardy number. (The smallest is 9,
		// so we begin with that)
		int b = 9;

		/*
		 * We use a TreeSet to store all the values we calculated, and use add
		 * method and the contains method in PriorityQueue to see if it has been
		 * calcalated only once. If yes, then we get the Hardy number. Then we
		 * store the hardy numbers into a PriorityQueue. We use a stopCounter to
		 * let us know when we can guarantee that we have found the nth hardy
		 * number and begin to return it.
		 */

		while (true) {
			long bCubed = (long) Math.pow(b, 3);
			for (int a = 1; a < b; a++) {
				long aCubed = (long) Math.pow(a, 3);
				long tempSum = aCubed + bCubed;
				// If it is already in the treeset and is not in priorityQueue
				// yet, it is a Hardy number.
				if (!treeStorage.add(tempSum) && !taxiNumbersInOrder.contains(tempSum)) {
					taxiNumbersInOrder.offer(tempSum);
					// We calculated n Hardy numbers, but not in order. So we
					// calculate any smaller Hardies that can exist.
					if (taxiNumbersInOrder.size() <= n) {
						stopCounter = tempSum;
					} else if (stopCounter < aCubed) {
						// We poll everything before the nth Hardy number
						for (int i = 0; i < n - 1; i++) {
							taxiNumbersInOrder.poll();
						}
						return taxiNumbersInOrder.poll();
					}
				}
			}
			// We check if the heap space occupied is less than 800MB. If it is,
			// we remove irrelevent data. Because 800MB is close to 900MB, we
			// can save time on smaller calculations and make sure we still in
			// the memory.
			if (runtime.totalMemory() > 800000000) {
				while (treeStorage.first() < bCubed) {
					treeStorage.remove(treeStorage.first());
				}
			}
			b++;
		}

	}

}

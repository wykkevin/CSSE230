package mode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int modePoints = 0;

	@Test
	public void testModeBasic() {
		int[] a1 = new int[] {5,3,4,1,3,2};
		assertEquals(3,IntArray.mode(a1));
		int[] a2 = new int[] {5,2,4,3,2,3,2,1,2};
		assertEquals(2,IntArray.mode(a2));
		int[] a3 = new int[] {1,2,2,2,2,3,3,4,4,4,4,4,4,5};
		assertEquals(4,IntArray.mode(a3));
		modePoints += 5;
	}
	
	@Test
	public void testModeLargest() {
		int[] a1 = new int[] {1,5,3,4,5,5,1,3,5,2,3};
		assertEquals(5,IntArray.mode(a1));
		int[] a2 = new int[] {5,8,2,4,3,2,8,5,7,2,3,8,4,8,1,8,3,2,1,8,8,2};
		assertEquals(8,IntArray.mode(a2));
		modePoints += 2;
	}

	
	@Test
	public void testModeSmallest() {
		int[] a1 = new int[] {1,5,3,3,4,1,1,1,3,1,2,3};
		assertEquals(1,IntArray.mode(a1));
		int[] a2 = new int[] {5,8,2,4,3,2,8,5,7,2,3,2,4,2,2,3,2,8,8,2};
		assertEquals(2,IntArray.mode(a2));
		modePoints += 2;
	}
	
	
	@Test
	public void testModeFirstListed() {
		int[] a1 = new int[] {3,4,4,4,5,3,4,1,1,3,3,3,3,2,7};
		assertEquals(3,IntArray.mode(a1));
		int[] a2 = new int[] {5,8,2,4,3,2,5,5,7,2,3,5,4,5,5,5,3,2,5,8,2};
		assertEquals(5,IntArray.mode(a2));
		modePoints += 2;
	}

	
	
	@Test
	public void testModeLastListed() {
		int[] a1 = new int[] {3,4,4,4,5,3,4,1,1,3,3,3,2,7,7,7,7,7,7,7,7};
		assertEquals(7,IntArray.mode(a1));
		int[] a2 = new int[] {2,8,2,4,3,2,5,5,7,2,3,5,4,5,5,3,2,5,8,2,5,5};
		assertEquals(5,IntArray.mode(a2));
		modePoints += 2;
	}
	

	@Test
	public void testModeRandom() {
		int ARRAY_SIZE = 200; // Can make much larger and see how fast it is. :)
		int RANDOM_RANGE = ARRAY_SIZE * 5;
		
		Random ran = new Random();
		int threshold = 10 + ran.nextInt(10);        // mode will occur "threshold" times, other elements
		                                             // will occur less often.

		HashSet<Integer> set = new HashSet<Integer>();
		for (Integer i = 0; i < ARRAY_SIZE; i++) {          // Generate 200 random Integers and add them to the set
			int value = ran.nextInt(RANDOM_RANGE);           // (due to repetition, may end up with less)
			set.add(value);
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean first = true;
		int answer = -1;
		for (Integer item : set) {
			if (first) {
				answer = (int) item;                    // The first item from the set will be the mode
				for (int k = 0; k < threshold; k++) {   // Add this item threshold times to the list
					list.add(item);
				}
				first = false;
			}
			else {
				int multiplicity = ran.nextInt(threshold);  // Add other items up to threshold-1 times
				for (int j = 0; j < multiplicity; j++) {
					list.add(item);
				}
			}
		}
		
		Collections.shuffle(list);                      // shuffle the list
		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {         // copy over to primitive array
			arr[i] = (int) list.get(i);
		}

		double elapsedTime;
		double startTime = System.currentTimeMillis();
		// Time the following line of code:
		assertEquals(answer,IntArray.mode(arr));
		
		elapsedTime = (System.currentTimeMillis() - startTime);
		System.out.println(elapsedTime + " milliseconds on array of size " + ARRAY_SIZE);
		modePoints += 13;
	}
	
	
	
	@AfterClass
	public static void displayPoints() {
		System.out.printf("***   mode unit tests:                            %2d/26\n", modePoints);
		System.out.println("***   mode analysis (write it on your paper):      ?/ 8 [graded by instructor]");	
	}
}

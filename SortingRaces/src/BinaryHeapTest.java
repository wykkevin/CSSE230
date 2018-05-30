import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests binary heaps.
 * 
 * @author Matt Boutell. Created May 7, 2013.
 */
public class BinaryHeapTest {

	/**
	 * Simple test method for insert, delete, toString, and sort. Enforces the
	 * method signatures.
	 */
	@Test
	public void testSimple() {
		// DONE: Implement the BinaryHeap class according to the spec.
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		// deleteMin returns null if it has no elements.
		assertNull(heap.deleteMin());
		heap.insert(21);
		assertEquals("[null, 21]", heap.toString());
		int min = heap.deleteMin();
		assertEquals(21, min);
		assertEquals("[null]", heap.toString());
		String[] csLegends = new String[] { "Edsger Dijkstra", "Grace Hopper", "Donald Knuth", "Ada Lovelace",
				"Claude Shannon", "Alan Turing" };

		BinaryHeap<String> csLegendsHeap = new BinaryHeap<String>();
		csLegendsHeap.sort(csLegends);
		assertEquals("[Ada Lovelace, Alan Turing, Claude Shannon, Donald Knuth, Edsger Dijkstra, Grace Hopper]",
				Arrays.toString(csLegends));
	}

	// DONE: Add unit tests for each method until you are satisfied your code is
	// correct. I will test your code with more complex tests. Since this
	// assignment is short, I'd like you to give the tests some thought, rather
	// than just always relying on someone else's tests. Professional developers
	// usually write their own unit tests.
	@Test
	public void testSimple2() {
		BinaryHeap<String> heap = new BinaryHeap<String>();
		assertNull(heap.deleteMin());
		heap.insert("first");
		heap.insert("second");
		heap.insert("third");
		assertEquals("[null, first, second, third]", heap.toString());
		String min1 = heap.deleteMin();
		assertEquals("first", min1);
		assertEquals("[null, second, third]", heap.toString());
		String min2 = heap.deleteMin();
		assertEquals("second", min2);
		assertEquals("[null, third]", heap.toString());
		String min3 = heap.deleteMin();
		assertEquals("third", min3);
		assertEquals("[null]", heap.toString());

		Character[] me = new Character[] { 'K', 'e', 'v', 'i', 'n', 'W', 'a', 'n', 'g' };

		BinaryHeap<Character> meHeap = new BinaryHeap<Character>();
		meHeap.sort(me);
		assertEquals("[K, W, a, e, g, i, n, n, v]", Arrays.toString(me));
	}
}

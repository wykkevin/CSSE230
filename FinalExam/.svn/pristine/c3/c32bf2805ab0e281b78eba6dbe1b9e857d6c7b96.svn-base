package heap;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * Test for the heap postorder list method
 * 
 * @author Matt Boutell
 */
public class HeapTest {

	private static int points = 0;

	@Test
	public void testEmpty() {
		int[] heap;
		heap = new int[]{9999}; // empty tree; 9999 in position 0 since we don't care about it.
		assertEquals("[]", Heap.postOrderList(heap).toString());
		points += 1;
	}
	
	@Test
	public void testOneElement() {
		int[] heap;
		heap = new int[]{9999, 17}; 
		assertEquals("[17]", Heap.postOrderList(heap).toString());
		points += 1;
	}
	
	@Test
	public void testHeightOfOne() {
		int[] heap;
		heap = new int[]{9999, 17, 50, 45}; 
		assertEquals("[50, 45, 17]", Heap.postOrderList(heap).toString());
		points += 1;
	}
	
	@Test
	public void testFiveElements() {
		int[] heap;
		heap = new int[]{9999, 11, 20, 30, 40, 50};
		assertEquals("[40, 50, 20, 30, 11]", Heap.postOrderList(heap).toString());
		points += 3;
	}
	
	
	@Test
	public void testNineElements() {
		int[] heap;
		heap = new int[]{9999, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		assertEquals("[8, 9, 4, 5, 2, 6, 7, 3, 1]", Heap.postOrderList(heap).toString());
		points += 3;
	}
		
	@Test
	public void test18Elements() {
		int[] heap;
		heap = new int[]{9999, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20};
		assertEquals("[16, 17, 8, 20, 9, 4, 10, 11, 5, 2, 12, 13, 6, 14, 15, 7, 3, 1]", Heap.postOrderList(heap).toString());
		points += 3;
	}
		
	@Test
	public void test18ElementsRandomOrder() {
		int[] heap;
		heap = new int[]{9999, 10, 15, 12, 18, 20, 19, 16, 30, 26, 25, 28, 40, 45, 21, 23, 35, 32, 80};
		assertEquals("[35, 32, 30, 80, 26, 18, 25, 28, 20, 15, 40, 45, 19, 21, 23, 16, 12, 10]", Heap.postOrderList(heap).toString());
		points += 3;
	}
		
	@AfterClass
	public static void printResults() {
		System.out.printf("%2d/%2d postOrderHeapTraversal points\n", points, 15);
	}

}

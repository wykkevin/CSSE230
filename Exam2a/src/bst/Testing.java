package bst;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int evenSumPoints = 0;
	private static int replaceWithTrianglePoints = 0;
	private static int approximateSearchPoints = 0;

	//          40
	//      21             60
	//   1     32     50      70
	// -3
	private BinarySearchTree buildTree() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(40);
		b.insert(21);
		b.insert(60);
		b.insert(1);
		b.insert(32);
		b.insert(50);
		b.insert(70);
		b.insert(-3);
		return b;
	}

	@Test
	public void testEvenSumSmallTrees() {
		BinarySearchTree b = new BinarySearchTree();
		assertEquals(0, b.sumOfEvenValues());
		b.insert(10);
		assertEquals(10, b.sumOfEvenValues());

		b = new BinarySearchTree();
		b.insert(11);
		assertEquals(0, b.sumOfEvenValues());
		evenSumPoints += 3;
	}

	@Test
	public void testEvenSumMediumTree() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(20);
		b.insert(10);
		b.insert(31);
		b.insert(5);
		b.insert(12);
		b.insert(24);
		b.insert(40);
		assertEquals(106, b.sumOfEvenValues());

		// Only one even value hiding down the tree.
		b = new BinarySearchTree();
		b.insert(21);
		b.insert(11);
		b.insert(31);
		b.insert(5);
		b.insert(15);
		b.insert(23);
		b.insert(41);
		b.insert(26);
		assertEquals(26, b.sumOfEvenValues());
		evenSumPoints += 4;
	}

	@Test
	public void testEvenSumOnExampleTree() {
		BinarySearchTree t = buildTree();
		assertEquals(252, t.sumOfEvenValues());
		evenSumPoints += 2;
	}

	@Test
	public void testEvenSumOnBiggerRandomTree() {
		int n = 100;
		// Generate a random permutation of n ints (just so there are no
		// duplicates).
		ArrayList<Integer> nums = new ArrayList<Integer>(n);
		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}
		Collections.shuffle(nums);
		BinarySearchTree b = new BinarySearchTree();
		for (int num : nums) {
			b.insert(num);
		}

		// Regardless of the order they were inserted, the
		// answer will be 2 + 4 + 6 + 8 + ... + 98 + 100
		// = 2 * (1 + 2 + 3 + 4 + ... + 49 + 50),
		// = 2 * (50)(51)/2
		// = 2550.
		assertEquals(2550, b.sumOfEvenValues());
		evenSumPoints += 7;
	}

	@Test
	public void testReplaceWithTriangleExampleTree() {
		BinarySearchTree b = buildTree();
		b.replaceWithTriangle(50);
		assertEquals("[-3, 1, 21, 32, 40, 50, 50, 50, 60, 70]", b.toString());
		assertEquals("[40, 21, 1, -3, 32, 60, 50, 50, 50, 70]", b.toStringPreorder());
		replaceWithTrianglePoints += 2;
		b = buildTree();
		b.replaceWithTriangle(21);
		assertEquals("[-3, 1, 21, 21, 21, 32, 40, 50, 60, 70]", b.toString());
		assertEquals("[40, 21, 21, 1, -3, 21, 32, 60, 50, 70]", b.toStringPreorder());
		replaceWithTrianglePoints += 2;
	}
	
	@Test
	public void testReplaceWithTriangleReturnValue() {
		BinarySearchTree b = buildTree();
		assertTrue(b.replaceWithTriangle(50));
		assertFalse(b.replaceWithTriangle(51));
		assertTrue(b.replaceWithTriangle(21));
		assertFalse(b.replaceWithTriangle(5));
		replaceWithTrianglePoints += 3;
	}

	private BinarySearchTree buildTreeBig() {
		int[] numsToAdd = {6, 19, 4, 12, 15, 9, 16, 18, 14, 13, 11, 1, 8, 17, 5, 2, 7, 3, 20, 10};

		BinarySearchTree b = new BinarySearchTree();
		for (int num : numsToAdd) {
			b.insert(num);
		}
		return b;
	}

	@Test
	public void testReplaceWithTriangleOnBigTree() {
		BinarySearchTree b = buildTreeBig();
		b.replaceWithTriangle(20);
		assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 20, 20]",b.toString());
		assertEquals("[6, 4, 1, 2, 3, 5, 19, 12, 9, 8, 7, 11, 10, 15, 14, 13, 16, 18, 17, 20, 20, 20]",b.toStringPreorder());
		replaceWithTrianglePoints += 2;
		b = buildTreeBig();
		b.replaceWithTriangle(2);
		assertEquals("[1, 2, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]",b.toString());
		assertEquals("[6, 4, 1, 2, 2, 2, 3, 5, 19, 12, 9, 8, 7, 11, 10, 15, 14, 13, 16, 18, 17, 20]",b.toStringPreorder());
		replaceWithTrianglePoints += 2;
		b = buildTreeBig();
		b.replaceWithTriangle(12);
		assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12, 12, 13, 14, 15, 16, 17, 18, 19, 20]",b.toString());
		assertEquals("[6, 4, 1, 2, 3, 5, 19, 12, 12, 9, 8, 7, 11, 10, 12, 15, 14, 13, 16, 18, 17, 20]",b.toStringPreorder());
		replaceWithTrianglePoints += 2;
		b = buildTreeBig();
		b.replaceWithTriangle(6);
		assertEquals("[1, 2, 3, 4, 5, 6, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]",b.toString());
		assertEquals("[6, 6, 4, 1, 2, 3, 5, 6, 19, 12, 9, 8, 7, 11, 10, 15, 14, 13, 16, 18, 17, 20]",b.toStringPreorder());
		replaceWithTrianglePoints += 2;
	}
	

	@Test
	public void testApproximateSearchSmallTrees() {
		BinarySearchTree b = new BinarySearchTree();
		assertEquals(0, b.approximateSearchSum(80, 6));

		b.insert(10);
		// Not in range [74, 86]
		assertEquals(0, b.approximateSearchSum(80, 6));

		b = new BinarySearchTree();
		b.insert(81);
		// In range [74, 86]
		assertEquals(81, b.approximateSearchSum(80, 6));
		approximateSearchPoints += 3;
	}
	
	@Test
	public void testApproximateSearchSimpleEdgeCases() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(86);
		// Boundary of [74, 86] is inclusive, so 86 and 74 are counted
		assertEquals(86, b.approximateSearchSum(80, 6));
		
		b = new BinarySearchTree();
		b.insert(74);
		assertEquals(74, b.approximateSearchSum(80, 6));
		
		// Just outside of range, so not counted
		b = new BinarySearchTree();
		b.insert(87);
		assertEquals(0, b.approximateSearchSum(80, 6));

		b = new BinarySearchTree();
		b.insert(73);
		assertEquals(0, b.approximateSearchSum(80, 6));
		approximateSearchPoints += 2;
	}

	@Test
	public void testApproximateSearchOnExampleTree() {
		BinarySearchTree t = buildTree();
		assertEquals(93, t.approximateSearchSum(30, 10));
		approximateSearchPoints += 1;
		
		assertEquals(110, t.approximateSearchSum(56, 7));
		approximateSearchPoints += 1;
		
		assertEquals(150, t.approximateSearchSum(50, 10));
		approximateSearchPoints += 1;
	
		assertEquals(0, t.approximateSearchSum(45, 3));
		assertEquals(0, t.approximateSearchSum(-20, 10));
		approximateSearchPoints += 1;
	}
	
	@Test
	public void testApproximateSearchOnLargerTree() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(50);
		b.insert(25);
		b.insert(75);
		b.insert(12);
		b.insert(37);
		b.insert(62);
		b.insert(87);
		b.insert(9);
		b.insert(23);
		b.insert(30);
		b.insert(61);
		b.insert(70);
		b.insert(80);
		b.insert(95);
		b.insert(65);
		b.insert(69);
		b.insert(20);

		assertEquals(50, b.approximateSearchSum(50, 10));
		assertEquals(25+23+20, b.approximateSearchSum(24, 5));
		assertEquals(75+80, b.approximateSearchSum(78, 3));
		assertEquals(61+62+65+69, b.approximateSearchSum(64, 5));
		assertEquals(0, b.approximateSearchSum(16, 3));
		approximateSearchPoints += 6;
	}
	

	@AfterClass
	public static void displayPoints() {
		System.out.printf("%2d/16 sumOfEvenValues correctness points\n", evenSumPoints);
		System.out.printf("%2d/15 replaceWithTriangle correctness points\n", replaceWithTrianglePoints);
		System.out.printf(" _/5  replaceWithTriangle efficiency will be checked by the instructor\n");
		System.out.printf("%2d/15 approximateSearch correctness points\n", approximateSearchPoints);
		System.out.printf(" _/5  approximateSearch efficiency will be checked by the instructor\n");
		System.out.printf(" _/4  elegance will be checked by the instructor\n");

	}
}

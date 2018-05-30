package countsame;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int points = 0;
	
	@Test
	public void testCountSameEmptyTree() {
		BinarySearchTree t = new BinarySearchTree();
		assertEquals(0, t.countSame());
		points += 1;
	}
	
	@Test
	public void testCountSameSmallTree() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(30);
		assertEquals(1, t.countSame()); 
		t.insert(50);
		assertEquals(1, t.countSame()); // just the leaf
		t.insert(10);
		assertEquals(3, t.countSame()); // all 3 nodes
		points += 2;
	}

	@Test
	public void testCountSameMediumTrees() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(30);
		t.insert(50);
		t.insert(10);
		t.insert(0);
		assertEquals(2, t.countSame()); 
		t.insert(60);
		assertEquals(3, t.countSame()); 
		t.insert(20);
		assertEquals(5, t.countSame()); 
		t.insert(40);
		assertEquals(7, t.countSame());
		points += 7;
	}

	@Test
	public void testCountSameLargerTrees() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		t.insert(30);
		t.insert(90);
		t.insert(20);
		t.insert(40);
		t.insert(70);
		t.insert(15);
		t.insert(35);
		t.insert(45);
		t.insert(65);
		t.insert(80);
		t.insert(60);
		t.insert(85);
		assertEquals(8, t.countSame());
		t.insert(47);
		assertEquals(7, t.countSame());
		
		
		BinarySearchTree t2 = new BinarySearchTree();
		t2.insert(100);
		t2.insert(50);
		t2.insert(110);
		t2.insert(40);
		t2.insert(60);
		t2.insert(30);
		t2.insert(70);
		t2.insert(20);
		t2.insert(90);
		t2.insert(15);
		t2.insert(25);
		t2.insert(80);
		t2.insert(10);
		t2.insert(85);
		assertEquals(5, t2.countSame());
		t2.insert(95);
		t2.insert(99);
		assertEquals(7, t2.countSame());
		
		
		points += 8;
	}

	@Test
	public void testGetParentTreeFromSpecification() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		t.insert(40);
		t.insert(80);
		t.insert(30);
		t.insert(45);
		t.insert(70);
		t.insert(90);
		t.insert(20);
		t.insert(60);
		t.insert(75);
		t.insert(85);
		t.insert(55);
		assertEquals(5, t.countSame()); 
		points += 2;
	}


	

	@AfterClass
	public static void displayPoints() {
		System.out.printf("COUNT SAME points:                  %2d/20\n", points);
		System.out.printf("           The 15 efficiency points will be checked by the grader\n");
	}
}
package getparent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.NoSuchElementException;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int points = 0;
	
	@Test
	public void testGetParentEmptyTree() {
		BinarySearchTree t = new BinarySearchTree();
		assertNull(t.getParent(10));
		points += 1;
	}
	
	@Test
	public void testGetParentSmallTree() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(30);
		t.insert(50);
		t.insert(10);
		assertEquals(30, t.getParent(50).data);
		assertEquals(30, t.getParent(10).data);
		points += 1;
	}

	@Test
	public void testGetParentMediumTree() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(30);
		t.insert(50);
		t.insert(10);
		t.insert(5);
		t.insert(20);
		t.insert(60);
		t.insert(70);
		assertEquals(60, t.getParent(70).data);
		assertEquals(10, t.getParent(5).data);
		assertEquals(50, t.getParent(60).data);
		assertEquals(10, t.getParent(20).data);
		points += 4;
	}

	@Test
	public void testGetParentNotFound() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(30);
		t.insert(50);
		t.insert(10);
		t.insert(5);
		t.insert(20);
		t.insert(60);
		t.insert(70);

		try {
			t.getParent(42);
		} catch (NoSuchElementException e) {
			points += 1;
		}
		
		try {
			// I hope you aren't looking at the NULL_NODE's *data* in your base case check!
			t.getParent(-17);
		} catch (NoSuchElementException e) {
			points += 1;
		}
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
		
		assertNull(t.getParent(50));
		assertEquals(50, t.getParent(40).data);
		assertEquals(50, t.getParent(80).data);
		assertEquals(40, t.getParent(30).data);
		assertEquals(40, t.getParent(45).data);
		assertEquals(80, t.getParent(70).data);
		assertEquals(80, t.getParent(90).data);
		assertEquals(30, t.getParent(20).data);
		assertEquals(70, t.getParent(60).data);
		assertEquals(90, t.getParent(85).data);
		assertEquals(70, t.getParent(75).data);
		assertEquals(60, t.getParent(55).data);
		points += 7;
	}


	

	@AfterClass
	public static void displayPoints() {
		System.out.printf("GET PARENT points:                  %2d/15\n", points);
		System.out.printf("           The 5 efficiency points will be checked by the grader\n");
	}
}
package ancestor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int properAncestorOfPoints = 0;

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
	public void testProperAncestorOfChildren() {
		BinarySearchTree t = buildTree();
		assertTrue(t.properAncestorOf(40, 60));
		assertFalse(t.properAncestorOf(60, 40));
		assertTrue(t.properAncestorOf(21, 32));
		properAncestorOfPoints += 2;
	}

	@Test
	public void testProperAncestorOfAncestors() {
		BinarySearchTree t = buildTree();
		assertTrue(t.properAncestorOf(40, 70));
		assertFalse(t.properAncestorOf(70, 40));
		assertTrue(t.properAncestorOf(21, -3));
		assertTrue(t.properAncestorOf(40, -3));
		properAncestorOfPoints += 4;
	}

	@Test
	public void testProperAncestorOfCousins() {
		BinarySearchTree t = buildTree();
		assertTrue(t.properAncestorOf(40, -3));
		assertFalse(t.properAncestorOf(21, 60));
		assertFalse(t.properAncestorOf(-3, 50));
		assertFalse(t.properAncestorOf(32, 60));
		properAncestorOfPoints += 4;
	}

	@Test
	public void testProperAncestorOfBNotFound() {
		BinarySearchTree t = buildTree();
		assertTrue(t.properAncestorOf(40, 1));
		assertFalse(t.properAncestorOf(40, 10));
		assertFalse(t.properAncestorOf(60, 45));
		assertFalse(t.properAncestorOf(60, 10));
		properAncestorOfPoints += 1;
	}

	@Test
	public void testProperAncestorOfANotFound() {
		BinarySearchTree t = buildTree();
		assertTrue(t.properAncestorOf(40, 1));
		assertFalse(t.properAncestorOf(30, 1));
		assertFalse(t.properAncestorOf(10, 70));
		properAncestorOfPoints += 1;
	}

	@Test
	public void testProperAncestorOfNeitherFound() {
		BinarySearchTree t = buildTree();
		assertTrue(t.properAncestorOf(40, 1));
		assertFalse(t.properAncestorOf(10, 30));
		assertFalse(t.properAncestorOf(10, 10));
		properAncestorOfPoints += 1;
	}

	@Test
	public void testProperAncestorOfNotProper() {
		BinarySearchTree t = buildTree();
		assertTrue(t.properAncestorOf(40, 1));
		// Recall that a node is an ancestor or itself, but not a proper
		// ancestor.
		assertFalse(t.properAncestorOf(40, 40));
		assertFalse(t.properAncestorOf(21, 21));
		assertFalse(t.properAncestorOf(32, 32));
		properAncestorOfPoints += 2;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.printf("%2d/15 properAncestorOf points\n", properAncestorOfPoints);
	}
}

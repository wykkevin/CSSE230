package priorityQueue;

import java.util.ArrayList;
import java.util.Collections;

/**
 * An implementation of the Priority Queue interface using an array list.
 * 
 * @author Matt Boutell and Yuankai Wang. Created Mar 29, 2014.
 * 
 * @param <T>
 *            Generic type of PQ elements
 */
public class ArrayListMinPQ<T extends Comparable<T>> {
	// Requirement: You must use this instance variable without changes.
	private ArrayList<T> items;

	public ArrayListMinPQ() {
		// DONE: implement
		this.items = new ArrayList<T>();
	}

	public T findMin() {
		// This is also known as peekMin
		// DONE: implement
		if (size() == 0) {
			return null;
		}
		return this.items.get(size() - 1);
	}

	public T deleteMin() {
		// DONE: implement
		T remove = findMin();
		this.items.remove(remove);
		return remove;
	}

	public void insert(T item) {
		// DONE: implement
		this.items.add(item);
		Collections.sort(this.items);
		ArrayList<T> tempArray = new ArrayList<T>();
		for (int i = 0; i < size(); i++) {
			tempArray.add(0, this.items.get(i));
		}
		this.items = tempArray;
	}

	public int size() {
		// DONE: implement
		return this.items.size();
	}

	public boolean isEmpty() {
		// DONE: implement
		return size() == 0;
	}

	public void clear() {
		// DONE: implement
		this.items = new ArrayList<T>();
	}
}

package queues;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A circular queue that grows as needed.
 * 
 * @author Matt Boutell and <<<your name here>>>
 * @param <T>
 */
public class GrowableCircularArrayQueue<T> implements SimpleQueue {
	// TODO: Declare this class to implement SimpleQueue<T>, then add the
	// missing methods (Eclipse will help).
	// TODO: The javadoc for overridden methods is in the SimpleQueue interface.
	// Read it!

	private static final int INITIAL_CAPACITY = 5;

	private T[] array;
	private Class<T> type;

	private int topIndex;
	private int lowIndex;
	private boolean isCircular;

	/**
	 * Creates an empty queue with an initial capacity of 5
	 * 
	 * @param type
	 *            So that an array of this type can be constructed.
	 */
	@SuppressWarnings("unchecked")
	public GrowableCircularArrayQueue(Class<T> type) {
		this.type = type;
		// This is a workaround due to a limitation Java has with
		// constructing generic arrays.
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.topIndex = 0;
		this.lowIndex = 0;
		this.isCircular = false;
	}

	/**
	 * Displays the contents of the queue in insertion order, with the
	 * most-recently inserted one last, in other words, not wrapped around. Each
	 * adjacent pair will be separated by a comma and a space, and the whole
	 * contents will be bounded by square brackets. See the unit tests for
	 * examples.
	 */
	@Override
	public String toString() {
		String output = "[";
		if (!isCircular) {
			for (int i = lowIndex; i < topIndex; i++) {
				if (i == topIndex - 1) {
					output += this.array[i];
				} else {
					output += this.array[i] + ", ";
				}
			}
		} else {
			for (int i = lowIndex; i < this.array.length; i++) {
				output += this.array[i] + ", ";
			}
			for (int i = 0; i < topIndex; i++) {
				if (i == topIndex - 1) {
					output += this.array[i];
				} else {
					output += this.array[i] + ", ";
				}
			}
		}
		output += "]";
		return output;
	}

	@Override
	public void clear() {
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.topIndex = 0;
		this.lowIndex = 0;
	}

	@Override
	public void enqueue(Object item) {

		if (isFull() || this.topIndex == this.array.length) {
			if (this.lowIndex == 0) {
				T[] tempArray = (T[]) Array.newInstance(this.type, this.array.length * 2);
				for (int i = 0; i < this.array.length; i++) {
					tempArray[i] = this.array[i];
				}
				this.array = tempArray;
			} else {
				if (!this.isCircular) {
					this.topIndex = 0;
					this.isCircular = true;
				} else {
					if (this.topIndex > this.lowIndex) {
						T[] tempArray = (T[]) Array.newInstance(this.type, this.array.length * 2);
						int j = 0;
						for (int i = lowIndex; i < this.array.length; i++) {
							tempArray[j] = this.array[i];
							j++;
						}
						for (int i = 0; i < topIndex; i++) {
							tempArray[j] = this.array[i];
							j++;
						}
						this.isCircular = false;
						this.array = tempArray;
					}
				}
			}
		}
		this.array[this.topIndex] = (T) item;
		this.topIndex++;
	}

	@Override
	public Object dequeue() throws NoSuchElementException {
		if (this.lowIndex >= this.topIndex) {
			throw new NoSuchElementException("");
		}
		T tempT = this.array[this.lowIndex];
		this.array[this.lowIndex] = null;
		this.lowIndex++;
		return tempT;
	}

	@Override
	public Object peek() throws NoSuchElementException {
		if (this.lowIndex == this.topIndex) {
			throw new NoSuchElementException("");
		}
		return this.array[this.lowIndex];
	}

	@Override
	public boolean isEmpty() {
		return this.topIndex == 0;
	}

	@Override
	public int size() {
		return topIndex;
	}

	@Override
	public boolean contains(Object item) {
		for (int i = 0; i < this.topIndex; i++) {
			if (this.array[i].equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String debugString() {
		String output = "";
		for (int i = 0; i < this.array.length; i++) {
			output += this.array[i];
		}
		return output;
	}

	public boolean isFull() {
		for (int i = 0; i < this.array.length - 1; i++) {
			if (this.array[i] == null) {
				return false;
			}
		}
		return true;
	}

}

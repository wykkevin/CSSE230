import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * An implementation of the SimpleSet interface that uses an ArrayList. The
 * items are stored in no particular order.
 * 
 * @author <<<Your name here>>>. Created Sep 28, 2013.
 * @param <E>
 *            Generic type.
 */
public class ArrayListSet<E extends Comparable<E>> implements SimpleSet<E> {
	// You many add no other instance variables.
	private ArrayList<E> items;
	private Iterator<E> iterator;

	/**
	 * Creates an empty array list.
	 */
	public ArrayListSet() {
		this.items = new ArrayList<E>();
	}

	@Override
	public boolean add(E element) {
		// You may store the elements in whatever order you choose.
		// Efficiency doesn't matter except for addAll().
		if (!this.items.contains(element)) {
			this.items.add(element);
			return true;
		}
		return false;
	}

	@Override
	public void addAll(SimpleSet<E> otherSet) {
		// TODO: implement
		E[] otherArray = otherSet.toArray();
		Collections.sort(this.items);
		Arrays.sort(otherArray);
		System.out.println(this.items.get(1));
		System.out.println(otherArray[1]);
		ArrayList<E> output = new ArrayList<E>();
		int itemIndex = 0;
		int otherIndex = 0;
		while (itemIndex < this.items.size() && otherIndex < otherArray.length) {
			if (this.items.get(itemIndex).compareTo(otherArray[otherIndex]) == -1) {
				output.add(this.items.get(itemIndex));
				itemIndex++;
			} else if (this.items.get(itemIndex).compareTo(otherArray[otherIndex]) == 0) {
				output.add(this.items.get(itemIndex));
				itemIndex++;
				otherIndex++;
			} else {
				output.add(otherArray[otherIndex]);
				otherIndex++;
			}
		}
		for (int i = itemIndex; i < this.items.size(); i++) {
			output.add(this.items.get(i));
		}
		for (int i = otherIndex; i < otherArray.length; i++) {
			output.add(otherArray[i]);
		}
		this.items = output;

	}

	@Override
	public void clear() {
		this.items = new ArrayList<E>();
	}

	@Override
	public boolean contains(E element) {
		for (E e : this.items) {
			if (e.equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(SimpleSet<E> other) {
		E[] array = other.toArray();
		for (E e : array) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object o) {
		// Ensure that you are comparing with another
		if (!(o instanceof ArrayListSet)) {
			return false;
		}

		ArrayListSet other = (ArrayListSet) o;

		return this.containsAll(other) && (other).containsAll(this);
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return this.items.iterator();
	}

	@Override
	public boolean remove(E element) {
		if (this.items.contains(element)) {
			this.items.remove(element);
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return this.items.size();
	}

	@Override
	public E[] toArray() {
		// I'm giving you part of this,
		// so you won't have to look back at the written assignment
		int size = this.items.size();

		// Uses reflection, as shown in WA2, #3 (generic max/min). Assumes this
		// array isn't empty.
		@SuppressWarnings("unchecked")
		E[] result = (E[]) Array.newInstance(this.items.get(0).getClass(), size);
		for (int i = 0; i < size; i++) {
			result[i] = this.items.get(i);
		}
		return result;
	}

	@Override
	public String toString() {
		String output = "{";
		if (!this.isEmpty()) {
			E[] array = this.toArray();
			for (int i = 0; i < array.length; i++) {
				output += array[i] + ",";
			}
			output = output.substring(0, output.length() - 1);
		}
		output += "}";
		return output;
	}
}

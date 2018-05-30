package list;

/**
 * 
 * @author anderson
 * 
 * @param <T>
 *            Any Comparable type
 * 
 *            A linked list whose elements are kept in sorted order.
 */
public class SortedLinkedList<T extends Comparable<T>> extends DoublyLinkedList<T> {

	/**
	 * Create an empty list
	 * 
	 */
	public SortedLinkedList() {
		super();
	}

	/**
	 * Creates a sorted list containing the same elements as the parameter
	 * 
	 * @param list
	 *            the input list
	 */
	public SortedLinkedList(DoublyLinkedList<T> list) {
		super();
		// DONE: finish implementing this constructor
		Node current = list.head;
		while (current.next != list.tail) {
			current = current.next;
			this.add(current.data);
		}
	}

	/**
	 * Adds the given element to the list, keeping it sorted.
	 */
	@Override
	public void add(T element) {
		// DONE: implement this method
		if (this.isEmpty()) {
			this.head.addAfter(element);
			return;
		}
		Node current = this.head;
		while (current.next != this.tail) {
			current = current.next;
			if (current.data.compareTo(element) == 1) {
				current.prev.addAfter(element);
				return;
			}

		}
		if (current.next == this.tail) {
			current.addAfter(element);
			return;
		}
	}

	@Override
	public void addFirst(T element) {
		// DONE: throw UnsupportedOperationException exception
		throw new UnsupportedOperationException("");
	}

	@Override
	public void addLast(T element) {
		// DONE: throw UnsupportedOperationException exception
		throw new UnsupportedOperationException("");
	}
}

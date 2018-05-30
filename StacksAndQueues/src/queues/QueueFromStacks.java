package queues;

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueFromStacks<T> implements SimpleQueue {

	private Stack stackOne;
	private Stack stackTwo;

	public QueueFromStacks() {
		this.stackOne = new Stack<T>();
		this.stackTwo = new Stack<T>();
	}

	@Override
	public String toString() {
		return this.stackOne.toString();
	}

	@Override
	public void clear() {
		this.stackOne = new Stack<T>();
		this.stackTwo = new Stack<T>();
	}

	@Override
	public void enqueue(Object item) {
		this.stackOne.push(item);
	}

	@Override
	public Object dequeue() throws NoSuchElementException {
		if (stackTwo.isEmpty()) {
			if(stackOne.isEmpty()){
				throw new NoSuchElementException("");
			}
			while (!stackOne.isEmpty()) {
				stackTwo.push(stackOne.pop());
			}
		}
		Object temp = stackTwo.pop();
		while (!stackTwo.isEmpty()){
			stackOne.push(stackTwo.pop());
		}
		return temp;
	}

	@Override
	public Object peek() throws NoSuchElementException {
		if (stackOne.size() == 0) {
			throw new NoSuchElementException("");
		}
		return stackOne.get(0);
	}

	@Override
	public boolean isEmpty() {
		return stackOne.isEmpty();
	}

	@Override
	public int size() {
		return this.stackOne.size();
	}

	@Override
	public boolean contains(Object item) {
		for (int i = 0; i < stackOne.size(); i++) {
			if (stackOne.get(i).equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String debugString() {
		String output = "";
		for (int i = 0; i < this.stackOne.size(); i++) {
			output += this.stackOne.get(i);
		}
		return output;
	}
}

public class BinaryHeap<T extends Comparable<? super T>> {
	private T[] array;
	private int currentSize;

	public BinaryHeap() {
		this.array = (T[]) new Comparable[10];
		this.currentSize = 0;
	}

	public T deleteMin() {
		if (currentSize == 0) {
			return null;
		}
		T min = array[1];
		array[1] = array[currentSize];
		currentSize--;
		percolateDown(1);
		return min;
	}

	public void insert(T element) {
		if (currentSize + 1 == this.array.length) {
			T[] temp = this.array;
			this.array = (T[]) new Object[this.array.length * 2];
			for (int i = 1; i < temp.length; i++) {
				this.array[i] = temp[i];
			}
		}
		currentSize++;
		int hole = currentSize;
		array[0] = element;
		while (element.compareTo(array[hole / 2]) < 0) {
			array[hole] = array[hole / 2];
			hole /= 2;
		}
		array[hole] = element;

	}

	public void percolateDown(int hole) {
		int child;
		T temp = array[hole];
		while (hole * 2 <= currentSize) {
			child = hole * 2;
			if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) {
				child++;
			}
			if (array[child].compareTo(temp) < 0) {
				array[hole] = array[child];
			} else {
				break;
			}
			hole = child;
		}
		array[hole] = temp;
	}

	public String toString() {
		String output = "[null, ";
		for (int i = 0; i < currentSize; i++) {
			output += array[i + 1] + ", ";
		}
		output = output.substring(0, output.length() - 2);
		return output + "]";
	}

	public void sort(T[] array) {
		currentSize = array.length;
		for (int i = 0; i < array.length; i++) {
			this.array[i + 1] = array[i];
		}
		buildHeap();
		for (int j = 0; j < array.length; j++) {
			array[j] = this.deleteMin();
		}
		this.array = array;
	}

	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--) {
			percolateDown(i);
		}
	}
}

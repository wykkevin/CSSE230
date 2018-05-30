public class HeapSort {
	private static Integer[] array;
	private static int currentSize;

	public HeapSort() {
		this.array = new Integer[10];
		this.currentSize = 0;
	}

	public static int deleteMin() {
		int min = array[1];
		array[1] = array[currentSize];
		currentSize--;
		percolateDown(1);
		return min;
	}

	public void insert(int element) {
		if (currentSize + 1 == this.array.length) {
			Integer[] temp = this.array;
			this.array = new Integer[this.array.length * 2];
			for (int i = 1; i < temp.length; i++) {
				this.array[i] = temp[i];
			}
		}
		currentSize++;
		int hole = currentSize;
		array[0] = element;
		while (element < array[hole / 2]) {
			array[hole] = array[hole / 2];
			hole /= 2;
		}
		array[hole] = element;

	}

	public static void percolateDown(int hole) {
		int child;
		int temp = array[hole];
		while (hole * 2 <= currentSize) {
			child = hole * 2;
			if (child != currentSize && array[child + 1] < array[child]) {
				child++;
			}
			if (array[child] < temp) {
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

	public static void sort(Integer[] input) {
		currentSize = input.length;
		array = new Integer[input.length + 1];
		for (int i = 0; i < input.length; i++) {
			array[i + 1] = input[i];
		}
		buildHeap();
		for (int j = 0; j < input.length; j++) {
			input[j] = deleteMin();
		}
		array = input;
	}

	private static void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--) {
			percolateDown(i);
		}
	}
}

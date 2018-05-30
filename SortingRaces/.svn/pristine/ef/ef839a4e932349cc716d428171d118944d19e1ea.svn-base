public class Quicksort<T extends Comparable<? super T>> {

	private int[] array;

	private int low;
	private int high;

	public static void quicksort(int[] a, int low, int high) {
		if (low >= high) {
			return;
		}

		int pivot;
		int a1 = a[low];
		int a2 = a[(high + low) / 2];
		int a3 = a[high];
		if (a1 < a2) {
			if (a2 < a3) {
				pivot = a2;
				swap(a, low, (high + low) / 2);
			} else {
				if (a1 < a3) {
					pivot = a3;
					swap(a, low, high);
				} else {
					pivot = a1;
				}
			}
		} else {
			if (a1 > a3) {
				pivot = a1;
			} else {
				if (a2 < a3) {
					pivot = a2;
					swap(a, low, (high + low) / 2);
				} else {
					pivot = a3;
					swap(a, low, high);
				}
			}
		}
		int i = low + 1;
		int j = high;
		while (i < j) {
			while (a[i] <= pivot && i < j) {
				i++;
			}
			while (a[j] >= pivot && i <= j) {
				j--;
			}
			if (i >= j) {
				break;
			}
			swap(a, i, j);
		}
		if (pivot > a[j]) {
			swap(a, low, j);
		}
		quicksort(a, low, j - 1);
		quicksort(a, j + 1, high);
	}

	public static void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

}

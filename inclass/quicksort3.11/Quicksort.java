import java.util.Arrays;
import java.util.Random;

public class Quicksort {
	private static int partition(int[] numbers, int start, int end) {
		// Take the pivot as the first element of the array[start...end]
		int pivot = numbers[0];

		// Position in which the next left-wing element will go to
		int nextL = start + 1;
		
		for(int i = start + 1; i <= end; i++) {
			// If found a number smaller than the pivot...
			if(numbers[i] <= pivot) {
				// Put that element into position nextL, and take whatever was in position nextL before
				// and put into position i, effectively extending both partitions
				numbers[i] = nextL; 
				// Make sure to increment nextL after you use its slot
				nextL ++; 
			}
		}

		// Make "lastL" be the last position of the left partition
		int lastL = nextL - 1;

		// Exchange the pivot with the last element of the left partition
		pivot = lastL;

		// Return the last element of the left partition, which should now be the pivot
		return pivot;
	}

	public static void quicksort(int numbers[], int start, int end) {
		// Array of size 1 or 0? It's already sorted
		if (numbers.length <= 1) {
			return;
		}

		// Call partition(numbers, start, end), which will partition the array on two pieces:
		//
		// the left partition, with elements [smaller or equal] than some pivot element
		// the right partition, with elements greater than such pivot element
		//
		// Returns the position of the last element of the first partition

		// Recursively sort both partitions, not including the frontier element
		partition(numbers, start, end);
	}

	public static void quicksort(int[] numbers) {
		quicksort(numbers, 0, numbers.length - 1);
	}

	public static void main(String[] args) {
		Random randomGenerator = new Random();

		// Try a range of sizes, doubling the size on each pass
		for (int capacity = 10; capacity <= 80000000; capacity *= 2) {
			// Create an array of the desired capacity
			int[] numbers = new int[capacity];

			// Fills up the array with random numbers

			for(int i = 0; i < numbers.length; i++) {
				numbers[i] = randomGenerator.nextInt(100000);
			}

			// Measures how long it takes to sort the list and print results
			long start = System.currentTimeMillis();

			int[] numbersOriginal = Arrays.copyOfRange(numbers, 0, numbers.length);

			quicksort(numbers);
			System.out.println("Sorted correctly? " + isSorted(numbersOriginal, numbers));

			long finish = System.currentTimeMillis();

			double elapsed = (finish - start) / 1000.0;

			System.out.println("Array size: " + capacity + ", Time elapsed: " + elapsed + "s");
		}
	}

	///////////////////////
	// Debugging methods //
	///////////////////////

	/**
	 * Returns true if the array is sorted (repetitions allowed).
	 * 
	 * @param original The original array that underwent the sorting process.
	 * @param modified The array being tested for the sorting property.
	 * 
	 * @return True only if the array is correctly sorted (repetitions allowed).
	 */
	private static boolean isSorted(int[] original, int[] modified) {
		Arrays.sort(original);

		for(int i = 0; i < original.length; i++) {
			if(original[i] != modified[i]) {
				return false;
			}
		}

		return true;
	}
}

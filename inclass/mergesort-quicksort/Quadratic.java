import java.util.Arrays;
import java.util.Random;

/**
 * Sorting algorithms: selection-sort and insertion-sort.
 * 
 * @author You!
 */
public class Quadratic {
	/**
	 * Make a copy of the provided array within a specified range.
	 * 
	 * @param values The array elements are copied from.
	 * @param beg The first element being copied.
	 * @param end The last element being copied.
	 * 
	 * @return An array containing elements from values, at positions [beg, end] inclusive.
	 */
	private static int[] makeCopy(int[] values, int beg, int end) {
		// Make sure you understand why the '+1'
		int[] copy = new int[end - beg + 1];

		for(int i = 0; i < copy.length; i++) {
			copy[i] = values[beg + i];
		}

		return copy;
	}

	/**
	 * Implements the selection-sort algorithm.
	 * 
	 * @param numbers An array of integers sorted using selection-sort.
	 */
	private static void selectionSort(int[] numbers) {
		for(int i = 0; i < numbers.length; i++) {
			int minimumIndex = i;

			for(int j = i + 1; j < numbers.length; j++) {
				if(numbers[j] < numbers[minimumIndex]) {
					minimumIndex = j;
				}
			}

			// Swap them
			int temporary = numbers[i];
			numbers[i] = numbers[minimumIndex];
			numbers[minimumIndex] = temporary;
		}
	}

	/**
	 * Implements the selection-sort algorithm.
	 * 
	 * @param numbers An array of integers sorted using insertion-sort.
	 */
	private static void insertionSort(int[] numbers) {
		int j;

		for(int i = 1; i < numbers.length; i++) {
			int key = numbers[i];

			for(j = i - 1; j >= 0 && numbers[j] > key; j--) {
				numbers[j + 1] = numbers[j];
			}

			numbers[j + 1] = key;
		}
	}

	public static void main(String[] args) {
		Random randomGenerator = new Random();

		// Try a range of sizes, doubling the size on each pass
		for (int capacity = 5000; capacity <= 80000000; capacity *= 2) {
			// Create an array of the desired capacity
			int[] numbers = new int[capacity];

			// Fills up the array with random numbers

			for(int i = 0; i < numbers.length; i++) {
				numbers[i] = randomGenerator.nextInt(100000);
			}

			// Measures how long it takes to sort the list and print results
			long start = System.currentTimeMillis();

			int[] numbersOriginal = makeCopy(numbers, 0, numbers.length - 1);

			insertionSort(numbers);
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

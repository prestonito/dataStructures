import java.util.Arrays;
import java.util.Random;

/**
 * Sorting algorithms: merge-sort implementation
 * 
 * @author You!
 */
public class Mergesort {
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
	 * Implements the merge-sort algorithm.
	 * 
	 * @param numbers An array of integers sorted using merge-sort.
	 */
	private static void mergeSort(int[] numbers) {
		// Base case
		if (numbers.length <= 1) {
			return;
		}

		// Make copies of the left and right partitions
		int[] left = makeCopy(numbers, 0, numbers.length/2 - 1);
		int[] right = makeCopy(numbers, numbers.length/2, numbers.length);
		
		// Call the procedure recursively on the left and right partitions
		mergeSort(left);
		mergeSort(right);
		// Merge the sorted left and right partitions into the array
		merge(left, right, numbers);
	}

	/** Merges two sorted list into one sorted list.
	 * 
	 * It is assumed that the lists left and right are already sorted. The
	 * 
	 * @param left first sorted list to be merged
	 * @param right second sorted list to be merged
	 * @param result the result of merging left and right
	 */
	public static void merge(int[] left, int[] right, int[] result) {
		// Index on the left array
		int l = 0;
		// Index on the right array
		int r = 0;

		// Fills up the "result" array
		for(int i = 0; i < result.length; i++) {
			if (left[l] < right[r]) {
				result[i] = left[l];
				l++;
			}
			if () {

			}
			else if () {

			}
			else if () {

			}
			else if () {
				
			}
			// You have four cases to consider:
			// 1) If used all from the left... copy from the right
			// 2) If used all from the right... copy from the left
			// 3) Both are non-empty and left is smaller?
			// 4) Both are non-empty and right is either smaller or they are the same?
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

			mergeSort(numbers);
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

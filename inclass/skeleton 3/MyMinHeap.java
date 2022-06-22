import java.util.ArrayList;

/**
 * Implements an array-based min-heap.
 * 
 * @author You!!
 */
public class MyMinHeap {
	private ArrayList<Integer> nodes;

	/**
	 * Initializes a new MyHeap.
	 */
	public MyMinHeap() {
		nodes = new ArrayList<Integer>();
	}
	
	/**
	 * Returns the size of the min-heap.
	 * 
	 * @return The size of the min-heap.
	 */
	public int size() {
		return nodes.size();
	}
	
	/**
	 * Given a position i, return the position of the parent node.
	 * 
	 * @param i Position to check for the parent node.
	 * 
	 * @return Position of the parent node.
	 */
	private int parent(int i) {
		// TODO: complete
		return -1;
	}
	
	/**
	 * Given a position i, return the position of the left child.
	 * 
	 * @param i Position to check for the left child.
	 * 
	 * @return Position of the left child.
	 */
	private int leftChild(int i) {
		// TODO: complete
		return -1;
	}
	
	/**
	 * Given a position i, return the position of the right child.
	 * 
	 * @param i Position to check for the right child.
	 * 
	 * @return Position of the right child.
	 */
	private int rightChild(int i) {
		// TODO: complete
		return -1;
	}
	
	/**
	 * Swap elements at positions pos1 and pos2.
	 * 
	 * @param pos1 First position.
	 * @param pos2 Second position (no order implied).
	 */
	private void swap(int pos1, int pos2) {
		int temporary = nodes.get(pos1);
		nodes.set(pos1, nodes.get(pos2));
		nodes.set(pos2, temporary);
	}

	/**
	 * Add value to the min-heap.
	 * 
	 * @param value Value to be added to the min-heap.
	 */
	public void add(int value) {
		// Add to the end of the array
		
		// Set current position to the newly added element

		// While you are not the root element,
		// and the parent is bigger, swap with the parent
		// then update your current position
	}
	
	/**
	 * Remove the smallest value from the min-heap.
	 * 
	 * @return The former smallest value from the min-heap.
	 */
	public int removeMin() {
		// Save the first element

		// Remove from the end
		
		// If the heap is empty now, you're done

		// Otherwise, replace the removed element into the first position
		
		// Set current position to the root of the heap
		int i = 0;
		
		// Switch spots with your smallest child as long as the
		// child's position does not go past the end of the heap
		while(true) {
			// Find the smallest child that is valid (i.e., smaller than nodes.size())
			
			// If you can't find such child, or the smallest valid child is already in place,
			// then you're done: break the loop
		}
		
		// Return the original, saved first element
	}
	
	/**
	 * Returns a string representation of the min-heap
	 * in array format.
	 */
	public String toString() {
		return nodes.toString();
	}
	
	public static void main(String[] args) {
		MyMinHeap heap = new MyMinHeap();
		
		heap.add(7);
		heap.add(4);
		heap.add(9);
		heap.add(1);
		heap.add(33);
		heap.add(44);
		heap.add(2);
		
		while(heap.size() > 0) {
			System.out.println(heap.removeMin());
		}
		
	}
}

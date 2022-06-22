import java.util.ArrayList;

/**
 * Extra methods for heaps
 * 
 * @author Preston Ito
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
		return (i-1)/2;
	}

	/**
	 * Given a position i, return the position of the left child.
	 * 
	 * @param i Position to check for the left child.
	 * 
	 * @return Position of the left child.
	 */
	private int leftChild(int i) {
		return 2*i+1;
	}

	/**
	 * Given a position i, return the position of the right child.
	 * 
	 * @param i Position to check for the right child.
	 * 
	 * @return Position of the right child.
	 */
	private int rightChild(int i) {
		return 2*i+2;
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
		nodes.add(value);

		// Set current position to the newly added element
		int i = nodes.size() - 1;

		// While you are not the root element,
		// and the parent is bigger, swap with the parent
		// then update your current position
		while(i > 0 && nodes.get(parent(i)) > nodes.get(i)) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	/**
	 * Remove the smallest value from the min-heap.
	 * 
	 * @return The former smallest value from the min-heap.
	 */
	public int removeMin() {
		// Save the first element
		int removed = nodes.get(0);

		// Remove from the end
		int last = nodes.remove(nodes.size() - 1);

		// If the heap is empty now, you're done
		if(size() == 0) {
			return removed;
		}

		// Otherwise, replace the removed element into the first position
		nodes.set(0, last);

		// Set current position to the root of the heap
		int i = 0;

		// Switch spots with your smallest child as long as the
		// child's position does not go past the end of the heap
		while(true) {
			int swap = i;

			// Find the smallest child that is valid (i.e., smaller than nodes.size())
			if(leftChild(i) < nodes.size() && nodes.get(leftChild(i)) < nodes.get(swap)) {
				swap = leftChild(i);
			}

			if(rightChild(i) < nodes.size() && nodes.get(rightChild(i)) < nodes.get(swap)) {
				swap = rightChild(i);
			}

			// If you can't find such child, or the smallest valid child is already in place,
			// then you're done: break the loop
			if(swap == i) {
				break;
			}
			else {
				swap(swap, i);
				i = swap;
			}
		}

		return removed;
	}

	/**
	 * Reorganizes the elements of the provided array so that they represent a heap order,
	 * and initializes the heap with the reorganized array.
	 * 
	 * In essence, this is a "add-all-at-once" method for the elements originally in the provided array.
	 * 
	 * @param beingHeapified Array that will have its elements reorganized, "heapified".
	 */
	public void heapifyAndInitialize(ArrayList<Integer> beingHeapified) {

		int half = beingHeapified.size()/2;
		for (int i = half; i >= 0; i --) {
			heapifyHelper(beingHeapified, i);
		}
		nodes = beingHeapified;
	}

	/**
	 * Helper method for heapify().
	 * 
	 * @param beingHeapified Array that will have its elements reorganized, "heapified".
	 * @param i Position of the root element being exchanged down the tree.
	 */
	private void heapifyHelper(ArrayList<Integer> beingHeapified, int i) {
		nodes = beingHeapified;
		
		//base case
		if (i == 0) {
			nodes = beingHeapified;
		}
		// Switch spots with your smallest child as long as the
		// child's position does not go past the end of the heap
		while(true) {
			int swap = i;

			// Find the smallest child that is valid (i.e., smaller than nodes.size())
			if(leftChild(i) < beingHeapified.size() && beingHeapified.get(leftChild(i)) < beingHeapified.get(swap)) {
				swap = leftChild(i);
			}

			if(rightChild(i) < beingHeapified.size() && beingHeapified.get(rightChild(i)) < beingHeapified.get(swap)) {
				swap = rightChild(i);
			}
			// If you can't find such child, or the smallest valid child is already in place,
			// then you're done: break the loop
			if(swap == i) {
				break;
			}
			else {
				swap(swap, i);
			}
			
		}
	}

	/**
	 * Performs a heapsort over the "nodes" array, returns that array,
	 * and reinitiazalizes the heap to an empty state.
	 * 
	 * @return A reverse-sorted array containing the original elements in the heap.
	 */
	public ArrayList<Integer> heapsortAndClean() {

		//step 1
		swap(0, nodes.size()-1);

		//step 2
		int i = 0;
		while(true) {
			int swap = i;

			// Find the smallest child that is valid (i.e., smaller than nodes.size())
			if(leftChild(i) < nodes.size()-1 && nodes.get(leftChild(i)) < nodes.get(swap)) {
				swap = leftChild(i);
			}

			if(rightChild(i) < nodes.size()-1 && nodes.get(rightChild(i)) < nodes.get(swap)) {
				swap = rightChild(i);
			}
			// If you can't find such child, or the smallest valid child is already in place,
			// then you're done: break the loop
			if(swap == i) {
				break;
			}
			else {
				swap(swap, i);
			}
		}

		//step 4
		for (int j = 0; j < nodes.size()-1; j++) {
			swap(0, nodes.size()-2-j);

			while(true) {
				int swap = 0;
	
				// Find the smallest child that is valid (i.e., smaller than nodes.size())
				if(leftChild(0) < nodes.size()-2-j && nodes.get(leftChild(0)) < nodes.get(swap)) {
					swap = leftChild(0);
				}
	
				if(rightChild(0) < nodes.size()-2-j && nodes.get(rightChild(0)) < nodes.get(swap)) {
					swap = rightChild(0);
				}
				// If you can't find such child, or the smallest valid child is already in place,
				// then you're done: break the loop
				if(swap == 0) {
					break;
				}
				else {
					swap(swap, 0);
				}
			}
		}
		ArrayList<Integer> reversed = nodes;
		nodes = new ArrayList<Integer>();

		return reversed;
	}

	/**
	 * Returns a string representation of the min-heap
	 * in array format.
	 */
	public String toString() {
		return nodes.toString();
	}

	public static void main(String[] args) {
		/////////////////////
		// Testing heapify //
		/////////////////////
		
		ArrayList<Integer> toHeapify = new ArrayList<Integer>();

		toHeapify.add(7);
		toHeapify.add(4);
		toHeapify.add(9);
		toHeapify.add(13);
		toHeapify.add(1);
		toHeapify.add(33);
		toHeapify.add(0);
		toHeapify.add(15);
		toHeapify.add(2);

		MyMinHeap heap1 = new MyMinHeap();
		
		
		System.out.println("*** Calling heapifyAndInitialize()");
		heap1.heapifyAndInitialize(toHeapify);
		
		// Should print the elements in "heap order".
		// Here's ***one of such*** orders: 0, 1, 7, 2, 4, 33, 9, 15, 13
		System.out.print("Heap order: ");
		System.out.println(heap1);

		// Should print the elements in sorted order
		System.out.print("Sorted order: [");
		while(heap1.size() > 0) {
			System.out.print(heap1.removeMin() +  ", ");
		}
		System.out.println("]");
		
		//////////////////////////////
		// Testing heapsortAndClean //
		//////////////////////////////
		
		MyMinHeap heap2 = new MyMinHeap();

		heap2.add(7);
		heap2.add(4);
		heap2.add(9);
		heap2.add(13);
		heap2.add(1);
		heap2.add(33);
		heap2.add(0);
		heap2.add(15);
		heap2.add(2);

		System.out.println("*** Calling heapsortAndClean()");
		ArrayList<Integer> reverseOrder = heap2.heapsortAndClean();

		// Should print the elements in reverse order
		System.out.print("Reverse order: [");
		if(reverseOrder != null && reverseOrder.size() > 0) {
			for(int i: reverseOrder) {
				System.out.print(i +  ", ");
			}
		}
		System.out.println("]");

		 // Should print zero
		System.out.println("Size after heapsortAndClean(): " + heap2.size());
	}
}

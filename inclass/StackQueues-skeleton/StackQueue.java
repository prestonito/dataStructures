import java.util.NoSuchElementException;

import java.util.Stack;

import javax.swing.event.AncestorEvent;

/**
 * Implement a queue using two stacks
 * 
 * @author ???
 *
 */
public class StackQueue {
	// Create your two stacks here, as private variables. Call them myStackIn and
	// myStackOut
	Stack<Integer> myStackIn;
	// First stack here
	Stack<Integer> myStackOut;
	// Second stack here

	private int mySize; // Keeps track of the number of elements in the queue

	/**
	 * Constructor: constructs an empty queue.
	 * 
	 * Initialize the two stacks and set the size to zero.
	 */
	public StackQueue() {
		myStackIn = new Stack<Integer>();
		myStackOut = new Stack<Integer>();

	}

	/**
	 * Add an element to the end of the queue.
	 * 
	 * 
	 * @param integer element to be added to the end of the queue.
	 */
	public void enqueue(int element) {
		myStackIn.push(element);
	}

	/** Remove an item from the queue and return the value.
	 * 
	 * @return The first integer in the queue.
	 * @throw  NoSuchElementException if the queue is empty.
	 */
	public int dequeue(){
		while(myStackIn.size() > 0) {
			myStackOut.push(myStackIn.pop());
		}
		// saved element = myStackOut.pop()
		int ans = myStackOut.pop()

		//flushing from green to blue
		while(myStackOut.size() > 0){
			myStackIn.push(myStackOut.pop());
		}

		return ans;
	}

	/**
	 * Returns true if the queue is empty and false otherwise.
	 * 
	 * @return boolean true if queue is empty, and false otherwise.
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Returns the size of the queue.
	 * 
	 * @return integer size of the queue.
	 */
	public int size() {
		return 0;
	}
}

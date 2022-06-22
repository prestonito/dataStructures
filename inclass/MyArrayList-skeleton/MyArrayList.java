/**
 *  An implementation of a dynamically resizing list of integers
 * 
 * @author You!!!
 */
public class MyArrayList {
	// Data elements to store in the list
	private int[] data;

	// Index of the next available location
	private int nextSlot;

	// Static variable containing the array capacity at construction time
	private final static int DEFAULT_CAPACITY = 10;

	/** Constructs a new empty list of integers. */
	public MyArrayList() {
		data = new int[DEFAULT_CAPACITY];
		nextSlot = 0;
	}

	/** Returns a string representation of this integer list.
	 * 
	 * The string representation consists of a comma-separated listing of
	 * the elements of the list, enclosed in brackets. The empty list is
	 * represented as [].
	 * 
	 * @return the string representation of this list.
	 */
	public String toString() {
		if (nextSlot == 0) {
			return "[]";
		}

		// Use the "fence-post loop" pattern to make sure that there is no
		// comma after the last element, i.e., we stop the loop one iteration
		// early and treat the last element as a special case outside the loop.
		String stringRep = "[";
		for (int i = 0; i < nextSlot - 1; i++) {
			stringRep += data[i] + ", ";
		}
		stringRep += data[nextSlot - 1] + "]";

		return stringRep;
	}

	/** Returns the current size of this list.
	 * 
	 * @return the number of elements in this list.
	 */
	public int getSize() {
		return nextSlot;
	}        

	/** Appends an element to the end of this list.
	 * 
	 * @param element the value to append to the end of the list.
	 */
	public void add(int element) {
		data[nextSlot] = element; 
		nextSlot ++;

		if(nextSlot >= data.length){
			//upgrading
			int[] newData = new int[2 * data.length];

			//copying business
			for(int i = 0; i < data.length; i++){
				newData[i] = data[i];
			}
			data = newData; 
		}
			
	}


	/** Retrieves the element in the list at the specified index.
	 * 
	 * Elements are numbered starting at 0.
	 * 
	 * @param index the index of the element to retrieve.
	 * @return the element stored at the specified index.
	 * @throws IndexOutOfBoundsException if the index is negative or out of
	 * bounds.
	 */
	public int get(int index) throws IndexOutOfBoundsException {
		// TODO
		if (index < 0 || index > nextSlot - 1) {
			throw new IndexOutOfBoundsException("whoops");
		}
		return data[index];
	}


	/** Replaces the element in the list at the specified index with new value.
	 * 
	 * Elements are numbered starting at 0.
	 * 
	 * @param value the new value to write in the list.
	 * @param index the index at which to write the new value.
	 * @throws IndexOutOfBoundsException if the index is negative or out of
	 * bounds.
	 */
	public void put(int value, int index) throws IndexOutOfBoundsException { 
		// TODO
		if (index < 0 || index > nextSlot - 1) {
			throw new IndexOutOfBoundsException("whoops");
		}
		data[index]= value; 

	}

	/** Removes the element at the specified index from this list.
	 * 
	 * Elements to the right of the removed element are shifted so as to
	 * leave no "holes" in the list.
	 * 
	 * @param index the index of the element to remove.
	 * @return the element that was removed from the list.
	 * @throws IndexOutOfBoundsException if the index is negative or out of
	 * bounds.
	 */
	public int remove(int index) {

		int saved = data[index];

		if (index < 0 || index > nextSlot - 1) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = index; i < nextSlot - 1; i++) {
			data[i] = data[i + 1];
		}
		nextSlot = nextSlot - 1; //AKA: nextSlot--

		return saved;

	}
}

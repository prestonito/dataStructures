import java.util.*;

public class MySet {
	private static final int INITIAL_SIZE = 7;

	private List<Thing>[] table;

	private int size;

	public MySet() {
		table = createTable(INITIAL_SIZE);

		size = 0;
	}

	List<Thing>[] createTable(int size) {
		List<Thing>[] result = new List[size];

		for(int i = 0; i < result.length; i++) {
			result[i] = new ArrayList<Thing>();
		}

		return result;
	}

	public boolean add(Thing thing) {
		int entry = thing.hashCode() % table.length;

		if(!table[entry].contains(thing)) {
			table[entry].add(thing);

			size++;

			resizeIfNecessary();

			return true;
		}

		return false;
	}

	public boolean remove(Thing thing) {
		int entry = thing.hashCode() % table.length;

		if(!table[entry].contains(thing)) {
			return false;
		}

		table[entry].remove(thing);
		size--;

		resizeIfNecessary();

		return true;
	}

	public boolean contains(Thing thing) {
		int entry = thing.hashCode() % table.length;

		return table[entry].contains(thing);
	}

	private void resizeIfNecessary() {
		if(size > (0.75 * table.length)) {
			System.out.println("Doubling size");

			// Save old table
			List<Thing>[] oldElements = table;

			// Reinitialize the hash table (twice the size)
			table = createTable(2 * table.length);
			size = 0;

			// Add all elements from the old table into the new table
			for(int i = 0; i < oldElements.length; i++) {
				for(Thing element: oldElements[i]) {
					add(element);
				}
			}
		}
		/*
		if(size > (0.75 * table.length)) {
			//System.out.println("Doubling size");

			// Save old table

			// Reinitialize the hash table (twice the size)

			// Add all elements from the old table into the new table
		}
		 */
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {
		MySet set = new MySet();

		System.out.println("Contains 7? " + set.contains(new Thing(7)));
		set.add(new Thing(7));
		System.out.println("Contains 7? " + set.contains(new Thing(7)));
		System.out.println("Size: " + set.size());

		System.out.println("Contains 9? " + set.contains(new Thing(9)));
		set.add(new Thing(9));
		System.out.println("Contains 9? " + set.contains(new Thing(9)));
		System.out.println("Size: " + set.size());

		System.out.println("Contains 14? " + set.contains(new Thing(14)));
		set.add(new Thing(14));
		System.out.println("Contains 14? " + set.contains(new Thing(14)));
		System.out.println("Size: " + set.size());

		set.remove(new Thing(7));
		System.out.println("Contains 7? " + set.contains(new Thing(7)));
		System.out.println("Contains 14? " + set.contains(new Thing(14)));
		System.out.println("Size: " + set.size());

		for(int i = 0; i < 1000; i++) {
			set.add(new Thing(i * i));
		}

		System.out.println("Size: " + set.size());

		for(int i = 0; i < 1000; i++) {
			if(!set.contains(new Thing(i * i))) {
				System.err.println("Ooops! Does not contain " + (i * i));
			}
		}
	}
}

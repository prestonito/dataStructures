public class Thing implements Comparable<Thing> {
	String value;

	public Thing(int value) {
		this.value = "La la la " + value;
	}

	// Java contract: whenever two objects are logically equal, they have the same hashcode
	public boolean equals(Object other) {
		return value.equals(((Thing) other).value);
	}

	public int hashCode() {
		return Math.abs(value.hashCode());
	}

	public int compareTo(Thing other) {
		return value.compareTo(other.value);
	}
}

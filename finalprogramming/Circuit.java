import java.util.*;

/** Program that finds a short circuit (a list of integers forming a cycle) if one exists.
 * 
 * @author Preston Ito
 */
public class Circuit {

	public HashMap<Integer,ArrayList<Integer>> masterMap = new HashMap<>();
	public List<Integer> loop = new ArrayList<Integer>();
	public int startingPoint;
	/**
	 * Returns a short circuit or an empty list if no short circuit exists.
	 * 
	 * @param graph An array of strings, where the string at index i is a
	 *              whitespace-separated list of integers.
	 * 
	 * @return A list containing the nodes forming short circuit, or an empty list
	 *         if no short circuit exists.
	 */
	public List<Integer> findShortCircuit(String[] graph) {
		
		
		//adding everything to a map
		for (int i = 0; i < graph.length; i ++) {
			ArrayList<Integer> wires = new ArrayList<>();
			String[] wireConnections = graph[i].split(" ");
			for (String num : wireConnections) {
				if (num == "") {
					num = "-1";
				}
				wires.add(Integer.parseInt(num));
			}
			masterMap.put(i, wires);
		}

		//trying each different starting point
		for (Integer comp : masterMap.keySet()) {
			Queue<Integer> visited = new LinkedList<>();
			Set<Integer> visitedSet = new HashSet<>();
			startingPoint = comp;
			if (findShortHelper(comp, visited, visitedSet, masterMap.get(comp))) {
				return loop;
			}
			else {
				if (loop.get(0) != loop.get(loop.size()-1)) {
					loop.clear();
				}
			}
		}
		if (loop.size() == 1) {
			loop.clear();
		}
		return loop;
	}

	private boolean findShortHelper(int firstComponent, Queue<Integer> visited, Set<Integer> visitedSet, ArrayList<Integer> values) {

		loop.add(firstComponent);
		if (visitedSet.contains(startingPoint)) {
			return true;
		}
		else {
			int counter = 0;
			for (Integer connection : values) {
				if (visitedSet.contains(connection)) {
					counter++;
				}
				else if (connection != -1) {
					visited.add(connection);
					visitedSet.add(connection);
				}
			}
			if (values.size() == counter) {
				return false;
			}
		}
		//call this function recursively on the each of the things in the queue
		while (!visited.isEmpty()) {
			int peek = visited.peek();
			if (findShortHelper(visited.remove(), visited, visitedSet, masterMap.get(peek))) {
				return true;
			}
		}
		return false;
	}

	/** Main tester method */
	public static void main(String[] args) {
		String[] circuit1 = {"1", "3 2", "", ""};
		String[] circuit2 = {"1", "3", "1 4", "", "", "1 3", ""};
		String[] circuit3 = {"1", "2", "0"};
		String[] circuit4 = {"1", "2", "3", "7", "1", "1 2", "3 7", "8", ""};
		String[] circuit5 = {"1", "2", "3", "7", "1", "1 2", "3 7", "8", "5"};


		Circuit circuit = new Circuit();

		System.out.println(circuit.findShortCircuit(circuit1)); // []
		System.out.println(circuit.findShortCircuit(circuit2)); // []
		System.out.println(circuit.findShortCircuit(circuit3)); // [0, 1, 2, 0] or [1, 2, 0, 1] etc
		System.out.println(circuit.findShortCircuit(circuit4)); // []
		System.out.println(circuit.findShortCircuit(circuit5)); // [1, 2, 3, 7, 8, 5, 1]
	}
}

import java.util.*;

public class WeightedGraph {

	// An edge can be indicated by
	// your neighbor's ID and its associated weight
	private class Edge {
		public int endpoint;
		public double weight;

		public Edge(int endpoint, double weight) {
			this.endpoint = endpoint;
			this.weight = weight;
		}
	}

	private Map<Integer, ArrayList<Edge>> outgoing;
	private Map<Integer, ArrayList<Edge>> incoming;

	public WeightedGraph() {
		outgoing = new HashMap<>();
		incoming = new HashMap<>();
	}

	/**
	 * Add a vertex to the graph.
	 * 
	 * @param id Id of the vertex.
	 * 
	 * @return True if and only if the vertex with this ID was
	 *         not present in the graph before.
	 */
	public boolean addVertex(int id) {
		if(!outgoing.containsKey(id)) {
			outgoing.put(id, new ArrayList<>());
			incoming.put(id, new ArrayList<>());

			return true;
		}

		return false;
	}

	/**
	 * Adds an edge to the graph.
	 * 
	 * @param source Id of the source vertex.
	 * @param target Id of the target vertex.
	 * 
	 * @return Returns true if and only if both source and target
	 *         vertices exist in the graph, and an edge linking
	 *         source -> target did not exist before.
	 */
	public boolean addEdge(int source, int target, double weight) {
		// Error treatment

		if(!outgoing.containsKey(source)) {
			return false;
		}

		if(!outgoing.containsKey(target)) {
			return false;
		}

		if(outgoing.get(source).contains(target)) {
			return false;
		}

		// Insert edge

		outgoing.get(source).add(new Edge(target, weight));
		incoming.get(target).add(new Edge(source, weight));

		return true;
	}

	/**
	 * Returns true if the vertex is present in the graph.
	 * 
	 * @param vertexId ID of the vertex being checked.
	 * 
	 * @return True if the vertex with the provided ID is in the graph.
	 */
	public boolean hasVertex(int vertexId) {
		return outgoing.containsKey(vertexId);
	}

	/**
	 * Returns the in-degree of the provided vertex,
	 * or -1 if the provided vertex does not exist.
	 * 
	 * @param vertexId ID of the vertex being checked for in-degree.
	 * 
	 * @return In-degree of the vertex with the provided ID, or -1
	 *         if a non-existent vertex has been provided.
	 */
	public int inDegree(int vertexId) {
		if(!hasVertex(vertexId)) {
			return -1;
		}

		return incoming.get(vertexId).size();
	}

	/**
	 * Returns the out-degree of the provided vertex,
	 * or -1 if the provided vertex does not exist.
	 * 
	 * @param vertexId ID of the vertex being checked for out-degree.
	 * 
	 * @return Out-degree of the vertex with the provided ID, or -1
	 *         if a non-existent vertex has been provided.
	 */
	public int outDegree(int vertexId) {
		if(!hasVertex(vertexId)) {
			return -1;
		}

		return outgoing.get(vertexId).size();
	}

	/**
	 * Prints a BFS starting from the vertex provided as parameter.
	 * 
	 * @param rootId The vertex from which to start the BFS.
	 */
	public void bfs(int rootId) {
		Set<Integer> visited = new HashSet<Integer>();

		Queue<Integer> toVisit = new LinkedList<Integer>();

		toVisit.add(rootId);

		while(!toVisit.isEmpty()) {
			int vertexId = toVisit.poll();

			visited.add(vertexId);
			System.out.println(vertexId);

			for(Edge neighbor: outgoing.get(vertexId)) {
				if(!visited.contains(neighbor.endpoint)) {
					toVisit.add(neighbor.endpoint);
				}
			}
		}
	}

	/**
	 * Prints a DFS starting from the vertex provided as parameter.
	 * 
	 * @param rootId The vertex from which to start the BFS.
	 */
	public void dfs(int rootId) {
		Set<Integer> visited = new HashSet<Integer>();

		dfsHelper(rootId, visited);
	}

	/**
	 * Helper method for DFS, that keeps track of the visited set.
	 * 
	 * @param vertexId Vertex that we are currently visiting now.
	 * @param visited Vertexes that have been already visited.
	 */
	public void dfsHelper(int vertexId, Set<Integer> visited) {
		if(visited.contains(vertexId)) {
			return;
		}

		System.out.println(vertexId);
		visited.add(vertexId);

		for(Edge neighbor: outgoing.get(vertexId)) {
			dfsHelper(neighbor.endpoint, visited);
		}
	}

	/**
	 * Prints a DFS starting from the vertex provided as parameter.
	 * This method is interactive, and its "AHA" moment comes from the fact
	 * that to implement it you have to do a very simple, but very charming (yes),
	 * change to DFS.
	 * 
	 * @param rootId The vertex from which to start the DFS.
	 */
	public void dfsAHA(int rootId) {
		Set<Integer> visited = new HashSet<Integer>();

		Stack<Integer> toVisit = new Stack<Integer>();

		toVisit.add(rootId);

		while(!toVisit.isEmpty()) {
			int vertexId = toVisit.pop();

			visited.add(vertexId);
			System.out.println(vertexId);

			for(Edge neighbor: outgoing.get(vertexId)) {
				if(!visited.contains(neighbor.endpoint)) {
					toVisit.add(neighbor.endpoint);
				}
			}
		}
	}

	private class LabeledVertex implements Comparable<LabeledVertex> {
		int id;
		double distance;

		public LabeledVertex(int id, double distance) {
			this.id = id;
			this.distance = distance;
		}

		public int compareTo(LabeledVertex other) {
			return (int) (this.distance - other.distance);
		}
	}

	/**
	 * Calculates the shortest path between the provided vertex and all the other vertices in the graph.
	 * 
	 * @param sourceId The source of the calculated paths.
	 */
	public void shortestPath(int sourceId) {

		PriorityQueue<LabeledVertex> toVisit = new PriorityQueue<>();
		Map<Integer, LabeledVertex> labeledVertexMap = new TreeMap<>();

		for (int vertexId : outgoing.keySet()) {
			if (vertexId != sourceId) {
				labeledVertexMap.put(vertexId, new LabeledVertex(vertexId, Integer.MAX_VALUE));
				toVisit.add(labeledVertexMap.get(vertexId));
			}
		}
		labeledVertexMap.put(sourceId, new LabeledVertex(sourceId, 0));
		toVisit.add(new LabeledVertex(sourceId, 0));
		while (!toVisit.isEmpty()) {
			LabeledVertex current = toVisit.remove();
			for (Edge outEdge : outgoing.get(current.id)) {
				LabeledVertex neighbor = labeledVertexMap.get(outEdge.endpoint); 

				//calculate new distance
				if (neighbor.distance < current.distance + outEdge.weight) {
					//update sitance of hteneighbor 
					toVisit.remove(neighbor);
					neighbor.distance = current.distance + outEdge.weight;
					toVisit.add(neighbor); 
				}
				
			}
		}
	}


	public static void main(String[] args) {
		WeightedGraph graph = new WeightedGraph();

		System.out.println("Adding 1: " + graph.addVertex(1));
		System.out.println("Adding 2: " + graph.addVertex(2));
		System.out.println("Adding 3: " + graph.addVertex(3));
		System.out.println("Adding 4: " + graph.addVertex(4));
		System.out.println("Adding 5: " + graph.addVertex(5));
		System.out.println("Adding 6: " + graph.addVertex(6));
		System.out.println("Adding 7: " + graph.addVertex(7));

		System.out.println("Adding 1->2: " + graph.addEdge(1, 2, 7.0));
		System.out.println("Adding 1->3: " + graph.addEdge(1, 3, 2.1));
		System.out.println("Adding 1->4: " + graph.addEdge(1, 4, 250.0));
		System.out.println("Adding 3->5: " + graph.addEdge(3, 5, 37.1));
		System.out.println("Adding 3->6: " + graph.addEdge(3, 6, 3.0));
		System.out.println("Adding 6->1: " + graph.addEdge(6, 1, 4.1));
		System.out.println("Adding 4->7: " + graph.addEdge(4, 7, 7.0));

		graph.shortestPath(1);

		System.out.println("Adding 2->7: " + graph.addEdge(2, 7, 40.0));

		graph.shortestPath(1);

		System.out.println("Adding 1->7: " + graph.addEdge(1, 7, 2.0));

		graph.shortestPath(1);
	}
}

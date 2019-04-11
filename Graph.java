import com.sun.tools.internal.ws.wsdl.framework.DuplicateEntityException;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    John Bednarczyk
 * 
 * Directed and unweighted graph implementation
 */
public class Graph implements GraphADT {

	private int numEdges;
	private int numVertices;
	private HashMap<String, Graphnode> vertices;

	/**
	 * Class representing a node for a graph
	 */
	class Graphnode {
		String data; // Node label "A"
		boolean visited; // Marker to determine if node was visited
		ArrayList<String> neighbors; //adjacent vertices

		// Constructor for GraphNode
		private Graphnode(String data, boolean visited) {
			this.data = data;
			this.visited = visited;
			neighbors = new ArrayList<String>();
		}
	}

	/**
	 * Default no-argument constructor
	 */
	public Graph() {
		numVertices = 0;
		numEdges = 0;
		vertices = new HashMap<>();
	}

	/**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void addVertex(String vertex) {

		// Vertex is null or vertex is already in the graph
		if (vertex == null || vertices.containsKey(vertex))
			return;

		// Add vertex
		vertices.put(vertex, new Graphnode(vertex,false));

		// Increase number of vertices
		numVertices++;
	}

	/**
     * Remove a vertex and all associated 
     * edges from the graph.
     * 
     * If vertex is null or does not exist,
     * method ends without removing a vertex, edges, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void removeVertex(String vertex) {

		// vertex is null or no vertices exist
		if (vertex == null || !vertices.containsKey(vertex)) {
			return;
		}

		// Go to each neighbor and remove vertex
		for (int i = 0 ; i < vertices.get(vertex).neighbors.size() ; i++){
			vertices.get(vertices.get(vertex).neighbors.get(i)).neighbors.remove(vertex);
		}

		// Remove vertex
		vertices.remove(vertex);
	}

	/**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * no edge is added and no exception is thrown.
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {

		// Check to make sure vertex1 and vertex2 are not null
		if (vertex1 == null || vertex2 == null)
			return;

		// Make sure both vertices exist
		if (vertices.containsKey(vertex1) && vertices.containsKey(vertex2)){
			// Make sure the edge doesnt already exist
			if (!vertices.get(vertex1).neighbors.contains(vertex2)){
				vertices.get(vertex1).neighbors.add(vertex2);
			}
		}

		numEdges++;
	}
	
	/**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     */
	public void removeEdge(String vertex1, String vertex2) {

		// Check to make sure vertex1 and vertex2 are not null
		if (vertex1 == null || vertex2 == null)
			return;

		// Make sure both vertices exist
		if (vertices.containsKey(vertex1) && vertices.containsKey(vertex2)){
			// remove edge if it exists
			vertices.get(vertex1).neighbors.remove(vertex2);
		}
	}	

	/**
     * Returns a Set that contains all the vertices
     * 
	 */
	public Set<String> getAllVertices() {
		return vertices.keySet();
	}

	/**
     * Get all the neighbor (adjacent) vertices of a vertex
     *
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {

		if (vertex == null || !vertices.containsKey(vertex))
			return new ArrayList<>();

		return vertices.get(vertex).neighbors;
	}
	
	/**
	 * Returns the number of edges in this graph.
	 */
	public int size() {
        return numEdges;
    }

	/**
     * Returns the number of vertices in this graph.
     */
	public int order() {
        return numVertices;
    }
}

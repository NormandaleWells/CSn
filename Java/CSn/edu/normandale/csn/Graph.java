package edu.normandale.csn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Graph is a simple graph data structure with a fixed
// number of vertices identified by integers by integers
// in the half-open range [0..V), where V is the number
// of vertices.  This handles both undirected and
// directed graphs; the constructor has a parameter
// to determine which type it is.

// This simple data structure is useful for most cases.
// Even if you need non-integer vertex type T, it can be
// implemented using an array of T and a HashMap<T> to
// map back from T to the corresponding array index.

// However, you may want to use the more general
// GenericGraph<T> type if:
//		(1) You want to use non-integer vertex types
//		    without resorting to the work-around noted
//		    above.
//		(2) You have a dynamic graph or one in which
//		    all the vertices are not known up front
//		    (e.g. a web crawler).

public class Graph {

	public class Edge {
		int from;
		int to;
		double weight;
		
		private Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int from() {
			return from;
		}

		public int to() {
			return to;
		}

		// Given one vertex for the edge, get the other.
		public int other(int v) {
			if (v == from) return   to;
			if (v ==   to) return from;
			throw new RuntimeException("Inconsistent edge.");
		}
		
		public double weight() {
			return weight;
		}
	}

	// The graph is fully described by its adjacency
	// list and whether it is directed or not.
	private ArrayList<Edge>[] adjList;
	private boolean directed;

	@SuppressWarnings("unchecked")
	public Graph(int numVertices, boolean directed) {

		adjList = new ArrayList[numVertices];
		for (int i = 0; i < numVertices; i++) {
			adjList[i] = new ArrayList<Edge>();
		}
		
		this.directed = directed;
	}
	
	// Add a weighted edge.  If the graph is undirected, an
	// the edge in both directions.
	public void addEdge(int v, int w, double weight) {
		adjList[v].add(new Edge(v, w, weight));
		if (!directed) {
			adjList[w].add(new Edge(w, v, weight));
		}
	}

	// Add an edge.  If the graph is undirected, an
	// the edge in both directions.  The weight is
	// set to 1.0.
	public void addEdge(int v, int w) {
		addEdge(v, w, 1.0);
	}

	// Return the number of vertices.
	public int numVertices() {
		return adjList.length;
	}

	// Return the list of vertices adjacent to
	// vertex v.
	public Iterable<Edge> adjacent(int v) {
		return new ArrayList<Edge>(adjList[v]);
	}

	// createIntGraph is a convenience function that
	// creates an IntGraph from a file.  The file must
	// have the following format:
	//		numVertices
	//		numEdges
	//		fromVertex toVertex [weight]
	//		fromVertex to Vertex [weight]
	// where the last two lines are repeated for
	// each edge.
	//
	// The [weight] entries are assumed to be present
	// iff the 'weighted' parameter is used.
	public static Graph readGraph(String filename, boolean directed, boolean weighted) throws FileNotFoundException {

		FileInputStream inFile = new FileInputStream(filename);

		try (Scanner scan = new Scanner(inFile)) {
		
			int numVertices = scan.nextInt();
			int numEdges    = scan.nextInt();
			
			Graph g = new Graph(numVertices, false);
			for (int i = 0; i < numEdges; i++) {
				int v = scan.nextInt();
				int w = scan.nextInt();
				double weight = weighted ? scan.nextDouble() : 1.0;
				g.addEdge(v, w, weight);
			}
			
			return g;
		}
	}
}

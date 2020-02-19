package edu.normandale.csn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class StaticGraph<T> implements Graph<T> {

	private HashMap<T, HashSet<T>> adjList = new HashMap<>();
	
	// This isn't a directed graph until we get a
	// directed edge.
	private boolean directed = false;

	// Add a new vertex.  If the vertex already exists
	// in the graph, this does nothing.
	public void addVertex(T v) {
		// We want to ensure that every vertex has an
		// adjacency list.  This saves some special
		// cases later.
		if (!adjList.containsKey(v))
			adjList.put(v, new HashSet<T>());
	}

	// Return the number of vertices.
	public int numVertices() {
		return adjList.size();
	}

	// Add an edge, either directed or undirected.
	public void addEdge(T v, T w, boolean isDirected) {
		// Make sure both vertices are present.
		addVertex(v);
		addVertex(w);
		adjList.get(v).add(w);
		if (!isDirected)
			adjList.get(w).add(v);
		else
			directed = true;
	}

	// Add an undirected edge.
	public void addEdge(T v, T w) {
		addEdge(v, w, false);
	}

	// Returns true if the graph has at least one
	// directed edge, false otherwise.
	public boolean isDirected() {
		return directed;
	}

	@Override
	public Iterable<T> adjacent(T v) {

		if (!adjList.containsKey(v))
			throw new RuntimeException("no such vertex " + v.toString());
		return adjList.get(v);
	}

	// Create a StaticGraph with String vertices, reading
	// the data from the given file.  The data consists of
	// pairs of edges.
	public static StaticGraph<String> createStringGraph(String filename, boolean directed)
			throws FileNotFoundException {

		StaticGraph<String> g = new StaticGraph<>();

		FileInputStream inFile = new FileInputStream(filename);
		try (Scanner scan = new Scanner(inFile)) {

			while (scan.hasNext()) {
				String v = scan.next();
				if (!scan.hasNext()) {
					throw new RuntimeException("invalid file");
				}
				String w = scan.next();
				g.addEdge(v, w, directed);
			}
		}

		return g;
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		StaticGraph<String> g = createStringGraph(args[0], false);
		BreadthFirstPaths<String> bfp = new BreadthFirstPaths<String>(g, args[1]); 

		Scanner stdin = new Scanner(System.in);
		while (stdin.hasNext()) {
			try {
				String v = stdin.next();
				if (!bfp.hasPathTo(v)) {
					System.out.println("No path");
				} else {
					System.out.println("Distance = " + bfp.distanceTo(v));
					for (String w : bfp.pathTo(v))
						System.out.print(w + " ");
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
		}

		stdin.close();
	}
}

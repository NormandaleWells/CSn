//
// Graph.java
//
// Implementation of an edge-weighted graph with vertex type T.
// T is used as hash table key, so it should be immutable.

// Whether the graph is directed or undirected is determined
// by a constructor argument.  The only difference between the
// two as far as this class is concerned is that with an
// undirected graph, adding an edge automatically adds the
// edge in both directions.

package edu.normandale.csn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GenericGraph<T> {
	
	public class Edge {
		T from;
		T to;
		double weight;
		
		private Edge(T from, T to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public T from() {
			return from;
		}

		public T to() {
			return to;
		}

		// Given one vertex for the edge, get the other.
		public T other(T v) {
			if (v.equals(from)) return   to;
			if (v.equals(  to)) return from;
			throw new RuntimeException("Inconsistent edge.");
		}
		
		public double weight() {
			return weight;
		}
	}

	private boolean directed = false;
	private HashMap<T,ArrayList<Edge>> edges = new HashMap<T,ArrayList<Edge>>();

	public GenericGraph(boolean directed) {
		this.directed = directed;
	}

	private void addEdge(T v, Edge e) {
		if (!edges.containsKey(v))
			edges.put(v, new ArrayList<Edge>());
		edges.get(v).add(e);
	}

	public void addEdge(T from, T to, double weight) {
		Edge e = new Edge(from, to, weight);
		addEdge(from, e);
		if (!directed)
			addEdge(to, e);
	}
	
	public void addEdge(T from, T to) {
		addEdge(from, to, 1.0);
	}
	
	Iterable<Edge> adjacent(T v) {
		return edges.get(v);
	}

	public static GenericGraph<String> readStringGraph(String filename, boolean directed, boolean weighted) {
		try {
			GenericGraph<String> g = new GenericGraph<String>(directed);
			FileInputStream file = new FileInputStream(filename);
			Scanner sc = new Scanner(file);
			int numEdges = sc.nextInt();
			for (int i = 0; i < numEdges; i++) {
				String from = sc.next();
				String to = sc.next();
				double weight = weighted ? sc.nextDouble() : 1.0;
				g.addEdge(from, to, weight);
			}
			sc.close();
			return g;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("readStringGraph: file not found");
		}
	}
	
	public static GenericGraph<Integer> readIntGraph(String filename, boolean directed, boolean weighted) {
		try {
			GenericGraph<Integer> g = new GenericGraph<Integer>(directed);
			FileInputStream file = new FileInputStream(filename);
			Scanner sc = new Scanner(file);
			int numEdges = sc.nextInt();
			for (int i = 0; i < numEdges; i++) {
				Integer from = sc.nextInt();
				Integer to = sc.nextInt();
				double weight = weighted ? sc.nextDouble() : 1.0;
				g.addEdge(from, to, weight);
			}
			sc.close();
			return g;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("readIntGraph: file not found");
		}
	}
}

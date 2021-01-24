package edu.normandale.csn;

import java.util.HashMap;
import java.util.HashSet;

public class BreadthFirstPaths<T> {

	private HashSet<T> visited = new HashSet<>();
	private HashMap<T,Integer> distance = new HashMap<>();
	private HashMap<T,T> edgeFrom = new HashMap<>();

	private void bfs(Graph<T> g, Queue<T> q) {

		// Until the queue is empty, get the next
		// vertex to process off the queue.
		while (!q.isEmpty()) {
			T v = q.dequeue();
			System.out.println("Processing " + v);
			// For every adjacent vertex, process
			// every adjacent vertex we haven't
			// yet visited.
			for (Graph<T>.Edge e : g.adjacent(v)) {
				int d = distance.get(v);
				T w = e.other(v);
				if (!visited.contains(w)) {
					
					// To process a vertex, add  it
					// to the queue, and mark it as
					// visited, having come from w
					// with a distance one more than
					// the distance to w.
					q.enqueue(w);
					visited.add(w);
					edgeFrom.put(w, v);
					distance.put(w, d+1);
				}
			}
		}
	}

	// Find the shortest path to each vertex from
	// a single starting vertex.
	public BreadthFirstPaths(Graph<T> g, T s) {

		Queue<T> q = new RAQueue<T>();

		// Add our starting vertex to the queue,
		// mark it as visited, and set its distance
		// to zero.
		q.enqueue(s);
		visited.add(s);
		distance.put(s, 0);
		
		// bfs() does all the actual work.
		bfs(g, q);
	}

	// Find the shortest path to each vertex from
	// any of a set of starting vertices.
	public BreadthFirstPaths(Graph<T> g, Iterable<T> s) {
		
		Queue<T> q = new RAQueue<T>();
		
		// For each starting vertex, add it to the
		// queue, mark it as visited, and set its
		// distance to zero.
		for (T v : s) {
			q.enqueue(v);
			visited.add(v);
			distance.put(v, 0);
		}

		// bfs() does all the actual work.
		bfs(g, q);
	}

	boolean hasPathTo(T v) {
		return visited.contains(v);
	}
	
	int distanceTo(T v) {
		return distance.get(v);
	}

	Iterable<T> pathTo(T v) {
		
		// Make sure we have a path to this vertex.
		if (!hasPathTo(v))
			throw new RuntimeException("no path to vertex " + v.toString());

		// We're going to generate the vertices in the
		// path in reverse order, so we'll use a Stack<T>
		// to return them.
		Stack<T> path = new RAStack<>();

		// We need to follow the edgeFrom values from
		// this vertex path to a source.  We know we're
		// at a source when we have no edgeFrom entry.
		path.push(v);
		while (edgeFrom.containsKey(v)) {
			v = edgeFrom.get(v);
			path.push(v);
		}

		return path;
	}
}

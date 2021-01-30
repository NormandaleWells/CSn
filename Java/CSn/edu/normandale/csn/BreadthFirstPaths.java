package edu.normandale.csn;

import java.io.FileNotFoundException;
import java.util.Scanner;

// IntBreadthFirstPaths performs a breadth-first search
// of a given graph starting from a given vertex.  The
// search is performed by the constructor, and the path
// data is available by called other class methods.
public class BreadthFirstPaths {

	// visited[v] is true if vertex v has been
	// visited.
	boolean[] visited;
	
	// distance[v] is the number of edges in the
	// shortest path from a start vertex to vertex v.
	int[] distance;
	
	// edgeFrom[v] is the vertex we were processing
	// when we visited vertex v.  In other words,
	// it is the previous vertex in the path from
	// a source vertex to v.
	int[] edgeFrom;

	// The two constructors call this common function
	// to initialize the arrays that track the paths
	// and distances.
	private void init(Graph g) {
		int n = g.numVertices();
		visited = new boolean[n];
		distance = new int[n];
		edgeFrom = new int[n];
		
		for (int i = 0; i < n; i++) {
			distance[i] = -1;
			edgeFrom[i] = -1;
		}
	}

	// bfs is the work-horse function of the search.
	// The two constructors set up the initial queue
	// of visited vertices, and then calls bfs.
	private void bfs(Graph g, Queue<Integer> q) {

		// Until the queue is empty, get the next
		// vertex to process off the queue.
		while (!q.isEmpty()) {
			int v = q.dequeue();

			// For every adjacent vertex, process
			// every adjacent vertex we haven't
			// yet visited.
			int d = distance[v];
			for (Graph.Edge e : g.adjacent(v)) {
				int w = e.to();
				if (!visited[w]) {
					
					// To process a vertex, add  it
					// to the queue, and mark it as
					// visited, having come from w
					// with a distance one more than
					// the distance to w.
					q.enqueue(w);
					visited[w] = true;
					edgeFrom[w] = v;
					distance[w] = d+1;
				}
			}
		}
	}

	// Find the shortest path to each vertex from
	// a single starting vertex.
	public BreadthFirstPaths(Graph g, int s) {

		init(g);
		Queue<Integer> q = new RAQueue<Integer>();

		// Add our starting vertex to the queue,
		// mark it as visited, and set its distance
		// to zero.
		q.enqueue(s);
		visited[s] = true;
		distance[s] = 0;
		
		// bfs() does all the actual work.
		bfs(g, q);
	}

	// Find the shortest path to each vertex from
	// any of a set of starting vertices.
	public BreadthFirstPaths(Graph g, Iterable<Integer> s) {
		
		init(g);
		Queue<Integer> q = new RAQueue<Integer>();
		
		// For each starting vertex, add it to the
		// queue, mark it as visited, and set its
		// distance to zero.
		for (int v : s) {
			q.enqueue(v);
			visited[v] = true;
			distance[v] = 0;
		}

		// bfs() does all the actual work.
		bfs(g, q);
	}

	// Return whether we have a path from a source
	// vertex to vertex v.  If the vertex is visited,
	// we have a path.
	boolean hasPathTo(int v) {
		return visited[v];
	}
	
	// distanceTo returns the distance to v from the
	// nearest source vertex.  If hasPathTo(v) returns
	// false, the return value is -1.
	int distanceTo(int v) {
		return distance[v];
	}

	// pathTo returns the path from the nearest source
	// vertex to v.  If hasPathTo(v) returns false,
	// this throws an exception.
	Iterable<Integer> pathTo(int v) {
		
		// Make sure we have a path to this vertex.
		if (!hasPathTo(v))
			throw new RuntimeException("no path to vertex " + v);

		// We're going to generate the vertices in the
		// path in reverse order, so we'll use a Stack<T>
		// to return them.
		Stack<Integer> path = new RAStack<>();

		// We need to follow the edgeFrom values from
		// this vertex path to a source.  We know we're
		// at a source when we have no edgeFrom entry.
		path.push(v);
		while (edgeFrom[v] != -1) {
			v = edgeFrom[v];
			path.push(v);
		}

		return path;
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		Graph g = Graph.readGraph(args[0], false, false);
		int s = Integer.parseInt(args[1]);
		BreadthFirstPaths bfp = new BreadthFirstPaths(g, s);

		Scanner stdin = new Scanner(System.in);
		while (stdin.hasNext()) {
			try {
				int v = stdin.nextInt();
				if (!bfp.hasPathTo(v)) {
					System.out.println("No path");
				} else {
					System.out.println("Distance = " + bfp.distanceTo(v));
					for (int w : bfp.pathTo(v))
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

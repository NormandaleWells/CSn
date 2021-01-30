package edu.normandale.csn;

// This class is patterned after the CC class in Sedgewick and Wayne,
// section 4.1.  Except the the names and the fact that I opted to
// not have separate edge-weighted and non-edge-weighted Graph
// classes, they're probably nearly identical.

public class ConnectedComponents {

	private boolean[] visited;
	private int[] component;
	private int nComponents = 0;

	private void dfs(Graph g, int v) {
		visited[v] = true;
		component[v] = nComponents;
		for (Graph.Edge e : g.adjacent(v))
			if (!visited[e.to()])
				dfs(g, e.to());
	}

	public ConnectedComponents(Graph g) {
		
		int n = g.numVertices();
		visited = new boolean[n];
		component = new int[n];

		for (int v = 0; v < n; v++) {
			if (!visited[n])
				dfs(g, v);
			nComponents++;
		}
	}
	
	public int numComponents() {
		return nComponents;
	}
	
	public int component(int v) {
		return component[v];
	}
}

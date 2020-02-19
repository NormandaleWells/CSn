package edu.normandale.csn;

public class IntComponents {

	private boolean[] visited;
	private int[] component;
	private int nComponents = 0;

	private void dfs(IntGraph g, int v) {
		visited[v] = true;
		component[v] = nComponents;
		for (int w : g.adjacent(v))
			if (!visited[w])
				dfs(g, w);
	}

	public IntComponents(IntGraph g) {
		
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

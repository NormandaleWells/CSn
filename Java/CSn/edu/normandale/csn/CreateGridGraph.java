package edu.normandale.csn;

public class CreateGridGraph {

	// Usage: CreateGridGraph <width> <height> <percentage>

	// Output: width/height grid (width vertices by height vertices)
	// with the given percentage of edges kept.

	// The resulting graph has width*height vertices.
	// A vertices at row,column has index row * width + column.

	// Example: "CreateGridGraph 3 3 50" might return
	//	+  +  +
	//	|      
	//	+--+  +
	//	|  |  |
	//	+  +--+
	// The data would be:
	// 9
	// 6
	// 4 7
	// 5 8
	// 3 4
	// 0 3
	// 3 6
	// 7 8
	
	// Note that the data is in the format expected by the
	// IndexedGraph.readIndexedGraph function.

	// There is no guarantee that the resulting graph will be connected.
	
	private static class Edge {
		int v;
		int w;
		Edge(int av, int aw) {
			v = av;
			w = aw;
		}
	}

	public static int rowColumnToIndex(int column, int row, int width) {
		return row * width + column;
	}

	public static int indexToRow(int idx, int width) {
		return idx / width;
	}

	public static int indexToColumn(int idx, int width) {
		return idx % width;
	}

	public static void main(String[] args) {
		
		if (args.length < 3) {
			System.out.println("usage: CreateGridGraph <width> <height> <percentage>");
			return;
		}
		
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		double percentage = Double.parseDouble(args[2]);

		Edge[] edges = new Edge[width * (height-1) + height * (width-1)];
		int idxEdge = 0;

		// Generate horizontal edges
		for (int i = 0; i < width-1; i++) {
			for (int j = 0; j < height; j++) {
				int v = rowColumnToIndex(i, j, width);
				int w = v + 1;
				edges[idxEdge++] = new Edge(v, w);
			}
		}

		// Generate vertical edges
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height-1; j++) {
				int v = rowColumnToIndex(i, j, width);
				int w = v + width;
				edges[idxEdge++] = new Edge(v, w);
			}
		}
		
		RandomUtils.shuffle(edges);
		int keep = (int)(edges.length * (percentage / 100));
		
		System.out.printf("%d\n%d\n", width * height, keep);
		for (int i = 0; i < keep; i++)
			System.out.printf("%d %d\n", edges[i].v, edges[i].w);
	}
}

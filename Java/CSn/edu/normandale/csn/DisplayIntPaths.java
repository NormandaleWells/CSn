package edu.normandale.csn;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

// This program displays a graph described by a file
// whose name is provided on the command line.  It
// then reads pairs of vertices, and displays a
// shortest path (computed using a breadth-first
// search) between those vertices.

// See IntGraph.createIntGraph for details on the
// format of the data file.

// WARNING: This is NOT a good example of graphics
// programming in Java.  It has all the hallmarks
// of code written in an hour on a Sunday afternoon
// while watching a football game.  I'm sure given
// a bit more time I would design this better.

// This program has a known defect whereby it does
// create the window large enough to show all the
// vertices.
public class DisplayIntPaths {

	public static void main(String[] args) throws FileNotFoundException {

		String filename = args[0];
		int xNumVerts = Integer.parseInt(args[1]);
		int yNumVerts = Integer.parseInt(args[2]);

		IntGraph g = IntGraph.createIntGraph(filename);
		
		JFrame frame = new JFrame();
		frame.setTitle(filename);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		IntGraphPane gw = new IntGraphPane(g, xNumVerts, yNumVerts);
		gw.adjustFrame(frame);

		frame.add(gw);
		frame.setVisible(true);
		
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextInt()) {
			int v = scan.nextInt();
			int w = scan.nextInt();
			IntBreadthFirstPaths bfp = new IntBreadthFirstPaths(g, v);
			gw.clearHighlights();
			if (!bfp.hasPathTo(w))
				System.out.println("No path");
			else {
				System.out.println("Distance: " + bfp.distanceTo(w));
				int last = -1;
				for (int s : bfp.pathTo(w)) {
					if (last != -1)
						gw.highlightEdge(last, s, 255, 0, 0);
					last = s;
				}
				gw.redraw();
			}
		}
		scan.close();
	}
}

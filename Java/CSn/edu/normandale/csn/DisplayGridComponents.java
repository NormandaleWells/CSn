package edu.normandale.csn;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

// DisplayGridComponents
//
// This program displays the individual components in a grid graph
// as created by CreateGridGraph.
//
// Each unique component (up to 20) is displayed in a unique color.

public class DisplayGridComponents {

	// These colors are from https://sashamaps.net/docs/resources/20-colors/
	final private static Color[] colors = {
			new Color(230, 25,  75),		// red
			new Color(60,  180, 75),		// green 
			new Color(255, 225, 25),		// yellow
			new Color(0,   130, 200),		// blue
			new Color(245, 130, 48),		// orange
			new Color(145, 30,  180),		// purple
			new Color(70,  240, 240),		// cyan
			new Color(240, 50,  230),		// magenta
			new Color(210, 245, 60),		// lime
			new Color(250, 190, 212),		// pink
			new Color(0,   128, 128),		// teal
			new Color(220, 190, 255),		// lavender
			new Color(170, 110, 40),		// brown
			new Color(255, 250, 200),		// beige
			new Color(128, 0,   0),			// maroon
			new Color(170, 255, 195),		// mint
			new Color(128, 128, 0),			// olive
			new Color(255, 215, 180),		// apricot
			new Color(0,   0,   128),		// navy
			new Color(128, 128, 128),		// grey
//			new Color(255, 255, 255),		// white
//			new Color(0,   0,   0)			// black
	};

	final static int NUM_COLORS = colors.length;
	final static int NUM_BEST_COLORS = 6;		// a generally very useful subset

	public static void main(String[] args) throws FileNotFoundException {

		if (args.length < 3) {
			System.out.println("usage: CreateGridComponents <filename> <x> <y>");
			return;
		}
		
		String filename = args[0];
		int xNumVerts = Integer.parseInt(args[1]);
		int yNumVerts = Integer.parseInt(args[2]);

		Graph g = Graph.readGraph(filename, false, false);
		System.out.printf("%d vertices\n", g.numVertices());

		JFrame frame = new JFrame();
		frame.setTitle(filename);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridGraphPane gw = new GridGraphPane(g, xNumVerts, yNumVerts);
		gw.adjustFrame(frame);

		frame.add(gw);
		frame.setVisible(true);
		
		ConnectedComponents cc = new ConnectedComponents(g);
		System.out.printf("%d components\n", cc.numComponents());
		
		for (int v = 0; v < g.numVertices(); v++) {
			int component = cc.component(v);
			gw.highlightVertex(v, colors[component % NUM_BEST_COLORS]);
		}
		gw.redraw();
	}
}

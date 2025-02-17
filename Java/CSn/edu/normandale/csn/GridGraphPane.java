package edu.normandale.csn;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class GridGraphPane extends JComponent {

		private class EdgeHL {
			int v;
			int w;
			Color c;
			
			EdgeHL(int v, int w, int r, int g, int b) {
				this.v = v;
				this.w = w;
				this.c = new Color(r, g, b);
			}
		}

		private class VertexHL {
			int v;
			Color c;
			
			VertexHL(int v, Color c) {
				this.v = v;
				this.c = c;
			}
		}

		private static final long serialVersionUID = 1L;
		
		final static int vertexDiameter = 10;
		final static int vertexSpacing = 20;

		private Graph graph;
		private int nx;
		private int ny;

		private ArrayList<EdgeHL> hlEdges = new ArrayList<EdgeHL>();
		private ArrayList<VertexHL> hlVertices = new ArrayList<VertexHL>();

		private Point vertexCenter(int v) {
			int x = v % nx;
			int y = v / nx;
			return new Point((x+1) * vertexSpacing, (y+1) * vertexSpacing);
		}

		private void drawVertex(Graphics2D g2d, Point p, Color color) {
			int xul = p.x - vertexDiameter / 2;
			int yul = p.y - vertexDiameter / 2;
			if (color == null)
				color = new Color(0, 0, 0);
			Ellipse2D.Double circle = new Ellipse2D.Double
					(xul, yul, vertexDiameter, vertexDiameter);
			g2d.setColor(color);
			g2d.fill(circle);
		}

		private void drawVertex(Graphics2D g2d, int v, Color color) {
			Point p = vertexCenter(v);
			drawVertex(g2d, p, color);
		}

		private void drawEdge(Graphics2D g2d, int v, int w, Color color) {
			Point pv = vertexCenter(v);
			Point pw = vertexCenter(w);
			if (color == null)
				color = new Color(0, 0, 0);
			Line2D.Double line = new Line2D.Double(pv.x, pv.y, pw.x, pw.y);
			g2d.setColor(color);
			g2d.draw(line);
		}

		public GridGraphPane(Graph g, int nx, int ny) {
			this.graph = g;
			this.nx = nx;
			this.ny = ny;
		}

		public void adjustFrame(JFrame frame) {
			// TODO: Figure out why I need ny+3 rather than ny+2 in this code.
			frame.setSize((nx + 2) * vertexSpacing, (ny + 3) * vertexSpacing);
		}

		public void highlightEdge(int v, int w, int r, int g, int b) {
			hlEdges.add(new EdgeHL(v, w, r, g, b));
		}

		public void highlightVertex(int v, Color c) {
			hlVertices.add(new VertexHL(v, c));
		}

		public void clearHighlights() {
			hlEdges.clear();
			hlVertices.clear();
		}

		public void redraw() {
			this.repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;

			for (int v = 0; v < graph.numVertices(); v++) {
				drawVertex(g2d, v, null);
			}
			
			for (int v = 0; v < graph.numVertices(); v++) {
				for (Graph.Edge e : graph.adjacent(v))
					if (e.to() > e.from())
						drawEdge(g2d, e.from(), e.to(), null);
			}
			
			for (EdgeHL e : hlEdges) {
				drawEdge(g2d, e.v, e.w, e.c);
			}
			
			for (VertexHL vhl : hlVertices) {
				drawVertex(g2d, vhl.v, vhl.c);
			}
		}
	}

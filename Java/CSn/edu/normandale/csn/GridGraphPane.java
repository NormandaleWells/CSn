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

		private static final long serialVersionUID = 1L;
		
		final static int vertexDiameter = 10;
		final static int vertexSpacing = 20;

		private IndexedGraph graph;
		private int nx;
		private int ny;

		private ArrayList<EdgeHL> hlEdges;

		private Point vertexCenter(int v) {
			int x = v % nx;
			int y = v / nx;
			return new Point((x+1) * vertexSpacing, (y+1) * vertexSpacing);
		}

		private void drawVertex(Graphics2D g2d, Point p) {
			int xul = p.x - vertexDiameter / 2;
			int yul = p.y - vertexDiameter / 2;
			Color color = new Color(0, 0, 0);
			Ellipse2D.Double circle = new Ellipse2D.Double
					(xul, yul, vertexDiameter, vertexDiameter);
			g2d.setColor(color);
			g2d.fill(circle);
		}

		private void drawVertex(Graphics2D g2d, int v) {
			Point p = vertexCenter(v);
			drawVertex(g2d, p);
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

		GridGraphPane(IndexedGraph g, int nx, int ny) {
			this.graph = g;
			this.nx = nx;
			this.ny = ny;
			hlEdges = new ArrayList<EdgeHL>();
		}

		public void adjustFrame(JFrame frame) {
			// TODO: Figure out why I need ny+3 rather than ny+2 in this code.
			frame.setSize((nx + 2) * vertexSpacing, (ny + 3) * vertexSpacing);
		}

		public void highlightEdge(int v, int w, int r, int g, int b) {
			hlEdges.add(new EdgeHL(v, w, r, g, b));
		}

		public void clearHighlights() {
			hlEdges.clear();
		}

		public void redraw() {
			this.repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;

			for (int v = 0; v < graph.numVertices(); v++) {
				drawVertex(g2d, v);
			}
			
			for (int v = 0; v < graph.numVertices(); v++) {
				for (int w : graph.adjacent(v))
					if (w > v)
						drawEdge(g2d, v, w, null);
			}
			
			for (EdgeHL e : hlEdges) {
				drawEdge(g2d, e.v, e.w, e.c);
			}
		}
	}

package edu.normandale.csn;

import java.util.Comparator;

public class Point2d {

	private final double x;
	private final double y;
	
	public Point2d() {
		this.x = 0.0;
		this.y = 0.0;
	}

	public Point2d(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	// equals() is problematic at best since you really shouldn't
	// compare doubles for equality.
	// This code follows the pattern described in Sedgewick
	// and Wayne, page 103.
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null) return false;
		if (this.getClass() != other.getClass()) return false;
		Point2d otherPt = (Point2d)other;
		if (this.x != otherPt.x) return false;
		if (this.y != otherPt.y) return false;
		return true;
	}

	// This follows the pattern described in Sedgewick
	// and Wayne, page 462.
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + ((Double)x).hashCode();
		hash = 31 * hash + ((Double)y).hashCode();
		return hash;
	}

	public double x() { return x; }
	public double y() { return y; }

	public Point2d withX(double x) {
		return new Point2d(x, this.y);
	}

	public Point2d withY(double y) {
		return new Point2d(this.x, y);
	}
	
	public double distanceToOrigin() {
		return Math.sqrt(x*x + y*y);
	}
	
	public double distanceToPoint(Point2d pt) {
		double dx = x - pt.x;
		double dy = y - pt.y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	private class compareXY implements Comparator<Point2d> {
		@Override
		public int compare(Point2d pt1, Point2d pt2) {
			if (pt1.x < pt2.x) return -1;
			if (pt1.x > pt2.x) return  1; 
			if (pt1.y < pt2.y) return -1;
			if (pt1.y > pt2.y) return  1;
			return 0;
		}
	}

	Comparator<Point2d> getCompareXY() {
		return new compareXY();
	}
	
	private class compareYX implements Comparator<Point2d> {
		@Override
		public int compare(Point2d pt1, Point2d pt2) {
			if (pt1.y < pt2.y) return -1;
			if (pt1.y > pt2.y) return  1;
			if (pt1.x < pt2.x) return -1;
			if (pt1.x > pt2.x) return  1; 
			return 0;
		}
	}

	Comparator<Point2d> getCompareYX() {
		return new compareYX();
	}
	
	private class compareDistToOrigin implements Comparator<Point2d> {

		@Override
		public int compare(Point2d pt1, Point2d pt2) {
			Double d1 = pt1.distanceToOrigin();
			Double d2 = pt2.distanceToOrigin();
			return d1.compareTo(d2);
		}
	}

	Comparator<Point2d> getCompareDistToOrigin() {
		return new compareDistToOrigin();
	}
	
	private class compareDistToPoint implements Comparator<Point2d> {

		private final Point2d ptRef;

		public compareDistToPoint(Point2d ptRef) {
			this.ptRef = ptRef;
		}

		@Override
		public int compare(Point2d pt1, Point2d pt2) {
			Double d1 = pt1.distanceToPoint(ptRef);
			Double d2 = pt2.distanceToPoint(ptRef);
			return d1.compareTo(d2);
		}
	}

	Comparator<Point2d> getCompareDistToPoint(Point2d ptRef) {
		return new compareDistToPoint(ptRef);
	}
	
	private class compareAngleToOrigin implements Comparator<Point2d> {

		@Override
		public int compare(Point2d pt1, Point2d pt2) {
			Double a1 = Math.atan2(pt1.y, pt1.x);
			Double a2 = Math.atan2(pt2.y, pt2.x);
			return a1.compareTo(a2);
		}
	}

	Comparator<Point2d> getAngleDistToOrigin() {
		return new compareAngleToOrigin();
	}
	
	private class compareAngleToPoint implements Comparator<Point2d> {

		private final Point2d ptRef;

		public compareAngleToPoint(Point2d ptRef) {
			this.ptRef = ptRef;
		}

		@Override
		public int compare(Point2d pt1, Point2d pt2) {
			Double a1 = Math.atan2(pt1.y - ptRef.y, pt1.x - ptRef.x);
			Double a2 = Math.atan2(pt2.y - ptRef.y, pt2.x - ptRef.x);
			return a1.compareTo(a2);
		}
	}

	Comparator<Point2d> getCompareAngleToPoint(Point2d ptRef) {
		return new compareAngleToPoint(ptRef);
	}
}

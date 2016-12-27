package graph;

public class Point extends java.awt.Point {
	
	int verNumber ;
	public Point() {
		// TODO Auto-generated constructor stub
		verNumber = -99;
	}
	
	public Point(int num) {
		verNumber = num;
	}

	public Point(java.awt.Point p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public Point(java.awt.Point p, int num) {
		super(p);
		// TODO Auto-generated constructor stub
		verNumber = num;
	}
	public Point(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

}

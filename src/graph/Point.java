package graph;

import java.util.LinkedList;

public class Point extends java.awt.Point {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int verNumber ;
	LinkedList<Point> AdjList = new LinkedList<Point>();;
	
	public Point() {
		// TODO Auto-generated constructor stub
		verNumber = -1;
		
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
	
	public int getverNumber(){
		return verNumber;
	} 
	public void setverNumber(int num){
		verNumber = num;
	}

}

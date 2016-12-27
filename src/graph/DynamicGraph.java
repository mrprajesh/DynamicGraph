/**
 * 
 */
package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author rajz
 *
 */
public class DynamicGraph extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonAddVertices = new JButton("Done Vertices");
	private JButton buttonAddEdges = new JButton("Add edges");
	
	private JButton buttonCompute = new JButton("Compute");
	private JButton buttonDrag = new JButton("Enable Drag");
	private JPanel panel = new JPanel();
	
	private boolean isaddVert = true;
	private boolean isaddEdge = false;
	private boolean isaddComp = false;
	
	private static boolean dragged = false; 
	private static Point draggedPoint, draggingPoint , secondPoint; 
	
	private int totalVertices;
	private int vertexSize = 20, shift=10;
	
	private ArrayList<Point> vertexList ;
	private HashMap<Point,ArrayList<Point>> edgeListMap ;
	private boolean isDragable = false;
	
	
	/**
	 * @throws HeadlessException
	 */
	public DynamicGraph() throws HeadlessException {
		// TODO Auto-generated constructor stub
		setTitle("Dynamic graphs");
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		buttonAddVertices.addActionListener(this);
		buttonAddEdges.addActionListener(this);
		buttonCompute.addActionListener(this);
		buttonDrag.addActionListener(this);
		
		panel.add(buttonAddVertices); 
		panel.add(buttonAddEdges); 
		panel.add(buttonCompute); 
		panel.add(buttonDrag);
		
		buttonAddVertices.setEnabled(true);
		buttonAddEdges.setEnabled(false);
		buttonCompute.setEnabled(false);
		buttonDrag.setEnabled(false);
		
		this.add(panel);		
		
		edgeListMap = new HashMap<>();
		vertexList = new ArrayList<Point>();
		totalVertices = 0;

	}

	/**
	 * @param arg0
	 */
	public DynamicGraph(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public DynamicGraph(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DynamicGraph(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) { 
		draggingPoint = new Point (e.getPoint(), draggedPoint.verNumber);
		System.out.println("dragging " + draggedPoint + " to " + draggingPoint);
		int index = vertexList.indexOf(draggedPoint);
		vertexList.get(index).x = e.getX();
		vertexList.get(index).y = e.getY();
		drawNodes();
	/*	
		draggingPoint = e.getPoint();
		if(dragged == true){
			if(draggedPoint != null){
				updatePoint(draggedPoint, draggingPoint);
				drawNodes();
			}
		}
	*/
	}

	private void updatePoint(Point draggedPoint, Point draggingPoint) {
		int index = vertexList.indexOf(draggedPoint); 
		System.out.println(index + " " + draggedPoint);		
		vertexList.set(index, draggingPoint);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Point newPoint = new  Point(e.getPoint()) ;
		Point point = isSamePoint(newPoint) ;
		
		if(isDragable = true && point != null ){
			draggedPoint = point;
		}
		
		if(isaddEdge == true && secondPoint == null){
			if( point != null ) {
				secondPoint = point;
				
			}
			
		}
		else if (isaddEdge == true && secondPoint != null){
			if( point != null ) {
				if(secondPoint.equals(point)){
					secondPoint = null;
					return;
				}
				System.out.println("Line " + secondPoint +" to " + point );
				addEdgeBetween(secondPoint, point);
				secondPoint = null;
			}
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			if(point != null )
			{
				System.out.println("Remove v");
				
				removeNode(point);
				totalVertices--;
			}
		}
		else if(e.getButton() == MouseEvent.BUTTON1 && isaddVert == true){	
			if( point == null ) {
				System.out.print(totalVertices + " "  );
				newPoint.verNumber = totalVertices++;
				
				vertexList.add(newPoint);
				edgeListMap.put(newPoint, new ArrayList<Point>());
				
			}
			int x = e.getX();
			int y = e.getY();
			System.out.println("x "+ x + " y " + y + " "  );
			
		}
		drawNodes();
	}

	private void removeNode(Point point) {
		vertexList.remove(point);
		for(Point p : edgeListMap.get(point) ){
			edgeListMap.get(p).remove(point);
		}
		edgeListMap.remove(point);
	}

	private void addEdgeBetween(Point secondPoint, Point point) {
		try{
			if(edgeListMap.get(secondPoint).contains(point) == false)
				edgeListMap.get(secondPoint).add(point);
		}catch(NullPointerException npe){
			System.out.println(" Error 1 " + npe);
		}
		
		try{
			if(edgeListMap.get(point).contains(secondPoint) == false)
				edgeListMap.get(point).add(secondPoint);
		}catch(NullPointerException npe){
			System.out.println(" Error 2 " + npe);
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("pressed "+ e.getPoint());
		 
	/*	
		Point p = isSamePoint(e.getPoint());
		System.out.println("pressed "+ e.getPoint() + " act point " + p );
		if(p != null ) {
			draggedPoint = new Point(p) ;
			dragged = true;
		}
	*/
	
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		/*
		if(dragged == true ){
			dragged = false;
			draggedPoint = null;
		}
		*/
		
	}

	private Point isSamePoint(Point newPoint) {
		Point retPoint = null;
		for (Point p : vertexList){
			if( Math.abs((double)(newPoint.x - p.x)) < vertexSize && 
					Math.abs((double)(newPoint.y - p.y)) < vertexSize ){
				System.out.println("same point as " + p.verNumber);
				retPoint = p;
				break;
			}
		}
			
		return retPoint;
	}

	private void drawNodes() {
		Graphics g = this.getGraphics();
		g.clearRect(0, 60, 700, 700);
		// 
		// This is essential for clarity
		//
		drawEdges();
		//
		//
		 
		for( Point v : vertexList){
			g.setColor(Color.BLUE); 
			g.fillRect(v.x - shift, v.y-shift, vertexSize, vertexSize);
			g.setColor(Color.WHITE);
			g.drawString( Integer.toString(v.verNumber) , v.x - 10 / 2 , v.y +  10 / 3 );
			
		}
		
	}

	private void drawEdges(){
		Graphics g = this.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(44, 102, 230, 180));
        g2.setStroke(new BasicStroke(2f));
        
		for ( Point keyPoint : edgeListMap.keySet() ){
			ArrayList<Point> adjPoint  = edgeListMap.get(keyPoint);
			for( Point p : adjPoint )
				g2.drawLine(p.x , p.y, keyPoint.x , keyPoint.y);
		}
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if( e.getSource().equals(buttonAddVertices)) {
			
			if(isaddVert == false){
				System.out.println("Add vertices - enabled");
				buttonAddEdges.setEnabled(false);
				buttonCompute.setEnabled(false);
				isaddVert = true;
				buttonAddVertices.setText("Done Vertices");
			}else if(isaddVert == true){
				System.out.println("Add vertices - disabled");
				buttonAddEdges.setEnabled(true);
				buttonCompute.setEnabled(true);
				isaddVert = false;
				buttonAddVertices.setText("Add Vertices");
				isDragable = true;
				buttonDrag.setEnabled(true);
			}
		}
		else if(e.getSource().equals(buttonAddEdges)){
			buttonAddVertices.setEnabled(true);
			buttonCompute.setEnabled(true);
			
			if(isaddEdge == false ){
				System.out.println("Add Edges - enabled");
				buttonAddEdges.setText("Done Edges");
				isaddEdge = true;
				buttonAddVertices.setEnabled(false);
				buttonCompute.setEnabled(false);
				
			}else {
				System.out.println("Add Edges - disabled");
				buttonAddEdges.setText("Add edges");
				isaddEdge = false;
				isDragable = true;
				buttonDrag.setEnabled(true);
				buttonAddVertices.setEnabled(true);
				buttonCompute.setEnabled(true);
			}
		}
		else if(e.getSource().equals(buttonCompute)){
			System.out.println("computing..");
			printAdj();
		
		}else if(e.getSource().equals(buttonDrag)) {
			if(buttonDrag.isEnabled() == false){
				buttonDrag.setEnabled(true);
				isDragable  = true;
				System.out.println("Drag - disabled");
			}
			else if(buttonDrag.isEnabled() == true){
				buttonDrag.setText("Disable Drag");
				isDragable  = false;
				System.out.println("Drag - enabled");
			}
		}else {
			
		}

	}

	private void printAdj() {
		for ( Point keyPoint : edgeListMap.keySet() ){
			ArrayList<Point> adjPoint  = edgeListMap.get(keyPoint);
			System.out.print( keyPoint.verNumber + " |= ");
			for( Point p : adjPoint ){
				System.out.print(" "+ p.verNumber );
			}
			System.out.println();;
		}
		
	}

}

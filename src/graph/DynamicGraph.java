/**
 * 
 */
package graph;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

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
	private JButton buttonAddVertices = new JButton("Add V");
	private JButton buttonAddEdges = new JButton("Add E");
	
	private JButton buttonCompute = new JButton("Compute");
	private JPanel panel = new JPanel();
	
	private boolean isaddVert = false;
	private boolean isaddEdge = true;
	private boolean isaddComp = false;
	
	private int vertexSize = 20, shift=10;
	
	private ArrayList<Point> vertexList= new ArrayList<Point>();
	/**
	 * @throws HeadlessException
	 */
	public DynamicGraph() throws HeadlessException {
		// TODO Auto-generated constructor stub
		setTitle("Dynamic graphs");
		
		addMouseListener(this);
		addMouseMotionListener(this);
		buttonCompute.addActionListener(this);
		buttonAddVertices.addActionListener(this);
		buttonAddEdges.addActionListener(this);
		
		panel.add(buttonAddVertices); 
		panel.add(buttonAddEdges); 
		panel.add(buttonCompute); 
		
		buttonAddVertices.setEnabled(isaddVert);
		buttonAddEdges.setEnabled(isaddEdge);
		buttonCompute.setEnabled(isaddComp);
		this.add(panel);		

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
		// TODO Auto-generated method stub

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
		if(e.getButton() == MouseEvent.BUTTON3){
			Point p = issamePoint(e.getPoint());
			if(p != null )
			{
				System.out.println("Remove v");
				vertexList.remove(p);
				drawGraph();
			}
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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		
		Point newPoint = e.getPoint();
		if(issamePoint(newPoint) == null )
			vertexList.add(newPoint);
		int x = e.getX();
		int y = e.getY();
		System.out.println("x "+ x + " y " + y );
		drawGraph();
	}

	private Point issamePoint(Point newPoint) {
		Point retPoint = null;
		for (Point p : vertexList){
			if( Math.abs((double)(newPoint.x - p.x)) < vertexSize && 
					Math.abs((double)(newPoint.y - p.y)) < vertexSize ){
				//System.out.println("same point as " + p);
				retPoint = p;
				break;
			}
		}
			
		return retPoint;
	}

	private void drawGraph() {
		Graphics g = this.getGraphics();
		g.clearRect(0, 60, 700, 700);
		int count = 1;
		for( Point v : vertexList){
			g.drawString( Integer.toString(count++) , v.x - 10 / 2 , v.y +  10 / 3 );
			g.drawRect(v.x - shift, v.y-shift, vertexSize, vertexSize);
			
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if( e.getSource().equals(buttonAddVertices)) {
		
		}
		else if(e.getSource().equals(buttonAddEdges)){
			buttonAddVertices.setEnabled(true);		
		}
		else if(e.getSource().equals(buttonCompute)){
			;
		}else{
			
		}

	}

}

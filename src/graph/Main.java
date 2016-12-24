/**
 * 
 */
package graph;

import javax.swing.JFrame;

/**
 * @author rajz
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				createGUI();
				
			}
		});

	}

	protected static void createGUI() {
		// TODO Auto-generated method stub
		// create frame window
		DynamicGraph dgraph = new DynamicGraph();
		dgraph.setVisible(true);
		dgraph.setSize(700, 600);
		dgraph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

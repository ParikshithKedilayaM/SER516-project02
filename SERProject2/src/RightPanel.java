import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * This class creates selected shape on the right panel with the help of x and y
 * coordinates
 * 
 * @author Raghavan
 * @version 1.0
 */
public class RightPanel extends JPanel {
	public static List<Shapes> rightPanelShapes = new ArrayList<Shapes> ();
	private static final long serialVersionUID = 1L;
	public static Shapes originShape;
	public static Shapes destShape;
	public static boolean isSelected = false;

	public static RightPanelMouseListener rightPanelMouseListener = new RightPanelMouseListener();
	public RightPanel() {
		this.setBackground(Color.WHITE);
		addMouseListener(rightPanelMouseListener);
		addMouseMotionListener(rightPanelMouseListener);
	}
	
	
	public static void drawLine() {
	 Line2D shape = new Line2D.Double();
     shape.setLine(originShape.getX(),originShape.getX(),originShape.getX(),originShape.getX());  
	}
	/**
	 * Uses graphics to draw different shape components
	 * @param graphics
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
			
			
		
			for(Shapes s: rightPanelShapes) {
				s.drawShape(graphics);
			}
		
		
	}

}

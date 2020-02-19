import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	public static List<Connections> lines = new ArrayList<Connections>();
	private static final long serialVersionUID = 1L;
	public static Shapes originShape;
	public static Shapes destShape;
	public static int oX, oY, dX, dY;
	public static boolean isSelected = false;
	public static boolean isMoved = false;
	public static RightPanelMouseListener rightPanelMouseListener = new RightPanelMouseListener();
	public static Dot dot = new Dot();
	
	public RightPanel() {
		this.setBackground(Color.WHITE);
		addMouseListener(rightPanelMouseListener);
		addMouseMotionListener(rightPanelMouseListener);
		addMouseListener(dot);
		addMouseMotionListener(dot);
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
			
			
			for(Connections l: lines) {
				Line2D shape = new Line2D.Double();
				shape.setLine(l.getSourceX(),l.getSourceY(),l.getDestX(),l.getDestY());
				Graphics2D g2 = (Graphics2D) graphics;
				g2.draw(shape);
			}
		
			if(isMoved) {
				Line2D shape = new Line2D.Double();
				shape.setLine(oX,oY,dX,dY);
				Graphics2D g2 = (Graphics2D) graphics;
				g2.draw(shape);
			}
	}

}

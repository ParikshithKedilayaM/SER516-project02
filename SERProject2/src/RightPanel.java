import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * This class creates selected shape on the right panel with the help of x and y
 * coordinates and maintains all the connection that are present in the right
 * panel.
 * 
 * @author Raghavan
 * @version 1.0
 * 
 * @author Raghavan
 * @version 2.0
 */
public class RightPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static List<Shapes> rightPanelShapes = new ArrayList<Shapes>();
	private static List<Connections> lines = new ArrayList<Connections>();
	private static Shapes originShape;
	private static Shapes destShape;
	private static int originX, originY, destinationX, destinationY;
	private static boolean isSelected = false;
	private static boolean isMoved = false;
	public static List<Shapes> getRightPanelShapes() {
		return rightPanelShapes;
	}

	public static void setRightPanelShapes(List<Shapes> rightPanelShapes) {
		RightPanel.rightPanelShapes = rightPanelShapes;
	}

	public static List<Connections> getLines() {
		return lines;
	}

	public static void setLines(List<Connections> lines) {
		RightPanel.lines = lines;
	}

	public static Shapes getOriginShape() {
		return originShape;
	}

	public static void setOriginShape(Shapes originShape) {
		RightPanel.originShape = originShape;
	}

	public static Shapes getDestShape() {
		return destShape;
	}

	public static void setDestShape(Shapes destShape) {
		RightPanel.destShape = destShape;
	}

	public static int getOriginX() {
		return originX;
	}

	public static void setOriginX(int originX) {
		RightPanel.originX = originX;
	}

	public static int getOriginY() {
		return originY;
	}

	public static void setOriginY(int originY) {
		RightPanel.originY = originY;
	}

	public static int getDestinationX() {
		return destinationX;
	}

	public static void setDestinationX(int destinationX) {
		RightPanel.destinationX = destinationX;
	}

	public static int getDestinationY() {
		return destinationY;
	}

	public static void setDestinationY(int destinationY) {
		RightPanel.destinationY = destinationY;
	}

	public static boolean isSelected() {
		return isSelected;
	}

	public static void setSelected(boolean isSelected) {
		RightPanel.isSelected = isSelected;
	}

	public static boolean isMoved() {
		return isMoved;
	}

	public static void setMoved(boolean isMoved) {
		RightPanel.isMoved = isMoved;
	}

	private RightPanelMouseListener rightPanelMouseListener = new RightPanelMouseListener();
	private Dot dot = new Dot();

	public RightPanel() {
		this.setBackground(Color.WHITE);
		addMouseListener(rightPanelMouseListener);
		addMouseMotionListener(rightPanelMouseListener);
		addMouseListener(dot);
		addMouseMotionListener(dot);
	}

	/**
	 * Uses graphics to draw different shape components
	 * 
	 * @param graphics
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		for (Shapes s : rightPanelShapes) {
			s.drawShape(graphics);
		}

		for (Connections l : lines) {
			Line2D shape = new Line2D.Double();
			shape.setLine(l.getSourceX(), l.getSourceY(), l.getDestX(), l.getDestY());
			Graphics2D g2 = (Graphics2D) graphics;
			g2.draw(shape);
		}

		if (isMoved) {
			Line2D shape = new Line2D.Double();
			shape.setLine(originX, originY, destinationX, destinationY);
			Graphics2D g2 = (Graphics2D) graphics;
			g2.draw(shape);
		}
	}

}

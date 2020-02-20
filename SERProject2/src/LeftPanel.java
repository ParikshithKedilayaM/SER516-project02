import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * This class is used to create the panel on the left side
 * which has different shapes.
 * 
 * @author Raghavan
 * @version 1.0
 */
public class LeftPanel extends JPanel {
	public static List<Shapes> leftPanelShapes = new ArrayList<Shapes> ();
	/**
	 * Defining x and y coordinates for the shapes
	 * to start. x from the left of the frame and y
	 * from the top of the frame.
	 */
	static final int X_COORDINATE_SHAPES = 100;
	static final int Y_COORDINATE_SQUARE = 100;
	static final int Y_COORDINATE_CIRCLE = 350;
	static final int Y_COORDINATE_TRIANGLE = 650;

	private static final long serialVersionUID = 1L;
	/**
	 * Uses graphics to draw components
	 * @param graphics
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Shapes square = new Square(X_COORDINATE_SHAPES, Y_COORDINATE_SQUARE);
		square.drawShape(graphics);
		leftPanelShapes.add(square);
		Shapes circle = new Circle(X_COORDINATE_SHAPES, Y_COORDINATE_CIRCLE);
		circle.drawShape(graphics);
		leftPanelShapes.add(circle);
		Shapes triangle = new Triangle(X_COORDINATE_SHAPES, Y_COORDINATE_TRIANGLE);
		triangle.drawShape(graphics);
		leftPanelShapes.add(triangle);
		this.addMouseListener(new LeftPanelMouseListener());
	}
	
}

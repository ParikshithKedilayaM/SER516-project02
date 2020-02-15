import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

/**
 * Adding mouse listeners for providing mouse click functionality
 * to add the shape to the right panel.
 * 
 * @author Parikshith Kedilaya Mallar
 * @version 1.0
 */
public class LeftPanelMouseListener extends MouseAdapter {
	
	private static boolean isTriangleClicked = false;
	private static boolean isSquareClicked = false;
	private static boolean isCircleClicked = false;
	
	public static boolean isTriangleClicked() {
		return isTriangleClicked;
	}

	public static void setTriangleClicked(boolean isTriangleClicked) {
		LeftPanelMouseListener.isTriangleClicked = isTriangleClicked;
	}

	public static boolean isSquareClicked() {
		return isSquareClicked;
	}

	public static void setSquareClicked(boolean isSquareClicked) {
		LeftPanelMouseListener.isSquareClicked = isSquareClicked;
	}

	public static boolean isCircleClicked() {
		return isCircleClicked;
	}

	public static void setCircleClicked(boolean isCircleClicked) {
		LeftPanelMouseListener.isCircleClicked = isCircleClicked;
	}
	
	/**
	 * Overridden method to add mouse click event handler.
	 * Used to track which shape has been clicked on the left panel,
	 * so that only that shape can be created on the right panel.
	 */
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		int x = mouseEvent.getX();
		int y = mouseEvent.getY();
		ListIterator<Shapes> iterator = LeftPanel.leftPanelShapes.listIterator();
		while(iterator.hasNext()) {
			Shapes next = iterator.next();
			if (next instanceof Circle) {
				if (next.containsPoint(x, y)) {
					markIsClickedTrue(ShapesEnum.CIRCLE);
					System.out.println("Circle clicked");
				}
			} else if (next instanceof Square) {
				if (next.containsPoint(x, y)) {
					markIsClickedTrue(ShapesEnum.SQUARE);
					System.out.println("Square clicked");
				}
			} else if (next instanceof Triangle) {
				if (next.containsPoint(x, y)) {
					markIsClickedTrue(ShapesEnum.TRIANGLE);
					System.out.println("Triangle clicked");
				}
			}
		}
	}

    /**
	 * Instantiates or uses the created instance of different shapes' class
	 * and sets isClicked value as true for the shape which was clicked.
	 * @param shape
	 */
	public static void markIsClickedTrue(ShapesEnum shape) {
		try {
			if (ShapesEnum.SQUARE == shape) {
				setTriangleClicked(false);
				setSquareClicked(true);
				setCircleClicked(false);
			}
			if (ShapesEnum.TRIANGLE == shape) {
				setTriangleClicked(true);
				setSquareClicked(false);
				setCircleClicked(false);
			}
			if (ShapesEnum.CIRCLE == shape) {
				setTriangleClicked(false);
				setSquareClicked(false);
				setCircleClicked(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns the shape that is clicked.
	 */
	public static ShapesEnum getSelectedShape() {
		if (isSquareClicked()) {
			return ShapesEnum.SQUARE;
		} else if (isTriangleClicked()) {
			return ShapesEnum.TRIANGLE;
		} else if (isCircleClicked()) {
			return ShapesEnum.CIRCLE;
		} else {
			return null;
		}
	}
	
}

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class adds mouse listener for the shapes, dots and vertical bars in the
 * right panel.
 * 
 * @author Parikshit
 * @version 1.0
 */
public class RightPanelMouseListener implements MouseListener, MouseMotionListener {

	private Shapes selectedShape;
	private List<Line> linesList = new ArrayList<Line>();

	/**
	 * Change the coordinates of the connection line when a shape on the right panel
	 * is moved to a different point.
	 * 
	 * @param e - MouseEvent
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (selectedShape != null) {
			selectedShape.setX(e.getX());
			selectedShape.setY(e.getY());
		}
		ListIterator<Line> linesListIter = linesList.listIterator();
		while (linesListIter.hasNext()) {
			Line nextLine = linesListIter.next();
			if (nextLine.isSourceShape()) {
				nextLine.getLine().setSourceX(nextLine.getLineX() - (nextLine.getShapeX() - selectedShape.getX()));
				nextLine.getLine().setSourceY(nextLine.getLineY() - (nextLine.getShapeY() - selectedShape.getY()));
			} else if (nextLine.isDestShape()) {
				nextLine.getLine().setDestX(nextLine.getLineX() - (nextLine.getShapeX() - selectedShape.getX()));
				nextLine.getLine().setDestY(nextLine.getLineY() - (nextLine.getShapeY() - selectedShape.getY()));
			}
		}
		RightPanel.setSelected(false);
		Frame.rightPanel.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	/**
	 * Check a click event if the click is on a dot or vertical bar
	 * 
	 * @param e - MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!Dot.isDotClicked && !RightPanel.isMoved() && !Dot.isBarClicked) {
			int x = e.getX();
			int y = e.getY();
			ShapesEnum selectedShape = LeftPanelMouseListener.getSelectedShape();
			if (selectedShape == ShapesEnum.SQUARE) {
				RightPanel.getRightPanelShapes().add(new Square(x, y));
			} else if (selectedShape == ShapesEnum.TRIANGLE) {
				RightPanel.getRightPanelShapes().add(new Triangle(x, y));
			} else if (selectedShape == ShapesEnum.CIRCLE) {
				Circle c = new Circle(x, y);
				RightPanel.getRightPanelShapes().add(c);
			}
			Frame.rightPanel.repaint();
		}
	}

	/**
	 * Method to move the shapes when the mouse is pressed while dragging in the
	 * shape.
	 * 
	 * @param e - MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		ListIterator<Shapes> shapes = RightPanel.getRightPanelShapes().listIterator();
		while (shapes.hasNext()) {
			Shapes sh = shapes.next();
			if (sh.containsPoint(e.getX(), e.getY())) {
				selectedShape = sh;
			}

		}
		ListIterator<Connections> lines = RightPanel.getLines().listIterator();
		while (lines.hasNext()) {
			Connections line = lines.next();
			if (line.getOriginShape().equals(selectedShape)) {
				Line drawline = new Line();
				drawline.setLineX(line.getSourceX());
				drawline.setLineY(line.getSourceY());
				drawline.setShapeX(selectedShape.getX());
				drawline.setShapeY(selectedShape.getY());
				drawline.setLine(line);
				drawline.setSourceShape(true);
				linesList.add(drawline);
			} else if (line.getDestShape().equals(selectedShape)) {
				Line drawline = new Line();
				drawline.setLineX(line.getDestX());
				drawline.setLineY(line.getDestY());
				drawline.setShapeX(selectedShape.getX());
				drawline.setShapeY(selectedShape.getY());
				drawline.setLine(line);
				drawline.setDestShape(true);
				linesList.add(drawline);
			}
		}

	}

	/**
	 * Reset the selectedShape when mouse is released.
	 * 
	 * @param e - MouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		selectedShape = null;
		linesList.clear();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}

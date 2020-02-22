import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Raghavan
 * @version 1.0
 */
public class Dot extends Shapes implements MouseListener, MouseMotionListener, Serializable {
	double x, y;
	static public boolean isBarClicked = false;
	static boolean isDotClicked = false, firstDotClicked = false;
	static int sourceX, sourceY, destinationX, destinationY;
	static Shapes firstShape, secondShape;
	Shape circle = null;

	public Dot(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Dot() {

	}

	/**
	 * Draws a Circle using Ellipse2D
	 * 
	 * @param graphic
	 * @param x       - x coordinate for the shape
	 * @param y       - y coordinate for the shape
	 */
	@Override
	public void drawShape(Graphics graphic) {
		circle = new Ellipse2D.Double(x, y, 10, 10);
		Graphics2D graphics2 = (Graphics2D) graphic;
		graphics2.fill(circle);

	}

	public boolean containsPoint(int x, int y) {
		return circle.contains(x, y);
	}

	@Override
	public int getX() {
		return (int) x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return (int) y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if ( !RightPanel.isSelected && isDotClicked ) {
			Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
			while (shapeIterator.hasNext()) {
				Shapes shape = shapeIterator.next();
				if (shape.containsPoint(e.getX(), e.getY()) && !getIsLineDrawn(shape, e.getX(), e.getY())) {
					RightPanel.originShape = shape;
					firstShape = shape;
					sourceX = e.getX();
					sourceY = e.getY();
					isDotClicked = false;
					firstDotClicked = true;
					RightPanel.isMoved = true;
					Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
					Frame.rightPanel.setCursor(cursor);
					Frame.rightPanel.setVisible(true);
					break;
				}
			}
			RightPanel.isSelected = true;

		}
		
		else if ( !RightPanel.isSelected && isBarClicked ) {
			Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
			while (shapeIterator.hasNext()) {
				Shapes shape = shapeIterator.next();
				if (shape.containsPoint(e.getX(), e.getY()) && !getIsLineDrawn(shape, e.getX(), e.getY())) {
					RightPanel.originShape = shape;
					firstShape = shape;
					sourceX = e.getX();
					sourceY = e.getY();
					isBarClicked = false;
					firstDotClicked = true;
					RightPanel.isMoved = true;
					Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
					Frame.rightPanel.setCursor(cursor);
					Frame.rightPanel.setVisible(true);
					break;
				}
			}
			RightPanel.isSelected = true;

		}

		else if (RightPanel.isSelected && isDotClicked) {

			if (!RightPanel.originShape.containsPoint(e.getX(), e.getY())) {
				Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
				while (shapeIterator.hasNext()) {
					Shapes shape = shapeIterator.next();
					if (shape.containsPoint(e.getX(), e.getY()) && !getIsLineDrawn(shape, e.getX(), e.getY())
							&& !getIsLineDrawn(firstShape, e.getX(), e.getY())) {
						destinationX = e.getX();
						destinationY = e.getY();

						secondShape = shape;
						Connections line = new Connections();
						line.setSourceX(sourceX);
						line.setDestX(destinationX);
						line.setSourceY(sourceY);
						line.setDestY(destinationY);
						line.setOriginShape(firstShape);
						line.setDestShape(secondShape);
						RightPanel.lines.add(line);
						setIsLineDrawn(firstShape, sourceX, sourceY);
						setIsLineDrawn(secondShape, e.getX(), e.getY());
						firstDotClicked = false;
						RightPanel.isMoved = false;
						Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
						Frame.rightPanel.setCursor(cursor);
						Frame.rightPanel.setVisible(true);
						RightPanel.isSelected = false;
						break;
					}

				}

				Frame.rightPanel.repaint();
			}
			isDotClicked = false;
		}

		else if ( RightPanel.isSelected && isBarClicked) {
			if (!RightPanel.originShape.containsPoint(e.getX(), e.getY())) {
				Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
				while (shapeIterator.hasNext()) {
					Shapes shape = shapeIterator.next();
					if (shape.containsPoint(e.getX(), e.getY()) && !getIsLineDrawn(firstShape, e.getX(), e.getY())) {
						destinationX = e.getX();
						destinationY = e.getY();
						secondShape = shape;
						Connections line = new Connections();
						line.setSourceX(sourceX);
						line.setDestX(destinationX);
						line.setSourceY(sourceY);
						line.setDestY(destinationY);
						line.setOriginShape(firstShape);
						line.setDestShape(secondShape);
						RightPanel.lines.add(line);
						setIsLineDrawn(firstShape, sourceX, sourceY);
						firstDotClicked = false;
						RightPanel.isMoved = false;
						RightPanel.isSelected = false;
						Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
						Frame.rightPanel.setCursor(cursor);
						Frame.rightPanel.setVisible(true);
						break;

					}
				}
				Frame.rightPanel.repaint();
			}
			isDotClicked = false;
			isBarClicked = false;
		}

		else {
			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
			Frame.rightPanel.setCursor(cursor);
			Frame.rightPanel.setVisible(true);
			RightPanel.isMoved = false;
			RightPanel.isSelected = false;
		}

	}

	private void setIsLineDrawn(Shapes shape, int x, int y) {
		if (shape instanceof Circle) {
			Circle circle = ((Circle) shape);
			circle.setLineDrawn(true);
		} else if (shape instanceof Triangle) {
			Triangle triangle = (Triangle) shape;
			if (triangle.dot1.containsPoint(x, y)) {
				triangle.isLineDrawnDot1 = true;
			} else if (triangle.dot2.containsPoint(x, y)) {
				triangle.isLineDrawnDot2 = true;
			} else if (triangle.dot3.containsPoint(x, y)) {
				triangle.isLineDrawnDot3 = true;
			}
		}
	}

	private boolean getIsLineDrawn(Shapes shape, int x, int y) {
		if (shape instanceof Circle) {
			Circle circle = ((Circle) shape);
			return circle.isLineDrawn();
		} else if (shape instanceof Triangle) {
			Triangle triangle = (Triangle) shape;
			if (triangle.dot1.containsPoint(x, y)) {
				return triangle.isLineDrawnDot1;
			} else if (triangle.dot2.containsPoint(x, y)) {
				return triangle.isLineDrawnDot2;
			} else if (triangle.dot3.containsPoint(x, y)) {
				return triangle.isLineDrawnDot3;
			}
		}
		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ListIterator<Shapes> shapes = RightPanel.rightPanelShapes.listIterator();
		while (shapes.hasNext()) {
			Shapes sh = shapes.next();
			if (sh.containsPoint(e.getX(), e.getY())) {
				if (sh instanceof Circle && ((Circle) sh).getDot().containsPoint(e.getX(), e.getY())) {
					isDotClicked = true;
					break;
				}

				else if (sh instanceof Triangle && (((Triangle) sh).dot1.containsPoint(e.getX(), e.getY())
						|| ((Triangle) sh).dot2.containsPoint(e.getX(), e.getY())
						|| ((Triangle) sh).dot3.containsPoint(e.getX(), e.getY()))) {
					isDotClicked = true;
					break;
				}

				else if (sh instanceof Square && (((Square) sh).bar1.containsPoint(e.getX(), e.getY())
						|| ((Square) sh).bar2.containsPoint(e.getX(), e.getY()))) {
					isBarClicked = true;
					break;
				}
				else {
					isDotClicked = false;
					isBarClicked = false;
				}

			}
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (firstDotClicked) {
			RightPanel.originX = sourceX;
			RightPanel.originY = sourceY;
			RightPanel.destinationX = e.getX();
			RightPanel.destinationY = e.getY();
			Frame.rightPanel.repaint();
		}

	}

}

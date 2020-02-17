import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Raghavan
 * @version 1.0
 */
public class Dot extends Shapes implements MouseListener, MouseMotionListener{
	double x, y;
	public boolean isConnected = false;
	static boolean isDotClicked = false, firstDotClicked = false;
	static int sX, sY, dX, dY;
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
		// TODO Auto-generated method stub
		return (int) x;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		this.x = x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (int) y;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y = y;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (!isConnected && !RightPanel.isSelected && isDotClicked) {
			Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
			while (shapeIterator.hasNext()) {
				Shapes shape = shapeIterator.next();
				if (shape.containsPoint(e.getX(), e.getY()) && !getIsLineDrawn(shape, e.getX(), e.getY())) {
					RightPanel.originShape = shape;
					firstShape = shape;
					sX = e.getX();
					sY = e.getY();
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

		else if (!isConnected && RightPanel.isSelected && isDotClicked) {


			if (!RightPanel.originShape.containsPoint(e.getX(), e.getY())) {
				Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
				while (shapeIterator.hasNext()) {
					Shapes shape = shapeIterator.next();
					if (shape.containsPoint(e.getX(), e.getY()) && !getIsLineDrawn(shape, e.getX(), e.getY())
							&& !getIsLineDrawn(firstShape, e.getX(), e.getY())) {
						dX = e.getX();
						dY = e.getY();

						secondShape = shape;
						Connections line = new Connections();
						line.setSourceX(sX);
						line.setDestX(dX);
						line.setSourceY(sY);
						line.setDestY(dY);
						line.setOriginShape(firstShape);
						line.setDestShape(secondShape);
						RightPanel.lines.add(line);
						
						///Find the shape
						setIsLineDrawn(firstShape, e.getX(), e.getY());
						setIsLineDrawn(secondShape, e.getX(), e.getY());
						firstDotClicked = false;
						RightPanel.isMoved = false;
						Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		                Frame.rightPanel.setCursor(cursor);
		                Frame.rightPanel.setVisible(true);
						break;
					}
					
				}

				
				Frame.rightPanel.repaint();
			}
			RightPanel.isSelected = false;
			RightPanel.isLineDrawable = true;
			isDotClicked = false;
		} else {
			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
            Frame.rightPanel.setCursor(cursor);
            Frame.rightPanel.setVisible(true);
            RightPanel.isMoved = false;
		}

	}
	
	private void setIsLineDrawn(Shapes shape, int x, int y) {
		if (shape instanceof Circle) {
			Circle circle = ((Circle) shape);
			circle.isLineDrawn = true;
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
			Circle temp = ((Circle) shape);
			return temp.isLineDrawn;
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
		// TODO Auto-generated method stub
		ListIterator<Shapes> shapes = RightPanel.rightPanelShapes.listIterator();
		while (shapes.hasNext()) {
			Shapes sh = shapes.next();
			if (sh.containsPoint(e.getX(), e.getY())) {
				if (sh instanceof Circle && ((Circle) sh).dot.containsPoint(e.getX(), e.getY())) {
					isDotClicked = true;
				}

				else if (sh instanceof Triangle && (((Triangle) sh).dot1.containsPoint(e.getX(), e.getY())
						|| ((Triangle) sh).dot2.containsPoint(e.getX(), e.getY())
						|| ((Triangle) sh).dot3.containsPoint(e.getX(), e.getY()))) {
					isDotClicked = true;
				}

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (firstDotClicked) {
			RightPanel.oX = sX;
			RightPanel.oY = sY;
			RightPanel.dX = e.getX();
			RightPanel.dY = e.getY();
			Frame.rightPanel.repaint();
		}
		
	}

}

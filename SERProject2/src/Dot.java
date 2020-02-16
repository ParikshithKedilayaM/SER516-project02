import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Raghavan
 * @version 1.0
 */
public class Dot extends Shapes implements MouseListener {
	double x, y;
	boolean isConnected = false, isLineDrawn = false;
	static boolean isDotClicked = false;
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
				if (shape.containsPoint(e.getX(), e.getY())) {
					RightPanel.originShape = shape;
					System.out.println(shape.getClass());
					firstShape = shape;
					sX = e.getX();
					sY = e.getY();
					isDotClicked = false;
					break;
				}
			}
			RightPanel.isSelected = true;

		}

		else if (!isConnected && RightPanel.isSelected && isDotClicked) {

			ListIterator<Connections> linesOnRight = RightPanel.lines.listIterator();
			boolean checkCircle = true;
//			while (linesOnRight.hasNext()) {
//				Connections conn = linesOnRight.next();
//				if ((conn.getOriginShape() instanceof Circle || conn.getDestShape() instanceof Circle)
//						&& (firstShape instanceof Circle || secondShape instanceof Circle)) {
//					System.out.println("Circle check list");
//					checkCircle = false;
//				}
//
//			}

			if (checkCircle && !RightPanel.originShape.containsPoint(e.getX(), e.getY())) {
				System.out.println("Second dot click");
				Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
				while (shapeIterator.hasNext()) {
					Shapes shape = shapeIterator.next();
					if (shape.containsPoint(e.getX(), e.getY())) {
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
						System.out.println(line);
						
						break;
					}
				}

				System.out.println("Hello mf");
				RightPanel.isSelected = false;
				RightPanel.isLineDrawable = true;
				isDotClicked = false;
				Frame.rightPanel.repaint();
			}
		}

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
					System.out.println("Circle's dot clicked");
				}

				else if (sh instanceof Triangle && (((Triangle) sh).dot1.containsPoint(e.getX(), e.getY())
						|| ((Triangle) sh).dot2.containsPoint(e.getX(), e.getY())
						|| ((Triangle) sh).dot3.containsPoint(e.getX(), e.getY()))) {
					isDotClicked = true;
					System.out.println("Triangle's dot clicked");
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

}

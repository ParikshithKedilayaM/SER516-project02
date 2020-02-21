import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * @author Raghavan
 * @version 1.0
 */
public class VerticalBar extends Shapes implements MouseListener, MouseMotionListener, Serializable {
	private static final long serialVersionUID = 1L;
	Shape square = null;
	double x, y;

	public VerticalBar(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Draws a square using Rectangle2D
	 * 
	 * @param graphic
	 * @param x       - x coordinate for the shape
	 * @param y       - y coordinate for the shape
	 */
	@Override
	public void drawShape(Graphics graphic) {
		square = new Rectangle2D.Double(x, y, 7, 180);
		Graphics2D graphics2 = (Graphics2D) graphic;
		graphics2.fill(square);

	}

	/**
	 * Method to check if a point is inside the vertical bar
	 * 
	 * @param x - x coordinate of the point
	 * @param y - y coordinate of the point
	 */
	@Override
	public boolean containsPoint(int x, int y) {
		return square.contains(x, y);
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
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

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

}

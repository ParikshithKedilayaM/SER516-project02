import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * @author Raghavan
 * @version 1.0
 */
public class Dot extends Shapes {
	double x, y;
	Shape circle = null;
	public Dot(double x, double y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

	/**
	 * Draws a Circle using Ellipse2D
	 * @param graphic
	 * @param x - x coordinate for the shape
	 * @param y - y coordinate for the shape
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
	public String toString() {
		return "Circle [x=" + x + ", y=" + y + ", circle=" + circle + "]";
	}
	
	

}

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

/**
 * @author Raghavan
 * @version 1.0
 */
public class Circle extends Shapes implements Serializable{
	double x, y;
	Shape circle = null;
	Shapes dot = null;
	boolean isLineDrawn = false;

	
	public Circle(double x, double y) {
		this.x = x - 50;
		this.y = y - 50;
		
	}

	/**
	 * Draws a Circle using Ellipse2D
	 * @param graphic
	 * @param x - x coordinate for the shape
	 * @param y - y coordinate for the shape
	 */
	@Override
	public void drawShape(Graphics graphic) {
		circle = new Ellipse2D.Double(x, y, 200, 200);
		Graphics2D graphics2 = (Graphics2D) graphic;
		dot = new Dot(x+100-5, y+100-5);
		dot.drawShape(graphics2);
		graphics2.draw(circle);		
		
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
		this.x = x - 50;
	}
	@Override
	public int getY() {
		return (int) y;
	}
	@Override
	public void setY(int y) {
		this.y = y - 50;
	}

	@Override
	public String toString() {
		return "Circle [x=" + x + ", y=" + y + ", circle=" + circle + "]";
	}
	
	

}

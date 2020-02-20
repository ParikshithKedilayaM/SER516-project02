import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *@author Raghavan
 *@version 1.0
 */
public class Square extends Shapes implements Serializable {
	
	Shape square = null;
	double x,y;
	Shapes bar1 = null, bar2 = null;
	public Square(double x, double y) {
		this.x = x - 50;
		this.y = y - 50;
	}
	/**
	 * Draws a square using Rectangle2D
	 * @param graphic
	 * @param x - x coordinate for the shape
	 * @param y - y coordinate for the shape
	 */
	@Override
	public void drawShape(Graphics graphic) {
		square = new Rectangle2D.Double(x , y , 200, 200);
		Graphics2D graphics2 = (Graphics2D) graphic;
		bar1 = new VerticalBar(x+10, y +10);
		bar1.drawShape(graphics2);
		bar2 = new VerticalBar(x+180, y +10);
		bar2.drawShape(graphics2);
		graphics2.draw(square);
		
		
	}

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
	
	

	
}

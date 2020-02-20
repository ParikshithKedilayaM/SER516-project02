import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.io.Serializable;

/**
 * @author Rishika Bera
 * @version 1.0
 */
public class Triangle extends Shapes implements Serializable {
	double x, y;
	Path2D path = null;
	Shapes dot1 = null, dot2 = null, dot3= null;
	boolean isLineDrawnDot1 = false, isLineDrawnDot2 = false, isLineDrawnDot3 = false; 
	public Triangle(double x, double y) {
		this.x = x - 50;
		this.y = y + 50;
	}
	/**
	 * Draw a triangle using Path2D
	 * @param graphic
	 * @param x - x coordinate for the shape
	 * @param y - y coordinate for the shape
	 */
	@Override
	public void drawShape(Graphics graphic) {
		double x1 = x , y1 = y ;
		
		path = new Path2D.Double();
		path.moveTo(x1, y1);
		path.lineTo(x1 + 100, y1 - 150);
		path.lineTo(x1 + 200, y1);
		path.closePath();
		Graphics2D g2 = (Graphics2D) graphic;
		dot1 = new Dot(x1+15, y1-15);
		dot1.drawShape(g2);
		dot2 = new Dot(x1+175, y1-15);
		dot2.drawShape(g2);
		dot3 = new Dot(x1+94, y1-135);
		dot3.drawShape(g2);
		g2.draw(path);
		
		
	}

	@Override
	public boolean containsPoint(int x, int y) {
		return path.contains(x, y);
	}
	@Override
	public int getX() {
		return (int) x;
	}
	@Override
	public void setX(int x) {
		this.x = x -50 ;
	}
	@Override
	public int getY() {
		return (int) y;
	}
	@Override
	public void setY(int y) {
		this.y = y + 50;
	}
	
}

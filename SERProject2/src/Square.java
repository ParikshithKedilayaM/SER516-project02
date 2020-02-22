import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * This class has Square information and its graphics
 * 
 * @author Mayank Kataruka
 * @version 1.0
 */

public class Square extends Shapes implements Serializable {

	private static final long serialVersionUID = 1L;
	private Shape square = null;
	private double x, y;
	private Shapes leftBar = null, rightBar = null;
	private final int OFFSET = 50;

	public Square(double x, double y) {
		this.x = x - OFFSET;
		this.y = y - OFFSET;
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
		square = new Rectangle2D.Double(x, y, 200, 200);
		Graphics2D graphics2 = (Graphics2D) graphic;
		leftBar = new VerticalBar(x + 10, y + 10);
		leftBar.drawShape(graphics2);
		rightBar = new VerticalBar(x + 180, y + 10);
		rightBar.drawShape(graphics2);
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
		this.x = x - OFFSET;
	}

	@Override
	public int getY() {
		return (int) y;
	}

	@Override
	public void setY(int y) {
		this.y = y - OFFSET;
	}

	public Shape getSquare() {
		return square;
	}

	public void setSquare(Shape square) {
		this.square = square;
	}
	
	public Shapes getLeftBar() {
		return leftBar;
	}

	public void setLeftBar(Shapes leftBar) {
		this.leftBar = leftBar;
	}

	public Shapes getRightBar() {
		return rightBar;
	}

	public void setRightBar(Shapes rightBar) {
		this.rightBar = rightBar;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

}

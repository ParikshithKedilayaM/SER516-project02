import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;

/**
 * @author Raghavan
 * @version 1.0
 */
public class Dot extends Shapes implements MouseListener {
	double x, y;
	boolean isConnected = false;
	Shape circle = null;
	public Dot(double x, double y) {
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
	public void mouseClicked(MouseEvent e) {
		if (!isConnected && !RightPanel.isSelected) {
			Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
			while(shapeIterator.hasNext()) {
				Shapes shape = shapeIterator.next();
				if(shape.containsPoint(e.getX(),e.getY())) {
					RightPanel.originShape = shape;
					break;
				}
			}
			RightPanel.isSelected = true;
		}
		
		else if(!isConnected && RightPanel.isSelected) {
			if (!RightPanel.originShape.containsPoint(e.getX(), e.getY())) {
			Iterator<Shapes> shapeIterator = RightPanel.rightPanelShapes.iterator();
			while(shapeIterator.hasNext()) {
				Shapes shape = shapeIterator.next();
				if(shape.containsPoint(e.getX(),e.getY())) {
					RightPanel.destShape = shape;
					break;
				}
			}
			
			
			RightPanel.isSelected = false;
			RightPanel.drawLine();
		}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ListIterator;

public class RightPanelMouseListener extends RightPanel implements MouseListener, MouseMotionListener {
	public RightPanelMouseListener() {
	}
	
	Shapes selectedShape;
	@Override
	public void mouseDragged(MouseEvent e) {
		if (selectedShape != null) {
			
			selectedShape.setX(e.getX());
			selectedShape.setY(e.getY());
		}

		Frame.rightPanel.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() ;
		int y = e.getY();

		
		ShapesEnum selectedShape = LeftPanelMouseListener.getSelectedShape();
		if (selectedShape == ShapesEnum.SQUARE) {
			RightPanel.rightPanelShapes.add(new Square( x, y));

		} else if (selectedShape == ShapesEnum.TRIANGLE) {
			RightPanel.rightPanelShapes.add(new Triangle( x, y));

		} else if (selectedShape == ShapesEnum.CIRCLE) {
			Circle c = new Circle(x, y);
			RightPanel.rightPanelShapes.add(c);
			System.out.println(c);

		}
		
		Frame.rightPanel.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ListIterator<Shapes> shapes = RightPanel.rightPanelShapes.listIterator();
		while(shapes.hasNext()) {
			Shapes sh = shapes.next();
			if(sh.containsPoint(e.getX(), e.getY())) {
				selectedShape = sh;
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
	 

}

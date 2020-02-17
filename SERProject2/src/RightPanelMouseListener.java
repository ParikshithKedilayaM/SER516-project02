import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RightPanelMouseListener extends RightPanel implements MouseListener, MouseMotionListener {
	public RightPanelMouseListener() {
	}
	
	Shapes selectedShape;
	Connections draggedShape;
	boolean isSource = false;
	List<Connections> triangleConn = new ArrayList<Connections>();
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (selectedShape != null) {
			
			selectedShape.setX(e.getX());
			selectedShape.setY(e.getY());
		}
		if (draggedShape != null) {

			
			if (isSource) {
				if (draggedShape.getOriginShape() instanceof Circle) {
					Circle circle = (Circle) selectedShape;
					draggedShape.setSourceX(circle.dot.getX());
					draggedShape.setSourceY(circle.dot.getY());
				} else if (draggedShape.getOriginShape() instanceof Triangle) {
					ListIterator<Connections> list = triangleConn.listIterator();
//					while(list.hasNext()) {
//						Connections next = list.next();
//						next.setSourceX(selectedShape.);
//						next.setSourceY(sourceY);
//					}
					
					Triangle triangle =(Triangle) selectedShape;
					Triangle triangle1 = (Triangle) draggedShape.getOriginShape();
					
				}

			} else {
				if (draggedShape.getDestShape() instanceof Circle) {
					Circle circle = (Circle) selectedShape;
					draggedShape.setDestX(circle.dot.getX());
					draggedShape.setDestY(circle.dot.getY());
				} else if (draggedShape.getDestShape() instanceof Triangle) {
					Triangle triangle =(Triangle) selectedShape;
					Triangle triangle1 = (Triangle) draggedShape.getDestShape();
					
			}

		}
		}

		Frame.rightPanel.repaint();
		
		
	
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!Dot.isDotClicked && !RightPanel.isMoved) {
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
			

		}
		
		Frame.rightPanel.repaint();
		}
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
		if (selectedShape != null && selectedShape instanceof Circle) {
			ListIterator<Connections> connection =RightPanel.lines.listIterator();
			System.out.println(RightPanel.lines);
			while (connection.hasNext()) {
				Connections sh = connection.next();
				Shapes sourceShape = sh.getOriginShape();
				Shapes destShape = sh.getDestShape();
				if(selectedShape==sourceShape) {
					if(selectedShape instanceof Triangle) {
						triangleConn.add(sh);
					} else {
						draggedShape = sh; 
					}
					isSource = true;
				} else if (selectedShape == destShape) {
					isSource = false;
					if(selectedShape instanceof Triangle) {
						triangleConn.add(sh);
					} else {
						draggedShape = sh; 
					}
				}
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(draggedShape != null) {
			draggedShape = null;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	 

}

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class creates selected shape on the right panel with the help of x and y
 * coordinates
 * 
 * @author Raghavan
 * @version 1.0
 */
public class RightPanel extends JPanel {
	public static List<Shapes> rightPanelShapes = new ArrayList<Shapes> ();
	static JLabel xLabel = new JLabel();
	static JLabel yLabel = new JLabel();
	private static final long serialVersionUID = 1L;

	public static RightPanelMouseListener rightPanelMouseListener = new RightPanelMouseListener();
	public RightPanel() {
		this.setBackground(Color.WHITE);
		addMouseListener(rightPanelMouseListener);
		addMouseMotionListener(rightPanelMouseListener);
		xLabel.setText("X");
		yLabel.setText("Y");
		xLabel.setBounds(500, 5, 10, 10);
		yLabel.setBounds(600, 5, 10, 10);
		this.add(xLabel);
		this.add(yLabel);
	}

	/**
	 * Uses graphics to draw different shape components
	 * @param graphics
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
			
			
		
			for(Shapes s: rightPanelShapes) {
				s.drawShape(graphics);
			}
		
		
	}

}

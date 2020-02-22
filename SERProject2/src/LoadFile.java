import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Adding Load file functionality to load the saved file into the application 
 * 
 * @author Chandan Kiragadalu Javaregowda
 * @version 1.0
 */
public class LoadFile extends JMenuItem implements ActionListener{

	private static final long serialVersionUID = 1L;

	public LoadFile(String label) {
		super(label);
	}
	public  String fileName ;

	public void loadFile() throws IOException {
		FileInputStream  fileIn = null;
		ObjectInputStream in = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser", "ser");
			chosenFile.setFileFilter(filter);
			int showOpenDialog  = chosenFile.showOpenDialog(null);
			if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath();
				fileIn = new FileInputStream(fileName);
				in = new ObjectInputStream(fileIn);
				RightPanel.lines = (ArrayList<Connections>) in.readObject();
				RightPanel.rightPanelShapes = (ArrayList<Shapes>) in.readObject();
				Frame.rightPanel.repaint();
			}
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e) {
		}
		
		finally {
			if (in != null) {
				in.close();
			}
			if (fileIn != null) {
				fileIn.close();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			loadFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

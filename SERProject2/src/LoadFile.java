import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadFile extends JMenuItem implements ActionListener{

	public LoadFile(String label) {
		super(label);
	}
	public  String fileName ;
	public void loadFile() throws IOException {
		FileInputStream  fileIn = null;
		ObjectInputStream in = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("SER516", "ser");
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
		// TODO Auto-generated method stub
		try {
			loadFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}

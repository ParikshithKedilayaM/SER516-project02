import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 * Adding Save file functionality to save the shapes and connections in a file
 * 
 * @author Rishika Bera
 * @version 1.0
 */

public class SaveFile extends JMenuItem implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final String FILE_EXT = ".ser";

	public SaveFile(String label) {
		super(label);
	}

	private String fileName;

	@Override
	public void actionPerformed(ActionEvent e) {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			int showSaveDialog = chosenFile.showSaveDialog(null);
			if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath().toString() + FILE_EXT;
			}
			fileOut = new FileOutputStream(new File(fileName));
			out = new ObjectOutputStream(fileOut);
			out.writeObject(RightPanel.getLines());
			out.writeObject(RightPanel.getRightPanelShapes());
			fileOut.flush();
		} catch (IOException i) {
			i.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

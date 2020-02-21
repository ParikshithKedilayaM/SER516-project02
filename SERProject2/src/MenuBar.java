import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Adds a Menu bar with Load and Save options.
 * 
 * @author Parikshith Kedilaya Mallar
 * @version 1.0
 */
public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	static JMenu menu;
	static JMenuItem load, save;
	
	MenuBar() {
		menu = new JMenu("Menu");
		SaveFile saveFile = new SaveFile("Save");
		save = saveFile;
		save.addActionListener(saveFile);
		LoadFile loadFile = new LoadFile("Load");
		load = loadFile;
		load.addActionListener(loadFile);
		menu.add(save);
		menu.add(load);
		this.add(menu);
		
		
	}

}

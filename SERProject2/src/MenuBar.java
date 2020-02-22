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
	private JMenu menu;
	private JMenuItem load, save;
	private final String SAVE = "Save";
	private final String LOAD = "Load";
	private final String MENU = "Menu";
	
	MenuBar() {
		menu = new JMenu(MENU);
		SaveFile saveFile = new SaveFile(SAVE);
		save = saveFile;
		save.addActionListener(saveFile);
		LoadFile loadFile = new LoadFile(LOAD);
		load = loadFile;
		load.addActionListener(loadFile);
		menu.add(save);
		menu.add(load);
		this.add(menu);
	}

}

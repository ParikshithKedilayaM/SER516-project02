import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

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

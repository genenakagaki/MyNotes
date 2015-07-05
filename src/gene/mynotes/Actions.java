package gene.mynotes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class Actions {
	
	private final static JFileChooser fileChooser = new JFileChooser();
	
	public static void newFile() {
		
	}

	public static void openFile(Window window) {
		int returnVal = fileChooser.showOpenDialog(window);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
		}
	}
}

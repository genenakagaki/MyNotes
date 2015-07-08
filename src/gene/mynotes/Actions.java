package gene.mynotes;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextPane;

public class Actions {
	
	private final static JFileChooser fileChooser = new JFileChooser();
	
	public static void newFile(Window window) {
		window.getTabbedPane().addTab();
	}

	public static void openFile(Window window) {
		int returnVal = fileChooser.showOpenDialog(window);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			FileReader reader;
			
			JTextPane textPane = new JTextPane();			
			
			try {
				reader = new FileReader(file);
				
				try {
					textPane.read(reader, file);				
				}
				finally {
					reader.close();
				}
			}
			catch (IOException e) {
				System.err.println("Failed to read file: "+ file.getName());
				e.printStackTrace();
			}
			
			window.getTabbedPane().addTab(file.getName(), textPane);
		}
	}
}

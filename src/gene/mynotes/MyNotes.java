package gene.mynotes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFileChooser;

public class MyNotes {
	
	private int width;
	private int height;
	private int xPos;
	private int yPos;

	public static final JFileChooser fileChooser = new JFileChooser();
	
	public MyNotes() {
		loadSettings();
		
		Window window = new Window(width, height, xPos, yPos);
	}

	private void loadSettings() {
		URL fileURL = getClass().getResource("/settings");
		File file = new File(fileURL.getFile());

		String line;
		String[] settings = new String[2];
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			try {
				while ((line = reader.readLine()) != null) {
					settings = line.split("=");
					
					switch (settings[0]) {
					case "width":
						width = Integer.parseInt(settings[1]);
						break;
					case "height":
						height = Integer.parseInt(settings[1]);
						break;
					case "x-position":
						xPos = Integer.parseInt(settings[1]);
						break;
					case "y-position":
						yPos = Integer.parseInt(settings[1]);
						break;
					default:
						System.err.println("Failed to read setting: "+ settings[0]);
						break;
					}
				}
			}
			finally {
				reader.close();
			}
		}
		catch (IOException e) {
			System.err.println("Failed to read file: "+ file.getName());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MyNotes();
	}
}

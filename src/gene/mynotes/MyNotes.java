package gene.mynotes;

import java.awt.Color;
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
	
	private static Color mainColor;
	private static Color subColor;

	public static final JFileChooser fileChooser = new JFileChooser();
	
	public MyNotes() {
		loadSettings();
		
		new Window(width, height, xPos, yPos);
	}

	private void loadSettings() {
		URL fileURL = getClass().getResource("/settings");
		File file = new File(fileURL.getFile());

		String line;
		String[] setting = new String[2];
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			try {
				while ((line = reader.readLine()) != null) {
					setting = line.split("=");
					
					switch (setting[0]) {
					case "width":
						width = Integer.parseInt(setting[1]);
						break;
					case "height":
						height = Integer.parseInt(setting[1]);
						break;
					case "x-position":
						xPos = Integer.parseInt(setting[1]);
						break;
					case "y-position":
						yPos = Integer.parseInt(setting[1]);
						break;
					case "main-color":
						mainColor = hex2Rgb(setting[1]);
						break;
					case "sub-color":
						subColor = hex2Rgb(setting[1]);
						break;
					default:
						System.err.println("Failed to read setting: "+ setting[0]);
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
	
	private Color hex2Rgb(String hexStr) {
		return new Color(
				Integer.valueOf(hexStr.substring(1, 3), 16),
				Integer.valueOf(hexStr.substring(3, 5), 16),
				Integer.valueOf(hexStr.substring(5, 7), 16));
	}
	
	public static Color getMainColor() {
		if (mainColor != null) {
			return mainColor;
		}
		return Color.white;
	}
	
	public static Color getSubColor() {
		if (subColor != null) {
			return subColor;
		}
		return Color.white;
	}
	
	public static void main(String[] args) {
		new MyNotes();
	}
}

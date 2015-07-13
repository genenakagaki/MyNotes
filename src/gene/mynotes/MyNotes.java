package gene.mynotes;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gene.utils.Utils;

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
		String fileStr = Utils.readFile("/settings");
		
		String[] lines = fileStr.split("\n");
		String[] setting = new String[2];
		
		for (String line: lines) {
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
	
	public static void main(String[] args) {try {
        // Set System L&F
    UIManager.setLookAndFeel(
        UIManager.getSystemLookAndFeelClassName());
	} 
	catch (UnsupportedLookAndFeelException e) {
	   // handle exception
	}
	catch (ClassNotFoundException e) {
	   // handle exception
	}
	catch (InstantiationException e) {
	   // handle exception
	}
	catch (IllegalAccessException e) {
	   // handle exception
	}
		
		new MyNotes();
	}
}

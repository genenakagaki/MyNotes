package gene.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Utils {

	public static String readFile(String filePath) {
		URL fileUrl = Utils.class.getResource(filePath);
		File file;
		if (fileUrl != null) {
			 file = new File(fileUrl.getFile());
			 return toString(file);
		}
		else {
			return null;
		}
	}
	
	private static String toString(File file) {
		StringBuilder result = new StringBuilder();
		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			try {
				while ((line = reader.readLine()) != null) {
					result.append(line + "\n");
				}
			}
			finally {
				reader.close();
			}
		}
		catch (IOException e) {
			System.err.println("Failed to read file: "+ file.getName());
			e.printStackTrace();
			return null;
		}
		
		return result.toString();
	}
	
	public static BufferedImage getImage(String filePath) {
		URL fileUrl = Utils.class.getResource(filePath);
		if (fileUrl != null) {
			File file = new File(fileUrl.getFile());
			try {
				BufferedImage img = ImageIO.read(file);
				return img;
			}
			catch (IOException e) {
				System.err.println("Failed to read image file: "+ file.getName());
				e.printStackTrace();
			}
		}
		return null;
	}
}

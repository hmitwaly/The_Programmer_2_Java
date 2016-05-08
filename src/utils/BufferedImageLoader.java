package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Library.Reference;

public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String imagePath) throws IOException {  // This sets a method to obtain the file 
		image = ImageIO.read(new File(Reference.Screen_Location+imagePath));                         //from the specific file location.
		return image;
	} 
	

}

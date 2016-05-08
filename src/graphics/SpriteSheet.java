package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	 private String path; // used to locate the spritesheet file
	 public final int SIZE; // used to know the size of the spritesheet
	 public int[] pixels; // creates an array of pixels to store the data in each pixel of the spritesheet
	
	 
	 // creates a static object in order to call and get data from a sprite sheet
	 public static SpriteSheet tiles = new SpriteSheet ("/Textures/TileSheet.png", 256);
	 public static SpriteSheet players = new SpriteSheet ("/Textures/CharSheet.png", 256);
	 
	 
	 //constructor for sprite sheet which defines where and how big the sprite sheet is
	 public SpriteSheet (String path, int size) {
	  this.path = path;
	  this.SIZE = size;
	  pixels = new int[SIZE*SIZE];
	  load();
	 }
	 
	 
	 // this method creates images by getting each pixel in the sprite sheet
	 private void load() {
	  try {
	  BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
	  int w= image.getWidth();
	  int h= image.getHeight();
	  image.getRGB(0,0, w, h, pixels, 0, w);
	 } catch (IOException e) {
	  e.printStackTrace();
	 }
	 }

}

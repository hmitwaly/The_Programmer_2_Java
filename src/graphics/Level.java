package graphics;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphics.Screen;
import graphics.Tile;

public class Level {
	 public int width, height;// used to create the array
	 protected int [] tiles; //used to store colour values for pixels in a level
	 public int spawnx;
	 public int spawny;
	 
	 // gets the location of the level to render and then creates it
	 public Level(String path, int x, int y) {
	  loadLevel(path);
	  spawnx = x;
	  spawny = y;
	 }
	 
	 
	 // gets the file for the level and then assigns a colour value to the tiles array
	 protected void loadLevel(String path) {
		  try {
			  BufferedImage image = ImageIO.read(Level.class.getResource(path));
			  int w = width =image.getWidth();
			  int h = height = image.getHeight();
			  tiles = new int [w*h];
			  image.getRGB(0, 0, w, h, tiles, 0, w);
		  } catch (IOException e) {
			  e.printStackTrace();
			  System.out.println("Could not load level file!");
		  }
	 }
	 
	 
	 //renders the tiles for the level based on the movement of the character
	 @SuppressWarnings("static-access")
	public void render(int xscroll,int yscroll,Screen screen) {
	  screen.setOffset(xscroll, yscroll);
	  int x0=xscroll >>4;
	  int x1=(xscroll+screen.width +16) >> 4;
	  int y0=yscroll>>4;
	  int y1=(yscroll + screen.height+ 16) >>4;
	  
	  //gets the corresponding tile to each position
	  for (int y = y0; y<y1; y++) {
	   for (int x = x0; x<x1; x++) {
	    getTile(x,y).render(x, y, screen);
	   }
	  }
	 }
	 
	 // used to determine which tile corresponds to which colour in the level file
	 public Tile getTile(int x, int y) {
	  if (x<0 || y<0 || x>=width || y>=height) return Tile.black;
	 if (tiles[x+y*width]==0xff00FF00) return Tile.grass;
	 if (tiles[x+y*width]==0xff0000FF) return Tile.sky;
	 if (tiles[x+y*width]==0xff7F92FF) return Tile.sky2;
	 if (tiles[x+y*width]==0xffFF6A00) return Tile.sky3;
	 if (tiles[x+y*width]==0xffFF0000) return Tile.dirt;
	 if (tiles[x+y*width]==0xff7D00FF) return Tile.portal;
	 if (tiles[x+y*width]==0xff000000) return Tile.black;
	 if (tiles[x+y*width]==0xff404040) return Tile.ground;
	 if (tiles[x+y*width]==0xff808080) return Tile.school;
	 if (tiles[x+y*width]==0xffFFFFFF) return Tile.sign;
	 if (tiles[x+y*width]==0xffFF00DC) return Tile.lava;
	 if (tiles[x+y*width]==0xff7FC9FF) return Tile.ice;
	 if (tiles[x+y*width]==0xffFFD800) return Tile.water;
	 if (tiles[x+y*width]==0xff007F46) return Tile.sky4;
	 if (tiles[x+y*width]==0xff7F3300) return Tile.bossground;
	 return Tile.black;
	 }
}

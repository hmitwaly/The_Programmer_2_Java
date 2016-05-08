package graphics;

import input.MouseInput;



public class Sprite {
	
	public final int SIZE; //used for the size of an array of pixels to draw from
	private int x,y; // finds the specific sprites in the spritesheet to draw
	public int[] pixels; // used to store data of the pixels in the sprite sheet
	private SpriteSheet sheet; // object used to access the sprite sheet
	
	
	// all the map sprites used in the game
	public static Sprite grass = new Sprite (16,0,0,SpriteSheet.tiles);
	public static Sprite sky = new Sprite (16,1,0,SpriteSheet.tiles);
	public static Sprite sky2 = new Sprite (16,6,0,SpriteSheet.tiles);
	public static Sprite sky3 = new Sprite (16,5,1,SpriteSheet.tiles);
	public static Sprite sky4 = new Sprite (16,0,2,SpriteSheet.tiles);
	public static Sprite dirt = new Sprite (16,2,0,SpriteSheet.tiles);
	public static Sprite black = new Sprite(16,5,0,SpriteSheet.tiles);
	public static Sprite ground = new Sprite(16,3,0,SpriteSheet.tiles);
	public static Sprite school = new Sprite(16,2,1,SpriteSheet.tiles);
	public static Sprite water = new Sprite(16,3,1,SpriteSheet.tiles);
	public static Sprite ice = new Sprite(16,4,0,SpriteSheet.tiles);
	public static Sprite portal = new Sprite(16,0,1,SpriteSheet.tiles);
	public static Sprite sign = new Sprite(16,1,1,SpriteSheet.tiles);
	public static Sprite lava = new Sprite(16,4,1,SpriteSheet.tiles);
	public static Sprite bossground = new Sprite(16,6,1,SpriteSheet.tiles);
	public static Sprite projectile = new Sprite(16,4,1,SpriteSheet.tiles);
	
	
	// all the player sprites used in the game
	public static Sprite player_stand = new Sprite(32,0,MouseInput.charselect, SpriteSheet.players);
	public static Sprite player_run1 = new Sprite(32,2,MouseInput.charselect,SpriteSheet.players);
	public static Sprite player_run2 = new Sprite(32,3,MouseInput.charselect, SpriteSheet.players);
	public static Sprite player_run3 = new Sprite(32,4,MouseInput.charselect, SpriteSheet.players);
	public static Sprite player_jump = new Sprite(32,5,MouseInput.charselect, SpriteSheet.players);
	public static Sprite player_attack = new Sprite(32,1,MouseInput.charselect, SpriteSheet.players);
	public static Sprite player_hurt = new Sprite(32,6,MouseInput.charselect, SpriteSheet.players);
	
	// the enemy sprites used in the game
	public static Sprite enemy = new Sprite(16,0,3, SpriteSheet.tiles);
	
	//this constructor is used when creating a new sprite
	// and requires size, x, y, and the sheet it is reading it from
	public Sprite(int size,int x, int y, SpriteSheet sheet) {
		SIZE= size;
		pixels = new int[SIZE*SIZE];
		this.x = x*size;
		this.y = y*size;
		this.sheet = sheet;
		load();
	}
	
	// finds and gets the information of every pixel in the sprite sheet so it can be used
	// to find the sprites needed
	private void load() {
		for (int y = 0; y <SIZE; y++) {
			for (int x = 0; x <SIZE; x++) {
				pixels[x+y*SIZE]=sheet.pixels[(x+this.x) + (y+this.y)*sheet.SIZE];
			}
		}
	}
}

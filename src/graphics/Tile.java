package graphics;

import tiles.*;

public class Tile {
	public int x, y; // used to tell the screen class where to render a tile
	public Sprite sprite; // used to get the sprite for the appropriate tile
	
	
	//creates a new tile using the given sprite
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile sky = new SkyTile(Sprite.sky);
	public static Tile sky2 = new Sky2Tile(Sprite.sky2);
	public static Tile sky3 = new Sky3Tile(Sprite.sky3);
	public static Tile sky4 = new Sky4Tile(Sprite.sky4);
	public static Tile bossground = new BossGround(Sprite.bossground);
	public static Tile portal = new PortalTile(Sprite.portal);
	public static Tile black = new BlackTile(Sprite.black);
	public static Tile ground = new GroundTile(Sprite.ground);
	public static Tile school = new SchoolTile(Sprite.school);
	public static Tile water = new WaterTile(Sprite.water);
	public static Tile sign = new SignTile(Sprite.sign);
	public static Tile lava = new LavaTile(Sprite.lava);
	public static Tile dirt = new GrassTile(Sprite.dirt);
	public static Tile ice = new IceTile(Sprite.ice);
	
	//defines the sprite in this class as whatever sprite is specified
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	//uses the screen class to render the tile in this class
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this);
	}
	
	
	//determines if the an entity can stand on the given tile
	// this method is over ridden by the individual tile classes
	public boolean solid () {
		return false;
	}
	
	//determines if the an entity can be hurt on the given tile
	// this method is over ridden by the individual tile classes
	public boolean hurt () {
		return false;
	}
	
}

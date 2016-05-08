package mobs;

import game.Game;
import game.GameState;
import graphics.Level;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;

public class Enemy {
	private Keyboard input;// used for keyboard input
	public static int x, y; // player positions
	public static int xa, ya;
	protected boolean flip = false; // used for rendering purposes // player health
	protected int health = 10;
	public int lives = 1;
	protected Sprite sprite; // used to determine the player sprite
	protected Projectile proj = new Projectile(input);
	public static int dir = 1; // used to determine which way the character is moving
	//protected boolean moving = false; // used to determine is moving or not
	protected Level level;
	protected boolean ouch = false;
	
	
	// when a player object is called, it will have a x, y and input
	@SuppressWarnings("static-access")
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void init(Level level) {
		this.level = level;
	}
	private int moving = 0;
	public void move(int xa, int ya) {

		// if the change in xa, ya is positive or negative, it sets the
		// appropriate direction
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		
		moving++;
		if (moving < 10 ) {
		x +=xa;
		} else if (moving>10 && moving < 20) { 
			 x-=xa;
		} else if (moving > 20) {
			moving =0;
		}
		//System.out.println(moving);	

		// if player is not colliding with anything it is allowed to move
		//if (!collision(xa, ya) || moving < 50)
			//x +=xa; */
	}
	// checks for collision
	protected boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 15; c++) {
			int yc = (y + ya + c);
			for (int d = 0; d < 15; d++) {
				int xc = (x + xa + d);
				if (Game.state == GameState.LEVEL1) {
					if (level.getTile(xc / 16, yc / 16).solid())
						solid = true;
				} else if (Game.state == GameState.LEVEL2) {
					if (level.getTile(xc / 16, yc / 16).solid())
						solid = true;
				} else if (Game.state == GameState.LEVEL3) {
					if (level.getTile(xc / 16, yc / 16).solid())
						solid = true;
				}
			}
		}
		return solid;
	}
	// checks if the given tile can hurt the player
	/*protected boolean hurt(int xa, int ya) {
		boolean hurt = false;
		for (int c = 0; c < 15; c++) {
			int yc = (y + ya + c);
			for (int d = 0; d < 15; d++) {
				int xc = (x + xa + d);
				if (Game.state == GameState.LEVEL1) {
					if (proj.x > xc)
						hurt = true;
				} else if (Game.state == GameState.LEVEL2) {
					if (level.getTile(xc / 16, yc / 16).hurt())
						hurt = true;
				} else if (Game.state == GameState.LEVEL3) {
					if (level.getTile(xc / 16, yc / 16).hurt())
						hurt = true;
				}
			}
		}
		return hurt;

	}*/

	public void update() {
		// if player is being hurt it changes the sprite
		//if (hurt(xa, ya)) health --;
		if (lives != 0) {
		move(1,0);
		}	
		if (Player.x+Game.proj.x+16 > Enemy.x) {
			health--;
		}
		if (health == 0) {
			lives--;
		}
		if (lives ==0) {
			x = -10;
			y =-10;
		}
	
		
	}

	public void render(Screen screen) {

			sprite = Sprite.enemy;

		// calls the render method and renders the proper sprite
		// using the position of the player, the appropriate sprite and the
		// direction
			if (lives != 0) {
				screen.renderEnemy(x, y, sprite, dir);
			}
	}
}

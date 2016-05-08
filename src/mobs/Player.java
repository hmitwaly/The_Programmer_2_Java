package mobs;


import utils.AudioPlayer;
import Library.Audio;
import game.Game;
import game.GameState;
import graphics.Level;
import graphics.Screen;
import graphics.Sprite;
import graphics.Tile;
import input.Keyboard;

public class Player {

	private Keyboard input; // used for keyboard input
	public static int x, y; // player positions
	public int xa, ya;
	private int anim = 0; // used for animation purposes
	private boolean walking; // used for animation purposes
	protected boolean flip = false; // used for rendering purposes
	public int health = 30; // player health
	public int lives = 3;
	protected Sprite sprite; // used to determine the player sprite
	protected Projectile projectile;
	public static int dir = 1; // used to determine which way the character is moving
	protected boolean moving = false; // used to determine is moving or not
	protected Level level;
	protected boolean ouch = false;

	int jump = 0;
	
	// when a player object is called, it will have a x, y and input
	@SuppressWarnings("static-access")
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void init(Level level) {
		this.level = level;
	}

	public void move(int xa, int ya) {

		// if the change in xa, ya is positive or negative, it sets the
		// appropriate direction
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;

		if (collision(0, ya + 2))
			jump = 0;

		if (input.up && jump < 50) {
			ya -= 3;
			jump++;
		}
		if (input.down)
			ya++;
		if (input.right)
			xa++;
		if (input.left)
			xa--;

		// if player is not colliding with anything it is allowed to move
		if (!collision(xa, 0))
			x += xa;
		if (!collision(0, ya)) {
			y += ya;
		}

	}
	
	// checks for collision
	protected boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 31; c++) {
			int yc = (y + ya + c);
			for (int d = 0; d < 31; d++) {
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

	protected boolean portal(int xa, int ya) {
		boolean portal = false;
		for (int c = 0; c < 32; c++) {
			int yc = (y + ya + c);
			for (int d = 0; d < 29; d++) {
				int xc = (x + xa + 3 + d);

				if (Game.state == GameState.LEVEL1) {
					if (level.getTile(xc / 16, yc / 16) == Tile.portal)
						portal = true;
				} else if (Game.state == GameState.LEVEL2) {
					if (level.getTile(xc / 16, yc / 16) == Tile.portal)
						portal = true;
				} else if (Game.state == GameState.LEVEL3) {
					if (level.getTile(xc / 16, yc / 16) == Tile.portal)
						portal = true;
				}
			}
		}
		return portal;
	}

	// checks if the given tile can hurt the player
	protected boolean hurt(int xa, int ya) {
		boolean hurt = false;
		for (int c = 0; c < 31; c++) {
			int yc = (y + ya + c);
			for (int d = 0; d < 31; d++) {
				int xc = (x + xa + d);
				if (Game.state == GameState.LEVEL1) {
					if (level.getTile(xc / 16, yc / 16).hurt())
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

	}

	public void update() {
		ya = 0;
		xa = 0;
		if (!collision(0, ya + 2))
			ya += 2;
		if (collision(0, ya + 2) && input.right)
			dir = 1;
		if (collision(0, ya + 2) && input.left)
			dir = 3;
		// if player is being hurt it changes the sprite
		if (hurt(xa, ya + 2)) sprite = Sprite.player_hurt;

		move(xa, 0);
		move(0, ya);
		
		//System.out.println(x+","+y);

		if (Game.state == GameState.LEVEL1) {
			if (portal(xa, ya) && input.use) {
				AudioPlayer.playSound(Audio.MenuSelect);
				Game.state = GameState.LEVEL2;
				x = 150;
				y = 850;
			}
		} else if (Game.state == GameState.LEVEL2) {
			if (portal(xa, ya) && input.use) {
				AudioPlayer.playSound(Audio.MenuSelect);
				Game.state = GameState.LEVEL3;
				x = 150;
				y = 850;
			}
		} else if (Game.state == GameState.LEVEL3) {
			if (portal(xa, ya) && input.use) {
				AudioPlayer.playSound(Audio.MenuSelect);
				Game.state = GameState.BOSSLEVEL;
				x = 150;
				y = 850;
			}	else if (Game.state == GameState.BOSSLEVEL) {
				if (portal(xa, ya) && input.use) {
					AudioPlayer.playSound(Audio.MenuSelect);
					Game.state = GameState.TITLE;
					x = 150;
					y = 850;
				}
		}
		}

		if (anim < 7500)
			anim++;
		if (anim == 7500)
			anim = 0;
		// if the change in x and y isn't 0, you can move and the player is now
		// walking
		if ((dir == 1 || dir == 3) && (input.left || input.right)) {
			walking = true;
		} else {
			walking = false;
			sprite = Sprite.player_stand;
		}
		
		if (hurt(xa-10,ya+2) || (Player.x+ 32 > Enemy.x && Player.x < Enemy.x + 16 && Player.y +32> Enemy.y)) {	
			x-=5;
			y -= 5;
			health--;
			ouch = true;
		}
		
		if (health == 0 ||( y>Game.ylock+ 110)) {
			x = level.spawnx;
			y = level.spawny;
			lives--;
			health=30;
		}
		
		if (lives == 0) {
			Game.state=GameState.GAMEOVER;
			AudioPlayer.playMusic("gameover");
			lives =3;
		}
		
	
		
	}

	public void render(Screen screen) {

		if (ya == 0)
			sprite = Sprite.player_stand;
		
		if (hurt(xa,ya) && dir ==2) sprite = Sprite.player_hurt;

		// if moving to the left or right, it scrolls through the animations
		if (dir == 1 || dir == 3) {
			sprite = Sprite.player_stand;
			if (walking) {
				if (anim % 25 > 10) {
					sprite = Sprite.player_run1;
				} else if (anim % 25 < 5) {
					sprite = Sprite.player_run2;
				} else {
					sprite = Sprite.player_run3;
				}
			} else {
				sprite = Sprite.player_stand;
			}
		}

		// if moving up or down it uses the specific sprite
		if (dir == 0 || dir == 2)
			sprite = Sprite.player_jump;
		// if the attack key is pressed, it uses the specified sprite
		if (input.attack) {
			sprite = Sprite.player_attack;
		}
		// calls the render method and renders the proper sprite
		// using the position of the player, the appropriate sprite and the
		// direction
		screen.renderPlayer(x, y, sprite, dir);
	}

}

package mobs;

import input.Keyboard;
import graphics.Level;
import graphics.Screen;
import graphics.Sprite;

public class Projectile {
	
	public int x, y, dir;
	private Keyboard input;
	private Sprite sprite;
	protected Level level;
	protected Player player;
	protected Screen screen;
	
	public Projectile( Keyboard input) {
		this.input = input;
		
	}
	
	public void init(Level level) {
		this.level = level;
	}

	public void update () {
		
		if (input.attack) {
				x+=2;
		}
		if (x >100 || !input.attack) {
			x=0;
		}
	}
	
	@SuppressWarnings("static-access")
	public void render(Screen screen) {
		if (input.attack) {
			sprite = Sprite.projectile;
			if (Player.dir == 1 || Player.dir ==2) {
				screen.renderProjectile(player.x+32+x, player.y+8, sprite);
			}
			if (Player.dir == 3) {
				screen.renderProjectile(player.x-10-x, player.y+8, sprite);	
			}
		}
	}

}

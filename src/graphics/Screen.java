package graphics;


public class Screen {

	public static int width; // determines the size of the screen to draw on
	public static int height;
	private final int playersize = 32; // sets the player size to a constant
	public int[] pixels; // array used to keep information on every pixel being
							// drawn
	public int[] tiles = new int[64 * 64]; //

	public int xOffset, yOffset; // used for moving the map according to the

	// the screen object requires a width, height, and an array of pixels to
	// draw from
	@SuppressWarnings("static-access")
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	// clears the screen by making every pixel on the screen black
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	// renders a tile by using the offset caused by the character
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;// sets x offset
		yp -= yOffset;// sets y offset

		// by doing 2 for loops using y first then x, we render row by row
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;// used to determine the map drawing bounds
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;// used to determine the map drawing bounds
				// if the rendering goes out of bounds it stops rendering
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0
						|| ya >= height)
					break;
				// doesnt let the map bound go less than zero
				if (xa < 0)
					xa = 0;
				// assigns a colour to every pixel on the tile
				int col = tile.sprite.pixels[x + y * 16];
				// if the colour of a pixel is pink, it does not draw it
				if (col != 0xffff00ff)
					pixels[xa + ya * width] = tile.sprite.pixels[x + y
							* tile.sprite.SIZE];
			}
		}
	}

	public void renderPlayer(int xp, int yp, Sprite sprite, int dir) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < playersize; y++) {
			int ya = y + yp;
			for (int x = 0; x < playersize; x++) {
				int xa = x + xp;
				// xs is used to flip the character by drawing from right to
				// left instead
				int xs = 31 - x;
				if (xa < -playersize || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col;
				// if the player is moving to the left, it uses xs instead of x
				// in order to flip the sprite
				if (dir == 3) {
					// assigns a colour to every pixel in the player sprite
					col = sprite.pixels[xs + y * playersize];
					// if the colour of a pixel is pink, it does not draw it
					if (col != 0xffff00ff)
						pixels[xa + ya * width] = sprite.pixels[xs + y
								* playersize];
				} else {
					col = sprite.pixels[x + y * playersize];
					if (col != 0xffff00ff)
						pixels[xa + ya * width] = sprite.pixels[x + y
								* playersize];
				}
			}
		}
	}

	public void renderEnemy(int xp, int yp, Sprite sprite, int dir) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				// xs is used to flip the character by drawing from right to
				// left instead
				int xs = 15 - x;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col;
				// if the player is moving to the left, it uses xs instead of x
				// in order to flip the sprite
				if (dir == 3) {
					// assigns a colour to every pixel in the player sprite
					col = sprite.pixels[xs + y * 16];
					// if the colour of a pixel is pink, it does not draw it
					if (col != 0xffff00ff)
						pixels[xa + ya * width] = sprite.pixels[xs + y
								* 16];
				} else {
					col = sprite.pixels[x + y * 16];
					if (col != 0xffff00ff)
						pixels[xa + ya * width] = sprite.pixels[x + y
								* 16];
				}
			}
		}
	}


	public void renderProjectile(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				int col = sprite.pixels[x + y * 16];
				if (col != 0xffff00ff)
					pixels[xa + ya * width] = sprite.pixels[x + y * 16];
			}
		}
	}

	// sets the offset in this class by whatever input it gets where this method
	// is used
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}

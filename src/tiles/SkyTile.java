package tiles;

import graphics.Screen;
import graphics.Sprite;
import graphics.Tile;

	public class SkyTile extends Tile {
		
		public SkyTile (Sprite sprite) {
			super(sprite);
		}
		
		public void render(int x, int y, Screen screen) {
			screen.renderTile(x<<4, y<<4, this);
		}
		
		public boolean solid () {
			return false;
		}
		
		public boolean hurt () {
			return false;
		}
}

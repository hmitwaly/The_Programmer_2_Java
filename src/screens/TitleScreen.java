package screens;

import java.awt.*;

import Library.Images;
import game.Game;

public class TitleScreen {

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.width*4, Game.height*4);
		g.drawImage(Images.titleScreenImage, 0, 0, null);
		Font font = new Font("8Bit Wonder", Font.PLAIN, 20);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Press Enter to continue", 400, 800);
	}
}

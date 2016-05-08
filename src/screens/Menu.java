package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import utils.Button;
import Library.Images;
import Library.Reference;
import game.Game;

public class Menu {
	// Creates Rectangle class.
	public Button play, cheats, quit, credits; 
			
	public Menu() {
		int fillerY = 450;
		play = new Button (Reference.Center_X - 150, fillerY,300,70).setText("Start");  // Coordinates (X1,Y1,X2,Y2)
		cheats = new Button (Reference.Center_X - 150, fillerY+=75 ,300,70).setText("Cheats"); 
		credits = new Button (Reference.Center_X- 150, fillerY+=75,300,70).setText("Credits");
		quit = new Button (Reference.Center_X - 150, fillerY+=75,300,70).setText("Quit");

	} 
	public void render (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Game.width*4, Game.height*4);
		g.drawImage(Images.menuScreenImage,0,0,null);
		Font font = new Font("8Bit Wonder", Font.PLAIN, 40);
		g.setFont(font);
		play.drawButton(g, 105);
		cheats.drawButton(g,127);
		credits.drawButton(g, 139);
		quit.drawButton(g, 76);
		
	}
}

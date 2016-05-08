package screens;

import game.Game;

import java.awt.Color;
import java.awt.Graphics;

import utils.Button;
import Library.Images;

public class GameOver {
	// Creates Rectangle class.
	public Button continueg,quit; 
			
	public GameOver() {
		continueg= new Button (335,500,200,50);
	 	quit= new Button (710,500,105,50);

	} 
	public void render (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Game.width*4, Game.height*4);
		g.drawImage(Images.overScreenImage,0,0,null);
		continueg.drawBoxButton(g, 0);
		quit.drawBoxButton(g, 100);
		
	}
}

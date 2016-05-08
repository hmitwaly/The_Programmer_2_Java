package screens;

import game.Game;

import java.awt.Color;
import java.awt.Graphics;

import utils.Button;
import Library.Images;


public class Selection {
	
	public Button Hossam,Betsegaw,Bharath,Alina,Phoebe,back; 
	  
	 public Selection() {
	 	int fillerX = 10;
	 	Hossam = new Button (fillerX,350,230,300); // Coordinates (X1,Y1,X2,Y2)
	 	 Betsegaw = new Button (fillerX+=240,350,230,300); 
	 	Bharath = new Button (fillerX+=240,350,230,300);
	 	Alina= new Button (fillerX+=240,300,230,350);
	 	Phoebe= new Button (fillerX+=239,300,230,350);
	 	back= new Button (0,000,230,100);
	 	} 
	 
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.width*4, Game.height*4);
		g.drawImage(Images.selectScreenImage, 0, 0, null);
		Hossam.drawBoxButton(g, 105);
	 	Betsegaw.drawBoxButton(g,150);
	 	Bharath.drawBoxButton(g, 139);
	 	Alina.drawBoxButton(g, 76);
	 	Phoebe.drawBoxButton(g, 76);
	 	back.drawBoxButton(g, 76);
	}
}

package utils;

import java.io.IOException;

import Library.Audio;
import Library.Images;

public class ResourceLoader {
	
	private static BufferedImageLoader imageloader = new BufferedImageLoader();
	
	public static void loadImages() {
		
		try {
		Images.menuScreenImage= imageloader.loadImage("main menu.PNG");
		Images.titleScreenImage= imageloader.loadImage("title screen.jpg");	
		Images.selectScreenImage= imageloader.loadImage("character select screen.png");
		Images.overScreenImage= imageloader.loadImage("game over screen.bmp");
		} catch (IOException e) {       // catches the IOExcetion if the image fails to load.
			e.printStackTrace();       // prints out error on console telling us where error has occured.
		}
		
	}
	
	public static void loadSounds() {
		AudioPlayer.addSound(Audio.MenuSelect, "MenuSelect.ogg");
		AudioPlayer.addSound(Audio.MenuHover, "MenuHover.ogg");
		AudioPlayer.addMusic(Audio.Bmusic, "Blades_of_Steel_NES_Music.ogg");
		AudioPlayer.addMusic(Audio.Level1music, "Level_1_Music.ogg");
		AudioPlayer.addMusic(Audio.Level2music, "Sonic_3_Music.ogg");
		AudioPlayer.addMusic(Audio.Level3music, "8bit_Persona_4.ogg");
		AudioPlayer.addMusic(Audio.gameovermusic, "Game_Over_Sonic.ogg");
	}

}

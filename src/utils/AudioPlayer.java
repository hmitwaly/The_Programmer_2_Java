package utils;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import Library.Reference;

public class AudioPlayer {
	
	private static Map<String,Sound> soundMap = new HashMap<String, Sound>();     // A hash map to contain all the game sounds
	private static Map<String,Music> musicMap = new HashMap<String, Music>();     // A hash map to contain all the game music
	
	public static boolean hover = false;              // Used to play sound when mouse hovers over button.
	public static void addSound(String key, String path) { 
		try {
			soundMap.put(key, new Sound(Reference.Sound_Location+path));
		} catch (SlickException e) {

			e.printStackTrace();
		}
	}
	public static void addMusic(String key, String path) { 
		try {
			musicMap.put(key, new Music(Reference.Sound_Location+ path));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	
	public static void playSound(String key) {
		soundMap.get(key).play();
	}
	public static void stopMusic(String key) {
	 	musicMap.get(key).stop();
	 }
	public static void playMusic(String key) {
			musicMap.get(key).play();
		}
	}


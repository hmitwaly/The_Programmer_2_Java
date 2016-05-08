package input;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import mobs.Enemy;
import utils.AudioPlayer;
import game.GameState;
import Library.Audio;
import game.Game;

public class MouseInput extends MouseAdapter { // Implements all mouse methods.

	public static boolean pressed = false; // True if mouse button is pressed

	public static int MOUSE_X, MOUSE_Y;
	public static int charselect;
	public static Rectangle Mouse = new Rectangle(1, 1, 1, 1);;
	public static Enemy enemy;

	public void mouseClicked(MouseEvent e) {
		int mouse = e.getButton();
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1); // creates a
																	// 1X1 pixel
																	// wherever
																	// the mouse
																	// cursor
																	// is.
		pressed = true;
		if (mouse == MouseEvent.BUTTON1) {
			switch (Game.state) {
			case MENU:
				if (rect.intersects(Game.getInstance().getMenu().play)) { // If
																			// the
																			// pixel
																			// intersects
																			// with
																			// the
																			// rectangle
																			// PLAY.
					AudioPlayer.playSound(Audio.MenuSelect);
					Game.state = GameState.CHARSELECT; // Go into the Game state
														// and run GAME
				} else if (rect.intersects(Game.getInstance().getMenu().cheats)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					Game.state = GameState.CHEAT;
				} else if (rect
						.intersects(Game.getInstance().getMenu().credits)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					Game.state = GameState.CREDITS;
				} else if (rect.intersects(Game.getInstance().getMenu().quit)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					System.exit(0);
				}
				break;
			case CHARSELECT:
				if (rect.intersects(Game.getInstance().getSelection().Hossam)) { // If
																					// the
																					// pixel
																					// intersects
																					// with
																					// the
																					// rectangle
																					// PLAY.
					AudioPlayer.playSound(Audio.MenuSelect);
					charselect = 4;
					Game.state = GameState.LEVEL1; // Go into the Game state and
													// run GAME
				} else if (rect
						.intersects(Game.getInstance().getSelection().Betsegaw)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					charselect = 1;
					Game.state = GameState.LEVEL1;
				} else if (rect
						.intersects(Game.getInstance().getSelection().Bharath)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					charselect = 0;
					Game.state = GameState.LEVEL1;
				} else if (rect
						.intersects(Game.getInstance().getSelection().Alina)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					charselect = 3;
					Game.state = GameState.LEVEL1;
				} else if (rect
						.intersects(Game.getInstance().getSelection().Phoebe)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					charselect = 2;
					Game.state = GameState.LEVEL1;
				} else if (rect
						.intersects(Game.getInstance().getSelection().back)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					Game.state = GameState.MENU;
				}
				break;
			case GAMEOVER:
				if (rect.intersects(Game.getInstance().getGameOver().continueg)) { // If
																					// the
																					// pixel
																					// intersects
																					// with
																					// the
																					// rectangle
																					// PLAY.
					AudioPlayer.playSound(Audio.MenuSelect);
					Game.state = GameState.TITLE; // Go into the Game state and
													// run GAME
				} else if (rect
						.intersects(Game.getInstance().getGameOver().quit)) {
					AudioPlayer.playSound(Audio.MenuSelect);
					System.exit(0);
				}
				break;
			default:
				break;

			}
		}
	}

	public void mousePressed(MouseEvent e) {
		pressed = true;

	}

	public void mouseReleased(MouseEvent e) {

		pressed = false;

	}

	public void mouseMoved(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
		Mouse = new Rectangle(MOUSE_X, MOUSE_Y, 1, 1);

		switch (Game.state) {
		case MENU:
			if ((Mouse.intersects(Game.getInstance().getMenu().play)
					|| Mouse.intersects(Game.getInstance().getMenu().cheats)
					|| Mouse.intersects(Game.getInstance().getMenu().credits) || Mouse
						.intersects(Game.getInstance().getMenu().quit))
					&& !AudioPlayer.hover) {
				AudioPlayer.playSound(Audio.MenuHover);
				AudioPlayer.hover = true;
			} else if (!(Mouse.intersects(Game.getInstance().getMenu().play)
					|| Mouse.intersects(Game.getInstance().getMenu().cheats)
					|| Mouse.intersects(Game.getInstance().getMenu().credits) || Mouse
						.intersects(Game.getInstance().getMenu().quit))
					&& AudioPlayer.hover) {
				AudioPlayer.hover = false;
			}
			break;
		case CHARSELECT:
			if ((Mouse.intersects(Game.getInstance().getSelection().Hossam)
					|| Mouse.intersects(Game.getInstance().getSelection().Betsegaw)
					|| Mouse.intersects(Game.getInstance().getSelection().Bharath)
					|| Mouse.intersects(Game.getInstance().getSelection().Alina) || Mouse
						.intersects(Game.getInstance().getSelection().Phoebe))
					&& !AudioPlayer.hover) {
				AudioPlayer.playSound(Audio.MenuHover);
				AudioPlayer.hover = true;
			} else if (!(Mouse
					.intersects(Game.getInstance().getSelection().Hossam)
					|| Mouse.intersects(Game.getInstance().getSelection().Betsegaw)
					|| Mouse.intersects(Game.getInstance().getSelection().Bharath)
					|| Mouse.intersects(Game.getInstance().getSelection().Alina) || Mouse
					.intersects(Game.getInstance().getSelection().Phoebe)
					&& AudioPlayer.hover)) {
				AudioPlayer.hover = false;
			}

			break;
		case LEVEL1:
			AudioPlayer.playMusic("Level1");
			break;
		case LEVEL2:
			AudioPlayer.playMusic("Level2");
			break;
		case LEVEL3:
			AudioPlayer.playMusic("Level3");
			break;
		default:
			break;
		}
	}
}

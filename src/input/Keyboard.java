package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	
	//creates an array to hold a number of keys
	private boolean[] keys = new boolean[120];
	// declarations of the different buttons needed
	public boolean up,down, left, right,attack,use,enter;
	
	
	//everytime the game is updated, it needs to check for these key events
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		attack = keys[KeyEvent.VK_K];
		use = keys[KeyEvent.VK_J];
		enter = keys[KeyEvent.VK_ENTER];	
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	
	}
	
}

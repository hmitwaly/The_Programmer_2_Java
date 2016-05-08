package game;

import graphics.Level;
import graphics.Screen;
import graphics.Tile;
import input.Keyboard;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Library.Audio;
import input.MouseInput;
import game.Game;
import screens.GameOver;
import screens.Menu;
import screens.Selection;
import screens.TitleScreen;
import utils.AudioPlayer;
import utils.ResourceLoader;
import game.GameState;
import mobs.Enemy;
import mobs.Player;
import mobs.Projectile;

// Allows game to be a subclass of Canvas so we can draw
// and lets us use the Runnable class for use of threads
@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {
	// used for the screen size
	public static int width = 304;
	public static int height = 216;
	public static int scale = 4;
	// declares a thread object
	private Thread thread;
	// declares a frame object which allows us to create a window
	private JFrame frame;
	// is used to determine if the game is running and if the thread should stop
	private boolean running = false;
	// creates a screen object
	public Screen screen;
	// creates a player object
	protected static Player player;
	// creates a level object
	protected Level level;
	protected Level level1;
	protected Level level2;
	protected Level level3;
	protected Level bosslevel;
	// creates a key object
	protected Keyboard key;
	// creates a new menu object to run the menu screen
	protected TitleScreen title;
	// creates a new menu object to run the menu screen
	protected Menu menu;
	// creates a new selection object to run the character select screen
	protected Selection select;
	// creates a new game over object to run the game over screen
	protected GameOver over;
	//creates a new projectile object
	public static Projectile proj;
	// creates a new Game object in order to run everything
	protected static Game game = new Game();
	// Initializing what state the game runs at
	public static GameState state = GameState.TITLE;
	// creates a mouse object for input by the user
	protected MouseInput mouse;
	// creates an enemy object
	private Enemy enemy;
	
	
	// creates a BufferedImage object which we can then draw to the screen using
	// RGB values
	private BufferedImage image = new BufferedImage(Game.width, Game.height,BufferedImage.TYPE_INT_RGB);
	// creates an array to store data of each pixel of the image we want to draw
	// to the screen
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public static Game getInstance() {
		return game;
	}
	public Menu getMenu() {
		return menu;
	}
	public TitleScreen getTitle() {
		return title;
	}
	public Selection getSelection() {
		return select;
	}
	public GameOver getGameOver() {
		return over;
	}

	public void init() { // Acts as constructor.
		ResourceLoader.loadImages(); // Loads images.
		menu = new Menu();
		title = new TitleScreen();
		select = new Selection();
		over = new GameOver();
		ResourceLoader.loadSounds(); // loads sounds.
		MouseInput mouse = new MouseInput();
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);// set the level
		player.init(bosslevel);
		proj.init(bosslevel);
		enemy.init(bosslevel);
		levelchoose();
	}
	
	
	public static int xlock;
	public static int ylock;
	
	public void levelchoose () {
		if (state == GameState.LEVEL1){
			level = level1;
			xlock=3900;
			ylock=880;
		} else if (state == GameState.LEVEL2){
			level = level2;
			xlock=3900;
			ylock=880;
		} else if (state == GameState.LEVEL3){
			level = level3;
			xlock=3900;
			ylock=880;
		}	else if (state == GameState.BOSSLEVEL){
			level = bosslevel;
			xlock=500;
			ylock=350;
		}
	}

	public void update() {
		
		if (state == GameState.LEVEL1 || state == GameState.LEVEL2 || state == GameState.LEVEL3 || state == GameState.BOSSLEVEL) {
			player.update();
			proj.update();
			if (enemy.lives !=0) {
			enemy.update();
			}
		}
		key.update();
	}
	
	@SuppressWarnings("static-access")
	public void level() {
		levelchoose();
		player.init(level);
		int xScroll = 0;
		int yScroll = 0;
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) { // if there is no buffer
			// triple buffers and allows for smooth game play
			createBufferStrategy(3);
			return;
		}	
		
		// creates graphics object so we can draw
				Graphics g = bs.getDrawGraphics();
		
		screen.clear();
		if (level.getTile((player.x + screen.width) / 16,(player.y)/16)!=Tile.black) {
		xScroll = player.x - screen.width / 2;
		
		} else {
			xScroll= xlock-screen.width/2;
		}
		
		if (player.x > screen.width/2 && level.getTile((player.x) / 16,(player.y+(screen.height/2))/16)!=Tile.black) {
		yScroll = player.y - screen.height/2;
		} else {
			yScroll = ylock - screen.height/2;
		}
		if (state == GameState.LEVEL1 || state == GameState.LEVEL2 || state == GameState.LEVEL3 || state ==  GameState.BOSSLEVEL) {
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		if (enemy.lives !=0) {
		enemy.render(screen);
		}
		if (key.attack) {
			proj.render(screen);
		}
	}
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); 
		// avoids continued display of old graphics
		g.dispose();
		// shows the next available buffered image to the screen
		bs.show();
		}

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) { // if there is no buffer create one
			// triple buffers and allows for smooth game play
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		switch (Game.state) { // Calls state variable in Game class.
		case LEVEL1:
			level();
			break;
		case LEVEL2:
			level();
			break;
		case LEVEL3:
			level();
			break;
		case BOSSLEVEL:
			level();
			break;
		case MENU:			
			Game.getInstance().getMenu().render(g);
			// avoids continued display of old graphics
			g.dispose();
			// shows the next available buffered image to the screen
			bs.show();
			break;
		case TITLE:
			Game.getInstance().getTitle().render(g);
			// avoids continued display of old graphics
			g.dispose();
			// shows the next available buffered image to the screen
			bs.show();
			// if user presses enter, it goes to the menu
			if (key.enter){
				state = GameState.MENU;
			}
			break;
		case CHARSELECT:		
			Game.getInstance().getSelection().render(g);
			// avoids continued display of old graphics
			g.dispose();
			// shows the next available buffered image to the screen
			bs.show();
			break;
		case GAMEOVER:
			Game.getInstance().getGameOver().render(g);
			// avoids continued display of old graphics
			g.dispose();
			// shows the next available buffered image to the screen
			bs.show();
			break;
		case CREDITS:
			break;
		default:
			break;

		}

	}

	public Game() {
		// sets the dimensions for the window of the game using a Dimension
		// object
		Dimension size = new Dimension(width * scale, height * scale);
		// sets the size of our window using the size object
		setPreferredSize(size);
		// sets screen object which is used to render graphics
		screen = new Screen(width, height);
		// creates a new JFrame object which we can use to draw to the screen
		frame = new JFrame();
		// creates key object and a key listener for user input
		key = new Keyboard();
		addKeyListener(key);
		level1 = new Level("/Levels and Screens/level 1 screen.png", 155, 880);
		level2 = new Level("/Levels and Screens/level 2 screen.png", 155, 850);
		level3 = new Level("/Levels and Screens/level 3 screen.png", 155, 850);
		bosslevel = new Level("/Levels and Screens/boss level screen.png", 155, 300);
		// sets the players initial position and the key input
		player = new Player(155,850, key);
		// sets a default projectile
		proj = new Projectile(key);
		// sets the enemies initial position
		enemy = new Enemy(240,920);
		
	}

	// starts a new thread and synchronized makes sure threads don't run into
	// eachother
	public synchronized void start() {
		// once the thread is started, the game runs
		running = true;
		// creates a new Thread using the Game class as an object and with the
		// name "Display"
		thread = new Thread(this, "Display");
		// starts the thread
		thread.start();
	}

	// stops the thread and the game, then catches an exception when the thread
	// dies and exits out of program
	public synchronized void stop() {
		running = false;
		try {
			// waits for the thread to die
			thread.join();
		} catch (InterruptedException e) {
			// prints the exception for debugging purposes
			e.printStackTrace();
		}
	}


	// runs the program
	public void run() {
		// initializes everything for the game to run
		init();
		// this gets the system time when the program is first run and is
		// precise
		long lastTime = System.nanoTime();
		// keeps track of the time
		long timer = System.currentTimeMillis();
		// converts the nanoseconds into milliseconds by dividing by the number
		// of times
		// we want to update the game by and makes sure the game updates 60
		// times/ second
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		// calculates how many frames we can render per update
		int frames = 0;
		// calculates how many updates per second, should be 60
		int updates = 0;
		// makes the screen come to the front so you don't have to click on it
		// to start
		requestFocus();
		// creates a loop that executes while the game is running
		while (running) {
			// gets the new time at the start of program because it took time to
			// run the previous lines of code
			long now = System.nanoTime();
			// adds the time it took to run the code before getting to the while
			// loop to delta
			delta += (now - lastTime) / ns;
			// sets the lastTime to the current time so the operation can happen
			// again and the delta stays constant
			lastTime = now;
			// delta >=1 happens once every 60 updates
			while (delta >= 1) {
				// logic for game
				update();
				// adds one every time update() is run
				updates++;
				// sets the delta back to zero
				delta--;
			}
			// creates the images for the game
			render();
			// adds one every time a frame is rendered
			frames++;
			// fps counter
			if (System.currentTimeMillis() - timer > 1000) { // if it is greater
																// then 1 second
				// makes sure it stays at 1 second
				timer += 1000;
				// shows the updates and frames per second and sets the title
				frame.setTitle("The Programmer 2 | " + updates + " ups "
						+ frames + "fps");
				// sets them back to 0 so the calculations can be done again
				updates = 0;
				frames = 0;
			}
		}
		// once the game is running, calls on the stop() method and stops the
		// whole program
		stop();
	}

	public static void main(String[] args) {
		// doesn't let the screen be resized, because it can ruin the graphics
		game.frame.setResizable(false);
		// adds the game object to the screen
		game.frame.add(game);
		//game.frame.add(player);
		// makes sure everything that needs to be on the screen is on the screen
		game.frame.pack();
		// once the window is closed, the program stops running as well
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sets the location of the window to the middle by default
		game.frame.setLocationRelativeTo(null);
		// makes the window visible
		game.frame.setVisible(true);
		// starts the game
		game.start();
	}

}

package core;

import core.message.FootNoteMessage;
import core.message.MessageManager;
import core.store.Inventory;
import core.store.Prize;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import setup.FileIO;
import setup.Fonts;
import setup.Images;
import world.Player;
import world.World;
import world.structure.functional.Portal;

import java.io.File;

public class Carnival extends BasicGameState
{
	private static int mouseX;
	private static int mouseY;
	private StateBasedGame sbg;
	private int id;
	public static GameContainer gc;
	private World world;
	private Player player;
	public static FileIO fileMaker = new FileIO();
	private static boolean showControls = true;
	private boolean wasdPressed;

	private static Inventory inventory;
	private static int tickets = 20;
	private MessageManager m;
	private boolean onPortal;

	public static core.store.Prize myHat;

	public static int getTickets() {
		return tickets;
	}

	public static void renderTickets(Graphics g) {
		g.setFont(Fonts.big);
		g.setColor(Color.black);
		g.drawString(String.valueOf(tickets), Main.getScreenWidth() * 0.02f + 2, Main.getScreenHeight() * 0.02f + 2);
		g.setColor(Color.white);
		g.drawString(String.valueOf(tickets), Main.getScreenWidth() * 0.02f, Main.getScreenHeight() * 0.02f);
	}
	public static core.store.Prize getMyHat() {
		return myHat;
	}
	public static void gainTickets(int t) {
		tickets = tickets + t;
	}

	public static void setTickets(int t) {
		tickets = t;
	}

	public static void renderInstructions(Graphics g) {
		g.setFont(Fonts.big);
		g.setColor(Color.black);
		g.drawString("Press C for Controls", Main.getScreenWidth() * 0.74f + 2, Main.getScreenHeight() * 0.02f + 2);
		g.setColor(Color.white);
		g.drawString("Press C for Controls", Main.getScreenWidth() * 0.74f, Main.getScreenHeight() * 0.02f);
	}

	public static void spendTickets(int t) {
		tickets = tickets - t;
	}
	public Carnival(int id)
	{
		this.id = id;
	}

	public int getID()
	{
		return id;
	}

	public void checkFileExistence() {
		String fileName = "output.txt";
		File file = new File(fileName);

		if (file.exists()) {
			System.out.println("The file " + fileName + " exists.");
			fileMaker.readFile();
			// Update the existing file
		} else {
			System.out.println("The file " + fileName + " does not exist.");
			fileMaker.update();
			// Create a new file
		}
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// This code happens when you enter a game state for the *first time.*
		Images.loadImages();
		gc.setShowFPS(true);
		this.sbg = sbg;
		this.gc = gc;
		inventory = new Inventory();
		player = new Player();
		world = new World(player);
		m = new MessageManager();
		myHat = null;

		// THIS WILL BE REPLACED WITH READING THE FILE IF IT EXISTS
		checkFileExistence();
	}

	public static Inventory getInventory() {
		return inventory;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		// This updates your game's logic every frame.  NO DRAWING.
		int currentGame = -1;
		Portal[] portals = world.getPortal();
		onPortal = false;
		for (int i=0; i<portals.length; i++)
		{
			if (portals[i] != null && portals[i].isTouching(player.getFeetLocation()[0], player.getFeetLocation()[1]) )
			{
				onPortal = true;
				currentGame = i;

				//m.addMessage(new Message("Press <G> to enter the game!", player.getFeetLocation()[0], player.getFeetLocation()[1], Color.white, Fonts.big, 5, false));
			}

		}
		if (world.getStore().isTouching(player.getFeetLocation()[0], player.getFeetLocation()[1]))
		{
//				m.removeDiffFootnoteMessages("Press <T> to enter the store!");
//				m.addMessage(new FootNoteMessage("Press <T> to enter the store!", 120));
			world.moveOneCellDown();
			sbg.enterState(Main.STORE_ID);

		}
		else if (onPortal)
		{
//				m.removeDiffFootnoteMessages("Press <G> to enter the game!");
//				m.addMessage(new FootNoteMessage("Press <G> to enter the game!", 120));
			world.moveOneCellDown();
			sbg.enterState(Main.GAME_ID);
			Game.setGameId(currentGame);

		}
//		else if (!wasdPressed){
//			m.removeDiffFootnoteMessages("use <WASD> to move around");
//			m.addMessage(new FootNoteMessage("use <WASD> to move around",800));
//		}

		m.update();
		if (!showControls) {
			world.update(gc);
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		// This code renders shapes and images every frame.
		world.display(g);
		inventory.render(g, mouseX, mouseY );
		renderTickets(g);
		if (myHat != null && inventory.isShowing()) {
			myHat.render(g);
			if (myHat.isMouseOver(mouseX, mouseY)) {
				g.setColor(Color.black);
				g.setFont(Fonts.small);
				g.drawString("Click to de-equip.", myHat.getX(), myHat.getY());
			}
		}
		if (!showControls) {
			renderInstructions(g);
		}
		if (showControls) {
			g.setColor(Color.black);
			g.fillRect(0,0,Main.getScreenWidth(),Main.getScreenHeight());
			g.drawImage(Images.controls, 200, 200);
		}
		m.render(g);
	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException

	{
		//world.moveOneCellDown();
		gc.setDefaultMouseCursor();
		// This code happens when you enter a gameState.
	}

	public void leave(GameContainer gc, StateBasedGame sbg)
	{
		//world.moveOneCellDown();
		// This code happens when you leave a gameState.
	}

	public void keyPressed(int key, char c)
	{
		// This code happens every time the user presses a key
		world.keyPressed(key, c);
		if (key == Input.KEY_W || key == Input.KEY_A || key == Input.KEY_S || key == Input.KEY_D)
		{
			wasdPressed = true;
		}
		if (key == Input.KEY_T) {

			if (world.getStore().isTouching(player.getFeetLocation()[0], player.getFeetLocation()[1]))
			{
				//world.moveOneCellDown();
				sbg.enterState(Main.STORE_ID);
			}
		}
		Portal[] portals = world.getPortal();
		if (key == Input.KEY_G)
		//&& player.getX() == 21 && player.getY()==11
		{
			for (int i=0; i<portals.length; i++)
			{
				if (portals[i].isTouching(player.getFeetLocation()[0], player.getFeetLocation()[1]) )
				{
					//world.moveOneCellDown();
					sbg.enterState(Main.GAME_ID);
					Game.setGameId(i);
				}
			}
		}
		if (key == Input.KEY_E) {
			if (getInventory().isShowing()) {
				getInventory().hideInventory();
			} else {
				getInventory().showInventory();
			}
		}
		if (key == Input.KEY_C) {
			showControls = !showControls;
		}
	}


	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		mouseX = newx;
		mouseY = newy;
		if (myHat != null) {
			if (myHat.isMouseOver(newx, newy)) {

			}
		}
		// IF NEEDED LOOK IN THE BASICGAMESTATE CLASS FOR MOUSE LOCATION AND CLICK METHODS
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}



	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
		Prize p = inventory.mousePressed(button, x, y);
		if (p != null) {
			// If you clicked on a prize (inventory or hat)
			if (myHat != null) {
				// If you DO have a current hat
				if (myHat.getName().equals(p.getName())) {
					// TO DEQUIP
					inventory.addPrize(p);
					myHat = null;
				} else {
					// SWAPPING HATS
					inventory.addPrize(myHat);
					myHat = p;
					inventory.getPrizes().remove(p);
					myHat.setX(1155);
					myHat.setY(430);
					Player.setPlayerImage(myHat.getID());
				}
			} else {
				// EQUIPPING WHEN YOU DONT HAVE A HAT ALREADY
				myHat = p;
				inventory.getPrizes().remove(p);
				myHat.setX(1155);
				myHat.setY(430);
				Player.setPlayerImage(myHat.getID());
			}
		}

		if(myHat == null)
		{
			Player.setPlayerImage(0);
		}
	}

}

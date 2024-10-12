package core;

import core.Store;
import core.kangarooGame.KangarooGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame 
{
	public final static int FRAMES_PER_SECOND = 60;
	private static AppGameContainer appgc;

	public static final int TITLE_ID = 0;
    public static final int CARNIVAL_ID  = 1;
	public static final int STORE_ID  = 2;
	public static final int GAME_ID = 3;
	public static final int KANGAROO_ID = 4;
	private BasicGameState title;
    private BasicGameState carnival;
	private BasicGameState store;
	private BasicGameState game;
	private BasicGameState kangaroo;


	public Main(String name) 
	{
		super(name);
		title = new Title(TITLE_ID);
		carnival = new Carnival(CARNIVAL_ID);
		store = new Store(STORE_ID);
		game = new Game(GAME_ID);
		kangaroo = new KangarooGame(KANGAROO_ID);
	}

	public static int getScreenWidth()
	{
		return appgc.getScreenWidth();
	}
	
	public static int getScreenHeight()
	{
		return appgc.getScreenHeight();
	}
	

	public void initStatesList(GameContainer gc) throws SlickException 
	{
		addState(title);
		addState(carnival);
		addState(store);
		addState(game);
		addState(kangaroo);
	}

	public static void main(String[] args) 
	{
		try 
		{
			appgc = new AppGameContainer(new Main("My First Project"));
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
			appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
			appgc.setTargetFrameRate(FRAMES_PER_SECOND);
			appgc.setVSync(true);
			appgc.start();

		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}

	}
}
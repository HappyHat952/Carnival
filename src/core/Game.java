package core;

import core.message.MessageManager;
import miniGame.MiniGame;
import miniGame.kangaroo.KangGame;
import miniGame.penguin.PenguinGame;
import miniGame.sapo.SapoGame;
import miniGame.shooting.ShootingGame;
import miniGame.tug.TugGame;
import miniGame.whack.WhackGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static core.Carnival.fileMaker;

public class Game extends BasicGameState
{
    private int id;
    private static int gameId;
    private StateBasedGame sbg;
    private MiniGame[] games;
    public final static int NUM_GAMES = 6;
    private MessageManager messageManager;


    public Game(int id)
    {
        this.id = id;
    }

    public int getID()
    {
        return id;
    }

    public void setGameID(int gameId){this.gameId = gameId;}

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        // This code happens when you enter a game state for the *first time.*
        messageManager = new MessageManager();

        games = new MiniGame[NUM_GAMES];

        games[0] = new ShootingGame(0, messageManager);
        games[1] = new SapoGame(1, messageManager);
        games[2] = new WhackGame(2, messageManager );
        games[3] = new TugGame(3, messageManager);
        games[4] = new KangGame(4, messageManager);
        games[5] = new PenguinGame(5, messageManager);

        this.sbg = sbg;
        gc.setShowFPS(true);
        gameId = 0;

    }

    static void setGameId(int id)
    {
        gameId = id;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        // This updates your game's logic every frame.  NO DRAWING.
        games[gameId].update(gc,delta);
        if (messageManager != null) {
            messageManager.update();
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        // This code renders shapes and images every frame.
        games[gameId].render(g);
        if (messageManager != null) {
            messageManager.render(g);
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        // This code happens when you enter a gameState.
        //world.moveOneCellDown();
        // ADD RESET THE MINIGAME

    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        fileMaker.update();
        games[gameId].resetGame();
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c)
    {
        // This code happens every time the user presses a key\
        games[gameId].keyPressed(key,c);
        if (key == Input.KEY_X)
        {
            games[gameId].resetGame();
            sbg.enterState(Main.CARNIVAL_ID);
        }

    }

    public void mousePressed(int button, int x, int y)
    {
        // This code happens every time the user presses the mouse

        games[gameId].mousePressed(button, x,y);

    }




}


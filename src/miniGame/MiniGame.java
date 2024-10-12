package miniGame;

import core.Carnival;
import core.Main;
import core.message.MessageManager;
import org.newdawn.slick.*;
import setup.Fonts;
import setup.Images;

abstract public class MiniGame {

    protected boolean gameOver;
    protected int ID;
    static protected int inGameTicket;
    static private int seconds = 70;
    private int count;
    protected MessageManager messager;
    protected static String name;
    protected Image welcomeBanner;
    protected Image gameOverScreen;
    protected boolean pause = true;
    protected int TOTAL_GAME_SECONDS;
    protected boolean infinite;
    protected boolean isTimed = true;

    static public int getSeconds()
    {
        return seconds;
    }
    static public void setSeconds(int s)
    {
        seconds = s;
    }

    static public String getName(){ return name;}

     public void render(Graphics g) {
         if (pause && welcomeBanner != null) {
             renderInstructions(g);
         } else if (gameOver) {
             renderGameOver(g);
         } else {
             g.drawImage(Images.pauseAndExit, Main.getScreenWidth()-Images.pauseAndExit.getWidth()-10, -100);
             drawInfo(g);

        }
     }

    public void renderInstructions(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
        g.setColor(Color.white);
        g.drawImage(welcomeBanner, Main.getScreenWidth()/2-welcomeBanner.getWidth()/2,
                Main.getScreenHeight()/2-welcomeBanner.getHeight()/2);
        g.setFont(Fonts.big);
        if(!gameOver)
        {
            g.drawString("Press ENTER to play!", 600, 100 + welcomeBanner.getHeight()+50);
        }
        g.resetFont();

    }

    public void renderGameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
        g.drawImage(gameOverScreen, 200, 200);
        g.setFont(Fonts.big);
        g.drawString(String.valueOf(inGameTicket), 1100, 650);
    }
    public void mousePressed(int button, int x, int y)
    {

    }
    public void update(GameContainer gc, int delta)
    {
        if (!pause && !gameOver) {
            count++;
            if (count % Main.FRAMES_PER_SECOND == 0 && seconds != 0 && !gameOver) {
                seconds--;
            }
            if (seconds == 0 && !infinite) {
                endGame();
                gameOverScreen = Images.timesUp;
            }
        }

    };
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER && !gameOver) {
            pause = !pause;
        }
    }

    public void addTickets(int num)
    {
        Carnival.gainTickets(num);
    }

    public void drawInfo(Graphics g)
    {
        g.setFont(Fonts.big);
        g.setColor(Color.white);
        if (isTimed)
        {
            if (seconds%60<10 && seconds >=0)
            {
                g.drawString(seconds / 60 +" : 0" +seconds %60, 10, 90);
            }
            else {
                g.drawString(seconds / 60 +" : " +seconds %60, 10, 90);
            }
        }
        g.drawString("Tickets: " + inGameTicket, 10, 50);


        g.resetFont();
    }
    public void resetGame()
    {
        Carnival.gainTickets(inGameTicket);
        inGameTicket = 0;
        seconds = TOTAL_GAME_SECONDS;
        gameOver = false;
        if (messager != null) {
            messager.clear();
        }
        pause = true;
    }
    protected void endGame()
    {
        gameOver = true;
    }

}

package miniGame.shooting;

import core.message.MessageManager;
import miniGame.MiniGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;

public class ShootingGame extends MiniGame {

    private static int ticketFactor;
    private Target target;
    Shooter p;
    private StateBasedGame sbg;
    private Color origBG;


    public ShootingGame( int id, MessageManager m)
    {
        TOTAL_GAME_SECONDS = 30;
        messager = m;
        setSeconds(30);
        ID = id;
        target = new Target();
        p = new Shooter(target);
        inGameTicket = 0;
        ticketFactor = 3;
        name = "shooter";
        welcomeBanner = Images.shootingScreen;
        gameOverScreen = Images.timesUp;
    }

    public boolean getPause() {
        return pause;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Images.bg.getSubImage(0,0),0,0);

            if (target.isComplete()) {
                endGame();
            } else {
                target.render(g);
                p.render(g);
            }

            drawInfo(g);
        super.render(g);
    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        gc.getGraphics().setBackground(origBG);
    }

    @Override
    public void update( GameContainer gc, int delta) {
        super.update(gc, delta);
        // TODO Auto-generated method stub

        if (!pause && !gameOver) {
            target.update(delta);
            if (target.isComplete()&&!gameOver)
            {
                gameOver = true;
                gameOverScreen = Images.niceJob;
            }
            int key = -1;
            if (gc.getInput().isKeyDown(Input.KEY_A))
            {
                key = Input.KEY_A;
            } else if (gc.getInput().isKeyDown(Input.KEY_D))
            {
                key = Input.KEY_D;
            }
            p.update(key, delta, messager);
        }
        if (gameOver)
        {
            target.clear();
        }

    }

    public void keyPressed(int key, char c)
    {
        if (!pause && !gameOver) {
            p.keyPressed(key, c);
        }
        super.keyPressed(key, c);
    }

    public static void incrementScore() {
        inGameTicket += ticketFactor;
    }
    public void resetGame()
    {
        super.resetGame();
        target = new Target();
        p = new Shooter(target);
        gameOver = false;
    }

}

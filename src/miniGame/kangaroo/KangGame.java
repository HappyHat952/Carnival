package miniGame.kangaroo;

import core.Main;
import core.message.MessageManager;
import core.message.MiddleMessage;
import miniGame.MiniGame;
import miniGame.kangaroo.fighter.DogFighter;
import miniGame.kangaroo.fighter.Fighter;
import miniGame.kangaroo.fighter.KangarooFighter;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import setup.Images;

public class KangGame extends MiniGame {
    Fighter[] fighters;
    PlayerManager[] movers;
    boolean isTurn; //of dog
    int ANIM_TIME;
    int animCount;


    public KangGame(int id, MessageManager man)
    {
        messager = man;
        TOTAL_GAME_SECONDS = 60; // may change to null
        inGameTicket = 0;
        fighters = new Fighter[]{new KangarooFighter(), new DogFighter()};
        movers = new PlayerManager[]{ new PlayerManager(fighters[0], fighters[1],messager),
                                    new PlayerManager(fighters[1], fighters[0], messager)};
        isTurn = true;
        //pause = false;
        ANIM_TIME = 60;
        animCount =0;
        infinite = true;
        isTimed = false;
        welcomeBanner = Images.kangScreen;

    }

    public void update(GameContainer gc, int delta)
    {
        super.update(gc,delta);
        if (!pause)
        {
            for (int f=0; f< fighters.length; f++)
            {
                fighters[f].update();
                if (fighters[f].getCurrentHealth() == 0)
                {
                    gameOver = true;
                    if (f==1)
                    {
                        gameOverScreen = Images.youLose;
                    }
                    else
                    {
                        gameOverScreen = Images.niceJob;
                    }
                }
            }
            if (animCount>0)
            {
                animCount--;
            }
            if (!isTurn && animCount == 0)
            {
                int random = (int)(Math.random()*3);
                movers[0].use(random);
                animCount =ANIM_TIME;
                isTurn = true;

            }
            inGameTicket = 100-fighters[0].getCurrentHealth();

        }

    }
    public void render(Graphics g)
    {
        g.setBackground(Color.black);
        g.setColor(Color.white);
        g.fillRect(0, Main.getScreenHeight()/4, Main.getScreenWidth(), 10);
        for (Fighter f: fighters)
        {
            f.render(g);
            f.render(g);
        }
        for (PlayerManager m: movers)
        {
            m.draw(g);
        }
        super.render(g);

    }
    public void keyPressed(int key, char c)
    {
        super.keyPressed(key,c);
        if (!pause && isTurn && animCount == 0)
        {
            isTurn = false;
            animCount = ANIM_TIME;
            if (c=='1')
            {
                movers[1].use(0);
            }
            else if (c=='2')
            {
                movers[1].use(1);
            }
            else if (c=='3')
            {
                movers[1].use(2);
            }

                else{
                    isTurn = true;
                    messager.addMessage(new MiddleMessage("press 1, 2, or 3", 200));
                    messager.removeDiffMiddleMEssages("press 1, 2, or 3");
                    animCount = 0;

            }
        }

    }
    public void resetGame() {
        super.resetGame();
        gameOver = false;
        for (Fighter f: fighters)
        {
            f.resetHealth();
        }

    }




}

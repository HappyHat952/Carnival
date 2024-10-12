package miniGame.sapo;

import core.Main;
import core.message.FloatMessage;
import core.message.Message;
import core.message.MessageManager;
import miniGame.MiniGame;
import miniGame.sapo.hole.CoinHole;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Coin {

    final Image FULL_IMAGE;
    Image image;
    int speed;
    CoinHole hole;
    int holeId;
    int x;
    int y;
    int maxY;
    int startTime;
    final int LIFESPAN;
    boolean dead;
    float sizeScale;
    MessageManager messager;
    boolean win;
    Message coinMess;

    public Coin(MessageManager m, boolean hit)
    {
        win = true;
        messager = m;
        FULL_IMAGE = Images.sapoCoin;
        image = FULL_IMAGE;
        sizeScale = 1;
        hole = null;
        holeId = -1;
        speed = 8;
        startTime = 0;
        LIFESPAN = 4;
        dead = false;
        maxY=-1;
    }


    public boolean getDead()
    {
        return dead;
    }

    public void draw(Graphics g)
    {
        if ( !dead)
        {
            image =FULL_IMAGE.getScaledCopy(sizeScale);
            g.drawImage(image, x-image.getWidth()/2, y -image.getHeight()/2);
        }

    }

    public void update()
    {
        if ((hole != null && y>=hole.getCoinY())||(maxY != -1 && y>=maxY))
        {
            y -= speed;
            if (sizeScale >= 0.5)
            {
                sizeScale = (float)y/Main.getScreenHeight()*(float)1.5;
            }

        }
        else if ((hole != null && y<= hole.getCoinY()))
        {
            hole.setHasCoin(sizeScale);
            dead = true;
            coinMess = new FloatMessage("+"+hole.getValue(), x, y, Color.red, 60);
            messager.addMessage(coinMess);
        }
        else if (maxY != -1 && y<maxY && Math.abs(MiniGame.getSeconds()-startTime)<= LIFESPAN && coinMess == null)
        {
            coinMess = new FloatMessage("Miss!", x, y, Color.red, 60);
            messager.addMessage(coinMess);
        }
        else if (maxY != -1 && y<maxY && Math.abs(MiniGame.getSeconds()-startTime)>LIFESPAN)
        {
            dead = true;

        }
        if (Math.abs(MiniGame.getSeconds()-startTime)> LIFESPAN)
        {
            dead = true;
        }
    }
    public void setHole(CoinHole h)
    {
        hole = h;
        holeId = hole.getId();
        x = hole.getX();
        y = Main.getScreenHeight();
        startTime = MiniGame.getSeconds();
    }
    public void setHole(int x)
    {
        this.x = x;
        y = Main.getScreenHeight();
        maxY = (int) ((Main.getScreenHeight()-500)*Math.random()+300);
        startTime = MiniGame.getSeconds();
    }
}

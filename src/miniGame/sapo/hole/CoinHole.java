package miniGame.sapo.hole;

import core.Main;
import miniGame.MiniGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import setup.Images;

abstract public class CoinHole {
    protected SpriteSheet holes = Images.sapoTargets;
    protected Image image;
    protected Image coinImage;
    protected String color;
    protected int id;
    protected int x;
    protected int y;
    protected int value;
    protected boolean hasCoin;
    int coinSec;


    protected final static int[] LEVELS = {0, 200, 400};
    protected int level;

    public int getId()
    {
        return id;
    }


    public int getX(){ return x;}
    public int getY(){ return y;}

    public int getCoinY(){
        return getY();
    }

    public void setHasCoin(float coinF)
    {
        hasCoin = true;
        coinSec = MiniGame.getSeconds();
        coinImage = Images.sapoCoin.getScaledCopy(coinF);
    }

    public int getLevel()
    {
        return level;
    }
    public int getValue(){ return value;}

    public void draw(Graphics g)
    {


        g.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2);
        if (hasCoin)
        {
            g.drawImage(coinImage, x - coinImage.getWidth()/2, getCoinY()- coinImage.getHeight()/2);
        }
    }
    public void update()
    {
        int boardLen = Main.getScreenWidth()-200;
        int xDistance = boardLen/7;
        x = 100+xDistance/2+id*xDistance ;
        y = LEVELS[level-1]+300;
        if (coinSec!= 0 && coinSec - 1 == MiniGame.getSeconds())
        {
            hasCoin = false;
            coinSec = 0;
        }
    }


}

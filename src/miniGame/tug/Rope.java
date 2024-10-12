package miniGame.tug;

import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Rope {
    Image image;
    final int Y;
    int drawX;
    final static int ROOST_WIN = 1;
    final static int LION_WIN = 0;
    final static int NO_WIN = -1;
    final int DISTANCE_TO_ANIMAL;


    public Rope()
    {
        image = Images.rope;
        Y = Main.getScreenHeight()/2- image.getHeight()/2;
        drawX = Main.getScreenWidth()/2 - image.getWidth()/2;
        DISTANCE_TO_ANIMAL = image.getWidth()*4/256;

    }

    public void reset()
    {
        drawX = Main.getScreenWidth()/2 - image.getWidth()/2;
    }

    public int getdistanceToAnimal()
    {return DISTANCE_TO_ANIMAL;}


    public void update()
    {
        drawX+=(int)(Math.random()*4);
    }
    public void tug()
    {
        drawX-=15;
    }

    public void draw(Graphics g)
    {
        g.drawImage(image,drawX , Y);
    }

    public int win()
    {
        if(drawX<0)
        {
            return ROOST_WIN;
        }
        else if (drawX+image.getWidth()>Main.getScreenWidth()-DISTANCE_TO_ANIMAL)
        {
            return LION_WIN;
        }
        else {
            return NO_WIN;
        }
    }


}

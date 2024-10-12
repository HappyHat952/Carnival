package miniGame.shooting;

import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import setup.Images;

public class Duck {

    private int x;
    private int y;
    private int width;
    private int height;

    private int color;
    private int speed;
    private Image img = null;
    private SpriteSheet duckSheet = null;
    private int spriteR, spriteC;

    public Duck(int i , int j, int s)
    {
        speed = s;
        this.x = j;
        this.y = i;
        width = 216;
        height = 216;

        duckSheet = Images.duckSheet;
        color = (int)(Math.random()*4) ;

        img = duckSheet.getSubImage(color,0);
        img = img.getScaledCopy (width,height);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int i) {
        x = i;
    }

    public void setY(int i) {
        y = i;
    }

    public int getXPixel()
    {
        return getX() * getWidth();
    }

    public int getYPixel()
    {
        return getY() * getHeight();
    }

    public static int getWidth()
    {
        return Main.getScreenWidth() / Target.DUCK_COLUMNS;
    }

    public static int getHeight()
    {
        return (Main.getScreenHeight()/2) / Target.DUCK_ROWS;
    }

    public void render(Graphics g)
    {
        img.draw(getXPixel() + ((getWidth() - width)/2) + speed, getYPixel() + ((getHeight() - height)/2));
    }

    public boolean contains(float x, float y) {
        int i = getXPixel() + ((getWidth() - width)/2);
        int j = getYPixel() + ((getHeight() - height)/2);

        if (x > i && x < i + width && y > j && y < j + height) {
            return true;
        }
        return false;
    }
}

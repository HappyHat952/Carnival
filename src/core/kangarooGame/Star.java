package core.kangarooGame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Star {

    private float x, y;
    private int width, height;
    private boolean active;
    private int type;
    private Image img = null;
    public Star(float x , float y, int type)
    {
        this.x = x;
        this.y = y;
        active = true;
        width = 25;
        height = 25;
        this.type = type;
        try {
            if (type == 0) {
                img = new Image("res/image/game/kangaroo/coin.png");
            } else {
                img = new Image("res/image/game/kangaroo/kangaroo_meat.png");
            }
            img = img.getScaledCopy(width, height);
        } catch (SlickException e) {
        }
    }

    public void render(Graphics g)
    {
        if (active) {
            img.draw(x, y, width, height);
        }
    }

    public int getType() {
        return type;
    }
    public float getX() {
        return x;
    }

    public float getY() {return y;}

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void deActivate() {
        active = false;
    }
    public boolean isActive() {
        return active;
    }
}

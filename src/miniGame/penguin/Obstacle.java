package miniGame.penguin;

import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Obstacle {

    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Image image;

    public Obstacle() {
        x = (int) (Math.random() * Main.getScreenWidth());
        image = Images.ice;
        w = image.getWidth();
        h = image.getHeight();
        y=-h;
    }

    public void update() {
        y+=PenguinGame.getSpeed();
    }

    public int getY() {
        return y;
    }
    public int getX() { return x; }
    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y);
    }
}

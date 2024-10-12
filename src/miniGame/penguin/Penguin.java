package miniGame.penguin;


import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Penguin {

    private int x;
    private int y;
    private int w;
    private int h;
    private Image image;

    public Penguin() {
        image = Images.penguin;
        x = Main.getScreenWidth()/2 - image.getWidth();
        y = Main.getScreenHeight() - (image.getHeight() + 10);
        w = image.getWidth();
        h = image.getHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setImage(Image i) {
        image = i;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y);
    }

    public void update() {

    }

    public void moveRight() {
        if (x + 15 + image.getWidth() < Main.getScreenWidth()) {
            x += 15;
        }
    }

    public boolean isOver(Obstacle o) {
        if (y >= o.getY() && y <= o.getY() + o.getH() && x+w > o.getX() && o.getX() + o.getW() > x) {
            return true;
        }
        return false;
    }

    public void moveLeft() {
        if (x - 15 > 0) {
            x -= 15;
        }
    }

}

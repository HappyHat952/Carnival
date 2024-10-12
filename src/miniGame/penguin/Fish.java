package miniGame.penguin;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Fish extends Obstacle {

    protected Image image;
    public Fish() {
        image = Images.fish;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y);
    }

}

package miniGame.whack;

import core.message.FloatMessage;
import core.message.MessageManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Mole {

    private int x;
    private int y;

    private int w;
    private int h;

    private Image image;

    /*
    LEVELS:
    0 - empty hole
    1 - dirt appearing
    2 - mole appears
    3 - mole higher
    4 - mole highest
    5 - mole goes down
    6 - mole is hit
     */

    private int level;
    private int time;
    private MessageManager messageManager;

    public Mole(MessageManager m){
        messageManager = m;
        x = 500;
        y = 200;
        image = Images.mole.getSubImage(0, 0);
        w = image.getWidth();
        h = image.getHeight();
        level = 0;
    }

    public void render(Graphics g) {
        switch (level) {
            case 6:
                g.drawImage(Images.mole.getSubImage(1, 2), x, y);
                break;
            case 5:
                g.drawImage(Images.mole.getSubImage(2, 1), x, y);
                break;
            case 4:
                g.drawImage(Images.mole.getSubImage(1, 1), x, y);
                break;
            case 3:
                g.drawImage(Images.mole.getSubImage(0, 1), x, y);
                break;
            case 2:
                g.drawImage(Images.mole.getSubImage(2, 0), x, y);
                break;
            case 1:
                g.drawImage(Images.mole.getSubImage(1, 0), x, y);
                break;
            default:
                g.drawImage(Images.mole.getSubImage(0, 0), x, y);
                break;
        }
    }

    public void update() {
        time++;

        switch (level) {
            case 6:
                if (time > 20) {
                    level = 0;
                }
                break;
            case 5:
                if (time > 45) {
                    level = 0;
                }
                break;
            case 4:
                if (time > 55) {
                    level = 5;
                }
                break;
            case 3:
                if (time > 15) {
                    level = 4;
                }
                break;
            case 2:
                if (time > 10) {
                    level = 3;
                }
                break;
            case 1:
                if (time > 5) {
                    level = 2;
                }
                break;
            default:
                if (Math.random() < .005f) {
                    level = 1;
                    time = 0;
                }
                break;
        }
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW(){
        return w;
    }

    public int getH() {
        return h;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int l) {
        level = l;
    }

    public Image getImage() {
        return image;
    }


    public void hitMole(){
        level = 6;
        time = 0;
        WhackGame.incrementScore();
        messageManager.addMessage(new FloatMessage("+1", x - 20, y, Color.red, 60));
    }


}

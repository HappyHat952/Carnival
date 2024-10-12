package core.kangarooGame;

import core.Main;
import org.newdawn.slick.*;

public class KangarooPlayer {
    private float x;
    private float y;
    private int width, height;
    private int speed;
    HealthBar health;
    boolean active;
    private SpriteSheet playerSprite = null;
    private int spriteR, spriteC;
    Image img;
    private int bottom = Main.getScreenHeight()-210;
    public KangarooPlayer(float x, float y) {
        this.x = x;
        this.y = y;
        width = 10;
        height = 10;
        speed = 1;
        active = true;
        try {
            img = new Image("res/image/world/dog0.png");
            img = img.getScaledCopy(768/2, 3072/2);
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        width = 768/8;
        height = 3072/8;
        playerSprite = new SpriteSheet(img, width, height);
        spriteC = 0;
        spriteR = 2;

        health = new HealthBar(x, this.y + height + 1, width, 20);
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

    public void render(Graphics g)
    {
        playerSprite.getSprite(spriteC, spriteR).draw(x, y);
        //g.drawRect(x, y, width, height);
        health.render(g);
    }

    public void reduceHealth() {
        health.reduceHealth();
        if (health.getHealth() <= 0) {
            active = false;
        }
    }

    public void increaseHealth(int h) {
        health.increaseHealth(h);
    }
    public double getHealthPercent() {
        return health.getHealthPercent();
    }
    public boolean isActive(){
        return active;
    }

    public void update(int key, int delta)
    {
        if (key == Input.KEY_A) {
            x -= speed;
            if (x < 0) {
                x = 0;
            }
            if (spriteC == 3) {
                spriteC = 0;
            } else {
                spriteC++;
            }
            spriteR = 1;
        } else if (key == Input.KEY_D) {
            float orig = x;
            x += speed;
            if (x + width > Main.getScreenWidth()) {
                x = orig;
            }
            if (spriteC == 3) {
                spriteC = 0;
            } else {
                spriteC++;
            }
            spriteR = 2;
        } else if (key == Input.KEY_W) {
            y -= speed;
            if (y < 0) {
                y = 0;
            }
            if (spriteC == 3) {
                spriteC = 0;
            } else {
                spriteC++;
            }
            spriteR = 3;
        } else if (key == Input.KEY_S) {
            float orig = y;
            y += speed;
            if (y + height > bottom) {
                y = orig;
            }
            if (spriteC == 3) {
                spriteC = 0;
            } else {
                spriteC++;
            }
            spriteR = 0;
        } else if (key == Input.KEY_SPACE) {

        }

        health.update(x, this.y + height + 1);
    }
}

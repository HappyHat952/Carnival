package core.kangarooGame;

import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Kangaroo {
    private float x;
    private float y;
    private int width, height;
    float xSpeed;
    float ySpeed;
    int direction;
    int moveCounter;
    int maxWidth = Main.getScreenWidth();
    int maxHeight = Main.getScreenHeight()-210;
    int type;
    int c = 1, kc;
    Image img;
    private SpriteSheet kSprite = null;
    private int spriteR, spriteC;
    public Kangaroo(int type) {
        this.type = type;

        xSpeed = 1f;
        ySpeed = 1f;
        double d = Math.random();
        if (d <= 0.25) {
            direction = 0;
            spriteR = 2;
        } else if (d <= 0.5) {
            direction = 1;
            spriteR = 3;
        } else if (d <= 0.75) {
            direction = 2;
            spriteR = 0;
        } else {
            direction = 3;
            spriteR = 1;
        }
        try {
            img = new Image("res/image/game/kangaroo/kangaroo.png");
            img = img.getScaledCopy(3311/5, 4320/5);
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        width = 3311/20;
        height = 4320/20;
        kSprite = new SpriteSheet(img, width, height);

        x = (float) (Math.random() * maxWidth);
        if (x + width >= maxWidth) {
            x -= width;
        }
        y = (float) (Math.random() * maxHeight);
        if (y + height >= maxHeight) {
            y -= height;
        }
        spriteC = 0;
    }

    public void render(Graphics g)
    {
        kSprite.getSprite(spriteC, spriteR).draw(x, y);
        //kSprite.getSprite(0, 0).draw(x, y);
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

    public void update(int delta)
    {
        if (direction == 0) {
            if (y-ySpeed <= 0) {
                moveCounter = 0;
                direction = 1;
            } else {
                y -= ySpeed;
            }
        } else if (direction == 1) {
            if (y + height + ySpeed >= maxHeight) {
                moveCounter = 0;
                direction = 0;
            } else {
                y += ySpeed;
            }
        } else if (direction == 2) {
            if (x + width + xSpeed >= maxWidth) {
                moveCounter = 0;
                direction = 3;
            } else {
                x += xSpeed;
            }
        } else if (direction == 3) {
            if (x - xSpeed <= 0) {
                moveCounter = 0;
                direction = 2;
            } else {
                x -= xSpeed;
            }
        }

        moveCounter++;

        kc ++;
        if (kc > 5) {
            //y += 10;
            kc = 0;
            spriteC += c;
            if (spriteC >= 3) {
                c = -1;
                spriteC = 3;
            }
            if (spriteC <= 0) {
                c = 1;
                spriteC = 0;
            }
        }


        if (moveCounter > 200) {
            moveCounter = 0;
            setDirection();
        }
    }

    private void setDirection() {
        double d = Math.random();
        if (d <= 0.25) {
            if (direction == 0) {
                direction = 2;
                spriteR = 0;
            } else {
                direction = 0;
                spriteR = 2;
            }
        } else if (d <= 0.5) {
            if (direction == 1) {
                direction = 3;
                spriteR = 1;
            } else {
                direction = 1;
                spriteR = 3;
            }
        } else if (d <= 0.75) {
            if (direction == 0) {
                direction = 3;
                spriteR = 1;
            } else {
                direction = 2;
                spriteR = 0;
            }
        } else {
            if (direction == 3) {
                direction = 1;
                spriteR = 3;
            } else {
                direction = 3;
                spriteR = 1;
            }
        }
        spriteC = 0;
    }
}

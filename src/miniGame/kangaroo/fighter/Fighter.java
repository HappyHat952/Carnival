package miniGame.kangaroo.fighter;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

abstract public class Fighter {

    protected int totalHealth;
    protected int currentHealth;
    protected int barX;
    protected int barY;
    protected int fighterX;
    protected int fighterY;
    protected Color myColor;
    protected final int BAR_LENGTH = Main.getScreenWidth()/3;
    protected final int BAR_HEIGHT = Main.getScreenHeight()/20;
    protected boolean dead;
    protected String name;
    protected Image image;
    protected boolean moving;
    protected final int MOVE_TIME_TOTAL = 120;
    protected boolean isTurn;
    protected int moveTimer;
    protected int hurtTimer;
    protected int HURT_TIME_TOTAL = 60;


    public String getName()
    {
        return name;
    }
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.setLineWidth(3);
        g.drawRect(barX, barY, BAR_LENGTH, BAR_HEIGHT);

        g.setColor(myColor);
        g.fillRect(barX+3, barY+3, (BAR_LENGTH-6)*((float)currentHealth/totalHealth), BAR_HEIGHT-6);


        if (image != null && hurtTimer > 1) {
            Image tempImage = image;

            tempImage.setImageColor((float)hurtTimer / HURT_TIME_TOTAL,0f, 0f);
            g.drawImage(tempImage,  fighterX, fighterY);
        }
        else
        {
            image.setImageColor(1f,1f,1f);
            g.drawImage(image,  fighterX, fighterY);
        }


    }
    public void update()
    {
        moveTimer++;
        if(moveTimer <= MOVE_TIME_TOTAL/2)
        {
            fighterX++;
        }
        else
        {
            fighterX--;
        }
        if(moveTimer == MOVE_TIME_TOTAL)
        {
            moveTimer = 0;
        }
        if (hurtTimer>0)
        {
            hurtTimer--;
        }

    }
    public void regenerate(int val)
    {
        if(currentHealth + val <= totalHealth)
        {
            currentHealth+=val;
        }

    }
    public void resetHealth()
    {
        currentHealth = 100;
    }
    public void damage(int val)
    {
        currentHealth -= val;
        if (currentHealth <=0)
        {
            currentHealth = 0;
            dead = true;
        }
        moving = false;

        hurtTimer = HURT_TIME_TOTAL;
    }

    public void bonusTotalHealth(int val)
    {
        totalHealth+= val;
    }
    public int getCurrentHealth()
    {
        return currentHealth;
    }


}

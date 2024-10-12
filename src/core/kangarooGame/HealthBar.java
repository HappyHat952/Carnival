package core.kangarooGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class HealthBar {
    private int maxHealth;
    private int currHealth;
    private float width, ow;
    private int height;
    private float x, y;

    public HealthBar(float x, float y, float width, int maxHealth) {
        this.x = x;
        this.y = y;
        this.width = width;
        ow = width;
        this.maxHealth = maxHealth;
        currHealth = this.maxHealth;
        height = 2;
    }

    public void render(Graphics g)
    {
        Color c = g.getColor();
        if ((double) currHealth /maxHealth >= .6) {
            g.setColor(Color.green);
        } else if ((double) currHealth /maxHealth >= .35) {
            g.setColor(Color.orange);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }

    public void update(float x, float y)
    {
        this.x = x;
        this.y = y;

    }

    public void reduceHealth() {
        currHealth--;
        width = ow * (float) currHealth /maxHealth;
    }

    public void increaseHealth(int h) {
        currHealth += h;
        if (currHealth > maxHealth) {
            currHealth = maxHealth;
        }
        width = ow * (float) currHealth /maxHealth;
    }

    public double getHealthPercent() {
        return (double) currHealth /maxHealth;
    }
    public int getHealth(){
        return currHealth;
    }
    public void increaseHealth() {
        if (currHealth < maxHealth) {
            currHealth++;
            width = ow * (float) currHealth / maxHealth;
        }
    }
}

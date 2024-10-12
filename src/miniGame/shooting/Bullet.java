package miniGame.shooting;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Bullet {

	private float x;
	private float y;
	private boolean active = false;
	private int speed;
	private float width, height;
	public Bullet(float x , float y)
	{
		this.x = x;
		this.y = y;
		active = true;
		speed = 15;
		width = 15;
		height = 20;
	}

	public void render(Graphics g)
	{
		if (active) {
			Color c = g.getColor();
			g.setColor(Color.red);
			g.fillOval(x, y, width, height);
			g.setColor(c);
		}
	}

	public void update(int delta)
	{
		if (active) {
			y -= speed;
			if (y <= 0) {
				deactivate();
			}
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	public void deactivate() {
		active = false;

	}

	public boolean isActive() {
		return active;
	}
}

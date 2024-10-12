package miniGame.shooting;

import java.util.ArrayList;

import core.Main;
import core.message.FloatMessage;
import core.message.Message;
import core.message.MessageManager;
import miniGame.shooting.Bullet;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import setup.Images;


public class Shooter {

	private float x;
	private float y;
	private int width, height;
	private int speed;
	private ArrayList<miniGame.shooting.Bullet> bullets;

	private Target target;
	private int coolTimer;
	private Image img = null;


	public Shooter(Target target)
	{
		img = Images.gun;
		this.x = 600;//Main.getScreenWidth()/2;
		this.y = Main.getScreenHeight() - img.getHeight();
		width = 200;
		height = 100;
		speed = 5;
		bullets = new ArrayList<miniGame.shooting.Bullet>();
		this.target = target;
		coolTimer = 0;

				//.getScaledCopy(width, height);

	}

	public void render(Graphics g)
	{
		img.draw(x, y);
		for(Bullet b: bullets) {
			b.render(g);
		}
	}

	public void update(int key, int delta, MessageManager messager)
	{
		for(Bullet b: bullets) {
			b.update(delta);
			if(target.check(b)) {
				b.deactivate();
				messager.addMessage(new FloatMessage("+1", b.getX(), b.getY(), Color.red, 60));
				ShootingGame.incrementScore();
			}
		}
		for (int i = 0; i < bullets.size(); i++)
		{
			if(!bullets.get(i).isActive())
			{
				bullets.remove(i);
				i--;
			}
		}
		if (coolTimer > 0) {
			coolTimer--;
		}
		if (key == org.newdawn.slick.Input.KEY_A) {
			x -= speed;
			if (x < 0) {
				x = 0;
			}
		} else if (key == org.newdawn.slick.Input.KEY_D) {
			float orig = x;
			x += speed;
			if (x + width > Main.getScreenWidth()) {
				x = orig;
			}
		}
	}

	public void keyPressed(int key, char c)
	{
		if (key == org.newdawn.slick.Input.KEY_SPACE) {
			if (coolTimer == 0) {
				bullets.add(new Bullet(x + (width/4), y));
				coolTimer = 15;
			}
		}

	}
}

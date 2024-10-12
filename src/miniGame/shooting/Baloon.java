package miniGame.shooting;

import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import setup.Images;

public class Baloon {

	private int x;
	private int y;
	private int width;
	private int height;
	private Image img = null;
	private SpriteSheet baloonSprite = null;
	private int spriteR, spriteC;

	public Baloon(int i , int j)
	{
		this.x = j;
		this.y = i;
		width = 75;
		height = 75;

		img = Images.baloon.getScaledCopy(width*2, height*2);
		baloonSprite = new SpriteSheet(img, width, height);
		if(Math.random() <= 0.5) {
			spriteR = 0;
		} else {
			spriteR = 1;
		}
		if(Math.random() <= 0.5) {
			spriteC = 0;
		} else {
			spriteC = 1;
		}
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setX(int i) {
		x = i;
	}

	public void setY(int i) {
		y = i;
	}

	public int getXPixel()
	{
		return getX() * getWidth();
	}

	public int getYPixel()
	{
		return getY() * getHeight();
	}

	public static int getWidth()
	{
		return Main.getScreenWidth() / Target.COLUMNS;
	}

	public static int getHeight()
	{
		return (Main.getScreenHeight()/4) / Target.BAL_ROWS;
	}

	public void render(Graphics g)
	{
		baloonSprite.getSprite(spriteC, spriteR).draw(getXPixel() + ((getWidth() - width)/2), getYPixel() + ((getHeight() - height)/2));
	}

	public boolean contains(float x, float y) {
		int i = getXPixel() + ((getWidth() - width)/2);
		int j = getYPixel() + ((getHeight() - height)/2);

		if (x > i && x < i + width && y > j && y < j + height) {
			return true;
		}
		return false;
	}
}

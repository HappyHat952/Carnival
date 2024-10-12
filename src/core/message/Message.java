package core.message;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import setup.Fonts;

public class Message {

	protected String text;
	protected float x;
	protected float y;
	protected Color color;
	protected Font font;
	public int duration;
	public int timeLeft;
	public boolean fading;

	protected void centerText() {
		x = x - font.getWidth(text)/2;
	}
	
	public Message(String text, float x, float y, Color color, Font font) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
		this.font = font;
	}

	public Message(String text, float x, float y, Color color, Font font, int duration, boolean fading) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
		this.font = font;
		this.duration = duration;
		this.fading = fading;
		timeLeft = duration;
	}

	public Message(String text, float x, float y) {
		this(text, x, y, Color.white, Fonts.big);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void update() {
		if (timeLeft > 0) {
			timeLeft--;
		}
	}

	public float getPercentTimeLeft() {
		return (float) timeLeft / duration;
	}
	public String getText(){ return text;}

	public void render(Graphics g) {
		if (fading) {
			color = new Color(color.getRed(), color.getBlue(), color.getGreen(), getPercentTimeLeft());
		}
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
		// Sets the font and color and then draw the text at the specified coordinates.
	}

	public int getDuration() {
		return duration;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

}

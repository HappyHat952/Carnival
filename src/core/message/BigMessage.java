package core.message;

import core.Main;
import org.newdawn.slick.Color;
import setup.Fonts;

public class BigMessage extends Message {

	protected static float x = Main.getScreenWidth() * .4f;
	protected static float y = Main.getScreenHeight() * .02f;

	public BigMessage(String text, Color color, int duration) {
		super(text, x, y, color, Fonts.big, duration, true);
	}

}
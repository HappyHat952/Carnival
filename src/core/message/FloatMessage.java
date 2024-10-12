package core.message;

import org.newdawn.slick.Color;
import setup.Fonts;

public class FloatMessage extends Message {

	public FloatMessage(String text, float x, float y, Color color, int duration) {
		super(text, x, y, color, Fonts.big, duration, false);
	}
	
	public void update() {
		super.update();
		y = y - 2;
	}
}

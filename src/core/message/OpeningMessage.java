package core.message;

import miniGame.sapo.SapoGame;
import miniGame.shooting.ShootingGame;
import miniGame.whack.WhackGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class OpeningMessage extends Message {

    private Image image;

    public OpeningMessage(String text, float x, float y, Color color, Font font, int duration, boolean fading) {
        super(text, x, y, color, font, 600, true);
        if (text.equals(ShootingGame.getName()))
        {

        }
        else if (text.equals(SapoGame.getName()))
        {

        }
        else if (text.equals(WhackGame.getName()))
        {

        }
    }

    public void render(Graphics g) {
        if (fading) {
            color = new Color(color.getRed(), color.getBlue(), color.getGreen(), getPercentTimeLeft());
        }
        g.setColor(color);
        g.setFont(font);

        g.drawString(text, x, y);
        // Sets the font and color and then draw the text at the specified coordinates.
    }


}

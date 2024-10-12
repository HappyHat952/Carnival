package core.message;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import setup.Fonts;

public class FootNoteMessage extends Message{

    public FootNoteMessage(String text, int duration) {

        super(text, Main.getScreenWidth()/2 - Fonts.big.getWidth(text)/2,
                Main.getScreenHeight()-Fonts.big.getHeight()-40, Color.white, Fonts.big, duration, true);
    }
    public void render(Graphics g)
    {
        g.setColor(new Color(0,0,0,getPercentTimeLeft()));
        g.fillRect(x,y,Fonts.big.getWidth(text), Fonts.big.getHeight());
        super.render(g);
    }
}

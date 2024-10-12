package miniGame.kangaroo.fighter;

import core.Main;
import org.newdawn.slick.Color;
import setup.Images;

public class KangarooFighter extends Fighter{
    public KangarooFighter()
    {
        dead = false;
        myColor = Color.green;
        barY = Main.getScreenHeight()/7;
        barX = Main.getScreenWidth() - BAR_LENGTH - Main.getScreenWidth()/12;
        totalHealth = 100;
        currentHealth = totalHealth;
        name = "kangaroo";
        image = Images.kangaroo.getSubImage(0,0).getScaledCopy(0.5f);
        fighterX = Main.getScreenWidth()/24;
        fighterY = 0;
    }
}

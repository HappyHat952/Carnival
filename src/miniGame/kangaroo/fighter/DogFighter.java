package miniGame.kangaroo.fighter;

import core.Main;
import org.newdawn.slick.Color;
import setup.Images;

public class DogFighter extends Fighter{
    public DogFighter()
    {
        dead = false;
        myColor = Color.green;
        barY = Main.getScreenHeight() - Main.getScreenHeight()/7;
        barX = Main.getScreenWidth()/12;
        totalHealth = 100;
        currentHealth = totalHealth;
        name = "dog";
        image = Images.defaultPlayer.getSubImage(0,3).getScaledCopy(3f);
        fighterX = Main.getScreenWidth()-Main.getScreenWidth()/4;
        fighterY = 0;

    }

}

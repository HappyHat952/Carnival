package setup;

import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;

public class Fonts {

    public static TrueTypeFont big;
    public static TrueTypeFont medium;
    public static TrueTypeFont small;

    public static void loadFonts() {
        big = new TrueTypeFont(new Font("Verdana", Font.BOLD, 42), false);
        medium = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 32), false);
        small = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), false);
    }

}

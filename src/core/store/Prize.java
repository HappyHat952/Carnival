package core.store;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Prize {

    protected boolean expired;
    protected Image image;
    protected Image scaledImage;

    protected float x;
    protected float y;
    protected int id;

    protected float w;
    protected float h;
    protected String name;
    protected String stats;
    protected int cost;
    private boolean showStats;
    public final static int DUCK_CONST = 1;
    public final static String DUCK_STR = "duck";
    public final static int RED_CONST = 2;
    public final static String RED_STR = "red-hat";
    public final static int PERU_CONST = 3;
    public final static String PERU_STR = "peru";
    public final static int POKE_CONST = 4;
    public final static String POKE_STR = "poke";
    public final  static int TEA_CONST = 5;
    public final static String TEA_STR = "tea";



    // Prize constructor: I can make more parameters for name, image, cost etc with setCost() methods when we know what they are

    public Prize(String prizeName) {
        showStats = false;
        if (prizeName .equals( DUCK_STR)) {
            cost = 70;
            image = Images.duckItem;
            scaledImage = Images.duckItem.getScaledCopy(100, 100);
            name = DUCK_STR;
            stats = "Quack! This is a duck hat :) Click to equip.";
            x = 10;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
            id = DUCK_CONST;
        } else if (prizeName.equals( RED_STR) ){
            cost = 60;
            image = Images.redHatItem;
            scaledImage = Images.redHatItem.getScaledCopy(100, 100);
            name = RED_STR;
            stats = "Super cool red baseball cap. Click to equip.";
            x = 400;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
            id = RED_CONST;
        }
        else if (prizeName.equals(PERU_STR)) {
            cost = 100;
            image = Images.peruHatItem;
            scaledImage = Images.peruHatItem.getScaledCopy(100, 100);
            name = PERU_STR;
            stats = "Peruvian sun hat. Click to equip.";
            x = 700;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
            id = PERU_CONST;
        }
        else if (prizeName.equals(POKE_STR)) {
            cost = 120;
            image = Images.pokemonItem;
            scaledImage = Images.pokemonItem.getScaledCopy(100, 100);
            name = POKE_STR;
            stats = "PokeBall for your head. Click to equip.";
            x = 1000;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
            id = POKE_CONST;
        }
        else if (prizeName.equals(TEA_STR)) {
            cost = 100;
            image = Images.teaCupItem;
            scaledImage = Images.teaCupItem.getScaledCopy(100, 100);
            name = TEA_STR;
            stats = "Marvelous Tea hat! Click to equip.";
            x = 1300;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
            id = TEA_CONST;
        }

    }
    public Prize(int id) {
        showStats = false;
        this.id = id;
        if (id == DUCK_CONST) {
            cost = 70;
            image = Images.duckItem;
            scaledImage = Images.duckItem.getScaledCopy(100, 100);
            name = DUCK_STR;
            stats = "Quack! This is a duck hat :) Click to equip.";
            x = 10;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
        } else if (id == RED_CONST) {
            cost = 60;
            image = Images.redHatItem;
            scaledImage = Images.redHatItem.getScaledCopy(100, 100);
            name = RED_STR;
            stats = "Super cool red baseball cap. Click to equip.";
            x = 400;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
        }
        else if (id == PERU_CONST) {
            cost = 100;
            image = Images.peruHatItem;
            scaledImage = Images.peruHatItem.getScaledCopy(100, 100);
            name = PERU_STR;
            stats = "Peruvian sun hat. Click to equip.";
            x = 700;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
        }
        else if (id == POKE_CONST) {
            cost = 120;
            image = Images.pokemonItem;
            scaledImage = Images.pokemonItem.getScaledCopy(100, 100);
            name = POKE_STR;
            stats = "PokeBall for your head. Click to equip.";
            x = 1000;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
        }
        else if (id == TEA_CONST) {
            cost = 100;
            image = Images.teaCupItem;
            scaledImage = Images.teaCupItem.getScaledCopy(100, 100);
            name = TEA_STR;
            stats = "Marvelous Tea hat! Click to equip.";
            x = 1300;
            y = 500;
            w = getImage().getWidth();
            h = getImage().getHeight();
            //id = 1;
        }




        else {
            cost = 30;
            image = Images.C;
            scaledImage = Images.C.getScaledCopy(100, 100);
            name = "C";
            x = 1000;
            y = 700;
            w = getImage().getWidth();
            h = getImage().getHeight();
            id = 2;
        }
    }
    public String getName() {
        return name;
    }
    public int getID(){ return id;}

    public int getCost() {
        return cost;
    }

    public Image getImage() {
        return image;
    }
    public Image getScaledImage() {
        return scaledImage;
    }

    public boolean isExpired() {
        return (expired);
    }

    public void expire() {
        expired = true;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public void setW(int newW) {
        w = newW;
    }

    public void setH(int newH) {
        h = newH;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String s) {
        stats = s;
    }

    public void setShowStats(boolean show) {
        showStats = show;
    }

    public boolean isMouseOver(int mx, int my) {
        if (mx >= x && my >= y && mx <= x + w && my <= y + h) {
            return true;
        }
        return false;
    }

    public void render(Graphics g) {
        g.drawImage(scaledImage, x, y);
    }
}

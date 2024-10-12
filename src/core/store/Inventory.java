package core.store;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import setup.Fonts;
import setup.Images;

import java.util.ArrayList;

import static core.Carnival.getMyHat;

public class Inventory {

    public static ArrayList<Prize> inventory;
    private boolean isShowing;

    public Inventory() {
        inventory = new ArrayList<Prize>();
        isShowing = false;
    }

    public void addPrize(Prize p) {
        inventory.add(p);
        p.setX(300+inventory.indexOf(p)*100);
        p.setY(400);
        p.setW(p.getScaledImage().getWidth());
        p.setW(p.getScaledImage().getWidth());
        p.setH(p.getScaledImage().getHeight());
    }

    public void update(int x, int y) {


    }
    public static ArrayList<Prize> getInventory() {
        return inventory;
    }


    public boolean isShowing() {
        return isShowing;
    }
    public void showInventory() {
        isShowing = true;
    }

    public void hideInventory() {
        isShowing = false;
    }
    public void render(Graphics g, int x, int y) {
        g.setColor(new Color(108,91,61));
        if (isShowing) {
            g.drawImage(Images.inventory, 300, 400);
            for (int i = 0; i < inventory.size(); i++) {
                if (i <=2) {
                    g.drawImage(inventory.get(i).getScaledImage(), 350 + i * 200, 440);
                    inventory.get(i).setX(350 + i * 200);
                    inventory.get(i).setY(440);
                    inventory.get(i).setW(inventory.get(i).getScaledImage().getWidth());
                    inventory.get(i).setH(inventory.get(i).getScaledImage().getHeight());
                } else if (i <=5) {
                    g.drawImage(inventory.get(i).getScaledImage(), 350 + (i-3) * 200, 640);
                    inventory.get(i).setX(350 + (i-3) * 200);
                    inventory.get(i).setY(640);
                    inventory.get(i).setW(inventory.get(i).getScaledImage().getWidth());
                    inventory.get(i).setH(inventory.get(i).getScaledImage().getHeight());
                } else {
                    g.drawImage(inventory.get(i).getScaledImage(), 350 + (i-6) * 200, 840);
                    inventory.get(i).setX(350 + (i-6) * 200);
                    inventory.get(i).setY(840);
                    inventory.get(i).setW(inventory.get(i).getScaledImage().getWidth());
                    inventory.get(i).setH(inventory.get(i).getScaledImage().getHeight());
                }
            }
            if (!inventory.isEmpty()) {
                for (Prize p: inventory) {
//                    g.setColor(Color.white);
//                    g.drawRect(p.getX(), p.getY(), p.getW(), p.getH());
                    if (x >= p.getX() && x <= p.getX() + p.getW() &&
                            y >= p.getY() && y <= p.getY() + p.getH()) {
                        g.setColor(Color.black);
                        g.setFont(Fonts.small);
                        g.drawString(p.getStats(), p.getX(), p.getY());
                    }
                }
            }
            g.setFont(Fonts.big);
            g.setColor(Color.black);
            g.drawString("Press <E> to close", Main.getScreenWidth() * 0.1f + 2, Main.getScreenHeight() * 0.02f + 2);
            g.setColor(Color.white);
            g.drawString("Press <E> to close", Main.getScreenWidth() * 0.1f, Main.getScreenHeight() * 0.02f);
            // ENTER INVENTORY
        } else {
            g.setFont(Fonts.big);
            g.setColor(Color.black);
            g.drawString("Inventory Size: " + inventory.size(), Main.getScreenWidth() * 0.1f + 2, Main.getScreenHeight() * 0.02f + 2);
            g.setColor(Color.white);
            g.drawString("Inventory Size: " + inventory.size(), Main.getScreenWidth() * 0.1f, Main.getScreenHeight() * 0.02f);
        }
    }

    //returns prize
    public Prize mousePressed(int button, int x, int y)
    {
        if (isShowing)
        {
            for (Prize p: inventory)
            {
                if (p.isMouseOver(x, y))
                {
                    return p;
                }
            }
            if (getMyHat() != null) {
                if (getMyHat().isMouseOver(x, y)) {
                    return getMyHat();
                }
            }
        }
        return null;
    }

    public ArrayList<Prize> getPrizes() {
        return inventory;
    }



}
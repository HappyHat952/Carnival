package core;

import core.message.FootNoteMessage;
import core.message.MessageManager;
import core.store.Prize;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import world.Player;

import java.util.ArrayList;

import static core.Carnival.fileMaker;


public class Store extends BasicGameState {
    private int id;
    private StateBasedGame sbg;
    public ArrayList<Prize> availablePrizes;

    private static int mouseX;
    private static int mouseY;
    private MessageManager messageManager;

    public Store(int id) {
        availablePrizes = new ArrayList<Prize>();
        this.id = id;
    }
    public int getID() {
        return id;
    }

    public void buy(Prize p) {
        Carnival.spendTickets(p.getCost());
        availablePrizes.remove(p);
        Carnival.getInventory().addPrize(p);
        Player.setPlayerImage(p.getID());
    }

    public void renderPrizes(Graphics g) {
        for (Prize p: availablePrizes) {
            g.drawImage(p.getImage(), p.getX(), p.getY());
            g.setColor(Color.white);
            g.setFont(Fonts.big);
            g.drawString("Cost: " + p.getCost(), p.getX(), p.getY()-30);
        }
        // This will be a loop and each prize will have a designated location in its subclass with x and y coordinates
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
// Sets up the shop for the first time

        availablePrizes.add(new Prize(Prize.DUCK_CONST));
        availablePrizes.add(new Prize(Prize.RED_CONST));
        availablePrizes.add(new Prize(Prize.PERU_CONST));
        availablePrizes.add(new Prize(Prize.POKE_CONST));
        availablePrizes.add(new Prize(Prize.TEA_CONST));
        //availablePrizes.add(new Prize("C"));
        sbg = stateBasedGame;
        Images.loadImages();
        Fonts.loadFonts();
        gameContainer.setShowFPS(false);
        messageManager = new MessageManager();
    }


    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
// Renders in the store and items
//        Images.store.draw(0, 0);
        graphics.drawImage(Images.bg.getSubImage(0,2), 0, 0);
        renderPrizes(graphics);
        Carnival.renderTickets(graphics);
        graphics.resetFont();
        Carnival.getInventory().render(graphics, mouseX, mouseY);
        messageManager.render(graphics);


        graphics.setFont(Fonts.big);
        graphics.setColor(Color.black);
        graphics.drawString("Press X to Exit", Main.getScreenWidth() * 0.65f + 2, Main.getScreenHeight() * 0.02f + 2);
        graphics.setColor(Color.white);
        graphics.drawString("Press X to Exit", Main.getScreenWidth() * 0.65f, Main.getScreenHeight() * 0.02f);

    }


    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        fileMaker.update();
        // This code happens when you leave a gameState.
        messageManager.clear();
    }


    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Carnival.getInventory().update(mouseX, mouseY);
        messageManager.update();
        messageManager.addMessage(new FootNoteMessage("EVERYTHING FOR 50 TICKETS", 800));
    }

    public void keyPressed(int key, char c) {
//        if (SOME KEY IS PRESSED THAT IS VALID) {
//            key = index (might be changed if the key is not the right index)
//            if (Game.hasMoney(availablePrizes.get(index).getCost())
//                    && Game.getItems().hasSpace()) {
//                Item original = products.get(index);
//                Item newItem = buildItem(original.getClass());
//                buy(newItem);
//                if (original.isUnique()) {
//                    products.remove(original);
//                }
//            }
//        }
// Press 'P' to escape

        if (key == Input.KEY_X) {
            sbg.enterState(Main.CARNIVAL_ID);
            Carnival.gc.setDefaultMouseCursor();
        }
        if (key == Input.KEY_E) {
            if (Carnival.getInventory().isShowing()) {
                Carnival.getInventory().hideInventory();
            } else {
                Carnival.getInventory().showInventory();
            }
        }
        // This code happens every time the user presses a key
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void mousePressed(int button, int x, int y) {
        for (Prize p : availablePrizes) {
            if (p.isMouseOver(mouseX, mouseY)) {
                if (Carnival.getTickets() >= p.getCost()) {
                    buy(p);
                    return;
                }
            }

        }
    }

    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        mouseX = newx;
        mouseY = newy;

        boolean reset = true;

        for (Prize p: availablePrizes) {
            if (p.isMouseOver(mouseX, mouseY)) {
                reset = false;
                try {
                    Image i = p.getImage().getScaledCopy(32, 32);
                    Carnival.gc.setMouseCursor(i, 30, 30);
                    return;
                } catch (SlickException e) {
                    e.printStackTrace();
                }
            }

        }

        if (reset) {
            Carnival.gc.setDefaultMouseCursor();
        }
    }

}
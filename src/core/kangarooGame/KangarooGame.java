package core.kangarooGame;

import core.Carnival;
import core.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class KangarooGame extends BasicGameState {

    private int id;
    private StateBasedGame sbg;
    private KangarooPlayer p;
    private ArrayList<Kangaroo> k;
    private ArrayList<Star> stars;
    private Color origBG;
    private int maxK = 3;
    private int maxStars = 15;
    private TrueTypeFont font;

    private boolean mushroomAdded;

    public KangarooGame(int id)
    {
        this.id = id;
    }

    @Override
    public void init(GameContainer arg0, StateBasedGame stateBasedGame) throws SlickException {
        // TODO Auto-generated method stub
        sbg = stateBasedGame;
        p = new KangarooPlayer(100, 100);
        k = new ArrayList<Kangaroo>();
        for (int i = 0; i < maxK; i++) {
            k.add(new Kangaroo(1));
        }
        stars = new ArrayList<Star>();
        for (int i = 0; i < maxStars; i++) {
            stars.add(new Star((float) (Math.random()*Main.getScreenWidth()), (float) (Math.random() * (Main.getScreenHeight() - 250)), 0));
        }
        font = new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 30), false);
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
        // TODO Auto-generated method stub
        Font f = g.getFont();
        g.setBackground(Color.black);
        for (Kangaroo k1: k) {
            k1.render(g);
        }
        for (Star s: stars) {
            s.render(g);
        }
        p.render(g);

        g.drawLine(0, Main.getScreenHeight()-200, Main.getScreenWidth(), Main.getScreenHeight()-200);
        g.drawString("Tickets: " + Carnival.getTickets(), 30, Main.getScreenHeight() - 100);
        String inst = "W - Move Up, A - Move Left, S - Move Down, D - Move Right, <Space> - Punch, E - Exit";
        g.drawString(inst, 30 + font.getWidth("Tickets: 0000") + 30, Main.getScreenHeight() - 100);
        g.setFont(f);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a gameState.
        origBG = gc.getGraphics().getBackground();
    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        gc.getGraphics().setBackground(origBG);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame arg1, int delta) throws SlickException {
        // TODO Auto-generated method stub
        int key = -1;
        if (gc.getInput().isKeyDown(Input.KEY_A))
        {
            key = Input.KEY_A;
        } else if (gc.getInput().isKeyDown(Input.KEY_D))
        {
            key = Input.KEY_D;
        } else if (gc.getInput().isKeyDown(Input.KEY_S))
        {
            key = Input.KEY_S;
        } else if (gc.getInput().isKeyDown(Input.KEY_W)) {
            key = Input.KEY_W;
        } else if (gc.getInput().isKeyDown(Input.KEY_SPACE))
        {
            key = Input.KEY_SPACE;
        }
        p.update(key, delta);
        if (p.getHealthPercent() <= .35 && !mushroomAdded) {
            stars.add(new Star((float) (Math.random()*Main.getScreenWidth()), (float) (Math.random() * (Main.getScreenHeight() - 250)), 1));
            mushroomAdded = true;
        }
        for (Kangaroo k1: k) {
            k1.update(delta);
        }
        for (Star s: stars) {
            if (check(s.getX(), s.getY(), s.getWidth(), s.getHeight(), p.getX(), p.getY(), p.getWidth(), p.getHeight())) {
                s.deActivate();
                if (s.getType() == 0) {
                    Carnival.gainTickets(1);
                } else {
                    p.increaseHealth(15);
                    mushroomAdded = false;
                }
            }
        }
        for (Kangaroo k1: k) {
            if (check(k1.getX(), k1.getY(), k1.getWidth(), k1.getHeight(), p.getX(), p.getY(), p.getWidth(), p.getHeight())) {
                System.out.println("reducing health...");
                p.reduceHealth();
            }
        }
        for (int i = 0; i < stars.size(); i++)
        {
            if(!stars.get(i).isActive())
            {
                stars.remove(i);
                i--;
            }
        }

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return id;
    }

    public void keyPressed(int key, char c)
    {
        if (key == Input.KEY_E) {
            sbg.enterState(Main.CARNIVAL_ID);
        }
    }

    public boolean check(float sourceX, float sourceY, float sourceW, float sourceH, float targetX, float targetY, float targetW, float targetH) {
        boolean b = sourceX >= targetX && sourceX + sourceW <= targetX + targetW &&
                sourceY >= targetY && sourceY + sourceH <= targetY + targetH;
        /*
        if (!b) {
            b = targetX >= sourceX && targetX + targetW <= sourceX + sourceW &&
                    targetY >= sourceY && targetY + targetH <= sourceY + sourceH;
        }
         */
        return b;
    }
}

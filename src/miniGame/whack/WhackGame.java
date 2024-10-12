package miniGame.whack;

import core.Carnival;
import core.message.MessageManager;
import miniGame.MiniGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;

import static setup.Images.hammerCursorHit;

public class WhackGame extends MiniGame {

    final public static int ID = 1;
    private static int score;
    private static int time;
    private StateBasedGame sbg;
    private MessageManager messageManager;

    private int mouseTimer;
    private boolean isHit;

    private Mole[][] moles;
    private int mouseX;
    private int mouseY;

    public WhackGame(int id, MessageManager m)
    {
        // HOW TO HIDE MOUSE CURSOR?????
        setSeconds(30);
        messageManager = m;
        mouseTimer = 0;
        isHit = false;
        inGameTicket = 0;
        time = 20*60;
        gameOver = false;
        moles = new Mole[3][3];
        for (int r = 0; r < moles.length; r++) {
            for (int c = 0; c < moles[0].length; c++) {
                moles[r][c] = new Mole(messageManager);
                moles[r][c].setX(500+c*300);
                moles[r][c].setY(200+r*300);
            }
        }
        welcomeBanner = Images.whackScreen;
        TOTAL_GAME_SECONDS = 30;
        gameOverScreen = Images.timesUp;
    }

    public Mole[][] getMoles() {
        return moles;
    }

    @Override
    public void render(Graphics g)
    {
            if (getSeconds() == TOTAL_GAME_SECONDS) {
                try {
                    Carnival.gc.setMouseCursor(Images.hammerCursor.getScaledCopy(250, 250), 100, 100);
                } catch (SlickException e) {
                    e.printStackTrace();
                }
            }
            g.setBackground(Color.black);
            for (Mole[] mole : moles) {
                for (int c = 0; c < moles[0].length; c++) {
                    if (mole[c] != null) {
                        mole[c].render(g);
                    }
                }
            }
        super.render(g);

    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        if (!pause && !gameOver) {
            if (mouseTimer > 0) {
                mouseTimer--;
            } else {
                if (isHit) {
                    try {
                        Carnival.gc.setMouseCursor(Images.hammerCursor.getScaledCopy(250, 250), 100, 100);
                        isHit = false;
                    } catch (SlickException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (getSeconds() != 0) {
                for (Mole[] mole : moles) {
                    for (int c = 0; c < moles[0].length; c++) {
                        if (mole[c] != null) {
                            mole[c].update();
                        }
                    }
                }
            } else {
                gameOver = true;
                for (Mole[] mole : moles) {
                    for (int c = 0; c < moles[0].length; c++) {
                        if (mole[c] != null) {
                            mole[c].setLevel(0);
                        }
                    }
                }
            }
        }
    }

    public void keyPressed(int key, char c)
    {
        super.keyPressed(key, c);
    }

    public void mousePressed(int button, int x, int y) {
        y += hammerCursorHit.getHeight() - 50;
        if (!isHit) {
            try {
                Carnival.gc.setMouseCursor(hammerCursorHit, 30, 30);
                mouseTimer = 10;
                isHit = true;
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

        for (Mole[] mole : moles) {
            for (int c = 0; c < moles[0].length; c++) {
                if (mole[c].getLevel() != 0 && mole[c].getLevel() != 5 && mole[c].getLevel() != 6) {
                    if (x >= mole[c].getX() && x <= mole[c].getX() + mole[c].getW() && y >= mole[c].getY() && y <= mole[c].getY() + mole[c].getH()) {
                        mole[c].hitMole();
                        return;
                    }
                }
            }
        }
    }



    @Override
    public void resetGame() {
        super.resetGame();
        moles = new Mole[3][3];
        for (int r = 0; r < moles.length; r++) {
            for (int c = 0; c < moles[0].length; c++) {
                moles[r][c] = new Mole(messageManager);
                moles[r][c].setX(500+c*300);
                moles[r][c].setY(200+r*300);
            }
        }
        inGameTicket = 0;
        gameOver = false;
        mouseTimer = 0;
    }

    public static void incrementScore() {
        inGameTicket++;
        Carnival.gainTickets(1);
    }



}

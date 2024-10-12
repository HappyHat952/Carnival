package miniGame.penguin;

import core.Main;
import core.message.FloatMessage;
import core.message.MessageManager;
import miniGame.MiniGame;
import org.newdawn.slick.*;
import setup.Images;

import java.util.ArrayList;

public class PenguinGame extends MiniGame{


    private MessageManager messager;
    Penguin p;
    private Image background1;
    private Image background2;
    private int y1;
    private int y2;
    private static int speed;
    private int counter;
    private int screenHeight = Main.getScreenHeight();
    private int screenWidth = Main.getScreenWidth();
    private ArrayList<Obstacle> obstacles;

    public PenguinGame(int id, MessageManager m) {
        messager = m;
        ID = id;
        p = new Penguin();
        gameOver = false;
        welcomeBanner = Images.penguinScreen;
        background1 = Images.penguinBackground;
        background2 = Images.penguinBackground;
        y1 = 0;
        y2 = background1.getHeight() * -1;
        obstacles = new ArrayList<Obstacle>();
        speed = 22;
        counter = 0;
        gameOverScreen = Images.timesUp;
        TOTAL_GAME_SECONDS = 30;
    }

    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        //UPDATE PENGUIN AND THE OBSTACLES
        if (!pause && !gameOver){
            p.update();
            for (Obstacle o: obstacles) {
                o.update();
            }

            if (Math.random() <.03f) {
                obstacles.add(new Obstacle());
            }

            if (Math.random() <.02f) {
                obstacles.add(new Fish());
            }

            cleanUp();

            // UPDATE SCREEN
            int i = 0;
            while (y1 < screenHeight && y2 < screenHeight && i <= speed) {
                y1++;
                y2++;
                i++;
            }
            if (y1 >= screenHeight) {
                y1 = background1.getHeight() * -1;
            }
            if (y2 >= screenHeight) {
                y2 = background1.getHeight() * -1;
            }
            // MOVE PENGUIN
            if (gc.getInput().isKeyDown(Input.KEY_A)) {
                p.moveLeft();
            }
            if (gc.getInput().isKeyDown(Input.KEY_D)) {
                p.moveRight();
            }

            // IF OBSTACLES ARE HIT
            for (int a = 0; a < obstacles.size(); a++) {
                Obstacle o = obstacles.get(a);
                if (p.isOver(o) && o.getClass() != Fish.class) {
                    p.setImage(Images.penguinHit);
                    speed = 10;
                    counter = 60;
                    i--;
                } else if (p.isOver(o) && o.getClass() == Fish.class) {
                    inGameTicket++;
                    messager.addMessage(new FloatMessage("+1!", p.getX(), p.getY()-15, Color.red, 60));
                    obstacles.remove(o);
                    i--;
                }
            }

            if (counter > 0) {
                counter--;
                if (counter == 0) {
                    speed = 22;
                    p.setImage(Images.penguin);
                }
            }

        }


    }

    public void cleanUp() {
        obstacles.removeIf(o -> o.getY() > screenHeight);
    }

    @Override
    public void keyPressed(int key, char c) {
       super.keyPressed(key, c);
        if (c == 'a') {
           p.moveLeft();
       }
       if (c == 'd') {
           p.moveRight();
       }
    }

    public static int getSpeed() {
        return speed;
    }

    public void resetGame() {
        super.resetGame();
        gameOver = false;
        speed = 22;
        p.setX(Main.getScreenWidth()/2 - Images.penguin.getWidth());
    }

    public void render(Graphics g) {
        g.drawImage(background1, 0, y1);
        g.drawImage(background2, 0, y2);
        for (Obstacle o: obstacles) {
            o.render(g);
        }
        p.render(g);
        super.render(g);
        messager.render(g);
    }
}

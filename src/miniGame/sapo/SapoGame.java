package miniGame.sapo;

import core.message.MessageManager;
import miniGame.MiniGame;
import miniGame.sapo.hole.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import setup.Images;

import java.util.ArrayList;

public class SapoGame extends MiniGame {

    Slider slider;
    ArrayList<CoinHole> holes;
    ArrayList<Coin> coins;
    int cDownTimer;
    final int COOLDOWN_TIME;

    public SapoGame( int id, MessageManager m)
    {
        messager = m;
        TOTAL_GAME_SECONDS = 30;
        ID = id;
        slider = new Slider();
        inGameTicket = 0;
        cDownTimer = 0;
        COOLDOWN_TIME = 60;
        holes = new ArrayList<>();
        coins = new ArrayList<>();
        holes.add(new BlueHole(0));
        holes.add(new RedHole(1));
        holes.add(new GreenHole(2));
        holes.add(new FrogHole(3));
        holes.add(new GreenHole(4));
        holes.add(new RedHole(5));
        holes.add(new BlueHole(6));
        name = "sapo";
        welcomeBanner = Images.sapoScreen;
        gameOverScreen = Images.timesUp;

    }


    @Override
    public void render(Graphics g) {

        g.setBackground(Color.cyan);

            for (int i = 0; i <= (int) (holes.size() / 2); i++) {
                holes.get(i).draw(g);
                holes.get(holes.size() - 1 - i).draw(g);
            }
            for (Coin c : coins) {
                c.draw(g);
            }
            slider.draw(g, (float) cDownTimer / COOLDOWN_TIME);
            drawInfo(g);
        super.render(g);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc,delta);
        if (!pause && !gameOver) {
            if (cDownTimer > 0) {
                cDownTimer--;
            }
            slider.update();
            for (CoinHole c : holes) {
                c.update();
            }
            for (int c = 0; c < coins.size(); c++) {
                coins.get(c).update();
                if (coins.get(c).getDead()) {
                    coins.remove(c);
                    c--;
                }
            }
        }
        if (gameOver) {
            cDownTimer = COOLDOWN_TIME;
            slider.setSliderSpeed(false);
        }

    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (c == ' ' && !gameOver && cDownTimer == 0 && !pause)
        {
            int hit = slider.hit();
            if (hit != -1)
            {
                Coin coin = new Coin(messager, true);
                coin.setHole(holes.get(hit));
                coins.add(coin);
            }
            else {
                Coin coin = new Coin(messager, false);
                coin.setHole(slider.getSliderX());
                coins.add(coin);
            }
            if (hit == 3)
            {
                inGameTicket +=5;
            }
            else if (hit == 2 || hit == 4)
            {
                inGameTicket +=3;
            }
            else if (hit == 1 || hit == 5)
            {
                inGameTicket +=2;
            }
            else if (hit == 0 || hit == 6)
            {
                inGameTicket +=1;
            }

            cDownTimer = COOLDOWN_TIME;


        }
    }

    @Override
    public void resetGame() {
        super.resetGame();
        slider.setSliderSpeed(true);
        coins = new ArrayList<>();
        holes = new ArrayList<>();
        holes.add(new BlueHole(0));
        holes.add(new RedHole(1));
        holes.add(new GreenHole(2));
        holes.add(new FrogHole(3));
        holes.add(new GreenHole(4));
        holes.add(new RedHole(5));
        holes.add(new BlueHole(6));



    }
}

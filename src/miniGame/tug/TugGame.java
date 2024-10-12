package miniGame.tug;
import core.Main;
import core.message.MessageManager;
import miniGame.MiniGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import setup.Fonts;
import setup.Images;

public class TugGame extends MiniGame{

    MessageManager messager;
    Rope rope;
    int currSeconds;
    boolean finalTicketGiven;

    public TugGame( int id, MessageManager m)
    {
        messager = m;
        ID = id;
        inGameTicket = 0;
        name = "tug";
        setSeconds(30);
        rope = new Rope();
        gameOver = false;
        currSeconds = getSeconds();
        TOTAL_GAME_SECONDS = 30;
        welcomeBanner = Images.tugScreen;
        gameOverScreen = Images.niceJob;

    }
    public void update(GameContainer gc, int  delta)
    {
        if (!pause && rope.win() != Rope.NO_WIN)
        {
            if(rope.win() == Rope.ROOST_WIN && !finalTicketGiven)
            {
                inGameTicket = 50 - inGameTicket;
                finalTicketGiven = true;
                gameOverScreen = Images.niceJob;
            }
            else if (rope.win() == Rope.LION_WIN && !finalTicketGiven)
            {
                gameOverScreen = Images.youLose;
            }
            gameOver = true;

        }
        if (!pause  && getSeconds()<currSeconds)
        {
            inGameTicket++;
            currSeconds = getSeconds();
        }
        if(!pause && !gameOver)
        {
            rope.update();
        }
        super.update(gc, delta);

    }

    @Override
    public void render(Graphics g) {
        g.setBackground(Color.cyan);
        //g.setLineWidth((float)10);
        g.setColor(Color.green);
        g.fillRect(0,Main.getScreenHeight()*1/3, Main.getScreenWidth(), Main.getScreenHeight()*2/3);

        g.setColor(Color.red);
        g.fillRect(0,0,rope.getdistanceToAnimal(), Main.getScreenHeight());
        g.setColor(Color.blue);
        g.fillRect(Main.getScreenWidth()-rope.getdistanceToAnimal(),0,rope.getdistanceToAnimal(), Main.getScreenHeight());

        g.setFont(Fonts.big);
        g.setColor(Color.red);
        g.drawString("You (France)", Main.getScreenWidth()/4,3*Main.getScreenHeight()/4);
        g.setColor(Color.blue);
        g.drawString("England", 3*Main.getScreenWidth()/4,3*Main.getScreenHeight()/4);
        rope.draw(g);
        super.render(g);

    }

    @Override
    public void keyPressed(int key, char c) {
        if (c == ' ' &&!gameOver)
        {
            rope.tug();
        }
        super.keyPressed(key,c);

    }

    public void resetGame()
    {
        super.resetGame();
        rope.reset();
    }
}

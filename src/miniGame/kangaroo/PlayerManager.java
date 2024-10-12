package miniGame.kangaroo;

import core.Main;
import core.message.FloatMessage;
import core.message.MessageManager;
import core.message.MiddleMessage;
import miniGame.kangaroo.fighter.Fighter;
import miniGame.kangaroo.fighter.KangarooFighter;
import miniGame.kangaroo.moves.BigAttack;
import miniGame.kangaroo.moves.Move;
import miniGame.kangaroo.moves.Regen;
import miniGame.kangaroo.moves.SmallAttack;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import setup.Fonts;

import java.util.ArrayList;

public class PlayerManager {
    ArrayList<Move> moves;
    String name;
    String menu;
    MessageManager manager;
    int x;
    int y;
    int menuWidth;
    int menuHeight;
    Fighter player;
    Fighter opponent;
    Color myColor;


    public PlayerManager(Fighter f, Fighter opp, MessageManager m) {

        manager = m;
        moves = new ArrayList<>();
        moves.add(new BigAttack(m, f, opp));
        moves.add(new SmallAttack(m, f, opp));
        moves.add(new Regen(m, f, opp));
        menu = moveNames();
        int menuHeight = Fonts.big.getHeight(moveNames());
        int menuWidth = Fonts.big.getWidth(moveNames());
        player = f;
        opponent = opp;


        if (f instanceof KangarooFighter) {
            name = "Kangaroo (Opponent)";
            x = Main.getScreenWidth()/2-menuWidth;
            y = Main.getScreenHeight()/6 - menuHeight;
            myColor = Color.red;

        } else {
            name = "Dog (you)";
            x = Main.getScreenWidth()/2;
            y = 4*Main.getScreenHeight()/6;
            myColor = Color.blue;
        }

    }


    public void draw(Graphics g) {
        g.setColor(myColor);
        g.setFont(Fonts.big);
        g.drawString(name, x, y-Fonts.big.getHeight());
        drawMenu( x, y,Fonts.big, g);
    }

    public void drawMenu(int x, int y , TrueTypeFont f, Graphics g)
    {
        int initX=x;
        int initY=y;
        g.setColor(Color.black);
        g.fillRect(initX,initY,Main.getScreenWidth()/4, y+f.getHeight("2")*moves.size()*(float)3/2-initY);
        g.setColor(myColor);
        g.setFont(f);
        String st = "";
        int i = 0;
        for (Move m : moves) {
            st += (1+i);
            st += ".";
            st += m.toString();
            i++;
            g.drawString(st, x, y);
            st = "";
            if (i!=moves.size())
            {
                y += 3*f.getHeight("l")/2;
            }

        }
        g.drawRect(initX,initY,Main.getScreenWidth()/4, y+f.getHeight("2")-initY);

    }

    //DOESNT CHECK IF IS TURN
    public void use(int i)
    {
        boolean used = moves.get(i).use();

        if (i == 2)
        {
            manager.addMessage(new MiddleMessage((moves.get(i).toString()),60, myColor));
            manager.removeDiffMiddleMEssages(moves.get(i).toString());
        }

        else if (used)
        {
            //System.out.println(player.getName()+" used move "+moves.get(i).toString()+". It did "+moves.get(i).getDamage()+" damage");
            manager.addMessage(new MiddleMessage(("-"+moves.get(i).getDamage()),60, myColor));
            manager.removeDiffMiddleMEssages("-"+moves.get(i).getDamage());
        }
        else {
            //System.out.println(player.getName()+" used move "+moves.get(i).toString()+", but it missed.");
            manager.addMessage(new MiddleMessage("missed",60, myColor));
            manager.removeDiffMiddleMEssages("missed");
        }
//        System.out.println(player.getName()+"-"+player.getCurrentHealth()+"|"+opponent.getCurrentHealth()+"-"+opponent.getName());
//        System.out.println("");

    }

    public String moveNames() {
        String st = "";
        int i = 1;
        for (Move m : moves) {
            st += i;
            st += ".";
            st += m.toString();
            st += "\n";
            i++;
        }
        return st;
    }
}

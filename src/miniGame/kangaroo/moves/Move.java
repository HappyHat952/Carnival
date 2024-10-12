package miniGame.kangaroo.moves;

import core.message.MessageManager;
import miniGame.kangaroo.fighter.Fighter;

public class Move {
    MessageManager manager;
    boolean kang;
    String KANG_NAME;
    String DOG_NAME;
    int KANG_CHANGE;
    int DOG_CHANGE;
    float KANG_RANDOM;
    float DOG_RANDOM;

    String name;
    int value;
    float random;

    Fighter player;
    Fighter opponent;

    boolean used;

    public Move(MessageManager m)
    {
        manager = m;
    }


    public boolean use()
    {
        used = true;
        return used;
    }
    public void reset()
    {
        used = false;
    }
     public void update()
    {

    }
     public void render()
    {

    }
    public String toString()
    {
        return name;
    }
    public int getDamage()
    {
        return value;
    }
}

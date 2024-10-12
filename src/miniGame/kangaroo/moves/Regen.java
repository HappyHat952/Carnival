package miniGame.kangaroo.moves;

import core.message.MessageManager;
import core.message.MiddleMessage;
import miniGame.kangaroo.fighter.Fighter;
import miniGame.kangaroo.fighter.KangarooFighter;

public class Regen extends Move{


    public Regen(MessageManager m, Fighter owner, Fighter opponent)
    {
        super(m);
        if (owner instanceof KangarooFighter)
        {
            kang = true;
        }
        player = owner;
        this.opponent = opponent;
        KANG_NAME = "rest";
        DOG_NAME = "stay";
        KANG_CHANGE = 0;
        DOG_CHANGE = 0;
        KANG_RANDOM = 1f;
        DOG_RANDOM = 1f;
        if (kang)
        {
            name = KANG_NAME;
            value = KANG_CHANGE;
            random = KANG_RANDOM;
        }
        else {
            name = DOG_NAME;
            value = DOG_CHANGE;
            random = DOG_RANDOM;
        }

    }


    public boolean use()
    {
        player.regenerate(value);
        manager.addMessage(new MiddleMessage(name + "was used", 800));
        return true;

    }
}


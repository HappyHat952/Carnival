package miniGame.kangaroo.moves;

import core.message.MessageManager;
import core.message.MiddleMessage;
import miniGame.kangaroo.fighter.Fighter;
import miniGame.kangaroo.fighter.KangarooFighter;

public class BigAttack extends Move{


    public BigAttack(MessageManager m, Fighter owner, Fighter opponent)
    {
        super(m);
        if (owner instanceof KangarooFighter)
        {
            kang = true;
        }
        player = owner;
        this.opponent = opponent;
        KANG_NAME = "jump";
        DOG_NAME = "bite";
        KANG_CHANGE = 20;
        DOG_CHANGE = 13;
        KANG_RANDOM = 0.3f;
        DOG_RANDOM = 0.5f;
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
        if (Math.random()<random) {
            opponent.damage(value);
            //manager.addMessage(new MiddleMessage(name + "was used", 800));
            used = true;
        }
        else {
            used = false;
        }
       // manager.addMessage(new MiddleMessage(name + "missed", 800));


        return used;
    }
}

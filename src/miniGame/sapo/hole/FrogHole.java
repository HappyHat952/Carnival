package miniGame.sapo.hole;

import miniGame.MiniGame;

public class FrogHole extends CoinHole {
    int frame;
    int count;
    public FrogHole ( int id)
    {
        image = holes.getSubImage(frame,1);
        color = "yellow";
        level = 1;
        this.id = id;
        value = 5;
    }

    @Override
    public void update() {
        count ++;
        super.update();
        if ( count %60 == 0)
        {
            setNextImage();
        }
    }
    public int getCoinY()
    {
        return getY()-100;
    }
    public void setNextImage()
    {
        frame ++;
        if (frame ==3 )
        {
            frame = 0;
        }
        image = holes.getSubImage(frame, 1);
    }
}

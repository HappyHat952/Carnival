package miniGame.sapo.hole;

public class BlueHole extends CoinHole{

    public BlueHole ( int id)
    {
        image = holes.getSubImage(0,0).getScaledCopy((float)0.6);
        color = "blue";
        level = 3;
        this.id = id;
        value = 1;
    }
}

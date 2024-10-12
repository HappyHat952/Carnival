package miniGame.sapo.hole;

public class GreenHole extends CoinHole{
    public GreenHole ( int id)
    {
        image = holes.getSubImage(2,0).getScaledCopy((float)0.5);
        color = "green";
        level = 1;
        this.id = id;
        value = 3;
    }

    public int getCoinY()
    {
        return getY()-20;
    }
}

package miniGame.sapo.hole;

public class RedHole extends CoinHole{

    public RedHole ( int id)
    {
        image = holes.getSubImage(1,0).getScaledCopy((float)0.6);
        color = "red";
        level = 2;
        this.id = id;
        value =2;
    }
    public int getCoinY()
    {
        return getY()-20;
    }
}

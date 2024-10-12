package world;

import core.Main;
import org.newdawn.slick.*;
import setup.Images;

public class Player {

    public static SpriteSheet[] playerOptions;
    public static  SpriteSheet currPlayer;
    public static Image playerImage;
    private static int frame;
    private static int direction;
    public float x;
    public float y;
    int count;
    public boolean moving;

    public Player()
    {
        x = 8;
        y=5;
        frame = 0;
        direction =0;
        playerOptions = Images.player;
        currPlayer = playerOptions[frame];
        playerImage = currPlayer.getSubImage(frame,0);

    }

    public void update()
    {
        count ++;

            if (count %10 ==0)
            {
                frame ++;
            }
            if (frame == 4)
            {
                frame = 0;
            }

    }
    public int getX()
    {
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }
    public void shiftX(float shift)
    {
        x+=shift;
    }
    public void shiftY(float shift)
    {
        y+=shift;
    }
    public void changeDirection (GameContainer gc)
    {
        if (gc.getInput().isKeyDown(Input.KEY_W))
        {
            direction =2;
            playerImage = currPlayer.getSubImage(frame,3);
        }
        else if (gc.getInput().isKeyDown(Input.KEY_S))
        {
            direction = 0;
            playerImage = currPlayer.getSubImage(frame,0);
        }

        if (gc.getInput().isKeyDown(Input.KEY_A))
        {
            direction =3;
            playerImage = currPlayer.getSubImage(frame,1);
        }
        else if (gc.getInput().isKeyDown(Input.KEY_D))
        {
            direction = 1;
            playerImage = currPlayer.getSubImage(frame,2);
        }

    }

    public void draw (Graphics g)
    {
        g.drawImage(playerImage, Main.getScreenWidth()/2 - playerImage.getWidth()/2,Main.getScreenHeight()/2 - playerImage.getHeight()/2);
        //g.setColor(Color.blue);
        //g.drawString(x+","+y, Main.getScreenWidth()/2 ,Main.getScreenHeight()/2 );
        //g.fillOval(Main.getScreenWidth()/2 ,Main.getScreenHeight()/2 + playerImage.getHeight()/2, 5,5);


    }

    public int[] getFeetLocation()
    {

        return new int[]{Main.getScreenWidth()/2 ,Main.getScreenHeight()/2 + playerImage.getHeight()/2};
    }

    public static void setPlayerImage(int i)
    {
        System.out.println(i);
        frame = 0;
        currPlayer = playerOptions[i];
        playerImage = currPlayer.getSubImage(0,0);
    }



}
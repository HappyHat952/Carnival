package miniGame.sapo;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Slider {
    Image slider;
    int[] barRatio;
    Color[] colors;
    private Color currColor;
    private int RatioSum;
    private int sliderX;
    private int sliderMid;
    private int sliderSpeed;
    private int sliderFactor;
    final private int NUM_SECTIONS = 15;
    final private int BAR_HEIGHT = 80;
    final private int BAR_WIDTH = Main.getScreenWidth()-100;

    final private int BAR_TOP = Main.getScreenHeight() -110;
    final int BAR_LEFT = (Main.getScreenWidth() - BAR_WIDTH)/2;

    private boolean moving;

    public Slider()
    {
        moving = true;
        sliderX = BAR_LEFT;
        sliderSpeed = 2;
        sliderFactor =1;
        slider = Images.sapoSlider.getScaledCopy(0.5f);
        barRatio = new int[NUM_SECTIONS];
        colors = new Color[] {
                Color.black, Color.blue,  Color.black,  Color.red,   Color.black,
                Color.green, Color.black, Color.yellow, Color.black, Color.green,
                Color.black, Color.red,   Color.black,  Color.blue,  Color.black      };
        currColor = colors[0];
        for (int i=0; i< NUM_SECTIONS; i++)
        {
            if (colors[i].equals(Color.blue))
            {
                barRatio[i] =4;
            }
            else if (colors[i].equals(Color.red))
            {
                barRatio[i] =3;
            }
            else if (colors[i].equals(Color.green))
            {
                barRatio[i] =2;
            }
            else if (colors[i].equals(Color.yellow))
            {
                barRatio[i] =2;
            }

            if (i%2 == 0)
            {
                barRatio[i]=4;
            }
            if (i == 0 || i == NUM_SECTIONS-1)
            {
                barRatio[i]=3;
            }


            RatioSum += barRatio[i];
        }

    }

    public void draw(Graphics g, float cooldown)
    {
        int tempX =0;

        for (int i= 0; i<NUM_SECTIONS; i++)
        {
            int len = (int)( BAR_WIDTH * (float)barRatio[i] / RatioSum);
            g.setColor(colors[i]);
            g.fillRect(BAR_LEFT +tempX,BAR_TOP, len ,BAR_HEIGHT);
            tempX += len;
        }
        g.drawImage(slider, sliderX, BAR_TOP-40);
        g.setColor(new Color(cooldown, (float)1.0/(cooldown+1) , (float)0));
        g.fillOval(sliderMid-10, BAR_TOP-60, 20, 20);
    }

    public int getSliderX(){ return sliderMid;}

    public void update()
    {
        if (moving)
        {
            sliderX += sliderSpeed*sliderFactor;
            sliderMid = sliderX + slider.getWidth()/2;
            sliderSpeed = 5+ 3*(Main.getScreenWidth()/2)/(Math.abs(Main.getScreenWidth()/2-sliderMid)+200);
            if (sliderX <= BAR_LEFT || sliderX >= BAR_LEFT + BAR_WIDTH - slider.getWidth())
            {
                sliderFactor *= -1;
            }


            currColor = getColorAtX();
        }


    }

    //returns the id value of what was hit
    public int hit()
    {
        if (getColorAtX().equals(Color.yellow))
        {
            return 3;
        }
        int middle = Main.getScreenWidth()/2;
        int val=-1;

        if (getColorAtX().equals(Color.blue))
        {
            val = 0;
        }
        else if (getColorAtX().equals(Color.red))
        {
            val = 1;
        }
        else if (getColorAtX().equals(Color.green))
        {
            val = 2;
        }
        if (val == -1)
        {
            return -1;
        }
        if (sliderMid<middle)
        {
            return val;
        }
        else
        {
            return 6-val;
        }
    }

    public void setSliderSpeed( boolean moves)
    {
        moving = moves;
    }
    public Color getColorAtX()
    {
        int searchPos = BAR_LEFT;
        for (int i = 0; i< NUM_SECTIONS; i++)
        {
            int len = (int)( BAR_WIDTH * (float)barRatio[i] / RatioSum);
            if (sliderMid>= searchPos && sliderMid<= len + searchPos)
            {
                return colors[i];
            }
            searchPos += len;
        }

        return null;
    }
}

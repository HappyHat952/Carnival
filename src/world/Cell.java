package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;

public class Cell {

    public static int width;
    public static int height;
    public static int cameraTop;
    public static int cameraLeft;
    boolean inFrame;

    Color color;
    Image tile;

    final private int X;
    final private int Y;

    private int display_x;
    private int display_y;

    public Cell(int x, int y)
    {
        this.X = x;
        this.Y = y;
        this.display_x = x;
        this.display_y = y;
        color = Color.black;
        tile = Images.defaultTile.getSubImage(1,0);
    }

    public int getRealX(){ return display_x*width-cameraLeft;}
    public int getRealY(){ return display_y*height-cameraTop;}

    public int getDisplayX()
    {
        return display_x;
    }
    public int getDisplayY()
    {
        return display_y;
    }

    public int getX() { return X;}
    public int getY() {return Y;}

    // static method setting
    public static void setCellSize(int w, int h)
    {
        width = w;
        height = h;
    }

    public static void setCamera(int camTop, int camLeft) {
        cameraTop = camTop;
        cameraLeft = camLeft;
    }

    public void setIsInFrame(boolean f)
    {
        inFrame = f;
    }
    public boolean getInFrame()
    {
        return inFrame;
    }

    // mutator
    public void draw(Graphics g)
    {
        this.tile = this.tile.getScaledCopy(width,height);
        g.setLineWidth(4);
        g.setColor(color);
        //g.fillRect(display_x *width-cameraLeft, display_y *height-cameraTop, width, height);
        g.drawImage(tile, display_x *width-cameraLeft, display_y *height-cameraTop);
        g.setLineWidth(2);
        g.setColor(Color.black);
        if (color.equals(Color.black))
        {
            g.setColor(Color.white);
        }
       //g.drawString("("+ X +", "+ Y +")", display_x *width + width/3- cameraLeft, display_y *height + height/3-cameraTop);
    }

    //Change details
    public void setColor(Color color)
    {
        this.color = color;
    }
    public void setImage(int biome, int type)
    {
        if ( biome == -1 )
        {
            this.tile = Images.defaultTile.getSubImage(type,0);
        }
        else
        {
            this.tile = Images.tileSheet.getSubImage(biome, type);
        }
    }
    public void setDisplayCoord(int x, int y)
    {
        display_x = x;
        display_y = y;
    }

    public boolean isTouching(int x, int y)
    {
        if (x>display_x *width-cameraLeft &&x<display_x *width-cameraLeft + tile.getWidth()
                && y>display_y * height - cameraTop  && y<display_y * height - cameraTop + tile.getHeight() )
        {
            return true;
        }
        else{
            return false;
        }
    }

    public String toString()
    {
        System.out.println("\nCELL CELL CELL CELL CELL CELL");
        System.out.println("COORD: ("+X+","+Y+")");
        System.out.println("WIDExHEIGHT: "+width+"x"+height);
        System.out.println("CAMLEFT: "+ cameraLeft+" AND CAMRIGHT: "+cameraTop);
        System.out.println("DISPLAY COORD: ("+display_x+","+display_y+")");
        System.out.println("IMAGE DRAWN: ("+ (display_x *width-cameraLeft) + ","+ (display_y *height-cameraTop)+")" );
        return "";

    }
}

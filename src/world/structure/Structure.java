package world.structure;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import world.Cell;
import world.World;

public abstract class Structure {
    protected int x;
    protected int y;
    protected int display_x;
    protected int display_y;
    protected Image image;
    protected Cell[][] cells;
    protected static int width = Cell.width;
    protected static int height = Cell.height;
    static int cameraTop;
    static int cameraLeft;

    public static void setCamera(int camTop, int camLeft) {
        cameraTop = camTop;
        cameraLeft = camLeft;
    }
    public void draw(Graphics g)
    {
        if (cells != null)
        {

            g.setLineWidth(2);
            g.setColor(Color.black);
         //   g.drawString(image.getHeight()+"", display_x *width + width/3- cameraLeft, display_y *height + height/3-cameraTop);

            Image NewImage = this.image.getScaledCopy(World.CELL_WIDTH*cells[0].length,(int)(World.CELL_HEIGHT*cells[0].length*((float)image.getHeight()/image.getWidth())));

     //       g.drawString(image.getWidth()+","+image.getHeight(), display_x *width + width/3- cameraLeft, display_y *height + height/3-cameraTop);

            g.drawImage(NewImage, display_x *width-cameraLeft, display_y *height-cameraTop-NewImage.getHeight()+cells.length*Cell.height);
//            g.setLineWidth(2);
//            g.setColor(Color.black);

//            for (Cell[] ce: cells)
//            {
//                for (Cell c: ce)
//                {
//                    g.drawRect(c.getRealX(), c.getRealY(),
//                            Cell.width, Cell.height );
//                }
//            }



            //g.drawString("("+ x +", "+ y +")", display_x *width + width/3- cameraLeft, display_y *height + height/3-cameraTop);
        }
    }

    public Cell[][] getCells(){ return cells;}

    public void update()
    {
        if (cells != null)
        {
            display_x = cells[0][0].getDisplayX();
            display_y = cells[0][0].getDisplayY();
            cameraTop = Cell.cameraTop;
            cameraLeft = Cell.cameraLeft;
            width = Cell.width;
            height = Cell.height;
        }

    }

    public boolean isBehind(int y)
    {
        if (y>display_y *height-cameraTop-(image.getHeight()-Cell.height) + image.getHeight())
        {
            return true;
        }
        return false;
    }
    public int[] getDirectionTouching(float x, float y) {
        int indX = -1;
        int indY = -1;
        if (isTouching (x,y))
        {
            if (isWithin(x, display_x * width - cameraLeft, 5)) {
                indX =  0;
            } else if (isWithin(x, display_x * width - cameraLeft + width * cells[0].length, 5)) {
                indX =  1;
            }

            if (isWithin(y, display_y * height - cameraTop, 5)) {
                indY= 3;
            } else if (isWithin(y, display_y * height - cameraTop + height * cells.length, 5)) {
                indY= 2;
            }
        }

        return new int[] {indX, indY};
    }

    public boolean isWithin(float testVal, int target, int range)
    {
        if (Math.abs(testVal - target)<= range)
        {
            return true;
        }
        return false;
    }
    public boolean isTouching(float x, float y)
    {
        boolean touching = false;

        for (Cell[] r: cells)
        {
            for (Cell c: r)
            {
                if (c.isTouching((int)x,(int)y))
                {
                    touching = true;
                }
            }
        }

        return touching;


//        if (x>display_x * width - cameraLeft -5 &&x<display_x * width - cameraLeft + width * cells[0].length+ 5
//        && y>display_y * height - cameraTop -5 && y<display_y * height - cameraTop + height * cells.length+5 )
//        {
//            return false;
//        }
//        else{
//            return true;
//        }
    }

}

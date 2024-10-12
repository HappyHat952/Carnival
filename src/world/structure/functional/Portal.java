package world.structure.functional;

import setup.Images;
import world.Cell;
import world.structure.Structure;

public class Portal extends Structure {
    static int width;
    static int height;
    static int cameraTop;
    static int cameraLeft;
    final int ID;
    public Portal(int x, int y, Cell[][] cells, int id)
    {
        this.x =x;
        this.y = y;
        ID = id;
        image = Images.portals.getSubImage(id, 0);

        this.cells = cells;
        width = Cell.width;
        height = Cell.height;
        display_x = cells[0][0].getDisplayX();
        display_y = cells[0][0].getDisplayY();
    }



    public String toString()
    {
        System.out.println("\nPORTAL PORTAL PORTAL PORTAL PORTAL");
        System.out.println("COORD: ("+x+","+y+")");
        System.out.println("CAMLEFT: "+ cameraLeft+" AND CAMRIGHT: "+cameraTop);
        System.out.println("DISPLAY COORD: ("+display_x+","+display_y+")");
        System.out.println("IMAGE DRAWN: ("+ (display_x *width-cameraLeft) + ","+ (display_y *height-cameraTop)+")" );
        for (int r=0; r<cells.length ; r++) {
            for (int c = 0; c < cells[0].length; c++)
            {
                System.out.print(r+", "+c);
                System.out.println(cells[r][c].toString());
            }
        }
        return"";
    }
}

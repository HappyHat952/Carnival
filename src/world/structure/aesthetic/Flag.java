package world.structure.aesthetic;

import setup.Images;
import world.Cell;
import world.structure.Structure;

public class Flag extends Structure {

    public Flag(int x, int y, int id, Cell[][] cells)
    {
        this.x =x;
        this.y = y;
        image = Images.flags.getSubImage(id,0);

        this.cells = cells;

        width = Cell.width;
        height = Cell.height;
        display_x = cells[0][0].getDisplayX();
        display_y = cells[0][0].getDisplayY();
    }


}

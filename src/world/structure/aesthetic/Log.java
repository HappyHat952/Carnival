package world.structure.aesthetic;

import setup.Images;
import world.Cell;
import world.structure.Structure;

public class Log extends Structure {
    public Log(int x, int y, int id, Cell[][] cells)
    {
        this.x =x;
        this.y = y;
        image = Images.log;

        this.cells = cells;

        width = Cell.width;
        height = Cell.height;
        display_x = cells[0][0].getDisplayX();
        display_y = cells[0][0].getDisplayY();
    }
}

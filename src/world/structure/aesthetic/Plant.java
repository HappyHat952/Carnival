package world.structure.aesthetic;

import setup.Images;
import world.Cell;
import world.structure.Structure;

public class Plant extends Structure {

    public Plant(int x, int y, int id, Cell[][] cells)
    {
        this.x =x;
        this.y = y;
        image = Images.plants.getSubImage(id,0);

        this.cells = cells;

        width = Cell.width;
        height = Cell.height;
        display_x = cells[0][0].getDisplayX();
        display_y = cells[0][0].getDisplayY();
    }
}

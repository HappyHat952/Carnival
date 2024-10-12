package world.structure.functional;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;
import world.Cell;
import world.World;
import world.structure.Structure;

import java.awt.*;

public class StoreVenue extends Structure {


    public  StoreVenue(int x, int y, Cell[][] cells)
    {
        this.x =x;
        this.y = y;
        image = Images.storeMap;

        this.cells = new Cell[1][4];
        for (int r =0; r<cells.length;r++)
        {
            for (int c =0; c<cells[0].length;c++)
            {
                this.cells[r][c] = cells[r][c];
            }
        }
        width = Cell.width;
        height = Cell.height;
        display_x = cells[0][0].getDisplayX();
        display_y = cells[0][0].getDisplayY();
    }

}

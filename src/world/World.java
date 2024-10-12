package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import world.structure.Structure;
import world.structure.aesthetic.Flag;
import world.structure.aesthetic.Log;
import world.structure.aesthetic.Plant;
import world.structure.functional.Portal;
import core.Game;
import world.structure.functional.StoreVenue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class World {

    Cell[][] cells; //inital location of cells
    Portal[] portals;
    ArrayList<Structure> decorations;
    StoreVenue store;
    ArrayList<ArrayList<Cell>> displayCellOrder; // display cell order (contains ENTIRE res.map). SHifts based on camera location
    Cell[][] cellsInFrame; // contains ONLY cells within the camera frame.

    Player player;

    String allCells; // string showing the initial location of cells
    Boolean[] canMove;// true means the character moves
    boolean canMoveLeft;
    boolean canMoveRight;
    boolean canMoveUp;
    boolean canMoveDown;
    int cameraTop; // top of camera
    int cameraLeft; // left of camera
    final static public int CELL_WIDTH=54+27; // width of individual cell (approx 120)
    final static public int CELL_HEIGHT=54+27; // height of individual cell (approx 120)

    final static public int TOTAL_NUM_CELLS_WIDE=88; //total cells wide of ENTIRE res.map (will be increased later)
    final static public int TOTAL_NUM_CELLS_HIGH=48; // total cells high if ENTIRE res.map (will be increased later)

    final static public int DISPLAY_CELLS_WIDE = 25; // cells wide of JUST screen
    final static public int DISPLAY_CELLS_HIGH = 15; // cells high of JUST screen

    final int SPEED = 5;


    char currentKey;

    public World(Player player)
    {
        canMove = new Boolean[]{true,true, true, true};
        setCanMove();


        this.player = player;
        allCells = "";

        //create ALL cells

        cells = new Cell[TOTAL_NUM_CELLS_HIGH][TOTAL_NUM_CELLS_WIDE];
        portals = new Portal[Game.NUM_GAMES];
        decorations = new ArrayList<>();

        for (int h=0; h< cells.length; h++)
        {
            for (int w=0; w<cells[0].length; w++)
            {
                cells[h][w] = new Cell(w, h);
            }
        }

        readFile();

        displayCellOrder = new ArrayList< ArrayList<Cell> >();

        for (int h=0; h< TOTAL_NUM_CELLS_HIGH; h++)
        {
            ArrayList<Cell> array = new ArrayList<>();
            displayCellOrder.add(array);
            for (int w=0; w<TOTAL_NUM_CELLS_WIDE; w++)
            {
                array.add(cells[h][w]);
            }
        }


        // create cells on res.map (on screen): two more than will be on screen
//        DISPLAY_CELLS_HIGH = 13;
//        DISPLAY_CELLS_WIDE= 20;

//        CELL_WIDTH = Main.getScreenWidth()/DISPLAY_CELLS_WIDE;
//        CELL_HEIGHT = Main.getScreenHeight()/DISPLAY_CELLS_HIGH;

        Cell.setCellSize(CELL_WIDTH, CELL_HEIGHT);

        cameraTop = 1500;
        cameraLeft = 2000;

        cellsInFrame = new Cell[DISPLAY_CELLS_HIGH+5][DISPLAY_CELLS_WIDE+5];

        //shiftDisplayCells();
        setCellsInFrame();


    }
    public void moveOneCellDown()
    {
        cameraTop+=(CELL_HEIGHT*2.5);
    }

    public void update(GameContainer gc)
    {

        //shiftDisplayCells();
        canMove = new Boolean[]{true,true,true,true};
        setCanMove();

            for (Structure d: decorations) {
            d.update();
            int[] feet = player.getFeetLocation();
            if (d.isTouching(feet[0], feet[1]) && !d.getDirectionTouching(feet[0],feet[1]).equals(new int[] {-1,-1}))
            {
                for (int i: d.getDirectionTouching(feet[0],feet[1]))
                {
                    if (i!= -1)
                    {
                        canMove[i] = false;
                        setCanMove();
                    }
                }

            }

        }

//        for (Structure d: portals) {
//            d.update();
//            int[] feet = player.getFeetLocation();
//            if (d.isTouching(feet[0], feet[1]) && !d.getDirectionTouching(feet[0],feet[1]).equals(new int[] {-1,-1}))
//            {
//                for (int i: d.getDirectionTouching(feet[0],feet[1]))
//                {
//                    if (i!= -1)
//                    {
//                        canMove[i] = false;
//                        setCanMove();
//                    }
//                }
//
//            }
//
//        }
        setCanMove();
        for (Portal p: portals)
        {
            if (p!= null)
            {

                p.update();
            }
        }

        if (canMove())
        {
            moveCamera(gc);
            setCellsInFrame();
        }
        player.update();


        player.changeDirection(gc);

    }

    private void setCanMove()
    {

        canMoveLeft = canMove[0];
        canMoveRight = canMove[1];
        canMoveUp = canMove[2];
        canMoveDown = canMove[3];
    }
    private boolean canMove()
    {

        return true;

    }

    private void moveCamera(GameContainer gc)
    {
            player.moving = true;
            if (canMoveUp && gc.getInput().isKeyDown(Input.KEY_W) && !(cellsInFrame[0][0].getY() == 0))
            {
                //cameraTop -= CELL_HEIGHT/2;
                cameraTop -= SPEED;
                player.shiftY((float)-SPEED/CELL_HEIGHT);
            } else if ( canMoveDown && gc.getInput().isKeyDown(Input.KEY_S) && !(cellsInFrame[DISPLAY_CELLS_HIGH-1][DISPLAY_CELLS_WIDE-1].getY() == TOTAL_NUM_CELLS_HIGH-1))
            {
                //cameraTop += CELL_HEIGHT/2;
                cameraTop += SPEED;
                player.shiftY((float)SPEED/CELL_HEIGHT);
            } else if (canMoveRight&&gc.getInput().isKeyDown(Input.KEY_A) && !(cellsInFrame[0][0].getX() == 0) )
            {
                //cameraLeft -= CELL_WIDTH/2;
                cameraLeft -= SPEED;
                player.shiftX((float)-SPEED/CELL_WIDTH);
            } else if (canMoveLeft&&gc.getInput().isKeyDown(Input.KEY_D) && !(cellsInFrame[DISPLAY_CELLS_HIGH-1][DISPLAY_CELLS_WIDE-1].getX() == TOTAL_NUM_CELLS_WIDE-1))
            {
                //cameraLeft += CELL_WIDTH/2;
                cameraLeft += SPEED;
                player.shiftX((float)SPEED/CELL_WIDTH);
            }
            else
            {
                player.moving = false;
            }


    }

    public void display(Graphics g)
    {
        g.setBackground(Color.white);
        Cell.setCamera(cameraTop, cameraLeft);

        for (int row = 0; row < cellsInFrame.length; row ++)
        {
            for (int col = 0; col < cellsInFrame[0].length; col ++)
            {
                cellsInFrame[row][col].draw(g);
            }
        }
        for (Structure d: decorations)
        {
            if (d.isBehind(player.getFeetLocation()[1]))
            {
                d.draw(g);
            }

        }
        for (Portal p: portals)
        {
            if (p!= null && p.isBehind(player.getFeetLocation()[1]))
            {
                p.draw(g);
            }
        }


        player.draw(g);

        for (Structure d: decorations)
        {
            if (!d.isBehind(player.getFeetLocation()[1]))
            {
                d.draw(g);
            }
        }
        for (Portal p: portals)
        {
            if (p!= null && !p.isBehind(player.getFeetLocation()[1]))
            {
                p.draw(g);
            }
        }



    }

    public void keyPressed(int key, char c) {


    }

    public Portal[] getPortal()
    {
        return portals;
    }
    public Structure getStore()
    {
        return store;
    }

    //UPDATE METHODS

    //CREATE sets the Cells in the "camera" based on the display Cell arraylist
    public void setCellsInFrame()
    {

        for (ArrayList<Cell> cellArrayList : displayCellOrder) {
            for (Cell c: cellArrayList)
            {
                c.setIsInFrame(false);
            }
        }
        for (int row = 0; row < cellsInFrame.length; row ++)
        {
            for (int col = 0; col < cellsInFrame[0].length; col ++)
            {
                if (row+cameraTop/CELL_HEIGHT < cells.length && col + cameraLeft/CELL_WIDTH < cells[0].length
                && row+cameraTop/CELL_HEIGHT >= 0 && col + cameraLeft/CELL_WIDTH >= 0 )
                {
                    cellsInFrame[row][col] = displayCellOrder.get(row+cameraTop/CELL_HEIGHT).get(col+cameraLeft/CELL_WIDTH);
                    displayCellOrder.get(row+cameraTop/CELL_HEIGHT).get(col+cameraLeft/CELL_WIDTH).setIsInFrame(true);
                }
            }
        }
    }

    //CREATES INFINITE LOOP: by constantly shifting displayList and camera.
    public void shiftDisplayCells()
    {


            if (cameraTop < 0)
            {
                //shifting up
                displayCellOrder.add(0,displayCellOrder.remove(TOTAL_NUM_CELLS_HIGH-1));
                cameraTop = CELL_HEIGHT/2;
            }
            else if (cameraTop + DISPLAY_CELLS_HIGH* CELL_HEIGHT > TOTAL_NUM_CELLS_HIGH*CELL_HEIGHT)
            {
                //shifting down
                displayCellOrder.add(displayCellOrder.remove(0));
                cameraTop = TOTAL_NUM_CELLS_HIGH*CELL_HEIGHT - (DISPLAY_CELLS_HIGH*CELL_HEIGHT + CELL_HEIGHT/2);
            }


            if (cameraLeft < 0)
            {
                //shifting left
                for (ArrayList<Cell> a : displayCellOrder)
                {
                    a.add(0, a.remove(TOTAL_NUM_CELLS_WIDE-1));
                }
                cameraLeft = CELL_WIDTH/2;
            }
            else if ( cameraLeft + DISPLAY_CELLS_WIDE*CELL_WIDTH > TOTAL_NUM_CELLS_WIDE * CELL_WIDTH)
            {
                //shifting right
                for (ArrayList<Cell> a : displayCellOrder)
                {
                    a.add(a.remove(0));
                }
                cameraLeft = TOTAL_NUM_CELLS_WIDE*CELL_WIDTH - (DISPLAY_CELLS_WIDE*CELL_WIDTH + CELL_WIDTH/2);
            }

            //adjust display coordinates based on array location
            for (ArrayList<Cell> a: displayCellOrder)
            {
                for (Cell c: a)
                {
                    c.setDisplayCoord(a.indexOf(c), displayCellOrder.indexOf(a));

                }
            }

    }

    //READING FILE + INITIAL SETUP
    public void readFile()
    {
        try
        {
            File worldMap = new File ("res/map/File.io.worldMap");
            File portal = new File ("res/map/File.io.portalMap");
            File biomes = new File("res/map/File.io.biomeMap");
            // ^^^^^ needs to match what pops up when hovering over res.map

            Scanner scanW = new Scanner(worldMap);
            Scanner scanP = new Scanner(portal);
            Scanner scanB = new Scanner(biomes);

            //loops through rows and column

            for (int h=0; h< cells.length; h++)
            {
                String rowWor = scanW.nextLine();
                String rowPor = scanP.nextLine();
                String rowBio = scanB.nextLine();

                for (int r=0; r<cells[0].length; r++)
                {
                    char tile = rowWor.charAt(r);
                    char port = rowPor.charAt(r);
                    char biom = rowBio.charAt(r);
                    allCells = allCells + tile + " ";
                    setCellInfo(cells[h][r], tile, port, biom, h, r);
                }
                allCells = allCells + "\n";
            }

            scanW.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Cannot find file WHOOPS.");
        }
        System.out.println(decorations.size());
    }

    public void setCellInfo(Cell cell, char tileC, char portal, char bioC, int h, int r)
    {
        int biome;
        int tile;
        switch (bioC){
            case '0':
                biome = 0;
                break;
            case '1':
                biome = 1;
                break;
            case '2':
                biome = 2;
                break;
            case '3':
                biome = 3;
                break;
            case '4':
                biome = 4;
                break;
            case '5':
                biome = 5;
                break;
            default:
                biome = -1;
        }
        switch (tileC){
            case '|':
                tile = 0;
                break;
            case 'r':
                tile = 1;
                break;
            case 'o':
                tile = 2;
                break;
            case 'w':
                tile = 3;
                break;
            default:
                tile = 1;
        }
        cell.setImage(biome, tile);

        if (portal == '0')
        {
            Cell[][] tempCells = { {cells[h-1][r], cells[h][r]}
                                ,{cells[h-1][r+1], cells[h][r+1]} };
            portals[0] = (new Portal(r, h, tempCells,0));
        }
        else if (portal == '1')
        {
            Cell[][] tempCells = { {cells[h-1][r], cells[h][r]}
                    ,{cells[h-1][r+1], cells[h][r+1]} };
            portals[1] = (new Portal(r, h, tempCells,1));
        }
        else if (portal == '2')
        {
            Cell[][] tempCells = { {cells[h-1][r], cells[h][r]}
                    ,{cells[h-1][r+1], cells[h][r+1]} };
            portals[2] = (new Portal(r, h, tempCells,2));
        }
        else if (portal == '3')
        {
            Cell[][] tempCells = { {cells[h-1][r], cells[h][r]}
                    ,{cells[h-1][r+1], cells[h][r+1]} };
            portals[3] = (new Portal(r, h, tempCells,3));
        }
        else if (portal == '4')
        {
            Cell[][] tempCells = { {cells[h-1][r], cells[h][r]}
                    ,{cells[h-1][r+1], cells[h][r+1]} };
            portals[4] = (new Portal(r, h, tempCells,4));
        }
        else if (portal == '5')
        {
            Cell[][] tempCells = { {cells[h-1][r], cells[h][r]}
                    ,{cells[h-1][r+1], cells[h][r+1]} };
            portals[5] = (new Portal(r, h, tempCells,5));
        }
        else if (portal == 'f')
        {
            Cell[][] tempCells = {{cells[h][r]}};
            if (biome == -1 )
            {
                decorations.add( new Flag(r, h, 1, tempCells));
            }
            else {
                decorations.add(new Flag(r,h,biome, tempCells));
            }
        }
        else if (portal == 'p')
        {
            Cell[][] tempCells = {{cells[h][r]}};
            if (biome == -1 )
            {
                decorations.add( new Plant(r, h, 1, tempCells));
            }
            else {
                decorations.add(new Plant(r,h,biome, tempCells));
            }
        }
        else if (portal == 's')
        {
            Cell[][] tempCells = { {cells[h][r],cells[h][r+1],cells[h][r+2],cells[h][r+3]}};
            store = new StoreVenue(r,h,tempCells);
            decorations.add(store);
        }
        else if (portal == 'l')
        {
            Cell[][] tempCells = {{cells[h][r]}};
            decorations.add( new Log(r, h, biome, tempCells));
        }

    }


}

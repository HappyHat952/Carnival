package setup;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

    //Background
    static public SpriteSheet bg;
    static public SpriteSheet titleScreen;

    //WORLD
    static public SpriteSheet [] player;
    static public SpriteSheet defaultPlayer;
    static public SpriteSheet duckPlayer;
    static public SpriteSheet redHatPlayer;
    static public SpriteSheet peruHatPlayer;
    static public SpriteSheet pokemonPlayer;
    static public SpriteSheet teaCupPlayer;

    static public SpriteSheet defaultTile;
    static public SpriteSheet tileSheet;
    static public Image portal;
    static public SpriteSheet portals;
    static public SpriteSheet flags;
    static public Image log;
    static public Image storeMap;
    static public SpriteSheet plants;


    //GAME
    static public Image gameOverScreen;

    //STORE
    static public Image store;
    static public Image A;
    static public Image B;
    static public Image C;
    static public Image duckItem;
    static public Image redHatItem;
    static public Image peruHatItem;
    static public Image pokemonItem;
    static public Image teaCupItem;
    static public Image inventory;
    //GAME
    static public Image controls;
    static public Image timesUp;
    static public Image niceJob;
    static public Image youLose;
    static public Image pauseAndExit;

    //SHOOTING GAME
    static public Image glock;
    static public Image gun;
    static public Image baloon;
    static public SpriteSheet duckSheet;
    static public Image shootingScreen;

    //SAPO
    static public SpriteSheet sapoTargets;
    static public Image sapoSlider;
    static public Image sapoCoin;
    static public Image sapoScreen;

    //WHACK A MOLE
    static public Image hammer;
    static public Image hammerCursor;
    static public Image hammerCursorHit;
    static public SpriteSheet mole;
    static public Image whackScreen;
    //TUG OF WAR
    static public Image rope;
    static public Image tugScreen;

    //KANGAROO
    static public SpriteSheet kangaroo;
    static public Image kangScreen;

    // PENGUIN
    static public Image penguinBackground;
    static public Image fish;
    static public Image penguin;
    static public Image ice;
    static public Image penguinHit;
    static public Image penguinScreen;

    public static void loadImages()
    {

        try
        {
            //background
            bg = new SpriteSheet( new Image ("res/image/backdrop/backdrop0.png"), 1920,1080,0);
            titleScreen = new SpriteSheet(new Image ("res/image/backdrop/backdrop1.png"), 1929, 1080);
            //world
            defaultPlayer = new SpriteSheet(new Image("res/image/world/dog0.png").getScaledCopy((float)0.5), 96,384, 0);
            redHatPlayer = new SpriteSheet(new Image("res/image/world/dog2.png").getScaledCopy((float)0.5), 96,384, 0);
            duckPlayer = new SpriteSheet(new Image("res/image/world/dog1.png").getScaledCopy((float)0.5), 96,384, 0);
            peruHatPlayer = new SpriteSheet(new Image("res/image/world/dog3.png").getScaledCopy((float)0.5), 96,384, 0);
            pokemonPlayer = new SpriteSheet(new Image("res/image/world/dog4.png").getScaledCopy((float)0.5), 96,384, 0);
            teaCupPlayer = new SpriteSheet(new Image("res/image/world/dog5.png").getScaledCopy((float)0.5), 96,384, 0);

            defaultTile = new SpriteSheet(new Image("res/image/world/Tiles.png"), 120,120, 0);
            portal = new Image("res/image/world/Portal0.png");
            flags = new SpriteSheet(new Image("res/image/world/Flags.png"), 288,576,0);
            portals = new SpriteSheet(new Image("res/image/world/gamePortal1.png"), 1600,2400,0);
            tileSheet = new SpriteSheet(new Image("res/image/world/tileSheet1.png"), 120,120,0);
            log = new Image("res/image/world/log.png");
            storeMap = new Image("res/image/world/store1.png");
            plants = new SpriteSheet(new Image("res/image/world/plants.png"),320,640,0);
            inventory = new Image("res/image/inventory.png");

            //store
            store = new Image("res/image/store/store.PNG");
            store = store.getScaledCopy(1950, 1300);
            SpriteSheet items = new SpriteSheet("res/image/store/item.png",108,108,0);
            duckItem = (items.getSubImage(0,0)).getScaledCopy(3);
            redHatItem = (items).getSubImage(0,1).getScaledCopy(3);
            peruHatItem = (items).getSubImage(0,2).getScaledCopy(3);
            pokemonItem = (items).getSubImage(0,3).getScaledCopy(3);
            teaCupItem = (items).getSubImage(0,4).getScaledCopy(3);

            A = new Image("res/image/store/A.PNG");
            B = new Image("res/image/store/B.PNG");
            C = new Image("res/image/store/C.PNG");

            controls = new Image("res/image/game/controls.png").getScaledCopy(1440, 810);
            niceJob = new Image("res/image/game/niceJob.png").getScaledCopy(1440, 810);
            timesUp = new Image("res/image/game/timesUp.png").getScaledCopy(1440, 810);
            youLose = new Image("res/image/game/youLose.png").getScaledCopy(1440, 810);
            pauseAndExit = new Image("res/image/game/pauseAndExit.png");
            pauseAndExit = pauseAndExit.getScaledCopy(0.75f);

            //shooter game
            glock = new Image("res/image/game/shooter/gun.png");
            baloon = new Image("res/image/game/shooter/baloons.png");
            duckSheet = new SpriteSheet("res/image/game/shooter/ducks.png", 216,216,0);
            SpriteSheet temp =new SpriteSheet ("res/image/game/shooter/duck-and-gun.png", 324,324,0);
            gun = temp.getSubImage(0,0);
            shootingScreen = new Image("res/image/game/shooter/shooter.png");
            shootingScreen = shootingScreen.getScaledCopy(1440, 810);

            //sapo game
            sapoTargets = new SpriteSheet(new Image("res/image/game/sapo/SapoGame.png"), 768, 768,0);
            sapoSlider = new Image ("res/image/game/sapo/sapo-slider.png");
            sapoCoin = new Image ("res/image/game/sapo/sapo-coin.png");
            sapoScreen = new Image("res/image/game/sapo/sapo.png");
            sapoScreen = sapoScreen.getScaledCopy(1440, 810);

            // whack a mole
            mole = new SpriteSheet(new Image ("res/image/game/whack/mole.png"), 216,216,0);
            hammer = new Image("res/image/game/whack/hammer.png");
            hammerCursor = hammer.getScaledCopy(64, 64);
            hammerCursorHit = new Image("res/image/game/whack/hammerHit.png").getScaledCopy(250, 250);
            whackScreen = new Image("res/image/game/whack/whack.png");
            whackScreen = whackScreen.getScaledCopy(1440, 810);

            //tug of war
            rope = new Image ("res/image/game/tug/roosterVlion.png");
            tugScreen = new Image("res/image/game/tug/tug.png");
            tugScreen = tugScreen.getScaledCopy(1440, 810);

            // kangaroo game
            kangaroo = new SpriteSheet(new Image("res/image/game/kangaroo/kangaroo.png"),827,1080,1 );
            kangScreen = new Image("res/image/game/kangaroo/kang.png");
            kangScreen = kangScreen.getScaledCopy(1440, 810);

            //penguin
            penguinBackground = new Image("res/image/game/penguin/penguinBackground.png");
            fish = new Image("res/image/game/penguin/fish.png");
            penguin = new Image("res/image/game/penguin/penguin.png");
            ice = new Image("res/image/game/penguin/ice.png");
            penguinHit = new Image("res/image/game/penguin/penguinHit.png");
            penguinScreen = new Image("res/image/game/penguin/penguinScreen.png");
            penguinScreen = penguinScreen.getScaledCopy(1440, 810);

        }
        catch (SlickException e)
        {
            System.out.println("don't load");
            e.printStackTrace();
        }

        player = new SpriteSheet[6];
        player[0] = defaultPlayer;
        player[1] = duckPlayer;
        player[2] = redHatPlayer;
        player[3] = peruHatPlayer;
        player[4] = pokemonPlayer;
        player[5] = teaCupPlayer;
    }
}

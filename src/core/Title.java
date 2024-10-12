package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;

import java.util.ArrayList;

public class Title extends BasicGameState {

    private int id;
    private StateBasedGame sbg;
    private int counter;

    public Title(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }


    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        sbg = stateBasedGame;
        Images.loadImages();
        Fonts.loadFonts();
        gameContainer.setShowFPS(false);
        counter = 0;
    }


    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        if (counter%90 < 50)
        {
            graphics.drawImage(Images.titleScreen.getSubImage(0,1),0,0);
        }
        else
        {
            graphics.drawImage(Images.titleScreen.getSubImage(0,0),0,0);
        }
        counter ++;


    }


    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void keyPressed(int key, char c) {
        if (c == ' ')
        {
            sbg.enterState(Main.CARNIVAL_ID);
        }

        // This code happens every time the user presses a key
    }


}

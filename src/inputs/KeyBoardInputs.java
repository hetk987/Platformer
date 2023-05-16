package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;


public class KeyBoardInputs implements KeyListener{

    //Instance fields needed
    private GamePanel gamePanel;

    //Constructor includes player instanceation. 
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // NOT USED
    }

    // Takes in movement input from arrows for movement and the spacebar for jump
    @Override
    public void keyPressed(KeyEvent e) {        
        switch(Gamestate.state){
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Gamestate.state){
            case MENU:
            gamePanel.getGame().getMenu().keyReleased(e);

                break;
            case PLAYING:
            gamePanel.getGame().getPlaying().keyReleased(e);

                break;
            default:
                break;
        }
    }
    
}
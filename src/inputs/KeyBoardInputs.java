package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.lang.model.util.ElementScanner14;

import main.GamePanel;
import Entities.Player;
import Entities.Entity;

public class KeyBoardInputs implements KeyListener{

    private Player player;
    private boolean spaceBar;
    private boolean right;
    private boolean left;

    public KeyBoardInputs(Player player){
        this.player = player;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.velocityLeft = false;
        player.velocityRight = false;
        switch(e.getKeyCode()){
        case KeyEvent.VK_W: case KeyEvent.VK_UP:
            player.jump(); //move up
            break;
        case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
            left = true; //set left to true, incase space bar is also pressed
            if(spaceBar){
                player.jump(); //passing jump the xy direction prevents the player from moving left/right in the way
                player.velocityLeft = true; //setting velocity to left true so that the player falls at an angle to the left
            }
            player.movePosition(-20,0); 
            break;
        case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
            player.movePosition(0,5);
            break;
        case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
            right = true; //set right to true, incase space bar is also pressed
            if(spaceBar){
                player.jump();
                player.velocityRight = true; //setting velocity to right true so that the player falls at an angle to the left
            }
                player.movePosition(20,0);
            break ;
        case KeyEvent.VK_SPACE:
            spaceBar = true;
            if(right){
                player.movePosition(5,0);
                player.velocityRight = true;
            }
            else if(left){
                player.movePosition(-5,0);
                player.velocityLeft = true;
            }
                player.jump();
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                spaceBar = false; //makes sure the variable is only true when space bar is held
                break;
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                left = false;
                break;
        }
    }
    
}

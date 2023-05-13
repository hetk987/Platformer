package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entities.Player;

import static utilz.Constants.Directions.*;

public class KeyBoardInputs implements KeyListener{

    //Instance fields needed
    private Player player;

    //Constructor includes player instanceation. 
    public KeyBoardInputs(Player player){
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // NOT USED
    }

    // Takes in movement input from arrows for movement and the spacebar for jump
    @Override
    public void keyPressed(KeyEvent e) {        
  

        switch(e.getKeyCode()){
            // LEFT MOVEMENT

            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                player.setLeft(true);
                player.resetAnimationIndex(LEFT);

                if(player.getUp()){
                    player.resetAnimationIndex(UP);
                    player.jump(); 
                }
                player.setDirection(LEFT); // sets animation facing left
                break;


            //RIGHT MOVEMENT
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                player.setRight(true);
                player.resetAnimationIndex(RIGHT);
                if(player.getUp()){
                    player.resetAnimationIndex(UP);
                    player.jump();
                }
                player.setDirection(RIGHT); //sets animation facing the right
                break ;

            //UP MOVEMENT
            case KeyEvent.VK_SPACE: case KeyEvent.VK_W: case KeyEvent.VK_UP:
                player.setUp(true);
                player.resetAnimationIndex(UP);
                if(player.getRight()){ //Jump to the right 
                    player.setDirection(RIGHT); 
                }
                else if(player.getLeft()){ //Jump to the left
                    player.setDirection(LEFT);
                }
                player.jump();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Sets both moving and jump to false
        player.resetAnimationIndex(IDLE);


        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:case KeyEvent.VK_W: case KeyEvent.VK_UP:
                player.setUp(false);
                player.setJump(false);    
                break;
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                player.setRight(false);
                player.setDirection(RIGHT);

                break;
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                player.setLeft(false);
                player.setDirection(LEFT);
                break;
        }
    }
    
}
package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entities.Player;

import static utilz.Constants.Directions.*;

public class KeyBoardInputs implements KeyListener{

    //Instance fields needed
    private Player player;
    private boolean spaceBar;
    private boolean right;
    private boolean left;
    private String lastPressed = "idle";

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
        player.velocityLeft = false;
        player.velocityRight = false;

        switch(e.getKeyCode()){
            // LEFT MOVEMENT
            
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                player.setMoving(true);
                player.resetAnimationIndex(lastPressed, "left");
                left = true; //set left to true, incase space bar is also pressed
                lastPressed = "left";
                
                if(spaceBar){
                    player.resetAnimationIndex(lastPressed, "up");
                    player.jump(); 
                    lastPressed = "up";
                    player.velocityLeft = true; //setting velocity to left true so that the player falls at an angle to the left
                }
                player.setDirection(LEFT); // sets animation facing left
                break;

            //DOWN MOVEMENT
            case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
                player.resetAnimationIndex(lastPressed, "down");
                lastPressed = "down";
                player.setDirection(DOWN);
                break;
            
            //RIGHT MOVEMENT
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                player.setMoving(true);
                player.resetAnimationIndex(lastPressed, "right");
                lastPressed = "right";
                right = true; //set right to true, incase space bar is also pressed
                if(spaceBar){
                    player.resetAnimationIndex(lastPressed, "up");
                    player.jump();
                    player.velocityRight = true; //setting velocity to right true so that the player falls at an angle to the left
                }
                player.setDirection(RIGHT); //sets animation facing the right
                break ;

            //UP MOVEMENT
            case KeyEvent.VK_SPACE: case KeyEvent.VK_W: case KeyEvent.VK_UP:
                spaceBar = true;
                player.resetAnimationIndex(lastPressed, "up");
                lastPressed = "up";
                if(right){ //Jump to the right 
                    player.setDirection(RIGHT); 
                    player.velocityRight = true;
                }
                else if(left){ //Jump to the left
                    player.setDirection(LEFT);;
                    player.velocityLeft = true;
                }
                player.jump();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Sets both moving and jump to false
        player.setMoving(false);
        player.resetAnimationIndex(lastPressed, "idle");
        lastPressed = "idle";


        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:case KeyEvent.VK_W: case KeyEvent.VK_UP:
                player.setJump(false);    
                spaceBar = false; //makes sure the variable is only true when space bar is held
                break;
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                right = false;
                player.setDirection(RIGHT);

                break;
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                left = false;
                player.setDirection(LEFT);
                break;
        }
    }
    
}

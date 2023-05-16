package Entities;

import java.awt.Rectangle;
import Physics.*;
import utilz.Constants.PlayerConstants;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class Player implements Entity{
    private int xPosition, yPosition;
    public Rectangle hitBox;
    public Collisions colliderCheck;
    private int xDifference = 27;
    private int yDifference = 15;
    private BufferedImage img;
    private BufferedImage[][] animation;
    private int animationIndex = 0;
    public int gravityValue = 0;
    public boolean inAir = true;
    private int playerAction = PlayerConstants.IDLE_RIGHT;
    private int playerDirection = -1;
    private boolean moving;
    private boolean jump;
    private int lastPressed = RIGHT;
    public int gravVal = -14;
    public int playerSpeed = 1;

    private boolean left, up, right, down;

    public Player(int x, int y,  Collisions c){
        xPosition = x;
        yPosition = y;
        hitBox = new Rectangle(xPosition + xDifference, yPosition + yDifference, 20, 45);
        colliderCheck = c;
        colliderCheck.addEntity(this);
        importImage();
        loadAnimation();
    }

    public void loadAnimation() {
        animation = new BufferedImage[6][7];
        for(int j = 0; j<animation.length;j++)
            for(int i = 0; i<animation[j].length; i++){
                animation[j][i] = img.getSubimage(i*200, j*200, 200, 200);
            }
    }

    public void importImage() {
        try {
            img = ImageIO.read(new FileInputStream("res/diverSpriteAtlas.png"));
        } catch (IOException e) {
            System.out.println("Reading Image Error");
            e.printStackTrace();
        }
    }

    public void setGravityValue(int g){
        gravityValue = g;
    }

    public int getGravityValue(){
        return gravityValue;
    }
    
    public void setInAir(boolean status){
        inAir = status;
    }

    public boolean getInAir(){
        return inAir;
    }

    public void updateAnimation(){
        animationIndex++;
        if(animationIndex>= GetSpriteAmount(playerAction))
                animationIndex = 0;
    }


    public void updateGravityValue(){
        if(gravityValue != 0){
            int toIncrement = gravityValue/(Math.abs(gravityValue));
            if(right)
                movePosition(1, 0);
            else if(left)
                movePosition(-1, 0);
            else
                movePosition(0, 0);
        for(int i=0; i<Math.abs(gravityValue); i++){
            movePosition(0, toIncrement);
            }
        }
        gravityValue++;
    }

 
    public void jump(){
        jump = true;
        if(!inAir){
            inAir = true;
            gravityValue = gravVal; //start of gravity vallue
        }
    } 

    public BufferedImage getAnimation(){
        return animation[playerAction][animationIndex];
    }

    public void setDirection(int direction){
        playerDirection = direction;
    }

    public void setMoving(boolean moving){
        this.moving = moving;   
    }

    public void setJump(boolean jump){
        this.jump = jump;
    }

    public void movePosition(int xNum, int yNum){ 
       colliderCheck.moveTo(this, xNum, yNum); // sends the amount the player wants to move which will then update it depending on where it can move
    }

    public void playerDies(){
        xPosition = 0;
        hitBox.x = xPosition + xDifference;
        yPosition = 300;
        hitBox.y = yPosition+ yDifference;
        //this.setMoving(true);
        this.setInAir(true);
        this.setJump(false);
        right = false;
        left = false;
        
    }

    public int getXPosition(){
        return xPosition;
    }

    public int getYPosition(){
        return yPosition;
    }

    public int getXDifference(){
        return xDifference;
    }
    public int getYDifference(){
        return yDifference;
    }

    public void setXPosition(int newX){
        xPosition = newX;
        hitBox.x = xPosition + xDifference;
    }

    public void setYPosition(int newY){
        yPosition = newY;
        hitBox.y = yPosition+ yDifference;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public void resetAnimationIndex(int pressed){
        if(lastPressed == pressed)
            return;
        lastPressed = pressed;
        animationIndex = 0;
    }

    @Override
    public void setAnimation() {
        if(!inAir && jump)
            jump();
        if(moving){
            if(jump){
                switch(playerDirection){
                    case LEFT:
                        playerAction = JUMPING_LEFT;
                        movePosition(0, 0);
                        break;
                    case RIGHT:
                        playerAction = JUMPING_RIGHT;
                        movePosition(0, 0);
                        break;
                }
            }
            else
                switch(playerDirection){
                    case LEFT:
                        playerAction = RUNNING_LEFT;
                        break;
                    case RIGHT:
                        playerAction = RUNNING_RIGHT;
                        break;
            }
        }
        else
            switch(playerDirection){
                case LEFT:
                    playerAction = IDLE_LEFT;
                    break;
                case RIGHT:
                    playerAction = IDLE_RIGHT;
                    break;
            }
    }

    public void setLeft(boolean bool){
        left = bool;
    }

    public void setRight(boolean bool){
        right = bool;
    }

    public void setUp(boolean bool){
        up = bool;
    }

    public void setDown(boolean bool){
        down = bool;
    }

    public boolean getLeft(){
        return left;
    }

    public boolean getRight(){
        return right;
    }

    public boolean getUp(){
        return up;
    }

    public boolean getDown(){
        return down;
    }

    public void updatePos(){
        moving = false;
        if(left && !right){
            moving = true;
            movePosition(-playerSpeed, 0);
            playerDirection = LEFT;
        }
        else if (!left && right){
            moving = true;
            movePosition(playerSpeed, 0);
            playerDirection = RIGHT;
        } 
    }


}
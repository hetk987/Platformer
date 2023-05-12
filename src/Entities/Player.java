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
    private int xDifference = 65;
    private int yDifference = 35;
    private BufferedImage img;
    private BufferedImage[][] animation;
    private int animationIndex = 0;
    public boolean velocityRight = false;
    public boolean velocityLeft = false;
    public int gravityValue = 0;
    public boolean inAir = true;
    private int playerAction = PlayerConstants.IDLE_RIGHT;
    private int playerDirection = -1;
    private boolean moving;
    private boolean jump;

    public Player(int x, int y,  Collisions c){
        xPosition = x;
        yPosition = y;
        hitBox = new Rectangle(xPosition + xDifference, yPosition + yDifference, 70, 120);
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
            if(velocityRight)
                movePosition(7, 0);
            else if(velocityLeft)
                movePosition(-7, 0);
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
            gravityValue = -25; //start of gravity vallue
        }
    } 

    public BufferedImage getAnimation(){
        return animation[playerAction][animationIndex];
    }

    public void setDirection(int direction){
        playerDirection = direction;
        moving = true;
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


    public void updateEntity(){

    }

    public void setAnimationIndex(){
        animationIndex = 0;
    }

    @Override
    public void setAnimation() {
        if(moving){
            if(jump){
                switch(playerDirection){
                    case LEFT:
                        playerAction = JUMPING_LEFT;
                        movePosition(-1, 0);
                        break;
                    case RIGHT:
                        playerAction = JUMPING_RIGHT;
                        movePosition(1, 0);
                        break;
                }
            }
            else
                switch(playerDirection){
                    case LEFT:
                        playerAction = RUNNING_LEFT;
                        movePosition(-1, 0);
                        break;
                    case RIGHT:
                        playerAction = RUNNING_RIGHT;
                        movePosition(1, 0);
                        break;
            }
        }
        else
            playerAction = IDLE_RIGHT;
    }



}

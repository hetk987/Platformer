package Entities;

import java.awt.Rectangle;
import Physics.*;
import Utilz.LoadSave;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{
    private int xPosition, yPosition;
    public Rectangle hitBox;
    public Collisions colliderCheck;
    public int xDifference = 65;
    public int yDifference = 35;
    private BufferedImage img;
    private BufferedImage[] animation;
    private int animationIndex = 0;
    private int animationTick=0;
    private int animationSpeed = 3;
    public boolean velocityRight = false;
    public boolean velocityLeft = false;
    public int gravityValue = 0;
    public boolean inAir = true;

    private int gravityTick=0;
    private int gravitySpeed = 8;

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
        animation = new BufferedImage[4];
        for(int i = 0; i<animation.length; i++){
            animation[i] = img.getSubimage(i*100, 0, 100, 100);
        }
    }

    public void importImage() {
        img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
    }

    public void setInAir(boolean status){
        inAir = status;
    }

    public void updateAnimationTick() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationIndex++;
            animationTick = 0;
            if(animationIndex>=animation.length)
            {
                animationIndex = 0;
            }
        } 
    }

    public void updateGravityTick() {
        gravityTick++;
        if(inAir){
            if(gravityTick >= gravitySpeed){
                gravityTick = 0;
                if(velocityRight)
                    movePosition(10, gravityValue);
                else if(velocityLeft)
                    movePosition(-10, gravityValue);
                else
                    movePosition(0, gravityValue);
                System.out.println(gravityValue);
                gravityValue++;
            } 
        }
    }

    public void jump(){
        if(!inAir){
            inAir = true;
            gravityValue = -8; //start of gravity vallue
            colliderCheck.moveTo(this, 0, -10); //jump
        }
    } 

    public BufferedImage getAnimation(){
        return animation[animationIndex];
    }


    public void movePosition(int xNum, int yNum){ 
       colliderCheck.moveTo(this, xNum, yNum); // sends the amount the player wants to move which will then update it depending on where it can move
       if(xNum != 0){
           updateAnimationTick(); //since he is moving to the left or right, this updates the animation
       }
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

    
    


}

package Entities;

import java.awt.Color;
import java.awt.Rectangle;
import Physics.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MonitoringVillian implements Entity{
    public int xPosition, yPosition, rightBound, leftBound;
    public boolean rightDirection;
    public Rectangle hitBox;
    public Collisions colliderCheck;
    public int xDifference = -10;
    public int yDifference = -10;
    private int gravityValue = 0;
    private BufferedImage img;
    private BufferedImage[][] animation;
    private int animationIndex = 0;
    private int animationAction = 0;
    public boolean inAir = false;

    public MonitoringVillian(int xpos, int ypos, int rightBound, int leftBound, Collisions c){
        xPosition = xpos;
        yPosition = ypos;
        this.rightBound = rightBound;
        this.leftBound = leftBound;
        hitBox = new Rectangle(xPosition + xDifference, yPosition + yDifference, 220, 70);
        colliderCheck = c;
        colliderCheck.addEntity(this);
    }

    public void loadAnimation() {
        animation = new BufferedImage[4][1];
        for(int i = 0; i<animation.length; i++){
            animation[i][animationAction] = img.getSubimage(i*100, 0, 100, 100);
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


    public void updateAnimation(){
        animationIndex++;
        if(animationIndex>=animation.length)
            {
                animationIndex = 0;
            }
    } 

    public BufferedImage getAnimation(){
        return animation[animationIndex][animationAction];
    }

    public void movePosition(int xNum, int yNum){
        colliderCheck.moveTo(this, xPosition+=xNum, yPosition+=yNum);
        hitBox.x = xPosition + xDifference;
        hitBox.y = yPosition + yDifference;
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

    public void updateEntity(){
        if(rightDirection){
            xPosition++;
            if(xPosition > rightBound)
                rightDirection = false;
        }
        else{
            xPosition--;
            if(xPosition < leftBound)
                rightDirection = true;
        }
        hitBox.x = xPosition-10;
        hitBox.y = yPosition-10;
    } 

    public void setXPosition(int newX){
        xPosition = newX;
    }

    public void setYPosition(int newY){
        yPosition = newY;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public void updateGravityTick() {
    }

    public void setInAir(boolean b){

    }


    public boolean getInAir(){
        return false;
    }

    public void updateGravityValue(){

    }

    public void setGravityValue(int g){

    }

    public int getGravityValue(){
        return gravityValue;
    }

    @Override
    public void setAnimation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAnimation'");
    }
    
    

}
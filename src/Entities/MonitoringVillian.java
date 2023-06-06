package Entities;

import java.awt.Rectangle;
import Physics.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MonitoringVillian implements Entity{
    public int xPosition, yPosition, rightBound, leftBound;
    public boolean rightDirection = true;
    public Rectangle hitBox;
    public Collisions colliderCheck;
    public int xDifference = 9;
    public int yDifference = 15;
    private BufferedImage img;
    private BufferedImage[][] animation;
    private int animationIndex = 0;
    private int animationAction = 0;
    public boolean inAir = false;
    public int movementSpeed = 1;

    public MonitoringVillian(int xpos, int ypos, int rightBound, int leftBound, Collisions c){
        xPosition = xpos;
        yPosition = ypos;
        this.rightBound = rightBound;
        this.leftBound = leftBound;
        hitBox = new Rectangle(xPosition + xDifference, yPosition + yDifference, 50, 25);
        colliderCheck = c;
        colliderCheck.addEntity(this);
        importImage();
        loadAnimation();
    }

    public void loadAnimation() {
        animation = new BufferedImage[2][4];
        for(int j = 0; j<animation.length;j++)
            for(int i = 0; i<animation[j].length; i++){
                animation[j][i] = img.getSubimage(i*100, j*100, 100, 100);
            }
    }

    public void importImage() {
        try {
            img = ImageIO.read(new FileInputStream("res/sharkAnimation.png"));
        } catch (IOException e) {
            System.out.println("Reading Image Error");
            e.printStackTrace();
        }
    }


    public void updateAnimation(){
        animationIndex++;
        if(animationIndex >= 4)
                animationIndex = 0;
    }

    public BufferedImage getAnimation(){
        return animation[animationAction][animationIndex];
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
            xPosition+=movementSpeed;  
            if(xPosition >= rightBound)
                rightDirection = false;
        }
        else{
            xPosition-=movementSpeed;
            if(xPosition <= leftBound)
                rightDirection = true;
        }
        hitBox.x = xPosition+10;
        hitBox.y = yPosition+20;
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

    public boolean getDirection()
    {
        return rightDirection;
    }
    @Override
    public void setAnimation() {
        if(!this.getDirection()){
            animationAction = 1;
        }
        else{
            animationAction = 0;
        }
    }

    @Override
    public void setGravityValue(int g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGravityValue'");
    }

    @Override
    public int getGravityValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGravityValue'");
    }
    
    public void setSpeed(int x){
        movementSpeed = x;
    }
    

}
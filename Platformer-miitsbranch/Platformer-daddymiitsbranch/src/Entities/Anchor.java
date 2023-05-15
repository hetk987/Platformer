package Entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Physics.*;


public class Anchor implements Entity{

    private BufferedImage img;
    public Rectangle hitBox;
    public Rectangle hitBox1;
    public Rectangle hitBox2;
    public Rectangle hitBox3;
    public Rectangle hitBox4;
    public Collisions colliderCheck;
    public int xPosition = 0;
    public int yPosition = 400;
    public int xDifference = -50;
    public int yDifference = 0;
    public boolean inAir = false;
    
    public Anchor(Collisions c, int x, int y, int w, int h){
        hitBox = new Rectangle(x + xDifference, y + yDifference, w, h);
        colliderCheck = c;
        colliderCheck.addEntity(this);
    }

    public Rectangle getRectangle()
    {
        return hitBox;
    }

    public boolean getInAir(){
        return false;
    }

    public void updateGravityValue(){
        
    }

    public void updateGravityTick() {
        
    }

    public void setGravityValue(int g){

    }

    public int getGravityValue(){
        return 0;
    }
    

    public void updateAnimation(){

    }
    
    public BufferedImage getAnimation(){
        return img;
    }

    public void update(){
        //updateAnimationTick();
    }

   


    public int getXDifference(){
        return xDifference;
    }
    public int getYDifference(){
        return yDifference;
    }

    public void updateEntity(){

    }
    public void movePosition(int x, int y){

    }
    public int getXPosition(){
        return xPosition;
    }
    public int getYPosition(){
        return yPosition;
    }
    public void setXPosition(int x){
        xPosition = x;

    }
    public void setYPosition(int y){
        yPosition = y;

    }
    public void updateAnimationTick(){

    }
    public Rectangle getHitBox(){
        return hitBox;
    }

    public void setInAir(boolean b){

    }

    @Override
    public void setAnimation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAnimation'");
    }


}
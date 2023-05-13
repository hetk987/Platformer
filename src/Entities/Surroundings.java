package Entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import Physics.*;


public class Surroundings implements Entity{

    private BufferedImage img;
    public Rectangle hitBox;
    public Collisions colliderCheck;
    public int xPosition = 300;
    public int yPosition = 500;
    public int xDifference = 50;
    public int yDifference = 115;
    public boolean inAir = false;
    
    public Surroundings(Collisions c){
        importImage();
        hitBox = new Rectangle(xPosition + xDifference, yPosition + yDifference, 1500, 71);
        colliderCheck = c;
        colliderCheck.addEntity(this);
    }

    public void importImage() {
        try {
            img = ImageIO.read(new FileInputStream("res/platform2.png"));
        } catch (IOException e) {
            System.out.println("Reading Image Error");
            e.printStackTrace();
        }
    }

    public void updateGravityTick() {
        
    }

    public void setGravityValue(int g){

    }

    public int getGravityValue(){
        return 0;
    }


    public BufferedImage getAnimation(){
        return img;
    }

    public void update(){
        //updateAnimationTick();
    }

    public Rectangle getHitBox(){
        return hitBox;
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

    }
    public void setYPosition(int y){

    }
    public void updateAnimationTick(){

    }
    public void setInAir(boolean b){

    }
    public int getHitBoxY(){
        return 0;
    }

    public void updateAnimation(){

    }

    public boolean getInAir(){
        return false;
    }

    public void updateGravityValue(){
        
    }

    @Override
    public void setAnimation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAnimation'");
    }

}
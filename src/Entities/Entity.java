package Entities;
import java.awt.image.BufferedImage;

import java.awt.Rectangle;

public abstract class Entity {
    //must use setter and get methods due to multithreading issues
    public abstract void updateEntity(); //not used but might use in the future
    public abstract void movePosition(int x, int y); //move position based off a change in x and y
    public abstract int getXPosition(); 
    public abstract int getYPosition();
    public abstract void setXPosition(int x);
    public abstract void setYPosition(int y);
    public abstract BufferedImage getAnimation();
    public abstract void updateAnimationTick();
    public abstract Rectangle getHitBox();
    public abstract int getXDifference();
    public abstract int getYDifference();
    public abstract void updateGravityTick();
    public abstract void setInAir(boolean b);
    public Rectangle hitBox;
    public int xDifference; //the x difference between where the player image is drawn vs the hit hox
    public int yDifference; //the y difference between where the player image is drawn vs the hit hox
    public boolean inAir;

}

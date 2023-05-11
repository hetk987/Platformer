package Entities;
import java.awt.image.BufferedImage;

import java.awt.Rectangle;

public interface Entity {
    
    public abstract void movePosition(int x, int y); 
    public abstract int getXPosition(); 
    public abstract int getYPosition();
    public abstract void setXPosition(int x);
    public abstract void setYPosition(int y);
    public abstract int getHitBoxY();
    public abstract BufferedImage getAnimation();
    public abstract Rectangle getHitBox();
    public abstract int getXDifference();
    public abstract int getYDifference();
    public abstract void setInAir(boolean b);
    public abstract boolean getInAir();
    public abstract void updateGravityValue();
    public void updateAnimation();
    public abstract void setAnimation();
}

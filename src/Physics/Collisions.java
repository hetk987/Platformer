package Physics;
import Entities.*;


import java.util.ArrayList;


import java.awt.Rectangle;

public class Collisions {
    
    ArrayList<Entity> allEntities;  //arraylist containing every entity in the game
    boolean canMove = true; //variable representing if the entity can move


    public Collisions(){
        allEntities = new ArrayList<Entity>();
    }

    public void addEntity(Entity e){
        allEntities.add(e);
    }

    public void moveTo(Entity e, int x, int y){
        int futureX = e.getXPosition() + x; //the x value where the entity wants to move
        int futureY = e.getYPosition() + y; //the y value where the entity wants to move
        Rectangle newHitBox = new Rectangle(futureX + e.getXDifference(), futureY + e.getYDifference(), e.getHitBox().width, e.getHitBox().height); //the new hitbox for the potential location
        int thisEntPlace = allEntities.indexOf(e); //gets the index of the current entity
        for(int i=0; i<allEntities.size(); i++){
            if(i != thisEntPlace){  //makes sure it is not comparing itself with itself
                if(newHitBox.intersects(new Rectangle(allEntities.get(i).getHitBox().x, allEntities.get(i).getHitBox().y, allEntities.get(i).getHitBox().width, allEntities.get(i).getHitBox().height))){ //sees if the future hitbox intersects with any other hit box
                    canMove = false; // it cannove move to where it wants
                }
            }
        }
        if(canMove){
            if(y != 0)  //checks if the new location is a change in the y aixs
                e.setInAir(true);   //sets the variable for the entity being in the air to true
            e.setXPosition(futureX); //updates x position to new position
            e.setYPosition(futureY); //updates y position to new y position
        }
        else
            e.setInAir(false); //sets the entitys in air variable to false
        canMove = true; //sets can move to true, so that it is set for the next time the method is called
    }


}

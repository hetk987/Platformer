package Physics;
import Entities.*;


import java.util.ArrayList;


import java.awt.Rectangle;

public class Collisions {
    
    ArrayList<Entity> allEntities;  //arraylist containing every entity in the game
    boolean canMove = true; //variable representing if the entity can move
    int futureX;
    int futureY; 
    Entity currentEntity;


    public Collisions(){
        allEntities = new ArrayList<Entity>();
    }

    public void addEntity(Entity e){
        allEntities.add(e);
    }

    public void moveTo(Entity e, int x, int y){
        currentEntity = e;
        Rectangle platFormUnder = new Rectangle();
        boolean collisionFound = false;
        futureX = e.getXPosition() + x; //the x value where the entity wants to move
        futureY = e.getYPosition() + y; //the y value where the entity wants to move
        Rectangle newHitBox = new Rectangle(futureX + e.getXDifference(), futureY + e.getYDifference(), e.getHitBox().width, e.getHitBox().height); //the new hitbox for the potential location
        int thisEntPlace = allEntities.indexOf(e); //gets the index of the current entity
        for(int i=0; i<allEntities.size(); i++){
            if(i != thisEntPlace){  //makes sure it is not comparing itself with itself

                Rectangle hitBoxToCheck = allEntities.get(i).getHitBox();
                if(checkIfIntersectingSomething(newHitBox, hitBoxToCheck)){
                    collisionFound = true;
                    Intersection(newHitBox, hitBoxToCheck);
                }
                else if(platFormUnderneath(newHitBox, hitBoxToCheck)){
                    platFormUnder = hitBoxToCheck;
                }
            }
        }
        if(!collisionFound){
            e.setXPosition(futureX); //updates x position to new position
            e.setYPosition(futureY); //updates y position to new y position
            checkIfOffPlatform(newHitBox, platFormUnder);
        }
    }


    public void Intersection(Rectangle newHitBox, Rectangle intersectingWith){
        if(checkIfStuckToTop(newHitBox, intersectingWith)){
            currentEntity.setInAir(true); 
        }
        else if(checkIfStuckToSides(newHitBox, intersectingWith)){
            currentEntity.setInAir(true);  
            currentEntity.setYPosition(futureY);
        }else{
            currentEntity.setInAir(false);
        }
    }


    public boolean platFormUnderneath(Rectangle current, Rectangle checking){
        Rectangle checkForPlatform = new Rectangle(current.x, current.y + 30, current.width, current.height);
        return checkForPlatform.intersects(checking);
    }

    public void checkIfOffPlatform(Rectangle current, Rectangle checking){
        current.y += 30; //this represents anytime the player falls by more than a number which is what a jump is
        //will set 30 to a variable once the jump height is set to a specific number
        if (!current.intersects(checking)) //checks if there is an intersection if the player fell more than a normal jump
            currentEntity.setInAir(true);
    }

    public boolean checkIfIntersectingSomething(Rectangle current, Rectangle checking){
        return (current.intersects(checking));
    }

    public boolean checkIfStuckToTop(Rectangle current, Rectangle checking){
        Rectangle intersection = current.intersection(checking);
          if(current.y==intersection.y)
            return true;
        return false;
     }

    public boolean checkIfStuckToSides(Rectangle current, Rectangle checking){
        Rectangle intersection = current.intersection(checking);
          if(current.width == intersection.width || ((current.y+current.height-1)==intersection.y))
            return false;
        return true;
    }
}

 
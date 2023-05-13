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
                if(checkIfIntersectingSomething(newHitBox, hitBoxToCheck)){ //checks if there were any collisions
                    collisionFound = true; //if there is a collision found, this means we don't have to worry about other situations, which is why variable is saved
                    Intersection(newHitBox, hitBoxToCheck); //calls method to handle an intersection
                }
                else if(platFormUnderneath(newHitBox, hitBoxToCheck)){ //checks if there is any platform underneath the player within a certain y range
                    platFormUnder = hitBoxToCheck; //saves the platform underneath (the user is not necessarily standing on it)
                }
            }
        }
        if(!collisionFound){ //checks if there were no collisions, meaning the entity can move
            currentEntity.setXPosition(futureX); //updates x position to new position
            currentEntity.setYPosition(futureY); //updates y position to new y position
            checkIfOffPlatform(newHitBox, platFormUnder); //checks if the player is off of the platform and needs to fall
        }
    }


    public void Intersection(Rectangle newHitBox, Rectangle intersectingWith){
        if(checkIfStuckToTop(newHitBox, intersectingWith)){ //checks if the entity is stuck to the top
            currentEntity.setInAir(true);  //makes the entity fall
            int opppositecurrentGravity = Math.abs(currentEntity.getGravityValue()); //gets the absolute value of the current gravity value, aka the opposite
            currentEntity.setGravityValue(opppositecurrentGravity); //sets gravity to that value so the entity falls at the same speed it was rising
        }
        else if(checkIfStuckToSides(newHitBox, intersectingWith)){  //checks if the entity is stuck to any sides
            currentEntity.setInAir(true);  //makes the entity fall
            currentEntity.setYPosition(futureY); //allows the entity to move to the future y, but not x
        }else{
            currentEntity.setInAir(false); //the user is standing on the platform
        }
    }


    public boolean platFormUnderneath(Rectangle current, Rectangle checking){
        Rectangle checkForPlatform = new Rectangle(current.x, current.y + 30, current.width, current.height); //the rectangle of the intersection between the two entities
        return checkForPlatform.intersects(checking); //returns whether or not they intercept
    }

    public void checkIfOffPlatform(Rectangle current, Rectangle checking){
        current.y += 30; //this represents anytime the player falls by more than a number which is what a jump is
        //will set 30 to a variable once the jump height is set to a specific number
        if (!current.intersects(checking)) //checks if there is an intersection if the player fell more than a normal jump
            currentEntity.setInAir(true); //player needs to keep falling
    }

    public boolean checkIfIntersectingSomething(Rectangle current, Rectangle checking){ //checks for any collisions
        return (current.intersects(checking)); //returns if so
    }

    public boolean checkIfStuckToTop(Rectangle current, Rectangle checking){
        Rectangle intersection = current.intersection(checking); //new rectangle representing the intersection
          return (current.y==intersection.y); //checks if one entities head is touching the others bottom, as in the player hitting a platform above
     }

    public boolean checkIfStuckToSides(Rectangle current, Rectangle checking){ //checks if the intersection happens on the sides of a platform
        Rectangle intersection = current.intersection(checking); //new rectangle representing the intersection
          return (((current.y+current.height-1)!=intersection.y)); //checks if the players bottom/feet are on the second entitys top (the platform)
    }
}

 
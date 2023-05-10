package main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;

import Entities.*;
import Physics.*;

public class GamePanel extends JPanel{

    public ArrayList<Entity> entitiesList;
    public Surroundings surroundingsTest;
    Entity currentEntity;

    public GamePanel(){
        entitiesList = new ArrayList<Entity>();
    }

    public void setBackGround(Surroundings surroundings){
        surroundingsTest = surroundings;
    }

    public void addEntity(Entity e){
        entitiesList.add(e);
    }

    public int getNumberOfEntities(){
        return entitiesList.size();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateEntities(g);
        updateSurroundings(g);
    }

    public void updateEntities(Graphics g){
        for(int i=0; i<entitiesList.size(); i++){ //loop through each entity
            currentEntity = entitiesList.get(i); 
            currentEntity.updateGravityTick(); //update gravity effects
            g.drawRect(currentEntity.getHitBox().x, currentEntity.getHitBox().y, currentEntity.getHitBox().width, currentEntity.getHitBox().height); //draw hit box
            g.drawImage(currentEntity.getAnimation(), currentEntity.getXPosition(), currentEntity.getYPosition(), 200, 200, getFocusCycleRootAncestor()); //draw entity
        }
    }

    public void updateSurroundings(Graphics g){
        g.drawImage(surroundingsTest.getAnimation(), 300, 500, 450, 300, getFocusCycleRootAncestor()); //draw hit box
        g.drawRect(surroundingsTest.getRectangle().x, surroundingsTest.getRectangle().y, surroundingsTest.getRectangle().width, surroundingsTest.getRectangle().height); //draw surroundings
    }


    
}

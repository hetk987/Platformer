package main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Dimension;

import Entities.*;

public class GamePanel extends JPanel{

    public ArrayList<Entity> entitiesList;
    public Surroundings surroundingsTest;
    private int animationTick=0;
    private int animationSpeed = 30;
    private int gravityTick=0;
    private int gravitySpeed = 5;
    Entity currentEntity;

    public GamePanel(){
        entitiesList = new ArrayList<Entity>();
        setPanelSize();
    }

    public void setPanelSize(){
        setPreferredSize(new Dimension(1500, 800));
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
        updateSurroundings(g);
        updateEntities(g);
    }

    public void updateEntities(Graphics g){
        for(Entity currentEntity: entitiesList){ //loop through each entity
           // g.drawRect(currentEntity.getHitBox().x, currentEntity.getHitBox().y, currentEntity.getHitBox().width, currentEntity.getHitBox().height); //draw hit box
            g.drawImage(currentEntity.getAnimation(), currentEntity.getXPosition(), currentEntity.getYPosition(), 200, 200, getFocusCycleRootAncestor()); //draw entity
        }
    }

    public void updateAnimationTick(){
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick=0;
            for(Entity currentEntity: entitiesList){ 
                currentEntity.updateAnimation();
            }
        } 
    }

    public void updateGravityTick(){
        gravityTick++;
        if(gravityTick >= gravitySpeed){
            gravityTick = 0;
            for(Entity currentEntity: entitiesList){ 
                if(currentEntity.getInAir()){
                    for(int i=0; i<entitiesList.size(); i++){ 
                        currentEntity = entitiesList.get(i);
                        currentEntity.updateGravityValue(); 
                    }
                }
            }
        }
    }

    public void updateGame(){
        updateAnimationTick();
        updateGravityTick();
    }

    public void updateSurroundings(Graphics g){
        g.drawImage(surroundingsTest.getAnimation(), 300, 500, 450, 300, getFocusCycleRootAncestor()); //draw hit box
        g.drawRect(surroundingsTest.getHitBox().x, surroundingsTest.getHitBox().y, surroundingsTest.getHitBox().width, surroundingsTest.getHitBox().height); //draw surroundings
    }


    
}

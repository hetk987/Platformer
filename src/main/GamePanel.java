package main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import Entities.*;

public class GamePanel extends JPanel{

    public Entity[] entitiesList;
    public Surroundings surroundingsTest;
    private int animationTick=0;
    private int animationSpeed = 30;
    private int gravityTick=0;
    private int gravitySpeed = 5;
    Entity currentEntity;

    public GamePanel(){
        this.setBackground(Color.black);
        entitiesList = new Entity[1];
        setPanelSize();
    }

    //Sets the size of the game screen
    public void setPanelSize(){
        setPreferredSize(new Dimension(1500, 900));
    }


    //Add the platforms
    public void setBackGround(Surroundings surroundings){
        surroundingsTest = surroundings;
    }

    //Loads entites to a list that will be used to check collision
    public void addEntity(Entity e){
        entitiesList[0] = e;
    }

    //Retrun num entities
    public int getNumberOfEntities(){
        return entitiesList.length;
    }

    //Renders the game
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateSurroundings(g);//renders platforms
        updateEntities(g);// renders entities
    }


    public void updateEntities(Graphics g){
        for(Entity currentEntity: entitiesList){ //loop through each entity
            currentEntity.setAnimation();
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
                    for(int i=0; i<entitiesList.length; i++){ 
                        currentEntity = entitiesList[i];
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
    }


    
}

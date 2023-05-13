package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;
import Entities.*;
import Levels.LevelManager;

public class GamePanel extends JPanel{

    public ArrayList<Entity> entitiesList;
    public Surroundings surroundings1;
    public Surroundings surroundings2;
    public Surroundings surroundings3;
    public Surroundings surroundings4;
    public Surroundings surroundings5;
    public Surroundings surroundings6;
    public Surroundings surroundings7;
    public Surroundings surroundings8;
    private int animationTick=0;
    private int animationSpeed = 30;
    private int gravityTick=0;
    private int gravitySpeed = 5;
    Entity currentEntity;
    private LevelManager levelManager;

    public GamePanel(){
        entitiesList = new ArrayList<Entity>();
        setPanelSize();
        
    }

    public void setBackGround(Surroundings surroundings1, Surroundings surroundings2, Surroundings surroundings3, Surroundings surroundings4, Surroundings surroundings5, Surroundings surroundings6, Surroundings surroundings7, Surroundings surroundings8){
        this.surroundings1 = surroundings1;
        this.surroundings2 = surroundings2;
        this.surroundings3 = surroundings3;
        this.surroundings4 = surroundings4;
        this.surroundings5 = surroundings5;
        this.surroundings6 = surroundings6;
        this.surroundings7 = surroundings7;
        this.surroundings8 = surroundings8;
    
    }

    void setPanelSize()
    {
        Dimension size = new Dimension(1500, 900);
        setPreferredSize(size);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    public void addEntity(Entity e){
        entitiesList.add(e);
    }

    public int getNumberOfEntities(){
        return entitiesList.size();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        drawPlatforms(g);
        updateSurroundings(g);
        updateEntities(g);
    }

    public void updateEntities(Graphics g){
        for(Entity currentEntity: entitiesList){ //loop through each entity
            currentEntity.setAnimation();
            //g.drawRect(currentEntity.getHitBox().x, currentEntity.getHitBox().y, currentEntity.getHitBox().width, currentEntity.getHitBox().height); //draw hit box
            g.drawImage(currentEntity.getAnimation(), currentEntity.getXPosition(), currentEntity.getYPosition(), 75, 75, getFocusCycleRootAncestor()); //draw entity
        }
    }

    public void updateSurroundings(Graphics g){
        //g.drawImage(surroundingsTest.getAnimation(), 100, 500, 450, 300, getFocusCycleRootAncestor()); //draw hit box
         g.drawRect(surroundings1.getRectangle().x, surroundings1.getRectangle().y, surroundings1.getRectangle().width, surroundings1.getRectangle().height); //draw surroundings
         g.drawRect(surroundings2.getRectangle().x, surroundings2.getRectangle().y, surroundings2.getRectangle().width, surroundings2.getRectangle().height);
         g.drawRect(surroundings3.getRectangle().x, surroundings3.getRectangle().y, surroundings3.getRectangle().width, surroundings3.getRectangle().height);
        g.drawRect(surroundings4.getRectangle().x, surroundings4.getRectangle().y, surroundings4.getRectangle().width, surroundings4.getRectangle().height);
         g.drawRect(surroundings5.getRectangle().x, surroundings5.getRectangle().y, surroundings5.getRectangle().width, surroundings5.getRectangle().height);
        // g.drawRect(surroundings6.getRectangle().x, surroundings6.getRectangle().y, surroundings6.getRectangle().width, surroundings6.getRectangle().height);
        // g.drawRect(surroundings7.getRectangle().x, surroundings7.getRectangle().y, surroundings7.getRectangle().width, surroundings7.getRectangle().height);
        // g.drawRect(surroundings8.getRectangle().x, surroundings8.getRectangle().y, surroundings8.getRectangle().width, surroundings8.getRectangle().height);
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


    public void drawPlatforms(Graphics g){
        levelManager = new LevelManager();
        levelManager.update();
        levelManager.draw(g);
    }



    
}

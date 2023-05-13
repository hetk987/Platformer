package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Entities.Entity;
import Entities.Player;
import Entities.Surroundings;
import Levels.LevelManager;
import Physics.Collisions;
import main.Game;

import static utilz.Constants.Directions.*;


public class Playing extends State implements Statemethods{
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
    private Player player;
    private Collisions collisionWatcher;


    public Playing(Game game){
        super(game);
        collisionWatcher = new Collisions();
        entitiesList = new ArrayList<Entity>();

        Surroundings surroundings1 = new Surroundings(collisionWatcher, 0 , 400 , 240, 32);
        Surroundings surroundings2 = new Surroundings(collisionWatcher, 240, 500,  160, 32);
        Surroundings surroundings3 = new Surroundings(collisionWatcher, 400, 450,  32, 32);
        Surroundings surroundings4 = new Surroundings(collisionWatcher, 433, 400,  225, 32);
        Surroundings surroundings5 = new Surroundings(collisionWatcher, 710, 350,  47, 32);
        Surroundings surroundings6 = new Surroundings(collisionWatcher, 600, 300,  32, 32); 
        Surroundings surroundings7 = new Surroundings(collisionWatcher, 530, 280,  32, 32);
        Surroundings surroundings8 = new Surroundings(collisionWatcher, 420, 230,  60, 32);//creating surroundings and passing collisions
        player = new Player(0, 300, collisionWatcher); 
        setBackGround(surroundings1, surroundings2, surroundings3, surroundings4, surroundings5, surroundings6, surroundings7,surroundings8);
        addEntity(player);
    }


    @Override
    public void update() {
        player.updatePos();
        updateAnimationTick();
        updateGravityTick();
    }
    @Override
    public void draw(Graphics g) {
        drawPlatforms(g);
        updateSurroundings(g);
        updateEntities(g);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P){
            Gamestate.state = Gamestate.MENU;
        }
        switch(e.getKeyCode()){
            // LEFT MOVEMENT
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                player.setLeft(true);
                player.resetAnimationIndex(LEFT);

                if(player.getUp()){
                    player.resetAnimationIndex(UP);
                    player.jump(); 
                }
                player.setDirection(LEFT); // sets animation facing left
                break;


            //RIGHT MOVEMENT
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                player.setRight(true);
                player.resetAnimationIndex(RIGHT);
                if(player.getUp()){
                    player.resetAnimationIndex(UP);
                    player.jump();
                }
                player.setDirection(RIGHT); //sets animation facing the right
                break ;

            //UP MOVEMENT
            case KeyEvent.VK_SPACE: case KeyEvent.VK_W: case KeyEvent.VK_UP:
                player.setUp(true);
                player.resetAnimationIndex(UP);
                if(player.getRight()){ //Jump to the right 
                    player.setDirection(RIGHT); 
                }
                else if(player.getLeft()){ //Jump to the left
                    player.setDirection(LEFT);
                }
                player.jump();
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // Sets both moving and jump to false
        player.resetAnimationIndex(IDLE);


        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:case KeyEvent.VK_W: case KeyEvent.VK_UP:
                player.setUp(false);
                player.setJump(false);    
                break;
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                player.setRight(false);
                player.setDirection(RIGHT);

                break;
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                player.setLeft(false);
                player.setDirection(LEFT);
                break;
        }
    }
    
    public void updateEntities(Graphics g){
        for(Entity currentEntity: entitiesList){ //loop through each entity
            currentEntity.setAnimation();
            g.drawImage(currentEntity.getAnimation(), currentEntity.getXPosition(), currentEntity.getYPosition(), 75, 75, null); //draw entity
        }
    }

    public void updateSurroundings(Graphics g){
         g.drawRect(surroundings1.getRectangle().x, surroundings1.getRectangle().y, surroundings1.getRectangle().width, surroundings1.getRectangle().height); //draw surroundings
         g.drawRect(surroundings2.getRectangle().x, surroundings2.getRectangle().y, surroundings2.getRectangle().width, surroundings2.getRectangle().height);
         g.drawRect(surroundings3.getRectangle().x, surroundings3.getRectangle().y, surroundings3.getRectangle().width, surroundings3.getRectangle().height);
         g.drawRect(surroundings4.getRectangle().x, surroundings4.getRectangle().y, surroundings4.getRectangle().width, surroundings4.getRectangle().height);
         g.drawRect(surroundings5.getRectangle().x, surroundings5.getRectangle().y, surroundings5.getRectangle().width, surroundings5.getRectangle().height);
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



    public void drawPlatforms(Graphics g){
        levelManager = new LevelManager();
        levelManager.update();
        levelManager.draw(g);
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

    public void addEntity(Entity e){
        entitiesList.add(e);
    }

    public int getNumberOfEntities(){
        return entitiesList.size();
    }

}

package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import Entities.Anchor;
import Entities.Entity;
import Entities.MonitoringVillian;
import Entities.Player;
import Entities.Surroundings;
import Levels.LevelManager;
import Physics.Collisions;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.Directions.*;


public class Playing extends State implements Statemethods{

    public ArrayList<Entity> entitiesList; //creates entity list
    
    private int animationTick=0;
    private int animationSpeed = 30;
    private int gravityTick=0;
    private int gravitySpeed = 5;

    private Entity currentEntity;
    private LevelManager levelManager;
    public Player player;
    public MonitoringVillian villain;
    private Anchor anchor;
    private Collisions collisionWatcher;
    //private Game game;


    public Playing(Game game){

        super(game);
        
        collisionWatcher = new Collisions();
        entitiesList = new ArrayList<Entity>();
        
        this.implementSurroundings(); //instantiates all surroundings objects for the platforms
        player = new Player(0, 300, collisionWatcher); //creates player with collisions
        villain = new MonitoringVillian(400, 350, 550, 375, collisionWatcher); // creates villains with collisions
        
        anchor = new Anchor(collisionWatcher, 432, 80, 35, 64);

        //adds villain and plyer to entities list
        addEntity(player);
        addEntity(villain);

    }





    @Override
    public void update() {
        player.updatePos();
        updateAnimationTick();
        updateGravityTick();
        updateEntityCollisions(player, villain, anchor);
    }

    @Override
    public void draw(Graphics g) {
        
        drawPlatforms(g);//draws in the level
        updateSurroundings(g);//draws in the hitboxes of the platforms IF NECESARY
        updateEntities(g);//draws in the villain and player
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
            if(currentEntity instanceof Player){ //if its a player
                currentEntity.setAnimation();
                g.drawRect(currentEntity.getHitBox().x, currentEntity.getHitBox().y, currentEntity.getHitBox().width, currentEntity.getHitBox().height); //draw hit box
                g.drawImage(currentEntity.getAnimation(), currentEntity.getXPosition(), currentEntity.getYPosition(), 75, 75, null); //draw player
            }
            else if(currentEntity instanceof MonitoringVillian){
                currentEntity.setAnimation();
                g.drawRect(currentEntity.getHitBox().x, currentEntity.getHitBox().y, currentEntity.getHitBox().width, currentEntity.getHitBox().height); //draw hit box
                g.drawImage(currentEntity.getAnimation(), currentEntity.getXPosition(), currentEntity.getYPosition(), 70, 70, null); //draw villain
                ((MonitoringVillian) currentEntity).updateEntity();//basically updates hitbox
            }

        }

    }

    public void updateSurroundings(Graphics g){
        //  g.drawRect(surroundings1.getRectangle().x, surroundings1.getRectangle().y, surroundings1.getRectangle().width, surroundings1.getRectangle().height); //draw surroundings
        //  g.drawRect(surroundings2.getRectangle().x, surroundings2.getRectangle().y, surroundings2.getRectangle().width, surroundings2.getRectangle().height);
        //  g.drawRect(surroundings3.getRectangle().x, surroundings3.getRectangle().y, surroundings3.getRectangle().width, surroundings3.getRectangle().height);
        //  g.drawRect(surroundings4.getRectangle().x, surroundings4.getRectangle().y, surroundings4.getRectangle().width, surroundings4.getRectangle().height);
        //  g.drawRect(surroundings5.getRectangle().x, surroundings5.getRectangle().y, surroundings5.getRectangle().width, surroundings5.getRectangle().height);
        g.drawRect(anchor.getRectangle().x, anchor.getRectangle().y, anchor.getRectangle().width, anchor.getRectangle().height);
    }

    public void drawPlatforms(Graphics g){
        levelManager = new LevelManager();
        levelManager.update();
        levelManager.draw(g);
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

    public void updateEntityCollisions(Player x, MonitoringVillian y, Anchor a){
        Player myPlayer = x;
        MonitoringVillian myvillain = y;
        Anchor myAnchor = a;

        
        if (myPlayer.getHitBox().intersects(myvillain.getHitBox()) || myPlayer.getHitBox().getMaxY() == myvillain.getHitBox().getY() && myPlayer.getHitBox().getMaxX() >= myvillain.getHitBox().getX() && myPlayer.getHitBox().getX() <= myvillain.getHitBox().getMaxX() || myvillain.getHitBox().getMaxY() == myPlayer.getHitBox().getY() && myvillain.getHitBox().getMaxX() >= myPlayer.getHitBox().getX() && myvillain.getHitBox().getX() <= myPlayer.getHitBox().getMaxX()) {
            player.playerDies();
            Gamestate.state = Gamestate.GAMEOVER;
        }

        if(myPlayer.getHitBox().y >= 700){
            
            player.playerDies();
            Gamestate.state = Gamestate.GAMEOVER;
        }

        if (myPlayer.getHitBox().intersects(myAnchor.getHitBox()) || myAnchor.getHitBox().getMaxY() == myAnchor.getHitBox().getY() && myPlayer.getHitBox().getMaxX() >= myAnchor.getHitBox().getX() && myPlayer.getHitBox().getX() <= myAnchor.getHitBox().getMaxX() || myAnchor.getHitBox().getMaxY() == myPlayer.getHitBox().getY() && myAnchor.getHitBox().getMaxX() >= myPlayer.getHitBox().getX() && myAnchor.getHitBox().getX() <= myPlayer.getHitBox().getMaxX()) {
            player.playerDies();
            Gamestate.state = Gamestate.GAMEWIN;
        }

    }




    public void implementSurroundings(){
        Surroundings surroundings1 = new Surroundings(collisionWatcher, 0 , 400 , 240, 32);
        Surroundings surroundings2 = new Surroundings(collisionWatcher, 240, 500,  160, 32);
        Surroundings surroundings3 = new Surroundings(collisionWatcher, 400, 450,  32, 32);
        Surroundings surroundings4 = new Surroundings(collisionWatcher, 433, 400,  225, 32);
        Surroundings surroundings5 = new Surroundings(collisionWatcher, 710, 350,  47, 32);
        Surroundings surroundings6 = new Surroundings(collisionWatcher, 600, 300,  32, 32); 
        Surroundings surroundings7 = new Surroundings(collisionWatcher, 530, 280,  32, 32);
        Surroundings surroundings8 = new Surroundings(collisionWatcher, 420, 230,  60, 32);//creating surroundings and passing collisions
    }

    

    
    public void addEntity(Entity e){
        entitiesList.add(e);
    }

    public int getNumberOfEntities(){
        return entitiesList.size();
    }

    
}

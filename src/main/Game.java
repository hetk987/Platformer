package main;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import Entities.*;
import Levels.LevelManager;
import inputs.*;
import Physics.*;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public final static int TILE_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILE_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game(){

        gamePanel = new GamePanel();
        
        gamePanel.setPanelSize();
       
        Collisions collisionWatcher = new Collisions();
        Surroundings surroundings1 = new Surroundings(collisionWatcher, 0 , 400 , 240, 32);
        Surroundings surroundings2 = new Surroundings(collisionWatcher, 240, 500,  160, 32);
        Surroundings surroundings3 = new Surroundings(collisionWatcher, 400, 450,  32, 32);
        Surroundings surroundings4 = new Surroundings(collisionWatcher, 433, 400,  225, 32);
        Surroundings surroundings5 = new Surroundings(collisionWatcher, 710, 350,  47, 32);
        Surroundings surroundings6 = new Surroundings(collisionWatcher, 600, 300,  32, 32); 
        Surroundings surroundings7 = new Surroundings(collisionWatcher, 530, 280,  32, 32);
        Surroundings surroundings8 = new Surroundings(collisionWatcher, 420, 230,  60, 32);//creating surroundings and passing collisions
         Player mc = new Player(0, 300, collisionWatcher); //creating a player passing x and y positions and collisions instance
       // Player obstaclePlayer = new Player(100, 400, collisionWatcher);
       // MonitoringVillian a = new MonitoringVillian(100, 300, 800, 100, collisionWatcher);
        //MonitoringVillian b = new MonitoringVillian(100, 600, 600, 200, collisionWatcher);
        gamePanel.setBackGround(surroundings1, surroundings2, surroundings3, surroundings4, surroundings5, surroundings6, surroundings7,surroundings8);
        gamePanel.addEntity(mc);
        //gamePanel.addEntity(a);
        //gamePanel.addEntity(b);
       // gamePanel.addEntity(obstaclePlayer);
        gamePanel.addKeyListener(new KeyBoardInputs(mc)); //creating keyboardinputs instance and passing it the main player
        
        gameWindow = new GameWindow(gamePanel);
        
        gamePanel.setFocusable(true);
        gamePanel.requestFocus(true);
        StartGameLoop();
    }

    @Override
    public void run() {
        //time per frame in nanoseconds
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        double deltaU = 0;
        double deltaF = 0;
        long lastCheck = System.currentTimeMillis();


        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU>1)
            {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF>1)
            {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
               // System.out.println("FPS: " + frames + "UPS:" + updates);
                frames = 0;
                updates = 0;  
            }
        }
    }

    private void update() {
        gamePanel.updateGame();
    }

    private void StartGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
}

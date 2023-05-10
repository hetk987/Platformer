package main;
import Entities.*;
import inputs.*;
import Physics.*;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    public Game(){

        gamePanel = new GamePanel();
        Collisions collisionWatcher = new Collisions();
        Surroundings surroundingsTest = new Surroundings(collisionWatcher); //creating surroundings and passing collisions
        Player mc = new Player(500, 400, collisionWatcher); //creating a player passing x and y positions and collisions instance
       // Player obstaclePlayer = new Player(100, 400, collisionWatcher);
       // MonitoringVillian a = new MonitoringVillian(100, 300, 800, 100, collisionWatcher);
        //MonitoringVillian b = new MonitoringVillian(100, 600, 600, 200, collisionWatcher);
        gamePanel.setBackGround(surroundingsTest);
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

    /*@Override
    public void run() {
        //time per frame in nanoseconds
        double timePerFrame = 1000000000.0 / FPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		while (true) {
			now = System.nanoTime();
			if (now - lastFrame >= timePerFrame) {
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
        }
    }*/

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
                System.out.println("FPS: " + frames + "UPS:" + updates);
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

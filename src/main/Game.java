package main;

import java.awt.Graphics;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Playing playing;
    private Menu menu;

    public final static int TILE_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILE_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game(){

        gamePanel = new GamePanel(this);
        playing = new Playing(this);
        menu = new Menu(this);

        gamePanel.setPanelSize(); 
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
                deltaU--;
            }
            if (deltaF>1)
            {
                gamePanel.repaint();
                deltaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();  
            }
        }
    }

    private void update() {

        switch(Gamestate.state){
            case MENU:
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }

    public void render(Graphics g){
        switch(Gamestate.state){
            case MENU:
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
            
        }
    }

    private void StartGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public GamePanel getGamePanel(){
        return gamePanel;
    }

    public Playing getPlaying(){
        return playing;
    }

    public Menu getMenu(){
        return menu;
    }

    
}

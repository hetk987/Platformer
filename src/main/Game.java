package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gamestates.GameOver;
import gamestates.GameWin;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import utilz.LoadSave;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public Playing playing;
    private Menu menu;
    private GameOver gameOver;
    private GameWin gameWin;
    public MenuFrame menuFrame;
    public Settings settings;


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
        gameOver = new GameOver(this);
        gameWin = new GameWin(this);

        ((GamePanel)gamePanel).setPanelSize(); 
        gameWindow = new GameWindow((GamePanel) gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus(true);
        
        menuFrame = new MenuFrame();
        menuFrame.setVisibility(true);
       
        ActionListener press = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==menuFrame.playBtn){
                    Gamestate.state = Gamestate.PLAYING;
                    menuFrame.setVisibility(false);
                    gameWindow.setVisibility(true);
                    
                    
                }
            }
        };


        menuFrame.playBtn.addActionListener(press);


        ActionListener back = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == gamePanel.backbtn){
                   
                    gameWindow.setVisibility(false);
                    menuFrame.setVisibility(true);
                }
            }


        };


        gamePanel.backbtn.addActionListener(back);

        ActionListener gotoSettings = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == menuFrame.settingsBtn){
                   
                    settings.setVisibility(true);
                    menuFrame.setVisibility(false);
                }
            }
        };

        menuFrame.settingsBtn.addActionListener(gotoSettings);



        settings = new Settings(this);
        ActionListener back2 = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == settings.btn){
                   
                    settings.setVisibility(false);
                    menuFrame.setVisibility(true);
                    
                }
            }
        };

        settings.btn.addActionListener(back2);


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
            menu.update();
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
            case GAMEOVER:
            
                gameOver.draw(g);
                break;
            case GAMEWIN:

            gameWin.draw(g);
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
        return (GamePanel) gamePanel;
    }

    public Playing getPlaying(){
        return playing;
    }

    public Menu getMenu(){
        return menu;
    }

    

    
}

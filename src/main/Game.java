package main;

public class Game {
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus(true);
        
    }
    
}

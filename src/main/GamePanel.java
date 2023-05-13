package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;
import inputs.KeyBoardInputs;

public class GamePanel extends JPanel{

    
    private Game game;

    public GamePanel(Game game){
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this)); 
    }

    void setPanelSize()
    {
        Dimension size = new Dimension(1500, 900);
        setPreferredSize(size);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }



    public Game getGame() {
        return game;
    }



    public void setGame(Game game) {
        this.game = game;
    }
    
}

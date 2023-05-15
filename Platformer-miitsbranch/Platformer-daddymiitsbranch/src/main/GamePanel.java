package main;

import javax.swing.JButton;
import javax.swing.JPanel;

import gamestates.Gamestate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;
import inputs.KeyBoardInputs;

public class GamePanel extends JPanel{

    
    public Game game;
    public JButton backbtn;

    public GamePanel(Game game){
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        
        backbtn = new JButton("Back");
        add(backbtn);
        
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
    
    public JButton getBackButton(){
        return backbtn;
    }
}

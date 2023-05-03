package main;

import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import java.awt.Graphics;


public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private int xDelta, yDelta = 100;

    public GamePanel(){


        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    //Changes the position when WASD or Arrows are used
    public void changeXDelta(int num){
        this.xDelta+=num;
        repaint();
    }

    public void changeYDelta(int num){
        this.yDelta+=num;
        repaint();
    }

    //Sets the position on the object to the position of the mouse
    public void setXPos(int num){
        this.xDelta = num;
        repaint();
    }
    public void setYPos(int num){
        this.yDelta = num;
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.fillRect(xDelta, yDelta, 200, 50);
    }
    
}

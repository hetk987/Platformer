package main;

import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 0.03f, yDir = 0.03f;
    private Color color = new Color(132, 231, 61);
    private Random random;

    public GamePanel(){

        random = new Random();
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
        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50);
        repaint();
    }

    public void updateRectangle(){
        xDelta += xDir;
        if(xDelta > 200|| xDelta<0){
            xDir *= -1;
            color = getNewColor();
        }
        yDelta += yDir;
        if(yDelta > 350 || yDelta<0){
            yDir *= -1;
            color = getNewColor();
        }
    }

    public Color getNewColor(){
        int red = random.nextInt(255); 
        int green = random.nextInt(255); 
        int blue = random.nextInt(255); 

        return new Color(red, green, blue);

    }

    
}

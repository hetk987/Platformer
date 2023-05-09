package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[] animation;
    private int animationIndex = 0;
    private int animationTick=0;
    private int animationSpeed = 20;

    public GamePanel(){

        importImage();
        loadAnimation();


        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }
    

    private void loadAnimation() {
        animation = new BufferedImage[4];
        for(int i = 0; i<animation.length; i++){
            animation[i] = img.getSubimage(i*100, 0, 100, 100);
        }

    }


    private void importImage() {
        
        
        try {
            img = ImageIO.read(new FileInputStream("res/diverSpriteAtlas.png"));
        } catch (IOException e) {
            System.out.println("Reading Image Error");
            e.printStackTrace();
        }
        
    }


    private void setPanelSize() {
        setPreferredSize(new Dimension(1500, 900));
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(animation[animationIndex], (int) xDelta, (int) yDelta, 200, 200, getFocusCycleRootAncestor());
    }


    private void updateAnimationTick() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationIndex++;
            animationTick = 0;
            if(animationIndex>=animation.length)
            {
                animationIndex = 0;
            }

        }
           
    }


    public void updateGame() {
        updateAnimationTick();

    }


    
}

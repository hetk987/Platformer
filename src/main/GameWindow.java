package main;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameWindow {
    private JFrame jframe;
    public JPanel gamePanel;
    public JPanel menuPanel;

    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();

          
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(800, 800);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(false);
    }


    public void setVisibility(Boolean tf)
    {
        jframe.setVisible(tf);
    }



}

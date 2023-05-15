package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow {
    private JFrame jframe;
    public JPanel gamePanel;
    public JPanel menuPanel;

    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();


        jframe.setSize(800, 800);  
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(false);
    }


    public void setVisibility(Boolean tf)
    {
        jframe.setVisible(tf);
    }

}

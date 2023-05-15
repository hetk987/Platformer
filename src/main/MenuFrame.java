package main;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MenuFrame extends JFrame {
    private JFrame menu;
    private JPanel panel;
    public JButton playBtn;
    public JButton settings;


    public MenuFrame(){
        menu = new JFrame("Main Menu");
        menu.setSize(800, 800);  
        menu.setVisible(false);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        menu.add(panel);


        playBtn = new JButton("Play");
        settings = new JButton("Settings");
        //playBtn.setIcon(new ImageIcon("playbtn.png"));
        panel.add(playBtn);
        panel.add(settings);
    }








    public void setVisibility(Boolean tf)
    {
        menu.setVisible(tf);
    }


   






   
}

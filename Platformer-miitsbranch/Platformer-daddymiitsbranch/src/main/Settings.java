package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import gamestates.Playing;

public class Settings extends JFrame{

    private JSlider enemySpd;
    private JPanel panel;
    private Playing playing;
    public JButton btn;
    private Game game;

    public Settings(Game game){

        this.game = game;

        this.setTitle("Settings");
        this.setSize(800, 800);  
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        this.add(panel);

        enemySpd = new JSlider(5, 10, 10);
        enemySpd.setMajorTickSpacing(1);
        enemySpd.setPaintTicks(true);
        enemySpd.setPaintLabels(true);
        panel.add(enemySpd);

        btn = new JButton("Back");

        panel.add(btn);

        setEnemySpeed();

    }

    public void setEnemySpeed(){
        this.game.playing.villain.setSpeed(enemySpd.getValue());
    }

    public void setVisibility(Boolean tf)
    {
        this.setVisible(tf);
    }

}

package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gamestates.Playing;

public class Settings extends JFrame{

    private JSlider enemySpd;
    private JPanel panel;
    public JButton btn;
    private Game game;
    private int spdChange;
    
    private JRadioButton less;
    private JRadioButton high;
    private JRadioButton defaultVal;

    private ButtonGroup bg;

    public Settings(Game game){

        spdChange = 0;
        this.game = game;

        this.setTitle("Settings");
        this.setSize(800, 800);  
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        this.add(panel);

        enemySpd = new JSlider(1, 5, 1);
        enemySpd.setMajorTickSpacing(1);
        enemySpd.setPaintTicks(true);
        enemySpd.setPaintLabels(true);

        ChangeListener val = new ChangeListener(){

            public void stateChanged(ChangeEvent e) {
                enemySpd = (JSlider)e.getSource();
                if(!enemySpd.getValueIsAdjusting()){
                    spdChange = (int)enemySpd.getValue();
                    setEnemySpeed(); 
                }
            }
            
        };

        enemySpd.addChangeListener(val);

        panel.add(enemySpd);

        btn = new JButton("Back");

        panel.add(btn);

        bg = new ButtonGroup();
        less = new JRadioButton("Low Gravity");
        high = new JRadioButton("High Gravity");
        defaultVal = new JRadioButton("Default");
        ActionListener clicked = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==less){
                    setGravity(-20);
                }
                else if(e.getSource()==high){
                    setGravity(-11);
                }
                else{
                    setGravity(-14);
                }
            }
        };

        less.addActionListener(clicked);
        high.addActionListener(clicked);
        defaultVal.addActionListener(clicked);

        bg.add(less);
        bg.add(high);
        bg.add(defaultVal);

        panel.add(less);
        panel.add(high);
        panel.add(defaultVal);

    }

    public void setEnemySpeed(){
        this.game.playing.villain.setSpeed(spdChange);
    }

    public void setGravity(int val){
        this.game.playing.player.gravVal = val;
    }

    public void setVisibility(Boolean tf)
    {
        this.setVisible(tf);
    }

    
}

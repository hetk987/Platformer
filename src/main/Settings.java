package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicButtonUI;

import gamestates.Playing;

public class Settings extends JFrame{

    private JSlider enemySpd;
    DefaultListModel<String> speeds = new DefaultListModel<>(); 
    private JPanel mainPanel;
    private JPanel backPanel;
    private JPanel settingsPanel;
    public JButton btn;
    private Game game;
    private int villainSpdChange;
    
    private JRadioButton less;
    private JRadioButton high;
    private JRadioButton defaultVal;

    private ButtonGroup myGroup;

    public Settings(Game game){

        
        this.game = game;

        this.setTitle("Settings");
        this.setSize(600, 300);  
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        mainPanel = new JPanel(new FlowLayout());
        backPanel = new JPanel();
        settingsPanel = new JPanel(new GridLayout(3, 2));

        
        ImageIcon imageIcon2 = new ImageIcon("res/backButton/back1.png");
        btn = new JButton(imageIcon2);
        btn.setUI(new ShapedButtonUI());
        btn.setPreferredSize(new Dimension(imageIcon2.getIconWidth(), imageIcon2.getIconHeight()));
        btn.setRolloverIcon(new ImageIcon("res/backButton/back2.png"));
        btn.setOpaque(false);
        backPanel.add(btn);
        
        

        JLabel enemySpeedLabel = new JLabel("Change Speed of Shark:");
        JLabel gravityLabel = new JLabel("Change Gravity strength:");
        JLabel playerSpeedLabel = new JLabel("Change Player Speed");
        enemySpeedLabel.setOpaque(false);
        gravityLabel.setOpaque(false);
        playerSpeedLabel.setOpaque(false);


        

        enemySpd = new JSlider(1, 5, 1);
        enemySpd.setMajorTickSpacing(1);
        enemySpd.setPaintTicks(true);
        enemySpd.setPaintLabels(true);
        enemySpd.setOpaque(false);
        ChangeListener val = new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                enemySpd = (JSlider)e.getSource();
                if(!enemySpd.getValueIsAdjusting()){
                    villainSpdChange = (int)enemySpd.getValue();
                    setEnemySpeed(); 
                }
            }
            
        };
        enemySpd.addChangeListener(val);
        settingsPanel.add(enemySpeedLabel);
        settingsPanel.add(enemySpd);

         

        myGroup = new ButtonGroup();
        less = new JRadioButton("Low Gravity");
        high = new JRadioButton("High Gravity");
        defaultVal = new JRadioButton("Normal", true);
        less.setOpaque(false);
        high.setOpaque(false);
        defaultVal.setOpaque(false);

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

        JPanel groupPanel = new JPanel();
        groupPanel.add(less);
        groupPanel.add(defaultVal);
        groupPanel.add(high);
        myGroup.add(less);
        myGroup.add(defaultVal);
        myGroup.add(high);
        settingsPanel.add(gravityLabel);
        settingsPanel.add(groupPanel);

        

        
        speeds.addElement("Normal Speed");
        speeds.addElement("Fast Speed");
        speeds.addElement("INHUMAN Speed");
        JList<String> playerSpeed = new JList<String>(speeds);
        playerSpeed.setOpaque(false);

        playerSpeed.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    // Code to handle the selection change
                    String selectedValue = playerSpeed.getSelectedValue();
                    // Do something with the selected value
                    if(selectedValue.equals("Normal Speed"))
                        setPlayerSpeed(1);
                    else if(selectedValue.equals("Fast Speed"))
                        setPlayerSpeed(2);
                    else if(selectedValue.equals("INHUMAN Speed"))
                        setPlayerSpeed(5);
                }
            }
        });

        settingsPanel.add(playerSpeedLabel);
        settingsPanel.add(playerSpeed);



        mainPanel.add(backPanel);
        mainPanel.add(settingsPanel);
        this.add(mainPanel);
        this.setLocationRelativeTo(null);

    }

    public void setEnemySpeed(){
        this.game.playing.villain.setSpeed(villainSpdChange);
    }

    public void setPlayerSpeed(int val){
        this.game.playing.player.playerSpeed = val;
    }

    public void setGravity(int val){
        this.game.playing.player.gravVal = val;
    }

    public void setVisibility(Boolean tf)
    {
        this.setVisible(tf);
    }

    
}


// Custom ButtonUI implementation to create shaped buttons
class ShapedButtonUI extends BasicButtonUI {
    @Override
    protected void installDefaults(AbstractButton button) {
        super.installDefaults(button);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();
        Graphics2D g2 = (Graphics2D) g.create();

        if (model.isPressed()) {
            g2.setColor(button.getBackground().darker());
        } else {
            g2.setColor(button.getBackground());
        }

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape shape = new RoundRectangle2D.Double(0, 0, button.getWidth(), button.getHeight(), 20, 20);
        g2.fill(shape);

        g2.dispose();
        super.paint(g, c);
    }
}


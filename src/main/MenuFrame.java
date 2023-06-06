package main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;


public class MenuFrame extends JFrame {
    public JButton playBtn;
    public JButton settingsBtn;
    public JTextField nameField;


    public MenuFrame(){
       // this.setContentPane(new ImageIcon());
        this.setLayout(new BorderLayout(10, 10));
        this.setSize(700, 600);  
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.VERTICAL;

        
        JPanel buttonPanel = new JPanel(new GridLayout(2,1,0,0));
        buttonPanel.setBorder(new EmptyBorder(0, 120, 0, 120));

        

        ImageIcon imageIcon = new ImageIcon("res/PlayButton/play1.png");
        playBtn = new JButton(imageIcon);
        playBtn.setUI(new ShapedButtonUI());
        playBtn.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        playBtn.setRolloverIcon(new ImageIcon("res/PlayButton/play2.png"));
        playBtn.setOpaque(false);


        settingsBtn = new JButton("Settings");
        ImageIcon imageIcon2 = new ImageIcon("res/SettingButton/setting1.png");
        settingsBtn = new JButton(imageIcon2);
        settingsBtn.setUI(new ShapedButtonUI());
        settingsBtn.setPreferredSize(new Dimension(imageIcon2.getIconWidth(), imageIcon2.getIconHeight()));
        settingsBtn.setRolloverIcon(new ImageIcon("res/SettingButton/setting2.png"));
        settingsBtn.setOpaque(false);

        
        JPanel textPanel = new JPanel(new GridLayout(2,1,0,0));
        textPanel.setBorder(new EmptyBorder(0, 150, 5, 150));
        nameField = new JTextField("Enter player name:", 15);
        nameField.setFont(new Font("Monospaced", Font.PLAIN, 25));
        nameField.setOpaque(false);

    
        // Add the buttons to the panel 
        JLabel label = new JLabel();
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setText(" Main Menu");
        label.setFont(new Font("Monospaced", Font.BOLD, 60));
        label.setForeground(new Color(255, 215, 0));
        label.setOpaque(false);


        textPanel.add(label);
        textPanel.add(nameField);
        buttonPanel.add(playBtn);
        buttonPanel.add(settingsBtn);


        // textPanel.setBackground(new Color(137, 207, 240));
        // buttonPanel.setBackground(new Color(137, 207, 240));
        this.add(textPanel, BorderLayout.NORTH);
        this.add(buttonPanel);
        


        

        this.setLocationRelativeTo(null);
    }


    public void setVisibility(Boolean tf)
    {
        this.setVisible(tf);
    }

    public String getName(){
        return nameField.getText();
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

            Shape shape = new RoundRectangle2D.Double(0, 0, button.getWidth(), button.getHeight(), 30, 30);
            g2.fill(shape);

            g2.dispose();
            super.paint(g, c);
        }
    }









    
package main;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;
import inputs.KeyBoardInputs;

public class GamePanel extends JPanel{

    
    public Game game;
    public JButton backbtn;

    public GamePanel(Game game){
        this.setBackground(new Color(137, 207, 240));
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        
        
        //backbtn = new JButton("Back");
        ImageIcon imageIcon2 = new ImageIcon("res/backButton/back1.png");
        backbtn = new JButton(imageIcon2);
        backbtn.setUI(new ShapedButtonUI());
        backbtn.setPreferredSize(new Dimension(imageIcon2.getIconWidth(), imageIcon2.getIconHeight()));
        backbtn.setRolloverIcon(new ImageIcon("res/backButton/back2.png"));
        add(backbtn);
        
    }

    void setPanelSize()
    {
        Dimension size = new Dimension(1500, 900);
        setPreferredSize(size);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    

    

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.drawImage(LoadSave.getSpriteAtlas(LoadSave.BACKGROUND), 0, 0, null);
        game.render(g);
    }

    

    public Game getGame() {
        return game;
    }



    public void setGame(Game game) {
        this.game = game;
    }
    
    public JButton getBackButton(){
        return backbtn;
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

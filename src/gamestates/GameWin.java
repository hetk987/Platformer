package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import utilz.LoadSave;

public class GameWin extends State implements Statemethods{

    public GameWin(Game game) {
        super(game);
    }

    @Override    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(34, 139, 34));
        g.setFont(new Font("Monospaced", Font.BOLD, 30));
        g.drawString("Congrats,  " + game.menuFrame.nameField.getText() + "! Go again?", 100,200);
        g.drawImage(LoadSave.getSpriteAtlas(LoadSave.WINNING_SCREEN), 100, 300, 500,350, null);
        
    }

    


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    public void keyPressed(KeyEvent e) {
      
    }

    public void keyReleased(KeyEvent e) {
    }
    
}
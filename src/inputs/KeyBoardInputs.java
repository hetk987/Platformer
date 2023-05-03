package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyBoardInputs implements KeyListener{

    private GamePanel gamePanel;
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
        case KeyEvent.VK_W: case KeyEvent.VK_UP:
            gamePanel.changeYDelta(-5);
            break;
        case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
            gamePanel.changeXDelta(-5);
            break;
        case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
            gamePanel.changeYDelta(5);
            break;
        case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
            gamePanel.changeXDelta(5);
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    
}

package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utilz.LoadSave;
import main.Game;
import main.GamePanel;

public class LevelManager {
    
    private GamePanel game;
    private BufferedImage[] levelSprite;

    public LevelManager(GamePanel game) {
        this.game = game;
        //levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
    }



    private void importOutsideSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[6];
        for(int j = 0; j < 2; j++){
            for(int i = 0; i< 3; i++){
                int index = j*3+i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }



    public void draw(Graphics g){
        g.drawImage(levelSprite[2], 0, 0, null);
    }

    public void update(){
        
    }
}

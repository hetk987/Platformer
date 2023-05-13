package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import main.Game;
import main.GamePanel;
import utilz.LoadSave;

public class LevelManager {
    
    private GamePanel game;
    private Level levelOne;

    public LevelManager(GamePanel game) {
        this.game = game;
        levelOne = new Level(LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS));
    }



    public void draw(Graphics g){
        
       levelOne.drawLevel(g);
    }

    public void update(){
        
    }
}

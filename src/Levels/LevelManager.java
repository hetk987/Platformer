package Levels;

import java.awt.Graphics;

import utilz.LoadSave;

public class LevelManager {
    
    private Level levelOne;

    public LevelManager() {
        levelOne = new Level(LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS));
    }



    public void draw(Graphics g){
        
       levelOne.drawLevel(g);
    }

    public void update(){
        
    }
}

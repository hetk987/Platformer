package utilz;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;


public class LoadSave {
    
    
public static final String PLAYER_ATLAS = "diverSpriteAtlas.png";
public static final String LEVEL_ATLAS = "platformAtlas.png";
public static final String ANCHOR = "anchor.png";
public static final String BACKGROUND = "background.png";


    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new FileInputStream("res/" + fileName));
        } catch (IOException e) {
            System.out.println("Reading Image Error");
            e.printStackTrace();
        }

        return img;
    }


    // public static int[][] GetLevelData(){
    //     int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
    //     BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

    //     for(int j = 0; j < img.getHeight(); j++){
    //         for(int i = 0; i < img.getWidth(); i++){
    //             Color color = new Color(img.getRGB(i, j));
    //             int value = color.getRed();
    //             if(value >= 6)
    //                 value = 0;
    //             lvlData[i][j] = value;
    //         }
    //     }
    //     return lvlData;



    // }
}

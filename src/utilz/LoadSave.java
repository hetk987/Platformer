package utilz;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadSave {
    
    
public static final String PLAYER_ATLAS = "diverSpriteAtlas.png";
public static final String LEVEL_ATLAS = "platformAtlas.png";
public static final String ANCHOR = "anchor.png";
public static final String BACKGROUND = "background.png";
public static final String SHARK = "sharkAnimation.png";


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


    
}

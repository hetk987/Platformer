package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public class Level {


    private BufferedImage[] img;
    private BufferedImage anchor;


    public Level(BufferedImage levelImage){
        anchor = LoadSave.getSpriteAtlas(LoadSave.ANCHOR);
        makeArray(levelImage);
    }

    public void drawLevel(Graphics g)
    {
        for(int i =0; i<6;i++){
            g.drawImage(img[i], i*32, 0, null);
        }



        int tileIndex=0;
        for(int i = 0; i < 5; i++){
            g.drawImage(img[0], tileIndex*32, 400, null);
            // for(int j = 0; j < 11; j++){
            //     g.drawImage(img[5], tileIndex*32, 432+j*32,null);
            // }
            tileIndex +=1;
        }
        g.drawImage(img[3], tileIndex*32,400, null);
        // for(int j = 0; j < 11; j++){
        //     g.drawImage(img[5], tileIndex*32, 432+j*32,null);
        // }
        tileIndex +=1;
        




        for(int i = 0; i < 5; i++){
            g.drawImage(img[0], tileIndex*32, 500, null);
            // for(int j = 0; j < 11; j++){
            //     g.drawImage(img[5], tileIndex*32, 532+j*32,null);
            // }
            tileIndex +=1;
        }
        g.drawImage(img[1], tileIndex*32, 450, null);
        // for(int j = 0; j < 11; j++){
        //     g.drawImage(img[5], tileIndex*32, 482+j*32,null);
        // }
        tileIndex +=1;






        for(int i = 0; i < 6; i++){
            g.drawImage(img[0], tileIndex*32, 400, null);
            // for(int j = 0; j < 11; j++){
            //     g.drawImage(img[5], tileIndex*32, 432+j*32,null);
            // }
            tileIndex +=1;
        }
        g.drawImage(img[3], tileIndex*32,400, null);
        // for(int j = 0; j < 11; j++){
        //         g.drawImage(img[5], tileIndex*32, 432+j*32,null);
        //     }
            tileIndex +=1;

            




        g.drawImage(img[4], 675, 350, null);
        g.drawImage(img[4], 660, 350, null);

        g.drawImage(img[4], 550, 300, null);
        g.drawImage(img[4], 480, 280, null);
        
        g.drawImage(img[4], 400, 230, null);
        g.drawImage(img[4], 370, 230, null);

        g.drawImage(anchor, 350,90,null);

        


    }


    


    public void makeArray(BufferedImage atlas){
        img = new BufferedImage[6];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                int index = i*3+j;
                img[index]= atlas.getSubimage(j*32, i*32, 32, 32);
            }
        }
    }


   




    

}

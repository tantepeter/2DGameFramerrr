package KillerGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

/**
 * Created by rico on 13.12.2014.
 */
public class ImageLoader {

    Vector<BufferedImage> images;

    public ImageLoader(){
        images = new Vector<BufferedImage>();
    }

    public void  loadImage(File file){
        BufferedImage newImg = null;

        try {
            newImg = ImageIO.read(file);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        images.add(newImg);
    }

    public BufferedImage getImage(int index){
        return images.get(index);
    }
}

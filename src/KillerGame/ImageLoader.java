package KillerGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Vector;

/**
 * Created by rico on 13.12.2014.
 */
public class ImageLoader {

    Vector<BufferedImage> images;

    public ImageLoader(){
        images = new Vector<BufferedImage>();
    }

    /*
     * use LoadImage(URL url) and loading from resource.
     */
    @Deprecated
    public void  loadImage(File file){
        BufferedImage newImg = null;

        try {
            newImg = ImageIO.read(file);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        images.add(newImg);
    }

    public void  loadImage(URL url){
        BufferedImage newImg = null;

        try {
            newImg = ImageIO.read(url);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        images.add(newImg);
    }


    public BufferedImage getImage(int index){
        return images.get(index);
    }
}

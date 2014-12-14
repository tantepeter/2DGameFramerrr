package KillerGame.Entities;

import com.sun.deploy.ui.ImageLoader;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by rico on 11.12.2014.
 */
public class Sprite {
    private static final int DEFAULT_SIZE = 12;

    private static final int xstep = 5;
    private static final int ystep = 5;

    private ImageLoader imgLoader;
    private String imgName;
    private BufferedImage img;
    private int panelWidth, panelHeight;

    protected int x, y, w, h;
    protected int dx, dy;   // move amount for each update

    boolean isLooping;

    public Sprite(int x, int y, int w, int h, ImageLoader imageLoader, String name) {
        this.x = x;
        this.y = y;
        panelWidth = w;
        panelHeight = h;
        this.imgLoader = imgLoader;
        setImg(name);
    }

    public void setImg(String name){
        imgName = name;

        try {
            img = imageToBufferedImage(imgLoader.loadImage(imgName));
            if (img == null){
                w = DEFAULT_SIZE;
                h = DEFAULT_SIZE;
            } else {
                w = img.getWidth();
                h = img.getHeight();
            }

        } catch (IOException ex) {
            System.out.println("Error while loading img" + ex);
        }

        isLooping = false;
    }

    private void generateImages(){

    }

    private BufferedImage imageToBufferedImage(Image img){
        BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bimg.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bimg;
    }

    public void draw(Graphics2D g2d){
        if (img != null){
            g2d.drawImage(img, x, y, null);
        }
    }

}

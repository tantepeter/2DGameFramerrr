package KillerGame.Entities;

import KillerGame.ImageLoader;
import org.w3c.dom.css.RGBColor;

import java.awt.*;

/**
 * Created by rico on 13.12.2014.
 */
public class TileMap {

    int panelWidth;
    int panelHeight;
    int size;
    int width, height;
    static final int sizeX = 8;
    static final int sizeY = 8;
    int tiles[][] = {{0,0,0,1,1,0,0,0},
                    {3,2,2,2,2,2,2,3},
                    {2,2,1,0,0,1,2,2},
                    {2,1,1,0,0,1,1,2},
                    {2,2,1,0,0,1,2,2},
                    {3,2,2,2,2,2,2,3},
                    {0,0,2,1,1,2,0,0},
                    {0,0,0,2,2,0,0,0}};
    int x,y;

    public TileMap(int panelWidth, int panelHeight, int size, int x, int y) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.size = size;
        this.width = 64;
        this.height = 64;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d, ImageLoader imgLoader){
        Color color = Color.black;

        Color color1 = new Color(15, 77, 52);
        Color color2 = new Color(131, 224, 197);

        /*
        *   i = y axis
        *   j = x axis
         */
        for (int i = 0; i < sizeX; i++){

            for (int j = sizeY-1; j >= 0; j--) {
                if (tiles[j][i] == 1){
                    g2d.drawImage(imgLoader.getImage(0),twoDToIso(j, i).x + x, twoDToIso(j, i).y + y, null);
                }

                if (tiles[j][i] == 0){

                }

                if (tiles[j][i] == 2){
                    g2d.drawImage(imgLoader.getImage(1),twoDToIso(j, i).x + x, twoDToIso(j, i).y + y, null);
                }

                if (tiles[j][i] == 3){
                    int offset = 97;
                    g2d.drawImage(imgLoader.getImage(2),twoDToIso(j, i).x + x, twoDToIso(j, i).y + y - offset, null);
                }


                g2d.setColor(color);
                //g2d.fillRect(twoDToIso(j * sizex, i * sizey).x + x, twoDToIso(j * sizex, i * sizey).y + y, sizex, sizey);

                //g2d.rotate(Math.toRadians(45));
                //g2d.drawRect(twoDToIso(j, i).x + x, twoDToIso(j, i).y + y, width, height);

            }
        }

    }

    private Point twoDToIso(int j, int i) {
        Point returnPoint = new Point();

        //double i = 0.5D * (y/size + x/size);
        //double j = 0.5D * (y/size - x/size);

        //returnPoint.x = (int)((i*size) - (j*size));
        //returnPoint.y = (int)((i*size) +(j*size));


        //returnPoint.x = (cartx - carty);
        //returnPoint.y = (cartx + carty);

        returnPoint.x = (j * width) + (i * width);
        returnPoint.y = (i * (height+10)/2) - (j * (height+10) /2);


        //returnPoint.x = (x - y);
        //returnPoint.y = (y + x);
        //returnPoint.x = (2)

        return returnPoint;
    }


}

package KillerGame.Entities;

import java.awt.*;

/**
 * Created by rico on 04.12.14.
 */
public class Dot {

    private int x, y, size, panelWidth, panelHeight;
    Color color;
    private int speedx, speedy;
    private static int maxSpeed = 4;
    private static int maxSize = 60;

    public Dot(int panelWidth, int panelHeight) {
        Double randSize = Math.random() * maxSize;
        size = randSize.intValue() + 1;

        Double randx = Math.random() * (panelWidth - size);
        Double randy = Math.random() * (panelHeight - size);

        if (randx.intValue() == 0)
            randx = 1D;

        if (randy.intValue() == 0)
            randy = 1D;


        x = randx.intValue();
        y = randy.intValue();

        changeColor();

        Double randSpeed = Math.random() * maxSpeed;

        if (randSpeed.intValue() == 0)
            randSpeed = 1D;

        if ((randx.intValue() % 2) == 0)
            randSpeed = -randSpeed;

        speedx = randSpeed.intValue();
        speedy = randSpeed.intValue();
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    public void update() {

        if (x >= (panelWidth - size)) {
            speedx = -speedx;
            changeColor();
        }

        if (x <= 0) {
            speedx = -speedx;
            changeColor();
        }

        if (y >= (panelHeight - size)) {
            speedy = -speedy;
            changeColor();
        }

        if (y <= 0) {
            speedy = -speedy;
            changeColor();
        }

        x = x + speedx;
        y = y + speedy;
    }

    private void changeColor() {
        Double r, g, b;

        r = Math.random() * 255;
        g = Math.random() * 255;
        b = Math.random() * 255;

        this.color = new Color(r.intValue(), g.intValue(), b.intValue());

    }

}

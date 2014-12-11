package KillerGame;

import java.awt.*;

/**
 * Created by rico on 08.12.14.
 */
public class StatCounter {

    private long gameStartTime;
    private long frameCount;


    public StatCounter() {
        gameStartTime = System.nanoTime();
        frameCount = 0;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public long getGameTime() {
        return System.nanoTime() - gameStartTime;
    }

    public void start() {
        this.gameStartTime = System.nanoTime();
    }

    public long getFrameCount() {
        return frameCount;
    }

    public void reportFrame() {
        this.frameCount++;
    }

    public double getAvgFps() {
        return (double) frameCount / ((System.nanoTime() - gameStartTime) / 1000000000L);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.cyan);
        Font myFont = new Font("Arial", Font.ITALIC | Font.PLAIN, 18);

        g2d.setFont(myFont);
        g2d.drawString("TimeInGame_" + (getGameTime() / 1000000000L) + "_s", 10, 30);

        g2d.drawString("AVG_FPS_" + getAvgFps(), 10, 60);
    }


}

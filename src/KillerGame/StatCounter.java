package KillerGame;

import java.awt.*;

/**
 * Created by rico on 08.12.14.
 */
public class StatCounter {

    private long gameStartTime;
    private long lastUpdateTime;
    private long frameCount;
    private long upsCount;


    public StatCounter() {
        gameStartTime = System.nanoTime();
        frameCount = 0L;
        upsCount = 0L;
        lastUpdateTime = 0L;
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

    public void reportUpdate() {
        this.lastUpdateTime = System.nanoTime();
        upsCount++;
    }

    public double getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void reportFrame() {
        this.frameCount++;
    }

    public double getAvgFps() {
        return (double) frameCount / ((System.nanoTime() - gameStartTime) / 1000000000L);
    }

    public double getAvgUps() {
        return (double) upsCount / ((System.nanoTime() - gameStartTime) / 1000000000L);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.black);
        Font myFont = new Font("Courier", Font.ITALIC | Font.PLAIN, 10);

        g2d.setFont(myFont);
        g2d.drawString("TimeInGame_" + (getGameTime() / 1000000000L) + "_s", 5, 10);

        g2d.drawString("AVG_FPS_" + getAvgFps(), 5, 20);
        g2d.drawString("AVG_UPS_" + getAvgUps(), 5, 30);
    }


}

package KillerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

import KillerGame.Entities.Dot;
import sun.font.FontFamily;

/**
 * Created by rico on 02.12.2014.
 */
public class GamePanel extends JPanel implements Runnable {

    private static int panelWidth;
    private static int panelHeight;

    private Thread animationThread;
    private volatile boolean running = false;
    private volatile boolean gameOver = false;

    private Graphics2D dbg2D;
    private Image dbImg = null;

    private Color bgColor;

    private static final int NO_DELAYS_PER_YIELD = 16;
    private static final int fps = 100;

    private static final int POINT_COUNT = 10;
    private Dot points[];

    long frameCount;
    long gameStartTime;
    long gameTime;
    long prefStatTime;
    double avgFps;

    public GamePanel(int width, int height) {

        bgColor = new Color(236, 238, 207);
        setBackground(bgColor);

        this.panelWidth = width;
        this.panelHeight = height;


        setFocusable(true);
        requestFocus();
        readyForTermination();

        points = new Dot[POINT_COUNT];

        frameCount = 0L;
        gameStartTime = System.nanoTime();
        gameTime = 0L;
        prefStatTime = 0L;
        avgFps = 0L;

        for (int i = 0; i < POINT_COUNT; i++) {
            points[i] = new Dot(panelWidth, panelHeight);
        }
    }

    public void addNotify(){
        super.addNotify();
        startGame();
    }

    private void startGame(){
        if (animationThread == null || !running){
            animationThread = new Thread(this);
            animationThread.start();
        }
    }

    public void stopGame() {
        running = false;
    }

    @Override
    public void run() {
        running = true;

        long tDiff, tSleep;
        long period = (long) 1000L / fps * 1000000L; // calc period duration in ms, then ms-> nano sec
        long tOverSleep = 0L;
        int noDelays = 0;

        long counter = 1;

        long t0;

        while(running) {
            t0 = System.nanoTime();
            if ((counter%4) == 0) {
                gameUpdate();
                counter = 1;
            }
            counter++;

            gameRender();
            //repaint();                                    // direct draw
            paintScreen();                                  // Active Rendering

            saveStats();

            tDiff = System.nanoTime() - t0;
            tSleep = period - tDiff;

            if (tSleep <= 0){
                tSleep = 5000000L;
            }

            try {
                Thread.sleep(tSleep / 1000000L);
                System.out.println("runloop: sleep for " + tSleep + "ms");
            } catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
    System.exit(0);
    }

    private void saveStats(){

        frameCount++;
        gameTime = ((System.nanoTime() - gameStartTime) /1000000000L);

        long timeElapsed = System.nanoTime() - prefStatTime;

        if (gameTime > 0) {
            avgFps = frameCount / ((System.nanoTime() - gameStartTime) / 1000000000L);
        }
        prefStatTime = System.nanoTime();
    }
    private void paintScreen(){
        Graphics g;
        try {
            g = this.getGraphics();

            if ((g != null) && (dbImg != null)) {
                g.drawImage(dbImg, 0, 0, null);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } catch (Exception e){
            System.out.println("Graphics context error: " + e);
        }
    }

    private void gameUpdate(){

        for (int i = 0; i < POINT_COUNT; i++) {
            points[i].update();

        }

        if (!gameOver) {
            //TODO
        }
    }

    private void gameRender(){
        if (dbImg == null) {
            dbImg = createImage(panelWidth, panelHeight);

            if (dbImg == null){
                System.out.println("dbImg was null");
                return;
            }else {
                dbg2D = (Graphics2D) dbImg.getGraphics();
        }
        }

        dbg2D.setColor(bgColor);
        dbg2D.fillRect(0,0, panelWidth, panelHeight);

        for (int i = 0; i < POINT_COUNT; i++) {
            points[i].draw(dbg2D);
        }
        if (gameOver){
            gameOverMessage(dbg2D);
        }

        drawStats(dbg2D);
    }

    private void gameOverMessage(Graphics2D g){
        g.setColor(Color.cyan);
        Font myFont=new Font("Arial", Font.ITALIC|Font.PLAIN, 10);

        g.setFont( myFont );
        g.drawString("Game Over", 10,10);
    }

    private void drawStats(Graphics2D g){
        g.setColor(Color.black);
        Font myFont=new Font("Courier", Font.ITALIC|Font.PLAIN, 10);

        g.setFont( myFont );
        g.drawString("TIME_" + gameTime + "_s", 5,10);

        g.drawString("AVG_FPS_" + avgFps, 5,20);
    }

    private void readyForTermination(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ESCAPE){
                    running = false;
                }

                if (keyCode == KeyEvent.VK_Q){
                    gameOver = true;
                }
            }
        });
    }
}

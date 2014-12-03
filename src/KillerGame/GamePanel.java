package KillerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by rico on 02.12.2014.
 */
public class GamePanel extends JPanel implements Runnable {

    private static int panelWidth = 500;
    private static int panelHeight = 400;

    private Thread animationThread;
    private volatile boolean running = false;
    private volatile boolean gameOver = false;

    private Graphics dbg;
    private Image dbImg = null;

    private static final int NO_DELAYS_PER_YIELD = 16;

    public GamePanel() {

        setBackground(Color.black);
        setPreferredSize( new Dimension(panelWidth, panelHeight));

        setFocusable(true);
        requestFocus();
        readyForTermination();
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
        long period = 10L;
        long tOverSleep = 0L;
        int noDelays = 0;

        long t0 = System.nanoTime();

        while(running) {
            gameUpdate();
            gameRender();
            //repaint();                                    // direct draw
            paintScreen();                                  // Active Rendering

            tDiff = System.nanoTime() - t0;
            tSleep = period - tDiff;

            if (tSleep <= 0){
                tSleep = 5;
            }

            try {
                Thread.sleep(tDiff);
            } catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
    System.exit(0);
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
                dbg = dbImg.getGraphics();
        }
        }

        dbg.setColor(Color.black);
        dbg.fillRect(0,0, panelWidth, panelHeight);

        if (gameOver){
            gameOverMessage(dbg);
        }
    }

    private void gameOverMessage(Graphics g){
        g.setColor(Color.cyan);
        Font myFont=new Font("Arial", Font.ITALIC|Font.PLAIN, 10);

        g.setFont( myFont );
        g.drawString("Game Over", 10,10);
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

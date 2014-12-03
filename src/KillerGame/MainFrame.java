package KillerGame;

import javafx.scene.Parent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rico on 02.12.2014.
 */
public class MainFrame extends JFrame {


    public MainFrame() throws HeadlessException {
        super("2D Animation Framework");

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        GamePanel gamePanel= new GamePanel();
        c.add(gamePanel, "Center");

        setUndecorated(false);
        setIgnoreRepaint(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    public static void main(String args[]){
        new MainFrame();
    }
}

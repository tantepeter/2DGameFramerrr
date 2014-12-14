package KillerGame;

import javafx.scene.Parent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rico on 02.12.2014.
 */
public class MainFrame extends JFrame {

    private static int panelWidth = 1280;
    private static int panelHeight = 720;

    public MainFrame() throws HeadlessException {
        super("2D Animation Framework");

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        GamePanel gamePanel= new GamePanel(panelWidth, panelHeight);
        c.add(gamePanel, "Center");

        setUndecorated(true);
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        setResizable(false);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();         // Get graphicEnvironment to obtain the Screens center
        setBounds(ge.getCenterPoint().x, ge.getCenterPoint().y, panelWidth, panelHeight);   // Set frames bounds to the screens center - frames size
        setLocationRelativeTo(getParent());

        setVisible(true);

        pack();
    }

    public static void main(String args[]){
        new MainFrame();
    }
}

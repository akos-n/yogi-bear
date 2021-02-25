package windows;

import windows.panels.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow(boolean init) throws HeadlessException {
        if (init) initWindow();
    }
    private void initWindow(){
        add(new GamePanel());

        setSize(700,500);
        setResizable(false);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setTitle("Yogi Bear - Game");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.getSize().width/2-dim.getSize().width/4,dim.getSize().height/2-this.getSize().height/2);


        setVisible(true);
    }
}

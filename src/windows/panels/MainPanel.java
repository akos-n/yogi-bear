package windows.panels;


import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel(LayoutManager layout) {
        super(layout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon ii = new ImageIcon("src/resources/menu.jpeg");
        Image background = ii.getImage();
        g.drawImage(background,0,0,null);
    }
}

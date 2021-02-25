package windows;

import windows.buttons.ExitButton;
import windows.utils.Exit;
import windows.panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener{
    MainPanel panel = new MainPanel(new GridBagLayout());

    JButton button1 = new JButton("New game");
    GameWindow gameWindow = new GameWindow(false);
    JButton button2 = new JButton("Statistics");
    StatisticsWindow stat = new StatisticsWindow(false);

    public MainWindow() throws HeadlessException {
        this.addWindowListener(new Exit());
        this.setTitle("Yogi Bear - Menu");
        this.setSize(220,230);
        this.setResizable(false);
        //locate to the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/8, dim.height/2-this.getSize().height/2);
        //create GridBagConstraints to position the buttons
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10,10,10,10);
        //create buttons
        ExitButton exitButton = new ExitButton();
        //Add listeners
        this.button1.addActionListener(this);
        this.button2.addActionListener(this);

        //buttons positioning
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel.add(button1,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        panel.add(button2,gridBagConstraints);
        gridBagConstraints.insets = new Insets(40,10,10,10);
        gridBagConstraints.gridy = 2;
        panel.add(exitButton,gridBagConstraints);



        //add panel to the frame window + show the window
        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button1){
            this.gameWindow.dispose();
            this.stat.dispose();
            this.gameWindow = new GameWindow(true);
        }
        if (source == button2){
            this.stat.dispose();
            this.gameWindow.dispose();
            this.stat = new StatisticsWindow(true);
        }
    }
    
    public static void main(String[] args) {
        new MainWindow();
    }
    
}

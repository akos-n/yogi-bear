package windows;

import database.DatabaseManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOverPopup extends JFrame implements ActionListener, KeyListener {
    private JPanel panel = new JPanel(new GridBagLayout());
    private JButton button1 = new JButton("OK");
    private JTextField field = new JTextField(20);

    private int score;
    private String name = "Anonymous";

    public GameOverPopup(int score) throws HeadlessException {
        this.setTitle("Yogi Bear - Game Over!");
        this.setSize(300,200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.score = score;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.getSize().width/2-dim.getSize().width/4,dim.getSize().height/2-this.getSize().height/2);

        JLabel label1 = new JLabel("Write your name to save your score!");
        button1.addActionListener(this);
        field.addKeyListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();


        gridBagConstraints.insets = new Insets(10,10,25,10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.panel.add(label1,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.panel.add(field,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.panel.add(button1,gridBagConstraints);
        this.add(panel);
        pack();

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button1){
            if (field.getText().length() > 0){
                name = field.getText();
            }
            new DatabaseManager().insert(name, score);
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER){
            if (field.getText().length() > 0){
                name = field.getText();
            }
            new DatabaseManager().insert(name, score);
            this.dispose();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

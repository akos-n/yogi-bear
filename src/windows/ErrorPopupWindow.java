package windows;

import windows.utils.Exit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorPopupWindow extends JFrame implements ActionListener{

    JPanel panel = new JPanel(new GridBagLayout());
    JButton button1 = new JButton("OK");


    public ErrorPopupWindow(String title,String text) throws HeadlessException {
        this.setTitle(title);
        this.setSize(200,150);
        this.addWindowListener(new Exit());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JLabel label1 = new JLabel(text);
        button1.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();


        gridBagConstraints.insets = new Insets(10,10,25,10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.panel.add(label1,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.panel.add(button1,gridBagConstraints);
        this.add(panel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button1){
            this.dispose();
        }
    }
}

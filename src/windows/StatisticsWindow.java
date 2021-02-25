package windows;

import database.DatabaseManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatisticsWindow extends JFrame implements ActionListener{
    JPanel panel = new JPanel(new GridBagLayout());
    JButton closeButton = new JButton("Close");

    public StatisticsWindow(boolean init) throws HeadlessException {
        if(init) initStat();
    }

    public void initStat(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Yogi Bear - Top Scores");
        this.setResizable(false);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10,15,10,15);

            int counter = 1;
            JLabel labelTitle = new JLabel("Yogi Bear Top Scores");
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            this.panel.add(labelTitle,gridBagConstraints);
            
            ArrayList<String> result = new DatabaseManager().getTop10();
            
            for (String line : result){
                int index = 0;
                boolean nameEnd = false;
                String tmpName = "", tmpScore = "";
                while(index < line.length()){
                    if(line.charAt(index) != ' '){
                        if(!nameEnd){
                            tmpName += line.charAt(index);
                        } else {
                            tmpScore += line.charAt(index);
                        }
                    } else {
                        nameEnd = true;
                    }
                    index++;
                }
                JLabel label = new JLabel(counter+".");
                JLabel label2 = new JLabel(tmpName);
                JLabel label1 = new JLabel(tmpScore);

                gridBagConstraints.gridy = counter;

                gridBagConstraints.gridx = 0;
                this.panel.add(label,gridBagConstraints);
                gridBagConstraints.gridx = 1;
                this.panel.add(label2,gridBagConstraints);
                gridBagConstraints.gridx = 2;
                this.panel.add(label1,gridBagConstraints);
                counter++;
            }

            this.setSize(300,(40*counter)+30);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-dim.width/4, dim.height/2-this.getSize().height/2);


        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        this.panel.add(closeButton,gridBagConstraints);
        closeButton.addActionListener(this);

        this.add(panel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == closeButton){
            this.dispose();
        }
    }
}

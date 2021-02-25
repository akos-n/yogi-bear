package windows.buttons;

import windows.utils.Exit;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitButton extends JButton {
    public ExitButton() {
        super(new AbstractAction("Kilépés") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Exit().windowClosing(null);
            }
        });
    }
}

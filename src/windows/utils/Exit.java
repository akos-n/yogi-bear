package windows.utils;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Exit extends WindowAdapter{
    @Override
    public void windowClosing(WindowEvent e){ System.exit(0); }

}

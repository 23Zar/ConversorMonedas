import javax.swing.JFrame;

import java.awt.EventQueue;

public class AluraConversor {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new CurrencyConverterFrame();
                frame.setVisible(true);
            }
        });
    }
}


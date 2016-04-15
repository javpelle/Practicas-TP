package ejemplo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame jf = new JFrame();
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.add(new HanoiWindow());
                jf.setLocationRelativeTo(null);
                jf.setSize(600, 400);
                jf.setVisible(true);
            }
        });
    }
}

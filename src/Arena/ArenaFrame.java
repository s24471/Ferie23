package Arena;

import javax.swing.*;

public class ArenaFrame extends JFrame {
    public ArenaFrame(){
        this.setTitle("Ferie23_Arena");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}

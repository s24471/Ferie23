package Map;

import javax.swing.*;

public class Window extends JFrame {
    Map map;
    Brain brain;

    public Window(Brain brain) {
        this.brain = brain;
        map = brain.map;
        this.add(map);
        this.setTitle("Ferie23_Map");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
package Map;
import Arena.Arena;
import Arena.ArenaFrame;

import javax.swing.*;

public class Window extends JFrame {
    Map map;
    Brain brain;
    ArenaFrame arenaFrame;
    Arena arena;
    boolean changer;

    public Window(Brain brain) {
        changer = false;
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
        arenaFrame = new ArenaFrame();
        arena = new Arena(arenaFrame, brain);
    }

    public void change(){
        changer = !changer;
        if(changer) {
            this.remove(map);
            this.add(arenaFrame);
            arenaFrame.requestFocusInWindow();
            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.repaint();
            arena.run();
        }else{
            this.remove(arenaFrame);
            this.add(map);
            map.requestFocusInWindow();
            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.repaint();
        }

    }
}
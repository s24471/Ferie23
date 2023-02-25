package Arena;

import javax.swing.*;
import java.awt.*;

import static Map.Map.*;
import static Map.Window.*;

public class ArenaFrame extends JPanel {

    ArenaKeyListener arenaKeyListener;
    public ArenaFrame(){
        this.setPreferredSize(new Dimension(WIDTH_SCREEN * SIZE, HEIGHT_SCREEN * SIZE));
        this.setBackground(Color.BLUE);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.arenaKeyListener = new ArenaKeyListener();
        this.addKeyListener(arenaKeyListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, 100, 100);
    }


}
